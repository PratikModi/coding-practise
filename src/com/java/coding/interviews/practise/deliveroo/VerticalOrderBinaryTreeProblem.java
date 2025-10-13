package com.java.coding.interviews.practise.deliveroo;

import java.util.ArrayList;
import java.util.List;

/**
 * 314. Binary Tree Vertical Order Traversal
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Example 2:
 *
 *
 * Input: root = [3,9,8,4,0,1,7]
 * Output: [[4],[9],[3,0,1],[8],[7]]
 * Example 3:
 *
 *
 * Input: root = [1,2,3,4,10,9,11,null,5,null,null,null,null,null,null,null,6]
 * Output: [[4],[2,5],[1,10,9,6],[3],[11]]
 */
public class VerticalOrderBinaryTreeProblem {

    public static void main(String[] args) {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);
            root.right.left = new TreeNode(6);
            root.right.right = new TreeNode(7);
        System.out.println(verticalOrder(root));
    }

    public static List<List<Integer>> verticalOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> nodes = new ArrayList<>();
        dfs(root,0,0,nodes);
        nodes.sort((a,b)->{
            if(a[0]!=b[0]) return Integer.compare(a[0],b[0]);
            if(a[1]!=b[1]) return Integer.compare(a[1],b[1]);
            return Integer.compare(a[2],b[2]);
        });
        int previous = Integer.MIN_VALUE;
        for(int[] node : nodes){
            int col = node[0]; int val = node[2];
            if(col!=previous){
                result.add(new ArrayList<>());
                previous=col;
            }
            result.get(result.size()-1).add(val);
        }
        return result;
    }

    private static void dfs(TreeNode node, int row, int col, List<int[]> nodes){
        if(node==null) return;
        nodes.add(new int[]{col,row,node.val});
        dfs(node.left,row+1,col-1,nodes);
        dfs(node.right,row+1,col+1,nodes);
    }

}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
        this.left=null;
        this.right=null;
    }

    public String toString(){
        return this.val+"";
    }

}
