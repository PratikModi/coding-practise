package com.java.coding.interviews.practise.google;

/**
 * 530. Minimum Absolute Difference in BST
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
 *
 * Example 1:
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 * 🚀 Approach
 * Perform inorder traversal of the BST.
 * Keep track of the previous node visited (pre).
 * At each node, compute the difference between the current and previous node.
 * Update the global minimum difference min accordingly.
 *
 * ⏱️ Complexity
 * Time complexity: O(n)
 * Every node is visited exactly once.
 * Space complexity: O(h)
 * Where h is the height of the tree (due to recursion stack). Worst case O(n) for skewed tree, O(logn) for balanced.
 */
public class MinimumAbsoluteDiffBSTProblem {
    static int min = Integer.MAX_VALUE;
    static TreeNode previous = null;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        System.out.println(getMinimumDifference(root));
    }

    public static int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return min;
    }

    private static void inOrder(TreeNode node){
        if(node==null) return;
        inOrder(node.left);
        if(previous!=null){
            min = Math.min(min,Math.abs(previous.data-node.data));
        }
        previous=node;
        inOrder(node.right);
    }
}
