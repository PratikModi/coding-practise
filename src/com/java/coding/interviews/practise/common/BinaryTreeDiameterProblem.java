package com.java.coding.interviews.practise.common;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 *
 * Input: root = [1,2]
 * Output: 1
 */
public class BinaryTreeDiameterProblem {

    private static int diameter = 0;
    public static void main(String[] args) {
        TreeNode2 root = new TreeNode2(1);
        root.left=new TreeNode2(2);
        root.right=new TreeNode2(3);
        root.left.left=new TreeNode2(4);
        root.left.right=new TreeNode2(5);
        System.out.println(findDiameter(root));
    }

    public static int findDiameter(TreeNode2 root){
        if(root==null)
            return 0;
        findDepth(root);
        return diameter;
    }

    private static int findDepth(TreeNode2 root) {
        if(root==null)
            return 0;
        int left = findDepth(root.left);
        int right = findDepth(root.right);
        diameter=Math.max(diameter,left+right);
        return Math.max(left,right)+1;
    }

}


