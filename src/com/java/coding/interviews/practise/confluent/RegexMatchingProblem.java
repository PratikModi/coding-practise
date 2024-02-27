package com.java.coding.interviews.practise.confluent;

/**
 * Given an input string (v) and a pattern, implement a wildcard matching function with support for literal characters a through z and the wildcard character *
 * (which matches zero or more of any character).
 *
 * static boolean isMatch(String v, String pattern)
 * cat ct ---> true
 * cat t ---> true
 * dog ct ---> false
 * cat tac* --->false
 */

/**
 * Time complexity: O(m x n)
 * Auxillary space: O(m x n)
 */
public class RegexMatchingProblem {

    public static void main(String[] args) {
        boolean res = isMatch("cat","c*t");
        boolean res2 = isMatchConstantSpace("cat","c*t");
        System.out.println("cat c*t "+res);
        System.out.println("cat c*t "+res2);


        res = isMatch("cat","*t");
        res2 = isMatchConstantSpace("cat","*t");
        System.out.println("cat *t " +res);
        System.out.println("cat *t " +res2);

        res = isMatch("dog","c*t");
        res2 = isMatchConstantSpace("dog","c*t");
        System.out.println("dog c*t "+res);
        System.out.println("dog c*t "+res2);

        res = isMatch("cat","*t*a*c*");
        res2 = isMatchConstantSpace("cat","*t*a*c*");
        System.out.println("cat  *t*a*c* "+res);
        System.out.println("cat  *t*a*c* "+res2);

        res = isMatch("aab","c*a*b");
        res2 = isMatchConstantSpace("aab","c*a*b");
        System.out.println("aab  c*a*b "+res);
        System.out.println("aab  c*a*b "+res2);

    }

    public static boolean isMatch(String S, String P) {
        if (S == null || P == null)
            return false;
        int N = S.length();
        int M = P.length();
        boolean[][] DP = new boolean[N + 1][M + 1];
        DP[0][0] = true;
        for (int i = 1; i <= M; i++) {
            if (P.charAt(i - 1) == '*')
                DP[0][i] = DP[0][i-1];
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (S.charAt(i - 1) == P.charAt(j - 1)) {
                    DP[i][j] = DP[i - 1][j - 1];
                } else if (P.charAt(j - 1) == '*') {
                    DP[i][j] = DP[i - 1][j] || DP[i][j - 1];
                }else{
                    DP[i][j]=false;
                }
            }
        }
        return DP[N][M];
    }

    public static boolean isMatchConstantSpace(String s, String p){
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {
            // If the pattern character = string character
            // or pattern character = '?'
            if (pIdx < pLen && (p.charAt(pIdx) == s.charAt(sIdx))){
                ++sIdx;
                ++pIdx;
            }
            // If pattern character = '*'
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                // Check the situation
                // when '*' matches no characters
                starIdx = pIdx;
                sTmpIdx = sIdx;
                ++pIdx;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was no '*' character in pattern
            else if (starIdx == -1) {
                return false;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was '*' character in pattern before
            else {
                // Backtrack: check the situation
                // when '*' matches one more character
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }

        // The remaining characters in the pattern should all be '*' characters
        for(int i = pIdx; i < pLen; i++)
            if (p.charAt(i) != '*') return false;
        return true;
    }


}
