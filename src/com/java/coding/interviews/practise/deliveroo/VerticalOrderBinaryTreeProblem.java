package com.java.coding.interviews.practise.deliveroo;

import java.util.ArrayList;
import java.util.List;

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
