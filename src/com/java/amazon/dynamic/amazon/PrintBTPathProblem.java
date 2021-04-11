package com.java.amazon.dynamic.amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 *
 * A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 * Example 2:
 *
 * Input: root = [1]
 * Output: ["1"]
 */
public class PrintBTPathProblem {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.right=new TreeNode(5);
        System.out.println(printBTPath(root));
    }

    public static List<List<Integer>> printBTPath(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root==null)
            return result;
        List<Integer> paths = new ArrayList<>();
        paths.add(0,root.value);
        if(root.left==null && root.right==null) result.add(new ArrayList<>(paths));
        if(root.left!=null) helper(root.left,result,paths,1);
        if(root.right!=null) helper(root.right,result,paths,1);
        return result;
    }

    public static void helper(TreeNode root, List<List<Integer>> result,List<Integer> paths, int index){
        System.out.println(paths.size()+"--"+index);
        if(paths.size()>index)
            paths.set(index, root.value);
        else
            paths.add(index,root.value);
        index++;
        if(root.left==null && root.right==null){
            result.add(new ArrayList<>(paths));
            return;
        }
        if(root.left!=null) helper(root.left,result,paths,index);
        if(root.right!=null) helper(root.right,result,paths,index);
    }

}
class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.left=null;
        this.right=null;
    }
}