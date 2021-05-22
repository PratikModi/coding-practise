package com.java.amazon.dynamic.uber;

import java.util.*;

/**
 * Insert Delete GetRandom O(1) - Duplicates allowed
 *
 * Solution
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
 * Example:
 *
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 *
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 *
 * // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 * collection.insert(1);
 *
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 *
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 *
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 *
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 *
 * APPROACH
 * ========
 * Requirements
 * ===========
 * Insert() --> O(1)
 * remove()-->O(1)
 * getRandom()-> O(1)
 *
 *
 * List
 * ====
 * insert()-> O(1)
 * remove()->O(N)
 * getRandom() --> O(N)
 *
 * Map
 * ====
 * insert() --> O(1)
 * remove()-->O(1)
 * getRandom()-->O(N)
 *
 * Set
 * ====
 * insert() --> O(1)
 * remove()-->O(1)
 * getRandom()-->O(N)
 *
 *
 * List + Map
 * =======
 * Map<Integer,Set<Integer>> ==> Map<Value,Set<Index>>
 * List<Integer> ==> List<value>
 *
 * insert()-> O(1)
 * remove()->O(1) == Map.get(value) ==> Set<Indexes> ==> take first Index
 *                                                               Set.remove()--O(1) == List --> replace index with last last element and remove last index
 *                                                               update index of last element in map// remove the old last index from map
 *
 */
public class RandomizedCollection {
    public static void main(String[] args) {
        RandomizedCollection collection = new RandomizedCollection();
        System.out.println(collection.insert(1));
        System.out.println(collection.insert(1));
        System.out.println(collection.insert(2));
        System.out.println(collection.insert(2));
        System.out.println(collection.insert(2));
        System.out.println(collection.remove(1));
        System.out.println(collection.remove(1));
        System.out.println(collection.remove(2));
        System.out.println(collection.insert(1));
        System.out.println(collection.remove(2));
        System.out.println(collection.getRandom());
        //System.out.println(collection.remove(1));
        System.out.println(collection.getRandom());

    }

    List<Integer> elements=null;
    Map<Integer,Set<Integer>> indexer=null;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        elements = new ArrayList<>();
        indexer = new HashMap<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        indexer.putIfAbsent(val,new LinkedHashSet<>());
        indexer.get(val).add(elements.size());
        elements.add(val);
        System.out.println(indexer);
        return indexer.get(val).size()==1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        //System.out.println("VALUE==>"+val);
        if(!indexer.containsKey(val) || indexer.get(val).size()==0)
            return false;
        else{
            Set<Integer> indexes = indexer.get(val);
            int index=indexes.iterator().next();
            indexer.get(val).remove(index);
            int last = elements.get(elements.size()-1);
            elements.set(index,last);
            indexer.get(last).add(index);
            indexer.get(last).remove(elements.size()-1);
            elements.remove(elements.size()-1);
            return true;
        }
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        Random r = new Random();
        return elements.get(r.nextInt(elements.size()));
    }
}
