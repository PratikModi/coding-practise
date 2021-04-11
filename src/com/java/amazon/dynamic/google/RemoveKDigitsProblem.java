package com.java.amazon.dynamic.google;

import java.util.Stack;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the
 * new number is the smallest possible.
 *
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 *
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 *
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */

public class RemoveKDigitsProblem {

    public static void main(String[] args) {
        String result = removeKDigits("1432219",3);
        System.out.println(result);
        /*result = removeKDigits("10200",1);
        System.out.println(result);
        result = removeKDigits("10",2);
        System.out.println(result);
        System.out.println('1'>'0');*/
    }

    public static String removeKDigits(String nums, int K){
        if(nums==null || nums.length()<K)
            return "0";
        Stack<Character> stack = new Stack<>();
        char[] A = nums.toCharArray();
        for(char C : A){
            System.out.println(stack);
            while (!stack.isEmpty() && K>0 && stack.peek()>C){
                stack.pop();
                K-=1;
            }

            if(!stack.isEmpty() || C!='0'){
                stack.push(C);
            }
        }
        while(!stack.isEmpty() && K>0){
            stack.pop();
            K--;
        }
        if(stack.isEmpty())
            return "0";
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

}
