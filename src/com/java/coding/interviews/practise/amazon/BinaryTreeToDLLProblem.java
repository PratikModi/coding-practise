package com.java.coding.interviews.practise.amazon;

/**
 * Convert a given Binary Tree to Doubly Linked List | Set 1
 * Difficulty Level : Hard
 * Last Updated : 21 May, 2021
 * Given a Binary Tree (Bt), convert it to a Doubly Linked List(DLL).
 * The left and right pointers in nodes are to be used as previous and next pointers respectively in converted DLL.
 * The order of nodes in DLL must be the same as in Inorder for the given Binary Tree.
 * The first node of Inorder traversal (leftmost node in BT) must be the head node of the DLL.
 *
 * TreeToList
 */
public class BinaryTreeToDLLProblem {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(12);
        root.left.left = new TreeNode(25);
        root.left.right = new TreeNode(30);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(36);
        binaryTreeToDLL(root);
        while(headNode!=null){
            System.out.print(headNode.value+" ");
            headNode=headNode.right;
        }

    }

    private static TreeNode headNode;
    private static TreeNode previous;

    public static void binaryTreeToDLL(TreeNode node){
        if(node==null)
            return;
        binaryTreeToDLL(node.left);
        if(previous==null){
            headNode=node;
        }else{
            node.left=previous;
            previous.right=node;
        }
        previous=node;
        binaryTreeToDLL(node.right);
    }

}
