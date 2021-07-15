package com.java.coding.interviews.practise.common;

/**
 * Created by Pratik1 on 06-03-2020.
 */
public class LeftViewTreeProblem {
    static int max_level=0;
    public static void leftView(TreeNode node){
        helper(node,1);
    }

    private static void helper(TreeNode node,int currentLevel){
        //Print the first node of all level
        if(node==null)
            return;
        if(max_level< currentLevel){
            System.out.print(node.value+",");
            max_level = currentLevel;
        }
        helper(node.left,currentLevel+1);
        helper(node.right,currentLevel+1);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode("1");
        node.left = new TreeNode("2");
        node.right = new TreeNode("3");
        node.right.left = new TreeNode("4");
        node.right.right = new TreeNode("5");
        leftView(node);
    }
}
