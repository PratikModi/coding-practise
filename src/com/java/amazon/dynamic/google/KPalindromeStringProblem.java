package com.java.amazon.dynamic.google;

import java.util.Arrays;

/**
 * This problem was asked by Google.
 *
 * Given a string which we can delete at most k, return whether you can make a palindrome.
 *
 * For example, given 'waterrfetawx' and a k of 2, you could delete f and x to get 'waterretaw'.
 */

public class KPalindromeStringProblem {

    public static void main(String[] args) {
        System.out.println(isKPalindrome("waterrfetawx",2));
    }

    public static boolean isKPalindrome(String S, int K){
        if(S==null || S.isEmpty())
            return false;
        String RS = reverse(S);
        int minChars=findMinimumCharacterToMakePalindrome(S,RS);
        if(minChars<=K*2){
            return true;
        }
        return false;
    }

    private static int findMinimumCharacterToMakePalindrome(String S1, String S2){
        if(S1==null || S1.isEmpty() || S2==null || S2.isEmpty())
            return Integer.MAX_VALUE;
        int[][] DP = new int[S1.length()+1][S2.length()+1];
        for(int i=0;i<=S1.length();i++){
            for(int j=0;j<=S2.length();j++){
                if(i==0){
                    DP[i][j]=j;
                }else if(j==0){
                    DP[i][j]=i;
                }else if(S1.charAt(i-1) == S2.charAt(j-1)){
                    DP[i][j]=DP[i-1][j-1];
                }else{
                    DP[i][j]=1+Math.min(DP[i-1][j],DP[i][j-1]);
                }
            }
        }
        //Arrays.stream(DP).forEach(e-> System.out.println(Arrays.toString(e)));
        return DP[S1.length()][S2.length()];
    }

    private static String reverse(String S){
        if(S==null || S.isEmpty())
            return S;
        StringBuilder SB = new StringBuilder(S);
        return SB.reverse().toString();
    }

}
