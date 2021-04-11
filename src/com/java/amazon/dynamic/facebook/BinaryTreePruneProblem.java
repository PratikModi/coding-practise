package com.java.amazon.dynamic.facebook;

/**
 * Created by Pratik1 on 11-06-2020.
 */

/**
 * This question was asked by BufferBox.

 Given a binary tree where all nodes are either 0 or 1, prune the tree so that subtrees containing all 0s are removed.

 For example, given the following tree:

            0
           / \
          1   0
             / \
            1   0
           / \
          0   0
 should be pruned to:

     0
    / \
   1   0
      /
     1
 */
public class BinaryTreePruneProblem {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(0);
        root.right.left.left = new TreeNode(0);
        root.right.left.right = new TreeNode(0);
        pruneTree(root);
        root.print();
    }

    public static TreeNode pruneTree(TreeNode root) {
        if (root == null)
            return null;
        //if(root.left!=null && root.right!=null)

        //System.out.println(root.data);
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.data == 0) {
            return null;
        }
        return root;
    }
}