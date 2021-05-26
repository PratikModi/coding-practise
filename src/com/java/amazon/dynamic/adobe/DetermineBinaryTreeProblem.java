package com.java.amazon.dynamic.adobe;

/**
 * Determine if the binary tree is a binary search tree
 *
 * ​
 * Problem statement
 *
 * ​
 *
 * Given a Binary Tree, figure out whether it's a Binary Search Tree.
 * In a binary search tree, each node's key value is smaller than the key value of all nodes in the right subtree,
 * and are greater than the key values of all nodes in the left subtree i.e. L < N < R.
 *
 * ​
 */
public class DetermineBinaryTreeProblem {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(50);
        root.right = new BinaryTreeNode(200);
        root.left.left = new BinaryTreeNode(25);
        root.left.right = new BinaryTreeNode(75);
        root.right.right = new BinaryTreeNode(300);
        root.right.right.right = new BinaryTreeNode(350);
        System.out.println(checkIfBST(root));
    }

    public static boolean checkIfBST(BinaryTreeNode root){
        if(root==null)
            return true;
        return helper(root,Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean helper(BinaryTreeNode node,int min,int max){
        if(node==null)
            return true;
        if(node.value<min || node.value>max)
            return false;
        return helper(node.left,min, node.value-1) && helper(node.right,node.value+1,max);
    }

}
