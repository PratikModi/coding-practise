package com.java.coding.interviews.practise.adobe;

public class HeightOfTreeProblem {
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(50);
        root.right = new BinaryTreeNode(200);
        root.left.left = new BinaryTreeNode(25);
        root.left.right = new BinaryTreeNode(75);
        root.right.right = new BinaryTreeNode(300);
        root.right.right.right = new BinaryTreeNode(350);
        System.out.println(height(root));
        System.out.println(max);
        System.out.println(isBalanced(root));
    }

    public static int height(BinaryTreeNode root){
        if(root==null)
            return 0;
        else{
            int left = height(root.left);
            int right = height(root.right);
            if(left > right){
                 max = Math.max(max,left+1);
                 return left+1;
            }else{
                max = Math.max(max,right+1);
                return right+1;
            }
        }
    }


    public static boolean isBalanced(BinaryTreeNode root){
        if(root==null)
            return true;
        int left = height(root.left);
        int right = height(root.right);

        if(Math.abs(left-right)<=1 && isBalanced(root.left) && isBalanced(root.right)){
            return true;
        }
        return false;
    }
}
