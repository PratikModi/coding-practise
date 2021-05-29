package com.java.coding.interviews.practise.microsoft;

import java.util.*;

/**
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively.
 * The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 *
 *            1
 *         /    \
 *        2      3
 *       / \   /   \
 *      4   5  6   7
 *                /  \
 *               8   9
 *
 */
public class VerticalOrderBinaryTreeProblem {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(8);
        root.right.right.right = new TreeNode(9);

        System.out.println(verticalTraversal(root));

    }

    private static void helper(Map<Integer, Map<Integer, SortedSet<Integer>>> M, int vOrder, int hOrder, TreeNode root){
        if(root==null)
            return;
        M.putIfAbsent(vOrder,new TreeMap<Integer,SortedSet<Integer>>());
        Map<Integer,SortedSet<Integer>> horizontal = M.get(vOrder);
        horizontal.putIfAbsent(hOrder,new TreeSet<Integer>());
        horizontal.get(hOrder).add(root.value);

        helper(M,vOrder-1,hOrder+1,root.left);
        helper(M,vOrder+1,hOrder+1,root.right);

    }

    public static List<List<Integer>> verticalTraversal(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root==null)
            return result;
        Map<Integer, Map<Integer, SortedSet<Integer>>> M = new TreeMap();
        helper(M,0,0,root);

        Iterator<Integer> itr = M.keySet().iterator();
        while(itr.hasNext()){
            List<Integer> nodes = new ArrayList<>();
            Integer vOrder = itr.next();
            Iterator<Integer> it = M.get(vOrder).keySet().iterator();
            while(it.hasNext()){
                nodes.addAll(M.get(vOrder).get(it.next()));
            }
            result.add(nodes);
        }
        return result;
    }
}
