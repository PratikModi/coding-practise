package com.java.coding.interviews.practise.common;

/**
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: 5
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 */
public class UniqueBSTProblem {

    /**
     * Intuition
     *
     * The problem can be solved in a dynamic programming way.
     *
     * Given a sorted sequence 1 ... n, to construct a Binary Search Tree (BST) out of the sequence, we could enumerate each number i in the sequence,
     * and use the number as the root, then, the subsequence 1 ... (i-1) on its left side would lay on the left branch of the root,
     * and similarly the right subsequence (i+1) ... n lay on the right branch of the root. We then can construct the subtree from the subsequence recursively.
     * Through the above approach, we could be assured that the BST that we construct are all unique, since they start from unique roots.
     *
     * As we can see, the problem can be reduced into problems with smaller sizes, instead of recursively (also repeatedly) solve the sub problems,
     * we can store the solution of sub problems and reuse them later, i.e. the dynamic programming way.
     */
    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    public static int numTrees(int n) {
        int[] DP = new int[n+1];
        DP[0]=1;
        DP[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=1;j<=i;j++){
                DP[i]+=DP[j-1]*DP[i-j];
            }
        }
        return DP[n];
    }

}
