package com.java.coding.interviews.practise.uipath;

/**
 * Convert a given tree to its Sum Tree
 * Difficulty Level : Medium
 * Last Updated : 15 Jun, 2021
 * Given a Binary Tree where each node has positive and negative values.
 * Convert this to a tree where each node contains the sum of the left and right sub trees in the original tree. The values of leaf nodes are changed to 0.
 * For example, the following tree
 *
 *
 *                   10
 *                /      \
 *              -2        6
 *            /   \      /  \
 *          8     -4    7    5
 * should be changed to
 *
 *
 *                  20(4-2+12+6)
 *                /      \
 *          4(8-4)      12(7+5)
 *            /   \      /  \
 *          0      0    0    0
 */
public class CopyBinaryTreeSumProblem {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left=new TreeNode(-2);
        root.left.left=new TreeNode(8);
        root.left.right=new TreeNode(-4);
        root.right=new TreeNode(6);
        root.right.left=new TreeNode(7);
        root.right.right=new TreeNode(5);
        inorder(root);
        covert(root);
        System.out.println();
        inorder(root);
    }

    public static int covert(TreeNode root){
        if(root==null)
            return 0;
        int oldValue = root.value;
        root.value = covert(root.left)+covert(root.right);
        System.out.println(root.value);
        return oldValue+root.value;
    }

    public static void inorder(TreeNode root){
        if(root==null)
            return;
        inorder(root.left);
        System.out.print(root.value+" ");
        inorder(root.right);
    }

}
