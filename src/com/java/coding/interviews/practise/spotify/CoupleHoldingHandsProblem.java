package com.java.coding.interviews.practise.spotify;

import java.util.HashMap;
import java.util.Map;

/**
 * 765. Couples Holding Hands
 * Hard
 * 2.1K
 * 100
 * Companies
 * There are n couples sitting in 2n seats arranged in a row and want to hold hands.
 *
 * The people and seats are represented by an integer array row where row[i] is the ID of the person sitting in the ith seat.
 * The couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2n - 2, 2n - 1).
 *
 * Return the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.
 *
 *
 *
 * Example 1:
 *
 * Input: row = [0,2,1,3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 * Example 2:
 *
 * Input: row = [3,2,0,1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 */
public class CoupleHoldingHandsProblem {

    public static void main(String[] args) {
        System.out.println(minSwapsCouples(new int[]{0,2,1,3}));
        System.out.println(minSwapsCouples(new int[]{3,2,0,1}));
    }


    public static int minSwapsCouples(int[] row) {
        int swap=0;
        Map<Integer,Integer> couples = new HashMap<>();
        for(int i=0;i<row.length;i+=2){
            couples.put(row[i],row[i+1]);
            couples.put(row[i+1],row[i]);
        }
        for(int i=0;i<row.length;i+=2){
            if(couples.get(i)!=i+1){
                int current = couples.get(i);
                int right = couples.get(i+1);
                couples.put(current,right);
                couples.put(right,current);
                swap++;
            }
        }
    return swap;
    }

}
