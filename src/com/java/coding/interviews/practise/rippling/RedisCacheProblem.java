package com.java.coding.interviews.practise.rippling;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Build Redis cache with Transaction commit/rollback features.
 */
public class RedisCacheProblem {
    public static void main(String[] args) {
         /*IKVStore kvStore = new MyKVSTore();
        kvStore.set("1", "one");
        kvStore.set("2", "two");
        System.out.println(kvStore.get("1"));*/

        ITransactionalKVStore transactionalKVStore = new ConcurrentTransactionKVStore();
        transactionalKVStore.set("1", "one");
        transactionalKVStore.set("2", "two");
        System.out.println(transactionalKVStore.get("1"));
        transactionalKVStore.begin();
        transactionalKVStore.set("1", "11");
        System.out.println(transactionalKVStore.get("1"));
        transactionalKVStore.end();
        System.out.println(transactionalKVStore.get("1"));

        System.out.println("new txn");
        transactionalKVStore.begin();
        transactionalKVStore.set("1", "11");
        System.out.println(transactionalKVStore.get("1"));
        transactionalKVStore.commit();
        System.out.println(transactionalKVStore.get("1"));

        System.out.println("new txn");
        transactionalKVStore.begin();
        transactionalKVStore.set("1", "11");
        System.out.println(transactionalKVStore.get("1"));
        transactionalKVStore.begin();
        transactionalKVStore.set("1", "21");
        transactionalKVStore.commit();
        System.out.println(transactionalKVStore.get("1"));
        transactionalKVStore.commit();
        System.out.println(transactionalKVStore.get("1"));

    }
}

interface IKVStore{
    void set(String key,String value);
    String get(String key);
    String delete(String key);
    Set<Map.Entry<String,String>> getAllKVEntries();
    void clearAllKeys();
}

interface ITransactionalKVStore extends IKVStore{
    void begin();
    void end();
    void commit();
    void rollback();
}

class TransactionalKVStore implements ITransactionalKVStore{

    Stack<MyKVStore> stack=null;
    MyKVStore kvStore=null;

    TransactionalKVStore(){
        stack = new Stack<>();
        kvStore = new MyKVStore();
    }

    @Override
    public void set(String key, String value) {
        if(stack.isEmpty()){
            kvStore.set(key,value);
            return;
        }
        MyKVStore currentTxn = stack.peek();
        currentTxn.set(key,value);
    }

    @Override
    public String get(String key) {
        if(stack.isEmpty()){
            return kvStore.get(key);
        }
        MyKVStore currentTxn = stack.peek();
        return currentTxn.get(key);
    }

    @Override
    public String delete(String key) {
        if(stack.isEmpty()){
            return kvStore.delete(key);
        }
        MyKVStore currentTxn = stack.peek();
        return currentTxn.delete(key);
    }

    @Override
    public Set<Map.Entry<String, String>> getAllKVEntries() {
        if(stack.isEmpty()){
            return kvStore.getAllKVEntries();
        }
        MyKVStore currentTxn = stack.peek();
        return currentTxn.getAllKVEntries();
    }

    @Override
    public void clearAllKeys() {
        if(stack.isEmpty()){
            kvStore.clearAllKeys();;
            return;
        }
        MyKVStore currentTxn = stack.peek();
        currentTxn.clearAllKeys();
    }

    @Override
    public void begin() {
        MyKVStore newTxn = new MyKVStore();
        stack.push(newTxn);
    }

    @Override
    public void end() {
        stack.pop();
    }

    @Override
    public void commit() {
        if(stack.isEmpty()) {
            System.out.println("No Active Txn.");
            return;
        }
        MyKVStore currentTxn = stack.pop();
        MyKVStore newActiveTxn = stack.isEmpty()?null:stack.peek();
        for(Map.Entry<String,String> entry : currentTxn.getAllKVEntries()){
            kvStore.set(entry.getKey(), entry.getValue());
            if(newActiveTxn!=null){
                newActiveTxn.set(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public void rollback() {
        if(stack.isEmpty()){
            System.out.println("No Active Txn.");
            return;
        }
        MyKVStore currentTxn = stack.peek();
        currentTxn.clearAllKeys();
    }
}

class ConcurrentTransactionKVStore extends TransactionalKVStore{

    ReadWriteLock lock = null;

    public ConcurrentTransactionKVStore() {
        super();
        lock = new ReentrantReadWriteLock();
    }

    @Override
    public void commit(){
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try{
            super.commit();
        }finally {
            writeLock.unlock();
        }
    }
}

class MyKVStore implements IKVStore{

    private Map<String,String> myKVStore = new HashMap<>();

    @Override
    public void set(String key, String value) {
        myKVStore.put(key,value);
    }

    @Override
    public String get(String key) {
        if(myKVStore.containsKey(key))
            return myKVStore.get(key);
        throw new IllegalArgumentException("Key Not Found::"+key);
    }

    @Override
    public String delete(String key) {
        if(myKVStore.containsKey(key))
            return myKVStore.remove(key);
        throw new IllegalArgumentException("Key Not Found::"+key);
    }

    @Override
    public Set<Map.Entry<String, String>> getAllKVEntries() {
        return myKVStore.entrySet();
    }

    @Override
    public void clearAllKeys() {
        myKVStore.clear();
    }
}
