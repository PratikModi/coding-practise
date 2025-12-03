package com.java.coding.interviews.practise.salesforce;

/**
 * LeetCode: 124. Binary Tree Maximum Path Sum
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 * A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * 🧠 Time & Space Complexity
 * Complexity	Value
 * Time	O(n) — visits each node once
 * Space	O(h) recursion stack (h = tree height)
 * Core idea (one-sentence)
 * For each node, compute two things:
 * The best one-branch sum you can return to that node’s parent (must choose either left or right, or neither if negative).
 * The best complete path that passes through that node (left + node + right). Update a global maximum with this candidate.
 * We do this with a single post-order DFS: compute left and right gains first, then combine at the node.
 */
public class BinaryTreeMaxPathSumProblem {
    private static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(7);
        System.out.println(maxPathSum(root));
    }
    public static int maxPathSum(TreeNode root) {
        dfs(root);
        return maxValue;
    }

    private static int dfs(TreeNode node){
        if(node==null) return 0;
        int left = Math.max(0,dfs(node.left)); // Ignore Negative
        int right = Math.max(0,dfs(node.right));
        maxValue = Math.max(maxValue,left+node.value+right);
        return node.value+Math.max(left,right);

    }
}
class TreeNode{
    TreeNode left;
    TreeNode right;
    int value;
    TreeNode(int value){
        this.value=value;
    }
}
