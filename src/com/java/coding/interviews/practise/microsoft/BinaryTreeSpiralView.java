package com.java.coding.interviews.practise.microsoft;

import java.util.Stack;

public class BinaryTreeSpiralView {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left= new TreeNode(2);
        root.right= new TreeNode(3);
        root.left.left= new TreeNode(7);
        root.left.right= new TreeNode(6);
        root.right.left= new TreeNode(5);
        root.right.right= new TreeNode(4);
        System.out.println(spiralView(root));
    }

    public static String spiralView(TreeNode root){
        StringBuilder SB = new StringBuilder();
        if(root==null)
            return null;
        Stack<TreeNode> rightToLeft = new Stack<>();
        Stack<TreeNode> leftToRight = new Stack<>();
        rightToLeft.push(root);

        while(!rightToLeft.isEmpty() || !leftToRight.isEmpty()){
            while(!rightToLeft.isEmpty()){
                TreeNode temp = rightToLeft.peek();
                SB.append(temp.value).append(" ");
                rightToLeft.pop();
                if(temp.right!=null)
                    leftToRight.push(temp.right);
                if(temp.left!=null)
                    leftToRight.push(temp.left);
            }

            while(!leftToRight.isEmpty()){
                TreeNode temp = leftToRight.peek();
                SB.append(temp.value).append(" ");
                leftToRight.pop();
                if(temp.left!=null)
                    rightToLeft.push(temp.left);
                if(temp.right!=null)
                    rightToLeft.push(temp.right);
            }
        }
        return SB.toString();
    }

}

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value){
        this.value=value;
    }

    @Override
    public String toString() {
        return "value=" + value ;
    }

}
