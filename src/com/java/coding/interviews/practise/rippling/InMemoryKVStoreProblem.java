package com.java.coding.interviews.practise.rippling;

import java.util.*;

interface IKeyValueStore{
    void set(String key, String value);
    String get(String key);
    String delete(String key);
    void clearAll();
    Set<Map.Entry<String,String>> getAllEntries();
    Map<String,String> getInternalCopy();
}

class KeyValueStore implements IKeyValueStore{
    Map<String,String> mapStore = new HashMap<>();

    public KeyValueStore() {
    }

    public KeyValueStore(Map<String, String> mapStore) {
        this.mapStore.putAll(mapStore);
    }

    @Override
    public void set(String key, String value) {
        mapStore.put(key,value);
    }

    @Override
    public String get(String key) {
        if(!mapStore.containsKey(key)){
            throw new IllegalArgumentException("Key Not Found!!!"+key);
        }
        return mapStore.get(key);
    }

    @Override
    public String delete(String key) {
        if(!mapStore.containsKey(key)){
            throw new IllegalArgumentException("Key Not Found!!!"+key);
        }
        return mapStore.remove(key);
    }

    @Override
    public void clearAll() {
        mapStore.clear();
    }

    @Override
    public Set<Map.Entry<String, String>> getAllEntries() {
        return mapStore.entrySet();
    }

    @Override
    public Map<String, String> getInternalCopy() {
        return new HashMap<>(mapStore);
    }
}

interface ITransactionalKeyValueStore extends IKeyValueStore{
    void begin();
    void end();
    void commit();
    void rollback();
}

class Transaction extends KeyValueStore{
    Set<String> deletedKeys = new HashSet<>();

    public Transaction() {
        super();
    }

    public Transaction(Map<String,String> map){
        super(map);
    }

    public String delete(String key){
        deletedKeys.add(key);
        return super.delete(key);
    }

}

class TransactionalKeyValueStore implements ITransactionalKeyValueStore{
    IKeyValueStore globalStore = new KeyValueStore();
    Stack<Transaction> txns = new Stack<>();
    public TransactionalKeyValueStore(){

    }

    @Override
    public void set(String key, String value) {
        if(txns.isEmpty()){
            globalStore.set(key,value);
        }else{
            var txnStore = txns.peek();
            txnStore.set(key, value);
        }
    }

    @Override
    public String get(String key) {
        if(txns.isEmpty()){
            return globalStore.get(key);
        }else{
            var txnStore = txns.peek();
            return txnStore.get(key);
        }
    }

    @Override
    public String delete(String key) {
        if(txns.isEmpty()){
            return globalStore.delete(key);
        }else{
            var txnStore = txns.peek();
            return txnStore.delete(key);
        }
    }

    @Override
    public void clearAll() {
        if(txns.isEmpty()){
            globalStore.clearAll();
        }else{
            var txnStore = txns.peek();
            txnStore.clearAll();
        }
    }

    @Override
    public Set<Map.Entry<String, String>> getAllEntries() {
        return null;
    }

    @Override
    public Map<String, String> getInternalCopy() {
        return null;
    }

    @Override
    public void begin() {
        if(txns.isEmpty()){
            Transaction txn = new Transaction(globalStore.getInternalCopy());
            txns.push(txn);
        }else{
            txns.push(new Transaction(txns.peek().getInternalCopy()));        }
    }

    @Override
    public void end() {
        if(txns.isEmpty()){
            throw new IllegalStateException("No Active Txn.");
        }else{
            txns.pop();
        }
    }

    @Override
    public void commit() {
        if(txns.isEmpty()){
            throw new IllegalStateException("No Active Txn.");
        }
        var txn = txns.pop(); // Current Transaction
        var nextTxn = txns.isEmpty()?null:txns.peek();
        for(var entry : txn.getAllEntries()){
            //if(nextTxn==null) {
            globalStore.set(entry.getKey(), entry.getValue()); // Check what behavior is expected
            //}
            if(nextTxn!=null){
                nextTxn.set(entry.getKey(), entry.getValue());
            }
        }
        for(String key : txn.deletedKeys){
            //if(nextTxn==null){
                globalStore.delete(key);
            //}
            if(nextTxn!=null){
                nextTxn.delete(key);
            }
        }
    }

    @Override
    public void rollback() {
        if(txns.isEmpty()){
            throw new IllegalStateException("No Active Txn.");
        }else{
            var txnStore = txns.peek();
            txnStore.clearAll();
        }
    }
}


public class InMemoryKVStoreProblem {
}


