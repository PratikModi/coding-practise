package com.java.coding.interviews.practise.uber;

import java.util.Arrays;

/**
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 *
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */

//Time Complexity: - O(N^2) Space Complexity: - O(N^2)
public class PalindromeSubstringProblem {

    public static void main(String[] args) {
        System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aaa"));
    }

    public static int countSubstrings(String S){
        if(S==null || S.length()==0)
            return 0;
        int count=0;
        int N = S.length();
        boolean[][] DP = new boolean[N][N];
        for(int g=0;g<N;g++){
            for(int i=0,j=g;j<N;i++,j++){
                if(g==0){
                    DP[i][j]=true;
                }else if(g==1){
                    DP[i][j] = S.charAt(i)==S.charAt(j);
                }else{
                    DP[i][j] = (S.charAt(i)==S.charAt(j)) && DP[i+1][j-1];
                }
                if(DP[i][j]) count++;
            }
        }
        Arrays.stream(DP).forEach(e-> System.out.println(Arrays.toString(e)));
        return count;
    }
}
