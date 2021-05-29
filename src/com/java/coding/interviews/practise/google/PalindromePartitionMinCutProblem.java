package com.java.coding.interviews.practise.google;

import java.util.Arrays;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s. For example, given s = "aab",
 * return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitionMinCutProblem {
    public static void main(String[] args) {
        System.out.println(findMinimumCut("aab"));
        System.out.println(findMinimumCut("abc"));
        System.out.println(findMinimumCut("abba"));
    }

    public static int findMinimumCut(String S){
        if(S==null ||  S.length()<2)
            return 0;
        int N = S.length();
        boolean[][] dp = new boolean[N][N];
        int[] cut = new int[N];
        for(int j=0;j<N;j++){
            cut[j]=j; //Set Max Value
            for(int i=0;i<=j;i++){
                if(S.charAt(i)==S.charAt(j) && ( (j-i)<=2 || dp[i+1][j-1] )) {
                    dp[i][j] = true;
                    if (i > 0) { //if i>0 add 1 to previous cut
                        cut[j] = Math.min(cut[j], cut[i - 1] + 1);
                    } else {
                        cut[j] = 0; //entire string is palindrome .. no cut required
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(cut));
        return cut[N-1];
    }
}
