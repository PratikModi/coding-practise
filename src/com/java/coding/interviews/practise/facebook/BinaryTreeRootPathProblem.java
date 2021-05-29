package com.java.coding.interviews.practise.facebook;

/**
 * Created by Pratik1 on 25-05-2020.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return all paths from the root to leaves.

 For example, given the tree:

      1
     / \
    2   3
       / \
      4   5
 Return [[1, 2], [1, 3, 4], [1, 3, 5]].
 */
public class BinaryTreeRootPathProblem {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.right=new TreeNode(5);
        System.out.println(findPathFromRoot(root));
    }

    public static List<List<Integer>> findPathFromRoot(TreeNode node){
        List<List<Integer>> result = new ArrayList<>();
        int[] path = new int[10];
        helper(node,path,0,result);
        return result;
    }

    private static void helper(TreeNode node, int[] path,int index,List<List<Integer>> result){
        if(node==null)
            return;
        path[index++]=node.data;
        //System.out.println(Arrays.toString(path));
        if(node.left==null && node.right==null){
            result.add(Arrays.stream(path).filter(e->e!=0).boxed().collect(Collectors.toList()));
        }else{
            //System.out.println("Left-->"+index);
            helper(node.left,path,index,result);
            //System.out.println("Right-->"+index);
            helper(node.right,path,index,result);
        }
    }
}
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public void print(){
        TreeNode temp = this;
        if(temp==null)
            return;
        System.out.println(temp.data);
        if(temp.left!=null)
        temp.left.print();
        if(temp.right!=null)
        temp.right.print();
    }
}