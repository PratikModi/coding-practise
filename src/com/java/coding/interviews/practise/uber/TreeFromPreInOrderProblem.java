package com.java.coding.interviews.practise.uber;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Medium
 *
 * Add to List
 *
 * Share
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 *
 * Example 1:
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 */
public class TreeFromPreInOrderProblem {

    /**
     * Algorithm: -
     * Approach 1: Recursion
     * Intuition
     *
     * The two key observations are:
     *
     * Preorder traversal follows Root -> Left -> Right, therefore, given the preorder array preorder, we have easy access to the root which is preorder[0].
     *
     * Inorder traversal follows Left -> Root -> Right, therefore if we know the position of Root, we can recursively split the entire array into two subtrees.
     *
     * Now the idea should be clear enough. We will design a recursion function: it will set the first element of preorder as the root,
     * and then construct the entire tree. To find the left and right subtrees, it will look for the root in inorder,
     * so that everything on the left should be the left subtree, and everything on the right should be the right subtree.
     * Both subtrees can be constructed by making another recursion call.
     *
     * It is worth noting that, while we recursively construct the subtrees, we should choose the next element in preorder to initialize as the new roots.
     * This is because the current one has already been initialized to a parent node for the subtrees.
     */
    private static Map<Integer,Integer> inorderIndex = new HashMap<>();
    private static int preorderIndex=0;
    public static TreeNode buildTree(int[] preorder, int[] inorder){
        if(preorder==null || inorder==null || preorder.length==0 || inorder.length==0)
            return null;
        for(int i=0;i<inorder.length;i++){
            inorderIndex.put(inorder[i],i);
        }
        return arrayToTree(preorder,0,preorder.length-1);
    }

    private static TreeNode arrayToTree(int[] preorder, int left, int right){
        // if there are no elements to construct the tree
        if(left>right) return null;
        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);
        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder,left,inorderIndex.get(rootValue)-1);
        root.right = arrayToTree(preorder,inorderIndex.get(rootValue)+1,right);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder =  {9,3,15,20,7};
        TreeNode root = buildTree(preorder,inorder);
        root.print(root);
    }

}
