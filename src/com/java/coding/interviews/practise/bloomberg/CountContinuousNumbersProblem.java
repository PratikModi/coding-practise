package com.java.coding.interviews.practise.bloomberg;

/**
 * // The number 1112221111 can be read out as "one, one, one, two, ..." etc: saying each digit from left to right. However, we could be more efficient by grouping like digits. In this case: "three ones, three twos, four ones." This leads to the encoding 313241 (read it out loud!).
 *
 * // Write a program to encode integers in this way.
 *
 * // Note: consider the speed of your program: try to avoid expensive string operations.
 * // 111222. -> 3132
 *
 * // 3, 1, 3, 2 -->. 3132
 *
 * O(1) space
 * Any ideas?
 */
public class CountContinuousNumbersProblem {
    public static void main(String[] args) {
        System.out.println(countNumbers("111222"));
    }

    private static String countNumbers(String nums){
        int digitCount=1;
        char prevDigit=nums.charAt(0);
        StringBuilder result= new StringBuilder();
        for(int i=1;i<nums.length();i++){
            char c = nums.charAt(i);
            if(c == prevDigit){
                digitCount++;
            }else{
                result.append(digitCount+""+prevDigit);
                prevDigit=c;
                digitCount=1;
            }
        }
        result.append(digitCount+""+prevDigit);
        return result.toString();
    }

}
