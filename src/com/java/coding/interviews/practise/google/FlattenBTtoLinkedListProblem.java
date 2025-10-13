package com.java.coding.interviews.practise.google;

import java.util.Stack;

/**
 * 114. Flatten Binary Tree to Linked List
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [0]
 * Output: [0]
 *
 * 🧠 Intuition
 *
 * We need to “flatten” the binary tree such that:
 *
 * Every node’s right pointer points to the next node in preorder.
 * Every node’s left pointer becomes null.
 * We can achieve this by:
 * Visiting nodes in reverse preorder (right → left → root).
 * Maintaining a previous (prev) pointer to link nodes as we go backward.
 */
public class FlattenBTtoLinkedListProblem {
    static TreeNode previous = null;

    public static void main(String[] args) {

    }

    public static void flatten(TreeNode root) {
        if(root==null) return;

        // Step 1: Flatten right subtree
        flatten(root.right);
        // Step 2: Flatten left subtree
        flatten(root.left);
        // Step 3: Rearrange pointers
        root.right=previous;
        root.left=null;
        // Step 4: Update prev
        previous=root;
    }

    public static void flattenUsingStack(TreeNode root){
        if(root==null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode current = stack.pop();
            if(root.right!=null) {
                stack.push(root.right);
            }
            if(root.left!=null){
                stack.push(root.left);
            }
            if(!stack.isEmpty()) {
                current.right = stack.peek();
            }
            current.left=null;
        }
    }

}
