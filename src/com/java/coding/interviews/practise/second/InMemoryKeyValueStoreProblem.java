package com.java.coding.interviews.practise.second;


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

    public KeyValueStore(Map<String,String> map){
        this.keyValueStore.putAll(map);
    }

    @Override
    public void set(String key, String value) {
        this.keyValueStore.put(key, value);
    }

    @Override
    public String get(String key) {
        if(!keyValueStore.containsKey(key)){
            throw new IllegalArgumentException("Key "+key+" Not Found.");
        }
        return keyValueStore.get(key);
    }

    @Override
    public String delete(String key) {
        if(!keyValueStore.containsKey(key)){
            throw new IllegalArgumentException("Key "+key+" Not Found.");
        }
        return keyValueStore.get(key);
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

    public Transaction() {
        super();
    }
    public Transaction(Map<String, String> map) {
        super(map);
    }

    @Override
    public String delete(String key) {
        deletedKeys.add(key);
        return super.delete(key);
    }

    @Override
    public void deleteAll() {

        super.deleteAll();
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
            globalStore.set(key, value);
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
        }
        var txnStore = transactions.peek();
        return txnStore.getEntrySet();
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
            throw new IllegalStateException("No Active Transaction.");
        }else{
            transactions.pop();
        }
    }

    @Override
    public void commit() {
        if(transactions.isEmpty()){
            throw new IllegalStateException("No Active Transaction.");
        }
        Transaction current = transactions.pop();
        Transaction next = transactions.isEmpty()?null:transactions.peek();
        for(var entry : current.getEntrySet()){
            if(next==null){
                globalStore.set(entry.getKey(),entry.getValue());
            }else{
                next.set(entry.getKey(),entry.getValue());
            }
        }
        for(String key : current.deletedKeys){
            if(next==null){
                globalStore.delete(key);
            }else{
                next.delete(key);
            }
        }

    }

    @Override
    public void rollback() {
        if(transactions.isEmpty()){
            throw new IllegalStateException("No Active Transaction.");
        }
        transactions.pop();
    }
}
public class InMemoryKeyValueStoreProblem {
    public static void main(String[] args) {
        TransactionalKeyValueStore db = new TransactionalKeyValueStore();
        db.set("Key1","1");
        db.set("Key2","2");
        System.out.println(db.getEntrySet());
        db.begin();
        db.set("Key3","3");
        db.rollback();
        System.out.println(db.getEntrySet());
        System.out.println(db.get("Key1"));
        System.out.println(db.get("Key2"));
        //System.out.println(db.get("Key3"));
        db.begin();
        db.delete("Key3");
        db.commit();
        System.out.println(db.get("Key3"));
    }

}
