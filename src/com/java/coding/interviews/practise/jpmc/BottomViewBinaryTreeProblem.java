package com.java.coding.interviews.practise.jpmc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Bottom View Binary Tree
 */
public class BottomViewBinaryTreeProblem {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left=new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        bottomView(root);
    }

    public static void bottomView(BinaryTreeNode root){
        if(root==null)
            return;
        int hd =0;
        root.hd=0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        Map<Integer,BinaryTreeNode> bottomViewMap = new HashMap<>();
        queue.add(root);
        while (!queue.isEmpty()){
            BinaryTreeNode node = queue.poll();
            hd = node.hd;
            bottomViewMap.put(hd,node);
            if(node.left!=null){
                node.left.hd = hd-1;
                queue.add(node.left);
            }
            if(node.right!=null){
                node.right.hd=hd+1;
                queue.add(node.right);
            }
        }

        for(BinaryTreeNode node : bottomViewMap.values()){
            System.out.println(node.value);
        }
    }

}
