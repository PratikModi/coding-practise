package com.java.coding.interviews.practise.google;

/**
 * 116. Populating Next Right Pointers in Each Node
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 * The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to
 * point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers,
 * with '#' signifying the end of each level.
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 * 🧠 Intuition
 *
 * We need to connect nodes horizontally (level by level).
 *
 * Since the tree is perfect:
 * Every node has both left and right children (except leaves).
 * We can connect:
 * node.left.next = node.right
 * And also connect across subtrees:
 * node.right.next = node.next.left (if node.next exists)
 *
 * This allows a constant space O(1) solution without extra queues.
 *         1
 *       /   \
 *      2     3
 *     / \   / \
 *    4  5  6  7
 *
 *    Step 1:
 * Call connectNodes(1.left, 1.right) → connects 2 → 3
 *
 * Step 2:
 * Inside left subtree: connectNodes(2.left, 2.right) → connects 4 → 5
 * Inside right subtree: connectNodes(3.left, 3.right) → connects 6 → 7
 *
 * Step 3:
 * Cross connection: connectNodes(2.right, 3.left) → connects 5 → 6
 */
public class PopulateNextRightPointerBTProblem {

    public static Node connect(Node root) {
        if(root==null) return null;
        connect(root.left,root.right);
        return root;
    }

    private static void connect(Node left, Node right){
        if(left==null || right==null) return;
        left.next = right;
        // Connect children of same parent
        connect(left.left, left.right);
        connect(right.left, right.right);

        // Connect across parents
        connect(left.right,right.left);
    }
}
