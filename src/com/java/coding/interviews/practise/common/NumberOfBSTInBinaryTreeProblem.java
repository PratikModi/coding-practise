package com.java.coding.interviews.practise.common;

/**
 * Count the Number of Binary Search Trees present in a Binary Tree
 * Difficulty Level : Medium
 * Last Updated : 06 Dec, 2020
 * Given a binary tree, the task is to count the number of Binary Search Trees present in it.
 *
 * Examples:
 *
 *
 * Input:
 *
 *     1
 *    /  \
 *   2    3
 *  / \  / \
 * 4   5 6  7
 * Output: 4
 * Here each leaf node represents a binary search tree and there are total 4 nodes.
 * Input:
 *
 *       11
 *      /  \
 *     8    10
 *    /    /  \
 *   5    9    8
 *  / \
 * 4   6
 * Output: 6
 * Sub-tree rooted under node 5 is a BST
 *
 *
 *
 *
 *    5
 *   / \
 *  4   6
 * Another BST we have is rooted under the node 8
 *
 *         8
 *        /
 *       5
 *      / \
 *     4   6
 * Thus total 6 BSTs are present (including the leaf nodes).
 */

public class NumberOfBSTInBinaryTreeProblem {
    /**
     * ALGORITHM:-
     *
     * A Binary Tree is a Binary Search Tree if the following are true for every node x.
     *
     *
     * The largest value in left subtree (of x) is smaller than value of x.
     * The smallest value in right subtree (of x) is greater than value of x.
     * We traverse tree in bottom up manner. For every traversed node, we store the information of maximum and minimum of that subtree,
     * a variable isBST to store if it is a BST and variable num_BST to store the number of Binary search tree rooted under the current node.
     * Below is the implementation of the above approach:
     *
     */
    public static void main(String[] args) {
        TreeNode2 root = new TreeNode2(1);
        root.left= new TreeNode2(2);
        root.right= new TreeNode2(3);
        root.left.left= new TreeNode2(4);
        root.left.right= new TreeNode2(5);
        root.right.left= new TreeNode2(6);
        root.right.right= new TreeNode2(7);
        System.out.println(countBSTInBT(root));
    }

    public static BSTInfo countBSTInBT(TreeNode2 root){
        if(root==null){
            return new BSTInfo(Integer.MIN_VALUE,Integer.MAX_VALUE,0,true);
        }
        if(root.left==null && root.right==null){
            return new BSTInfo(root.val,root.val,1,true);
        }
        BSTInfo left = countBSTInBT(root.left);
        BSTInfo right = countBSTInBT(root.right);

        BSTInfo bstInfo = new BSTInfo();
        bstInfo.min = Math.min(root.val,Math.min(left.min,right.min));
        bstInfo.max = Math.max(root.val,Math.max(left.max,right.max));
        if(left.is_bst && right.is_bst && root.val > left.min && root.val < right.max){
            bstInfo.is_bst=true;
            bstInfo.num_bst=1+left.num_bst+ right.num_bst;
        }else{
            bstInfo.is_bst=false;
            bstInfo.num_bst=left.num_bst+ right.num_bst;
        }
        return bstInfo;
    }


}

class BSTInfo{
    int min;
    int max;
    int num_bst;
    boolean is_bst;

    public BSTInfo(int min, int max, int num_bst, boolean is_bst) {
        this.min = min;
        this.max = max;
        this.num_bst = num_bst;
        this.is_bst = is_bst;
    }

    public BSTInfo() {
    }

    @Override
    public String toString() {
        return "BSTInfo{" +
                "min=" + min +
                ", max=" + max +
                ", num_bst=" + num_bst +
                ", is_bst=" + is_bst +
                '}';
    }
}
