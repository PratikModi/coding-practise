package com.java.amazon.dynamic.uber;

import java.util.*;

/**
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 */
public class DuplicateSubTreeProblem {
    static List<TreeNode> result = new ArrayList<>();
    static Map<String,Integer> M = new LinkedHashMap<>();
    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(4);
        root.right.right = new TreeNode(4);
        System.out.println(findDuplicateSubTree(root));
    }

    public static List<TreeNode> findDuplicateSubTree(TreeNode root){
        if(root==null)
            return null;
        helper(root);
        System.out.println(M);
        return result;
    }

    public static String helper(TreeNode node){
        if(node==null)
            return "X";
        String left = helper(node.left);
        String right = helper(node.right);
        String path = node.value+","+left+","+right;
        M.putIfAbsent(path,0);
        M.put(path,M.get(path)+1);
        if(M.get(path)==2){
            result.add(node);
        }
        return path;
    }


}

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        left=null;
        right=null;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return value == treeNode.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
