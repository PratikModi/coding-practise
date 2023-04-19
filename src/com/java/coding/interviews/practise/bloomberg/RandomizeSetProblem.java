package com.java.coding.interviews.practise.bloomberg;

import java.util.*;

/**
 * Insert Delete GetRandom O(1)
 *
 * Implement the RandomizedSet class:
 *
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements
 * (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 */
public class RandomizeSetProblem {

    List<Integer> elements;
    Map<Integer,Integer> indexer;
    Random random;

    public RandomizeSetProblem() {
        this.elements = new ArrayList<>();
        this.indexer = new HashMap<>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if(indexer.containsKey(val))
            return false;
        indexer.put(val,elements.size());
        elements.add(val);
        return true;
    }

    public boolean remove(int val) {
        if(!indexer.containsKey(val))
            return false;
        int index = indexer.get(val);
        int last = elements.get(elements.size()-1);
        elements.set(index,last);
        indexer.put(last,index);
        elements.remove(elements.size()-1);
        indexer.remove(val);
        return true;
    }

    public int getRandom() {
        return elements.get(random.nextInt(elements.size()));
    }
}
