package com.java.coding.interviews.practise.apple;

/**
 * Given a tree, find the largest tree/subtree that is a BST.
 *
 * Given a tree, return the size of the largest tree/subtree that is a BST.
 *                   50
 *               /       \
 *             10         60
 *            /  \       /  \
 *           5   20    55    70
 *          /   /  \
 *         45  65  80
 */

public class FindMaximumBSTSubTreeProblem {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(50);
        root.left= new TreeNode(10);
        root.right= new TreeNode(60);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(20);
        root.right.left=new TreeNode(55);
        root.right.right=new TreeNode(70);
        root.left.left.left=new TreeNode(45);
        root.left.right.left=new TreeNode(65);
        root.left.right.right=new TreeNode(80);
        System.out.println(maximumBSTSize(root).size);
    }

    public static BSTNode maximumBSTSize(TreeNode root){
        if(root==null)
            return new BSTNode();
        BSTNode leftBSTNode = maximumBSTSize(root.left);
        BSTNode rightBSTNode = maximumBSTSize(root.right);

        BSTNode bstNode = new BSTNode();
        if(leftBSTNode.isBST==false || rightBSTNode.isBST==false || leftBSTNode.max > root.value || rightBSTNode.min<= root.value){
            bstNode.isBST=false;
            bstNode.size=Math.max(leftBSTNode.size, rightBSTNode.size);
            return bstNode;
        }
        bstNode.isBST = true;
        bstNode.size = leftBSTNode.size + rightBSTNode.size + 1;
        bstNode.min= root.left!=null ? leftBSTNode.min : root.value;
        bstNode.max=root.right!=null ? rightBSTNode.max : root.value;
        return bstNode;
    }


}

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        left=null;
        right=null;
    }
}

class BSTNode{
    int min;
    int max;
    boolean isBST;
    int size;

    public BSTNode() {
        this.min = Integer.MAX_VALUE;
        this.max = Integer.MIN_VALUE;
        this.isBST = true;
        this.size = 0;
    }
}
