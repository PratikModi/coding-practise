package com.java.amazon.dynamic.google;

/**
 * Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 */
public class KthSmallestBSTProblem {

    public static void main(String[] args) {
        BSTreeNode root = new BSTreeNode(5);
        root.left= new BSTreeNode(3);
        root.left.left = new BSTreeNode(2);
        root.left.left.left = new BSTreeNode(1);
        root.right = new BSTreeNode(6);
        root.left.right = new BSTreeNode(4);
        int result = findKthSmallest(root,4);
        System.out.println(result);

    }

    public static int findKthSmallest(BSTreeNode root, int K){
        int[] result = new int[2];
        inorder(root,result,K);
        return result[1];
    }

    private static void inorder(BSTreeNode root, int[] result, int K){
        if(root==null)
            return;
        inorder(root.left,result,K);
        if(++result[0]==K){
            result[1]=root.value;
            return;
        }
        inorder(root.right,result,K);
    }


}

class BSTreeNode{
    int value;
    BSTreeNode left;
    BSTreeNode right;

    public BSTreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}
