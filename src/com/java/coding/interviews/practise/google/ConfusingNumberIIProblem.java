package com.java.coding.interviews.practise.google;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 1088. Confusing Number II
 * Hard
 *
 * 276
 *
 * 76
 *
 * Add to List
 *
 * Share
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively.
 * When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.
 *
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 * (Note that the rotated number can be greater than the original number.)
 *
 * Given a positive integer n, return the number of confusing numbers between 1 and n inclusive.
 *
 * Example 1:
 *
 * Input: n = 20
 * Output: 6
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19].
 * 6 converts to 9.
 * 9 converts to 6.
 * 10 converts to 01 which is just 1.
 * 16 converts to 91.
 * 18 converts to 81.
 * 19 converts to 61.
 * Example 2:
 *
 * Input: n = 100
 * Output: 19
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 *
 * Note:
 *
 * 1 <= n <= 109
 */
public class ConfusingNumberIIProblem {

    private static Map<Integer,Integer> confusingNumber;
    static{
        confusingNumber = new HashMap<>();
        confusingNumber.put(0,0);
        confusingNumber.put(1,1);
        confusingNumber.put(6,9);
        confusingNumber.put(8,8);
        confusingNumber.put(9,6);
    }


    public static void main(String[] args) {
        int result = find(0,20);
        System.out.println(result);
        result = find(0,100);
        System.out.println(result);
        result = find(0,(int)Math.pow(10,9));
        System.out.println(result);
    }

    public static int find(int current,int N){
        int count=0;
        if(isConfusingNumber(current))
            count++;
        Iterator<Integer> numbers = confusingNumber.keySet().iterator();
        while(numbers.hasNext()){
            int num = numbers.next();
            int next = (current*10)+num;
            int baseLimit = Integer.MAX_VALUE / 10;
            int digitLimit = Integer.MAX_VALUE % 10;
            // prevent overflow
            if (current > baseLimit || current == baseLimit && num > digitLimit) {
                continue;
            }
            if(next>=1 && next<=N){
                count+=find(next,N);
            }
        }
        return count;
    }

    private static boolean isConfusingNumber(int N){
        int oldNumber=N;
        int newNumber=0;
        while(N>0){
            newNumber*=10;
            int digit = N%10;
            if(!confusingNumber.containsKey(digit))
                return false;
            newNumber+=confusingNumber.get(digit);
            N/=10;
        }
        return oldNumber!=newNumber;
    }

}
