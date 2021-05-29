package com.java.coding.interviews.practise.google;

import java.util.Arrays;

/**
 * Given A, B, C, find whether C is formed by the interleaving of A and B.
 *
 * Input Format:*
 *
 * The first argument of input contains a string, A.
 * The second argument of input contains a string, B.
 * The third argument of input contains a string, C.
 * Output Format:
 *
 * Return an integer, 0 or 1:
 *     => 0 : False
 *     => 1 : True
 * Constraints:
 *
 * 1 <= length(A), length(B), length(C) <= 150
 * Examples:
 *
 * Input 1:
 *     A = "aabcc"
 *     B = "dbbca"
 *     C = "aadbbcbcac"
 *
 * Output 1:
 *     1
 *
 * Explanation 1:
 *     "aa" (from A) + "dbbc" (from B) + "bc" (from A) + "a" (from B) + "c" (from A)
 */
public class StringInterleavingProblem {

    public static void main(String[] args) {
        String A = "aabcc";
        String B = "dbbca";
        String C = "aadbbcbcac";
        System.out.println(isInterleave(A,B,C));
        System.out.println(isInterleaveDP( A,B,C));
        System.out.println(isInterleaveDP( "aabc","abad","aabadabc"));
    }

    public static boolean isInterleave(String A, String B, String C) {
        int i = 0, j = 0, k = 0;

        // Iterate through all characters of C.
        while (k != C.length())
        {
            // Match first character of C with first character
            // of A. If matches them move A to next
            if (i<A.length()&&A.charAt(i) == C.charAt(k))
                i++;

                // Else Match first character of C with first
                // character of B. If matches them move B to next
            else if (j<B.length()&&B.charAt(j) == C.charAt(k))
                j++;

                // If doesn't match with either A or B, then return
                // false
            else
                return false;

            // Move C to next for next iteration
            k++;
        }

        // If A or B still have some characters,
        // then length of C is smaller than sum
        // of lengths of A and B, so return false
        if (i < A.length() || j < B.length())
            return false;
        return true;
    }

    public static boolean isInterleaveDP(String A,String B, String C){
        if(A.length()+B.length()!=C.length())
            return false;
        boolean[][] dp = new boolean[A.length()+1][B.length()+1];
        for(int i=0;i<=A.length();i++){
            for(int j=0;j<=B.length();j++){
                if(i==0 && j==0){
                    dp[i][j]= true;
                }else if(i==0){
                    dp[i][j]=C.charAt(i+j-1)==B.charAt(j-1)?dp[i][j-1]:false;
                }else if(j==0){
                    dp[i][j]=C.charAt(i+j-1)==A.charAt(i-1)?dp[i-1][j]:false;
                }else{
                    dp[i][j]=C.charAt(i+j-1)==A.charAt(i-1)?dp[i-1][j]:false || (C.charAt(i+j-1)==B.charAt(j-1)?dp[i][j-1]:false);
                }
            }
        }
     //   Arrays.stream(dp).forEach(e-> System.out.println(Arrays.toString(e)));
        return dp[A.length()][B.length()];
    }


}
