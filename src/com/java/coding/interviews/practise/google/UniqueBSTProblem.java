package com.java.coding.interviews.practise.google;

/**
 * 96. Unique Binary Search Trees
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique
 * values from 1 to n.
 *
 * Example 1:
 * Input: n = 3
 * Output: 5
 *
 * Example 2:
 * Input: n = 1
 * Output: 1
 *
 * 🧠 Intuition
 *
 * We can build BSTs recursively.
 * If we pick a node i as the root, then:
 * All values < i must go to the left subtree
 * All values > i must go to the right subtree
 * So the total number of unique BSTs with n nodes is the sum of:
 * numTrees(leftSubtree) * numTrees(rightSubtree)
 *
 * ⏱️ Complexity
 * Type	Complexity
 * Time	O(n²)
 * Space	O(n)
 */
public class UniqueBSTProblem {

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    public static int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0]=1; // Empty tree
        dp[1]=1; //Single node
        for(int nodes=2;nodes<=n;nodes++){
            for(int root=1;root<=nodes;root++){
                /*
                💡 Why dp[i-1] * dp[n-i]?
                    i-1 = nodes in left subtree
                    n-i = nodes in right subtree
                    Multiply because we can combine any left structure with any right structure
                 */
                dp[nodes]+=dp[root-1]*dp[nodes-root];
            }
        }
        return dp[n];
    }

}
