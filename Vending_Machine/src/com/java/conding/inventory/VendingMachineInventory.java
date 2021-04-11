package com.java.conding.inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pratik1 on 09-03-2020.
 */
public class VendingMachineInventory<T> {
    private Map<T, Integer> inventory = new HashMap<T, Integer>();
    public int getTotalItems(T item)
    {
        Integer value = inventory.get(item);
        return value == null? 0 : value ;
    }
    public void addItem(T item){
        int count = inventory.get(item);
        inventory.put(item, count+1);
    }
    public void deduct(T item) {
        if (hasItem(item)) {
            int count = inventory.get(item); inventory.put(item, count - 1);
        }
    }
    public boolean hasItem(T item){
        return getTotalItems(item) > 0;
    }
    public void clear(){
        inventory.clear();
    }
    public void put(T item, int quantity) {
        inventory.put(item, quantity);
    }


}
