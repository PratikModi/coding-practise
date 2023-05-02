package com.java.coding.interviews.practise.common;

/**
 * 10. Regular Expression Matching
 * Hard
 * 10.5K
 * 1.7K
 * Companies
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Example 1:
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 */
public class RegExMatchingProblem {

    public static void main(String[] args) {
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","a*"));
        System.out.println(isMatch("ab",".*"));
    }

    public static boolean isMatch(String S, String P){
        if(S==null || P==null)
            return false;
        int SL = S.length();
        int PL = P.length();
        boolean[][] DP = new boolean[SL+1][PL+1];
        DP[0][0]=true;
        for(int i=1;i<=PL;i++){
            if(P.charAt(i-1)=='*'){
                DP[0][i]=DP[0][i-2];
            }
        }
        for(int i=1;i<=SL;i++){
            for(int j=1;j<=PL;j++){
                if(P.charAt(j-1)=='.' || P.charAt(j-1)==S.charAt(i-1)){
                    DP[i][j] = DP[i-1][j-1];
                }else if(P.charAt(j-1)=='*'){
                    DP[i][j] = DP[i][j-2];
                    if(S.charAt(i-1)==P.charAt(j-2)||P.charAt(j-2)=='.'){
                        DP[i][j] = DP[i][j] || DP[i-1][j];
                    }
                }else{
                    DP[i][j]=false;
                }
            }
        }
        return DP[SL][PL];
    }
}
