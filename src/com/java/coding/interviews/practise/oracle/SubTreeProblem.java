package com.java.coding.interviews.practise.oracle;

/**
 * Check if a Binary Tree is subtree of another binary tree | Set 1
 * Read
 * Discuss(280)
 * Courses
 * Practice
 * Video
 * Given two binary trees, check if the first tree is a subtree of the second one.
 * A subtree of a tree T is a tree S consisting of a node in T and all of its descendants in T.
 * The subtree corresponding to the root node is the entire tree;
 * the subtree corresponding to any other node is called a proper subtree.
 *
 * Examples:
 *
 * Input:
 *
 *       Tree S
 *           10
 *         /    \
 *       4       6
 *        \
 *         30
 *
 *
 *         Tree T
 *               26
 *             /   \
 *           10     3
 *         /    \     \
 *       4       6      3
 *        \
 *         30
 * Output: S is subtree of tree T
 */
public class SubTreeProblem {

    public static void main(String[] args) {
        TreeNode T = new TreeNode(26);
        T.left = new TreeNode(10);
        T.right = new TreeNode(3);
        T.left.left = new TreeNode(4);
        T.left.right = new TreeNode(6);
        T.left.left.right = new TreeNode(30);
        T.right.right = new TreeNode(3);
        TreeNode S = new TreeNode(10);
        S.left = new TreeNode(4);
        S.right = new TreeNode(6);
        S.left.right = new TreeNode(30);

        System.out.println(isSubtree(T,S));
    }

    private static boolean areIdentical(TreeNode node1, TreeNode node2){
        if(node1==null && node2==null)
            return true;
        if(node1==null || node2==null)
            return false;
        return (node1.value==node2.value && areIdentical(node1.left,node2.left) && areIdentical(node1.right,node2.right));
    }

    private static boolean isSubtree(TreeNode T, TreeNode S){
        if(S==null)
            return true;
        if(T==null)
            return false;
        if(areIdentical(T,S))
            return true;
        return isSubtree(T.left,S) || isSubtree(T.right,S);
    }
}

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value){
        this.value = value;
        this.left=null;
        this.right=null;
    }
}