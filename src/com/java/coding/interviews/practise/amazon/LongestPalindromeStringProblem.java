package com.java.coding.interviews.practise.amazon;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 * Example 3:
 *
 * Input: s = "a"
 * Output: "a"
 * Example 4:
 *
 * Input: s = "ac"
 * Output: "a"
 */
public class LongestPalindromeStringProblem {

    public static void main(String[] args) {
        System.out.println(longestPalindromeString("cbbd"));
        System.out.println(longestPalindromeString("babad"));
        System.out.println(longestPalindromeString("a"));
        System.out.println(longestPalindromeString("ac"));
    }

    public static String longestPalindromeString(String S){
        int left=0;
        int right=0;
        int N = S.length();
        boolean[][] isPalindrome = new boolean[N][N];
        for(int j=1;j<N;j++){
         for(int i=0;i<j;i++){
             boolean innerPalindrome = isPalindrome[i+1][j-1] || (j-i)<=2;
             if(S.charAt(i)==S.charAt(j) && innerPalindrome){
                 isPalindrome[i][j]=true;
                 if((right-left)<(j-i)){
                     left=i;
                     right=j;
                 }
             }
         }
        }
        return S.substring(left,right+1);
    }

}
