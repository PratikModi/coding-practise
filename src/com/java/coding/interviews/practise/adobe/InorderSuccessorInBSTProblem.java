package com.java.coding.interviews.practise.adobe;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  Inorder Successor in BST
 * Medium
 *
 * 1627
 *
 * 72
 *
 * Add to List
 *
 * Share
 * Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST.
 * If the given node has no in-order successor in the tree, return null.
 *
 * The successor of a node p is the node with the smallest key greater than p.val.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3], p = 1
 * Output: 2
 * Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,null,1], p = 6
 * Output: null
 * Explanation: There is no in-order successor of the current node, so the answer is null.
 */

public class InorderSuccessorInBSTProblem {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(3);
        root.right = new BinaryTreeNode(6);
        root.left.left = new BinaryTreeNode(2);
        root.left.right = new BinaryTreeNode(4);
        root.left.left.left = new BinaryTreeNode(1);
        System.out.println(inOrderSuccessor(root,root.left));
        System.out.println(inOrderSuccessorExtraSpace(root,root.left));
    }

    public static BinaryTreeNode inOrderSuccessor(BinaryTreeNode root, BinaryTreeNode P){
        BinaryTreeNode successor = null;
        while(root!=null){
            if(P.value>=root.value){
                root=root.right;
            }else{
                successor=root;
                root=root.left;
            }
        }
        return successor;
    }

    public static BinaryTreeNode inOrderSuccessorExtraSpace(BinaryTreeNode root, BinaryTreeNode P){
        if(root==null)
            return null;
        Queue<BinaryTreeNode> Q = new LinkedList<>();
        inorder(root,Q);
        //System.out.println(Q);
        while(!Q.isEmpty()){
            if(Q.poll().value == P.value){
                break;
            }
        }
        return Q.peek()!=null?Q.peek():null;
    }

    private static void inorder(BinaryTreeNode root, Queue<BinaryTreeNode> Q){
        if(root==null)
            return;
        inorder(root.left,Q);
        Q.add(root);
        inorder(root.right,Q);
    }

}
