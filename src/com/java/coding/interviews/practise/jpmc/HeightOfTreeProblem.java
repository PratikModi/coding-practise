package com.java.coding.interviews.practise.jpmc;

/**
 * Height of Binary Tree Problem
 */
public class HeightOfTreeProblem {
    private static int height = Integer.MIN_VALUE;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(50);
        root.right = new TreeNode(200);
        root.left.left = new TreeNode(25);
        root.left.right = new TreeNode(75);
        root.right.right = new TreeNode(300);
        root.right.right.right = new TreeNode(350);
        System.out.println(findHeight(root));
        System.out.println(isBalanced(root));
    }
    //Time Complexity: O(N)
    public static int findHeight(TreeNode root){
        if(root==null)
            return 0;
        int left = findHeight(root.left);
        int right = findHeight(root.right);
        if(left>right){
            height = Math.max(height,left+1);
            return left+1;
        }else{
            height = Math.max(height,right+1);
            return right+1;
        }
    }

    public static boolean isBalanced(TreeNode root){
        if(root==null)
            return true;
        int left = findHeight(root.left);
        int right = findHeight(root.right);
        if(Math.abs(left-right)<=1 && isBalanced(root.left) && isBalanced(root.right))
            return true;
        return false;
    }

}

