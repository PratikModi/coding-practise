package com.java.coding.interviews.practise.jpmc;

import java.util.ArrayList;
import java.util.List;

/**
 * Vending machine problem. Given that it has coins of 1, 50p, 20p, 10p, 5p, 1p - and asked it to dispense an amount - should be able to dispense it properly
 */
public class VendingMachineChangeProblem {
    public static void main(String[] args) {
        List<Integer> coins = List.of(1,5,10,20,50);
        System.out.println(findChange(coins,135));
    }

    public static List<Integer> findChange(List<Integer> coins, int value){
        List<Integer> change = new ArrayList<>();
        for(int i=coins.size()-1;i>=0;i--) {
            while(value >= coins.get(i)) {
                value-=coins.get(i);
                change.add(coins.get(i));
            }
        }
        return change;
    }
}
