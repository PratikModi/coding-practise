package com.java.coding.interviews.practise.adobe;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given the root of a binary tree, return the sum of all left leaves.
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 24
 * Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
 * Example 2:
 *
 * Input: root = [1]
 * Output: 0
 */
public class SumOfLeftLeavesProblem {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(50);
        root.right = new BinaryTreeNode(200);
        root.left.left = new BinaryTreeNode(25);
        root.left.right = new BinaryTreeNode(75);
        root.right.right = new BinaryTreeNode(300);
        root.right.right.left = new BinaryTreeNode(350);
        System.out.println(findLeftLeavesSumRecursion(root));
        System.out.println(findLeftLeavesSumIterative(root));
    }

    public static int findLeftLeavesSumRecursion(BinaryTreeNode root){
        if(root==null)
            return 0;
        return processTree(root,false);
    }

    private static int processTree(BinaryTreeNode node, boolean isLeft){
        if(node==null)
            return 0;
        if(node.left==null && node.right==null){
            if(isLeft)
                return node.value;
            else
                return 0;
        }
        int total=0;
        if(node.left!=null){
            total+= processTree(node.left,true);
        }
        if(node.right!=null){
            total+=processTree(node.right,false);
        }
        return total;
    }

    private static boolean isLeftLeave(BinaryTreeNode node){
        if(node!=null && node.left==null && node.right==null)
            return true;
        return false;
    }

    public static int findLeftLeavesSumIterative(BinaryTreeNode root){
        if(root==null)
            return 0;
        Deque<BinaryTreeNode> DQ = new ArrayDeque<>();
        int total=0;
        DQ.offer(root);

        while(!DQ.isEmpty()){
            BinaryTreeNode node = DQ.poll();
            if(isLeftLeave(node.left)){
                total+=node.left.value;
            }
            if(node.right!=null){
                DQ.offer(node.right);
            }
            if(node.left!=null){
                DQ.offer(node.left);
            }
        }
        return total;
    }
}
