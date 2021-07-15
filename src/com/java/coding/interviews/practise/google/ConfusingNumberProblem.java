package com.java.coding.interviews.practise.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a number n, return true if and only if it is a confusing number, which satisfies the following condition:
 *
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively.
 * When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is a number that when rotated 180 degrees
 * becomes a different number with each digit valid.
 *
 * Example 1:
 *
 * Input: n = 6
 * Output: true
 * Explanation:
 * We get 9 after rotating 6, 9 is a valid number and 9!=6.
 * Example 2:
 *
 * Input: n = 89
 * Output: true
 * Explanation:
 * We get 68 after rotating 89, 86 is a valid number and 86!=89.
 */
public class ConfusingNumberProblem {

    public static void main(String[] args) {
        int N = 89;
        System.out.println(confusingNumber(N));
    }

    public static boolean confusingNumber(int N){
        int newNum=0;
        Map<Integer,Integer> confusingNumbers = new HashMap<>();
        confusingNumbers.put(0,0);
        confusingNumbers.put(1,1);
        confusingNumbers.put(6,9);
        confusingNumbers.put(8,8);
        confusingNumbers.put(9,6);
        int oldNum=N;
        while(N>0){
            newNum*=10;
            int digit=N%10;
            if(!confusingNumbers.containsKey(digit))
                return false;
            newNum+=confusingNumbers.get(digit);
            N/=10;
        }
        return newNum!=oldNum;
    }
}
