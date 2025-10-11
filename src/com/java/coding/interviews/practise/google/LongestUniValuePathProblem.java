package com.java.coding.interviews.practise.google;

/**
 * 687. Longest Univalue Path
 * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.
 *
 * The length of the path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,5,1,1,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 5).
 * Example 2:
 *
 *
 * Input: root = [1,4,5,4,4,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 4).
 * ⚙️ Complexity
 * Type	Complexity
 * Time	O(n) — visits every node once
 * Space	O(h) — recursion stack (height of tree)
 */
public class LongestUniValuePathProblem {
    static int maxLength=0;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left=new TreeNode(4);
        root.left.left=new TreeNode(1);
        root.left.right=new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        System.out.println(longestUniValuePath(root));
    }

    public static int longestUniValuePath(TreeNode root) {
        dfs(root);
        return maxLength;
    }

    private static int dfs(TreeNode node){
        if(node==null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        int leftLength=0,rightLength=0;
        if(node.left!=null && node.left.data == node.data)
            leftLength=left+1;
        if(node.right!=null && node.right.data == node.data)
            rightLength=right+1;
        maxLength = Math.max(maxLength,leftLength+rightLength);
        return Math.max(leftLength,rightLength);
    }

}
