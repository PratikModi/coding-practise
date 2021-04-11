package com.java.amazon.dynamic.microsoft;

import java.util.ArrayList;
import java.util.List;

/**
 * Merge Two Balanced Binary Search Trees
 * Difficulty Level : Medium
 *  Last Updated : 12 Jan, 2021
 * You are given two balanced binary search trees e.g., AVL or Red Black Tree.
 * Write a function that merges the two given balanced BSTs into a balanced binary search tree.
 * Let there be m elements in first tree and n elements in the other tree. Your merge function should take O(m+n) time.
 */
public class MergeBSTProblem {

    public static void main(String[] args) {
        /* Creating following tree as First balanced BST
                100
                / \
                50 300
                / \
               20 70
        */

        TreeNode root1 = new TreeNode(100);
        root1.left = new TreeNode(50);
        root1.right = new TreeNode(300);
        root1.left.left = new TreeNode(20);
        root1.left.right = new TreeNode(70);

        /* Creating following tree as second balanced BST
                80
                / \
              40 120
        */

        TreeNode root2 = new TreeNode(80);
        root2.left = new TreeNode(40);
        root2.right = new TreeNode(120);

        List<Integer> result = merge(root1,root2);
        System.out.println(result);
        inOrder(buildBST(result,0, result.size()-1));

    }

    public static void inOrder(TreeNode root){
        if(root==null)
            return;
        inOrder(root.left);
        System.out.print(root.value+" ");
        inOrder(root.right);
    }

    public static void inOrder(TreeNode root, List<Integer> L){
        if(root==null)
            return;
        inOrder(root.left,L);
        L.add(root.value);
        inOrder(root.right,L);
    }

    public static List<Integer> merge(TreeNode root1, TreeNode root2){
        List<Integer> AL1 = new ArrayList<>();
        List<Integer> AL2 = new ArrayList<>();
        inOrder(root1,AL1);
        inOrder(root2,AL2);
        if(AL1.isEmpty())
            return AL2;
        if(AL2.isEmpty())
            return AL1;
        int n= AL1.size();
        int m= AL2.size();
        List<Integer> result = new ArrayList<>();
        int i=0,j=0;
        while(i<n && j<m){
            if(AL1.get(i)< AL2.get(j)){
                result.add(AL1.get(i++));
            }else if(AL1.get(i)>AL2.get(j)){
                result.add(AL2.get(j++));
            }
        }
        while(i<n){
            result.add(AL1.get(i++));
        }
        while(j<m){
            result.add(AL2.get(j++));
        }
        return result;
    }

    public static TreeNode buildBST(List<Integer> AL, int start,int end){
        if(AL==null || AL.isEmpty() || start>end)
            return null;
        int mid = start+(end-start)/2;
        TreeNode root = new TreeNode(AL.get(mid));
        root.left=buildBST(AL,start,mid-1);
        root.right=buildBST(AL,mid+1,end);
        return root;
    }

}
