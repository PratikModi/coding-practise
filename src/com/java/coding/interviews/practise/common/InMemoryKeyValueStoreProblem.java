package com.java.coding.interviews.practise.common;


import java.util.*;

interface IKeyValueStore{
    void set(String key,String value);
    String get(String key);
    String delete(String key);
    void deleteAll();
    Set<Map.Entry<String,String>> getEntrySet();
    Map<String,String> getInternalCopy();
}

class KeyValueStore implements IKeyValueStore{

    Map<String,String> keyValueStore = new HashMap<>();

    public KeyValueStore() {
    }

    public KeyValueStore(Map<String,String> store){
        this.keyValueStore.putAll(store);
    }

    @Override
    public void set(String key, String value) {
        this.keyValueStore.put(key, value);
    }

    @Override
    public String get(String key) {
        if(!this.keyValueStore.containsKey(key))
            throw new IllegalArgumentException("Key: "+key+" Not Found");
        return this.keyValueStore.get(key);
    }

    @Override
    public String delete(String key) {
        if(!this.keyValueStore.containsKey(key))
            throw new IllegalArgumentException("Key: "+key+" Not Found");
        return this.keyValueStore.remove(key);
    }

    @Override
    public void deleteAll() {
        this.keyValueStore.clear();
    }

    @Override
    public Set<Map.Entry<String, String>> getEntrySet() {
        return this.keyValueStore.entrySet();
    }

    @Override
    public Map<String, String> getInternalCopy() {
        return new HashMap<>(keyValueStore);
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

    public Transaction(){
        super();
    }

    public Transaction(Map<String,String> store){
        super(store);
    }

    @Override
    public String delete(String key) {
        deletedKeys.add(key);
        return super.delete(key);
    }
}

class TransactionalKeyValueStore implements ITransactionalKeyValueStore{

    IKeyValueStore globalStore = new KeyValueStore();

    Stack<Transaction> transactions = new Stack<>();

    public TransactionalKeyValueStore() {
    }

    @Override
    public void set(String key, String value) {
        if(transactions.isEmpty()){
            globalStore.set(key,value);
        }else{
            var txnStore = transactions.peek();
            txnStore.set(key, value);
        }
    }

    @Override
    public String get(String key) {
        if(transactions.isEmpty()){
            return globalStore.get(key);
        }
        var txnStore = transactions.peek();
        return txnStore.get(key);
    }

    @Override
    public String delete(String key) {
        if(transactions.isEmpty()){
            return globalStore.delete(key);
        }
        var txnStore = transactions.peek();
        return txnStore.delete(key);
    }

    @Override
    public void deleteAll() {
        if(transactions.isEmpty()){
            globalStore.deleteAll();
        }else{
            var txnStore = transactions.peek();
            txnStore.deleteAll();
        }
    }

    @Override
    public Set<Map.Entry<String, String>> getEntrySet() {
        if(transactions.isEmpty()){
            return globalStore.getEntrySet();
        }else{
            var txnStore = transactions.peek();
            return txnStore.getEntrySet();
        }
    }

    @Override
    public Map<String, String> getInternalCopy() {
        return null;
    }

    @Override
    public void begin() {
        if(transactions.isEmpty()){
            Transaction txn = new Transaction(globalStore.getInternalCopy());
            transactions.push(txn);
        }else{
            transactions.push(new Transaction(transactions.peek().getInternalCopy()));
        }

    }

    @Override
    public void end() {
        if(transactions.isEmpty()){
            throw new IllegalStateException("No Active Transaction");
        }
        transactions.pop();
    }

    @Override
    public void commit() {
        if(transactions.isEmpty()) {
            throw new IllegalStateException("No Active Transaction");
        }
        var currentTxn = transactions.pop();
        var nextTxn = transactions.isEmpty()?null:transactions.peek();
        for(var entry : currentTxn.getEntrySet()){
            globalStore.set(entry.getKey(), entry.getValue());
            if(nextTxn!=null){
                nextTxn.set(entry.getKey(),entry.getValue());
            }
        }
        for(String key : currentTxn.deletedKeys){
            globalStore.delete(key);
            if(nextTxn!=null){
                nextTxn.delete(key);
            }
        }

    }

    @Override
    public void rollback() {
        if(transactions.isEmpty()){
            throw new IllegalStateException("No Active Transaction");
        }else{
            var txnStore = transactions.peek();
            txnStore.deleteAll();
        }
    }
}


public class InMemoryKeyValueStoreProblem {
    public static void main(String[] args) {
        TransactionalKeyValueStore keyValueStore = new TransactionalKeyValueStore();
        keyValueStore.set("1","One");
        keyValueStore.set("2","Two");
        keyValueStore.begin();
        keyValueStore.set("3","Three");
        keyValueStore.set("2","22");
        keyValueStore.delete("1");
        keyValueStore.commit();
        System.out.println(keyValueStore.get("2"));
        System.out.println(keyValueStore.get("3"));
        System.out.println(keyValueStore.get("1"));
    }

}
