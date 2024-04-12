package com.java.coding.interviews.practise.salesforce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Question:
 *
 * We have to create phone number of length 7 by the movement of knight and bishop on a keypad. The properties of the phone number:
 *
 * The phone number cannot start from 1 or 0.
 * The phone number should not contain special characters.
 * The length of the phone number should be 7.
 */
public class ChessPhoneNumberProblem {

    private static final Map<Integer, List<Integer>> knight_moves =
        Map.of(0, List.of(4,6),
               1, List.of(6,8),
               2, List.of(7,9),
               3, List.of(4,8),
               4, List.of(3,9,0),
               5, Collections.EMPTY_LIST,
               6, List.of(1,7,0),
               7, List.of(2,6),
               8, List.of(1,3),
               9, List.of(4,2)
        );

    private static final Map<Integer,List<Integer>> bishop_moves =
            Map.of(0, List.of(7,9),
                   1, List.of(4,5),
                   2, List.of(4,6),
                   3, List.of(5,7),
                   4, List.of(2,8),
                   5, List.of(1,3,7,9),
                   6, List.of(2,8),
                   7, List.of(5,0,3),
                   8, List.of(4,6),
                  9, List.of(5,0,1)
            );
    static final int mod = (int) 1e9 + 7;
    private static int[][] cache = new int[5001][10];
    public static void main(String[] args) {
        var nextNumbers = IntStream.range(0,10).boxed().collect(Collectors.toList());
        var count = findPhoneNumbers(3131, nextNumbers);
        System.out.println(count);
    }

    public static int findPhoneNumbers(int n, List<Integer> nextNumbers){
        //System.out.println(nextNumbers);
        if(n==1) return nextNumbers.size();
        int count=0;
        for(int nextNum : nextNumbers){
            int curr = cache[n][nextNum];
            if(curr==0){
                curr = findPhoneNumbers(n-1,knight_moves.get(nextNum));
                cache[n][nextNum]=curr;
            }
            count+=curr;
            count%=mod;
        }
        return count;
    }

}
