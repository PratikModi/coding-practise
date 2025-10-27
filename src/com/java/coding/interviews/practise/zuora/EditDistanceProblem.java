package com.java.coding.interviews.practise.zuora;

/**
 * LeetCode:72. Edit Distance
 *  Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 * 💡 Intuition
 *
 * This is a classic DP string transformation problem.
 * We solve it using a 2D DP table, where:
 *
 * dp[i][j] = minimum operations to convert
 * first i characters of word1 → first j characters of word2
 *
 * ✅ Approach (Dynamic Programming)
 * 	1.	Let m = word1.length(), n = word2.length().
 * 	2.	Create a 2D array dp[m+1][n+1].
 * 	3.	Base cases:
 * 	•	dp[0][j] = j → converting empty string to word2[0..j] needs j insertions.
 * 	•	dp[i][0] = i → converting word1[0..i] to empty string needs i deletions.
 * 	4.	Transition:
 * 	•	If characters match:
 * dp[i][j] = dp[i-1][j-1]
 * 	•	Else choose minimum among:
 * 	•	Insert: dp[i][j-1] + 1
 * 	•	Delete: dp[i-1][j] + 1
 * 	•	Replace: dp[i-1][j-1] + 1
 * 	5.	Answer = dp[m][n].
 *
 * 🧠 Complexity Analysis
 * Type                  Complexity             Explanation
 * Time                  O(m × n)               Each cell computed once
 * Space                O(m × n)               Full DP table
 */
public class EditDistanceProblem {

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance(word1,word2));
    }

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i =0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0){
                    dp[i][j]=j; //converting empty string to word2[0..j] needs j insertions.
                }else if(j==0){
                    dp[i][j]=i; //converting word1[0..i] to empty string needs i deletions.
                }else if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]; // no operations required
                }else{
                    dp[i][j]=1+Math.min(dp[i][j-1], Math.min(dp[i-1][j],dp[i-1][j-1]) ); // Minimum of insert/delete/replace
                }
            }
        }

        return dp[m][n];
    }

}
