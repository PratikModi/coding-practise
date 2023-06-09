package com.java.coding.interviews.practise.rippling;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 *
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 *
 *
 * Example 1:
 *
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 *
 * Explanation
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1);         // return "bar"
 * timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4);         // return "bar2"
 * timeMap.get("foo", 5);         // return "bar2"
 */
public class TimeBasedKVStoreProblem {
    private static Map<String, TreeMap<Integer,String>> map = new HashMap<>();
    public static void main(String[] args) {
        set("love","high",10);
        set("love","low",20);
        System.out.println(get("love",5));
        System.out.println(get("love",10));
        System.out.println(get("love",15));
        System.out.println(get("love",20));
        System.out.println(get("love",25));
    }
    public static void set(String key, String value, int timestamp) {
        map.putIfAbsent(key,new TreeMap<Integer,String>());
        map.get(key).put(timestamp,value);
    }

    public static String get(String key, int timestamp) {
        String value = "";
        TreeMap<Integer,String> keyMap = map.get(key);
        if(keyMap==null){
            return value;
        }else if(!keyMap.containsKey(timestamp)){
            if(keyMap.floorEntry(timestamp)!=null){
                Integer ts = keyMap.floorEntry(timestamp).getKey();
                value = keyMap.get(ts);
            }else{
                value="";
            }
        }else{
            value = keyMap.get(timestamp);
        }
        return value;
    }




}
