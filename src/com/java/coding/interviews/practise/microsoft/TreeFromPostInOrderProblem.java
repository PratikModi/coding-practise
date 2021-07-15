package com.java.coding.interviews.practise.microsoft;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
   Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree,
   construct and return the binary tree.
 *
 * Example 1:
 *
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 */
public class TreeFromPostInOrderProblem {

    public static void main(String[] args) {
        int[] postorder = {9,15,7,20,3};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree(postorder,inorder);
        root.print(root);
    }

    private static Map<Integer,Integer> inorderIndex = new HashMap<>();
    private static int postorderIndex;

    /**
     * Algorithm
     *
     * Build hashmap value -> its index for inorder traversal.
     *
     * Return helper function which takes as the arguments the left and right boundaries for the current subtree in the inorder traversal.
     * These boundaries are used only to check if the subtree is empty or not. Here is how it works helper(in_left = 0, in_right = n - 1):
     *
     * If in_left > in_right, the subtree is empty, return None.
     *
     * Pick the last element in postorder traversal as a root.
     *
     * Root value has index index in the inorder traversal, elements from in_left to index - 1 belong to the left subtree,
     * and elements from index + 1 to in_right belong to the right subtree.
     *
     * Following the postorder logic, proceed recursively first to construct the right subtree helper(index + 1, in_right) and
     * then to construct the left subtree helper(in_left, index - 1).
     *
     * Return root.
     *
     */
    public static TreeNode buildTree(int[] postorder, int[] inorder){
        if(postorder==null || inorder==null || postorder.length==0 || inorder.length==0)
            return null;
        postorderIndex=postorder.length-1;
        for(int i=0;i<inorder.length;i++){
            inorderIndex.put(inorder[i],i);
        }
        return helper(postorder,0,postorder.length-1);
    }

    private static TreeNode helper(int[] postorder, int left, int right){
        System.out.println(postorderIndex);
        if(left>right) return null;

        int rootValue = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootValue);
        root.right = helper(postorder,inorderIndex.get(rootValue)+1,right);
        root.left = helper(postorder,left,inorderIndex.get(rootValue)-1);
        return root;
    }
}
