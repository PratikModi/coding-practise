package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. Delete Nodes And Return Forest
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 * Example 2:
 *
 * Input: root = [1,2,4,null,3], to_delete = [3]
 * Output: [[1,2,4]]
 * ✅ Time Complexity: O(N) (visit every node once).
 * ✅ Space Complexity: O(H) recursion stack (H = height of tree).
 */
public class DeleteAndReturnForestProblem {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(delNodes(root, new int[]{3,5}));
    }
    private static List<TreeNode> result= new ArrayList<>();
    private static Set<Integer> toBeDeleted = new HashSet<>();

    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        for(int delete : to_delete){
            toBeDeleted.add(delete);
        }
        dfs(root,true);
        return result;
    }

    private static TreeNode dfs(TreeNode node, boolean isRoot){
        if(node==null) return null;
        boolean toDelete = toBeDeleted.contains(node.data);
        if(isRoot && !toDelete){
            result.add(node);
        }
        node.left = dfs(node.left,toDelete);
        node.right = dfs(node.right,toDelete);
        return toDelete?null:node; // if toDelete True then detach that node
    }


}

