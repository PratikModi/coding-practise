package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 24-05-2020.
 */

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * Assume that each node in the tree also has a pointer to its parent.
 */
public class BinaryTreeLCAProblem {

    public static void main(String[] args) {
        TreeNode root= new TreeNode(1);
        root.left=new TreeNode(2);
        root.left.left= new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right= new TreeNode(5);
        root.right.left= new TreeNode(6);
        root.right.right = new TreeNode(7);
        TreeNode LCA = findLCA(root,7,2);
        System.out.println(LCA);
        LCA = findLCA(root,3,4);
        System.out.println(LCA);
    }

    public static TreeNode findLCA(TreeNode root, int N1, int N2){
        if(root==null)
            return root;
        if(root.data==N1 || root.data==N2)
            return root;
        TreeNode left = findLCA(root.left,N1,N2);
        TreeNode right = findLCA(root.right,N1,N2);
        if(left==null && right==null)
            return null;
        if(left!=null && right!=null)
            return root;
        return left==null?right:left;
    }

}

class TreeNode{
    TreeNode left;
    TreeNode right;
    int data;
    TreeNode(int data){
        this.data=data;
        this.left=null;
        this.right=null;
    }
    public String toString(){
        return String.valueOf(this.data);
    }
}
