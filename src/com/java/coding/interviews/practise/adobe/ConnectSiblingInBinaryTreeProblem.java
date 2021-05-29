package com.java.coding.interviews.practise.adobe;

/**
 * Connect All Siblings
 * Problem Statement
 * Given the root to a binary tree where each node has an additional pointer called sibling (or next),
 * connect the sibling pointer to the next node in the same level.
 * The last node in each level should point to the first node of the next level in the tree.
 *
 * Consider the following binary tree.
 *
 *
 * widget
 * Here’s how the final tree would look when all sibling pointers have been connected.
 *
 *
 * widget
 */
public class ConnectSiblingInBinaryTreeProblem {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(50);
        root.right = new BinaryTreeNode(200);
        root.left.left = new BinaryTreeNode(25);
        root.left.right = new BinaryTreeNode(75);
        root.right.right = new BinaryTreeNode(300);
        root.right.right.right = new BinaryTreeNode(350);
        inOrder(root);
        connectSiblings(root);
        System.out.println();
        inOrder(root);
    }

    public static void connectSiblings(BinaryTreeNode root){
        if(root==null)
            return;
        BinaryTreeNode current = root;
        BinaryTreeNode last = root;

        while(current!=null){
            if(current.left!=null){
                last.next = current.left;
                last=current.left;
            }
            if(current.right!=null){
                last.next = current.right;
                last=current.right;
            }
            current=current.next;
            last.next=null;
        }
    }

    public static void inOrder(BinaryTreeNode node){
        if(node==null)
            return;
        inOrder(node.left);
        String value = node.value + (node.next!=null?"("+node.next.value+")":"");
        System.out.print(value+",");
        inOrder(node.right);
    }

}
class BinaryTreeNode{
    int value;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode next;

    public BinaryTreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.next = null;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "value=" + value +
                '}';
    }
}