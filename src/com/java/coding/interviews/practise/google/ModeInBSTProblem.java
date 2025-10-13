package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 501. Find Mode in Binary Search Tree
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
 *
 * If the tree has more than one mode, return them in any order.
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: root = [0]
 * Output: [0]
 *
 * A BST’s inorder traversal visits nodes in sorted (non-decreasing) order,
 * so duplicates will appear consecutively, which makes counting easy.
 *
 * 🧠 Explanation (Step-by-Step)
 * 🔹 Step 1: Inorder Traversal Property
 *
 * In a BST, the inorder traversal gives sorted order of values.
 *
 * So, duplicate values (like 2, 2, 2) appear consecutively.
 *
 * This allows us to count occurrences linearly while traversing.
 *
 * 📊 Complexity
 * Operation	Complexity
 * Time	O(N) — single traversal
 * Space	O(H) (recursion stack), O(1) additional
 */
public class ModeInBSTProblem {

    static Integer previous = null;
    static int count=0;
    static int maxCount=0;
    static List<Integer> modes = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        System.out.println(Arrays.toString(findMode(root)));
    }

    public static int[] findMode(TreeNode root) {
        inOrder(root);
        int[] result = new int[modes.size()];
        for(int i =0;i<modes.size();i++){
            result[i]=modes.get(i);
        }
        return result;
    }

    private static void inOrder(TreeNode node){
        if(node==null) return;
        inOrder(node.left);
        if(previous!=null && previous == node.data){
            count++;
        }else{
            count=1;
        }
        if(count>maxCount){
            maxCount=count;
            modes.clear();
            modes.add(node.data);
        }else if(count==maxCount){
            modes.add(node.data);
        }
        previous=node.data;
        inOrder(node.right);
    }

}
