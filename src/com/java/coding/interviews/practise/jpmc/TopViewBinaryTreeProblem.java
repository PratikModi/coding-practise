package com.java.coding.interviews.practise.jpmc;

import java.util.*;

/**
 * Top View of Binary Tree
 */
public class TopViewBinaryTreeProblem {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left=new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        topView(root);
    }
    //HD Horizontal Distance
    public static void topView(BinaryTreeNode root){
        if(root==null)
            return;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        int hd=0;
        root.hd=0;
        queue.add(root);
        Map<Integer,BinaryTreeNode> topViewMap = new HashMap<>();
        while(!queue.isEmpty()){
            BinaryTreeNode node = queue.poll();
            hd = node.hd;
            if(!topViewMap.containsKey(hd)){
                topViewMap.put(hd,node);
            }
            if(node.left!=null){
                node.left.hd=hd-1;
                queue.add(node.left);
            }
            if(node.right!=null){
                node.right.hd=hd+1;
                queue.add(node.right);
            }
        }

        for(BinaryTreeNode node : topViewMap.values()){
            System.out.println(node.value);
        }
    }

}
class BinaryTreeNode{
    BinaryTreeNode left;
    BinaryTreeNode right;
    int value;
    int hd;

    public BinaryTreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

}
