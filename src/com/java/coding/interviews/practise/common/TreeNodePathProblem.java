package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pratik1 on 07-03-2020.
 * Print path between any two nodes in a Binary Tree
 * Last Updated: 07-06-2019
 * Given a Binary Tree of distinct nodes and a pair of nodes. The task is to find and print the path between the two given nodes in the binary tree.
 */
public class TreeNodePathProblem {

    private static TreeNode getNode(String value){
        return new TreeNode(value);
    }

    public static boolean findPath(TreeNode root, List<String> list, String node){
        if(root==null)
            return false;
        list.add(root.value);
        if(root.value.equalsIgnoreCase(node))
            return true;
        if(findPath(root.left,list,node) || findPath(root.right,list,node))
            return true;
        list.remove(list.size()-1);
        return false;
    }

    public static void main(String[] args) {
        TreeNode node=getNode("1");
        node.left=getNode("2");
        node.right=getNode("4");
        node.left.left=getNode("3");
        node.right.right=getNode("5");
        List<String> node1Path = new ArrayList<>();
        List<String> node2Path = new ArrayList<>();
        findPath(node,node1Path,"3");
        findPath(node,node2Path,"1");
        int i=0,j=0;
        int intersection=-1;
        while(i!=node1Path.size() || j!=node2Path.size()){
            if(i==j && i<node1Path.size() && i < node2Path.size() && node2Path.get(i)!=null && node1Path.get(i).equalsIgnoreCase(node2Path.get(i))){
                i++;
                j++;
            }else {
                intersection=j-1;
                break;
            }
        }
        for(int k = node1Path.size()-1;k>intersection;k--){
            System.out.print(node1Path.get(k)+"->");
        }
        for(int k=intersection;k<node2Path.size();k++){
            System.out.print(node2Path.get(k)+"->");
        }
    }
}
