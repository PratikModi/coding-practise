package com.java.coding.interviews.practise.rippling;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

interface ITransactionalKeyValueStore{
    void begin();
    void end();
    void commit();
    void rollback();
}


public class InMemoryKVStoreProblem {
}


