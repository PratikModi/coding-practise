package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 */
public class BinaryTreeZigzagOrderProblem {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(zigzagLevelOrder(root));
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        Deque<TreeNode> deQueue = new LinkedList<>();
        deQueue.offer(root);
        boolean reverse=false;
        while(!deQueue.isEmpty()){
            List<Integer> list = new ArrayList<>(deQueue.size());
            int size = deQueue.size();
            for(int i=0;i< size;i++){
                if(!reverse){
                    TreeNode node = deQueue.pollFirst();
                    list.add(node.data);
                    if(node.left!=null) deQueue.addLast(node.left);
                    if(node.right!=null) deQueue.addLast(node.right);
                }else{
                    TreeNode node = deQueue.pollLast();
                    list.add(node.data);
                    if(node.right!=null) deQueue.addFirst(node.right);
                    if(node.left!=null) deQueue.addFirst(node.left);
                }
            }
            reverse=!reverse;
            result.add(list);
        }
        return result;
    }
}
