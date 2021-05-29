package com.java.coding.interviews.practise.google;

import java.util.Arrays;

/**
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStringProblem {
    public static void main(String[] args) {
        System.out.println(multiply("12","12"));
        System.out.println(multiply("12","1"));
    }

    public static String multiply(String S1, String S2){
        int M = S1.length();
        int N = S2.length();
        int[] A = new int[M+N];
        for(int i=M-1;i>=0;i--){
            for(int j=N-1;j>=0;j--){
                int multiply=(S1.charAt(i)-'0') * (S2.charAt(j)-'0');
                int sum = A[i+j+1]+multiply;
                A[i+j]+=sum/10;
                A[i+j+1]=sum%10;
                System.out.println(Arrays.toString(A));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int val:A){
            if(sb.length()!=0 || val!=0){
                sb.append(val);
            }
        }
        return sb.length()==0?"0":sb.toString();
    }
}
