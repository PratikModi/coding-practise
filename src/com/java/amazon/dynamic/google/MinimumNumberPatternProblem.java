package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 26-06-2020.
 */

/**
 * Form minimum number from given sequence
 Given a pattern containing only I’s and D’s.
 I for increasing and D for decreasing.
 Devise an algorithm to print the minimum number following that pattern.
 Digits from 1-9 and digits can’t repeat.

 Examples:

 Input: D        Output: 21
 Input: I        Output: 12
 Input: DD       Output: 321
 Input: II       Output: 123
 Input: DIDI     Output: 21435
 Input: IIDDD    Output: 126543
 Input: DDIDDIID Output: 321654798
 */

import java.util.Stack;

/**
 * Approach: -
 * We can that when we encounter I, we got numbers in increasing order but if we encounter ‘D’, we want to have numbers in decreasing order.
 * Length of the output string is always one more than the input string. So loop is from 0 till the length of the sting.
 * We have to take numbers from 1-9 so we always push (i+1) to our stack. T
 * hen we check what is the resulting character at the specified index.So,there will be two cases which are as follows:-
 Case 1: If we have encountered I or we are at the last character of input string,then pop from the stack
        and add it to the end of the output string until the stack gets empty.
 Case 2: If we have encountered D, then we want the numbers in decreasing order.so we just push (i+1) to our stack.
 */
public class MinimumNumberPatternProblem {

    public static void main(String[] args) {
        String S="DIDI";
        System.out.println(formMinimumNumber(S));
    }

    public static String formMinimumNumber(String S){
        String result = "";
        if(S==null || S.length()>=9){
            return result;
        }
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<=S.length();i++){
            stack.push(i+1);
            if(i==S.length() || S.charAt(i)=='I'){
                System.out.println(stack);
                while(!stack.isEmpty()) {
                    result += String.valueOf(stack.pop())+" ";
                }
            }
        }
        return result;
    }

}
