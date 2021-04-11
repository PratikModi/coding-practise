package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 16-05-2020.
 */

/**
 * Given a string which we can delete at most k, return whether you can make a palindrome.

 For example, given 'waterrfetawx' and a k of 2, you could delete f and x to get 'waterretaw'.
 */
public class StringPalindromeMaxKChange {

    public static void main(String[] args) {
        String str = "abcdedcba";
        boolean result = isPalindrome(str,2);
        System.out.println(result);
    }

    public static String reverseString(String str){
        return new StringBuilder(str).reverse().toString();
    }

    private static boolean isPalindromeByMaxKChange(String s1, String s2, int K, int N){

        int[][] dp = new int[N+1][N+1];
        for(int i=0;i<=s1.length();i++){
            for(int j=0;j<=s2.length();j++){
                if(i==0){
                    dp[i][j]=j;
                }else if(j==0){
                    dp[i][j]=i;
                }else if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=1+Math.min(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[N][N]<=K*2;
    }

    public static boolean isPalindrome(String str,int K){
        return isPalindromeByMaxKChange(str,reverseString(str),K,str.length());
    }


}
