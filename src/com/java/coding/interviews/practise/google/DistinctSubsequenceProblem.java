package com.java.coding.interviews.practise.google;

/**
 * Given two sequences A, B, count number of unique ways in sequence A, to form a subsequence that is identical
 * to the sequence B.
 *
 * Subsequence : A subsequence of a string is a new string which is formed from the original string by
 * deleting some (can be none) of the characters without disturbing the relative positions of the remaining
 * characters.
 * (ie, “ACE” is a subsequence of “ABCDE” while “AEC” is not).
 */

/**
 * Input 1:
 *     A = "abc"
 *     B = "abc"
 *
 * Output 1:
 *     1
 *
 * Explanation 1:
 *     Both the strings are equal.
 *
 * Input 2:
 *     A = "rabbbit"
 *     B = "rabbit"
 *
 * Output 2:
 *     3
 *
 * Explanation 2:
 *     These are the possible removals of characters:
 *         => A = "ra_bbit"
 *         => A = "rab_bit"
 *         => A = "rabb_it"
 *
 *     Note: "_" marks the removed character.
 */
//Time Complexity:- O(mn) Space Complexity:- O(mn)
public class DistinctSubsequenceProblem {

    public static void main(String[] args) {
        String S = "rabbbit";
        String T = "rabbit";
        System.out.println(findUniqueWays(S,T));
    }

    public static int findUniqueWays(String S, String T){
        if(S==null || T==null){
            return 0;
        }
        int[][] dp = new int[S.length()+1][T.length()+1];
        for(int i=0;i<=S.length();i++){
            dp[i][0]=1;
        }

        for(int i=1;i<=S.length();i++){
            for(int j=1;j<=T.length();j++){
                if(S.charAt(i-1)==T.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j]; //Match include + exclude char
                }else{
                    dp[i][j]=dp[i-1][j]; // Don't match remove char
                }
            }
        }
        return dp[S.length()][T.length()];
    }
}
