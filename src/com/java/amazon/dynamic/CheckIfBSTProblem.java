package com.java.amazon.dynamic;

/**
 * Created by Pratik1 on 05-03-2020.
 */
public class CheckIfBSTProblem {

    public static boolean isBST(TreeNode root, Integer min, Integer max){
        if(root==null)
            return true;
        if(Integer.parseInt(root.value) < min || Integer.parseInt(root.value) > max)
            return false;
        return isBST(root.left,min,Integer.parseInt(root.value)-1) &&
                isBST(root.right,Integer.parseInt(root.value)+1,max);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("2");
        root.left = new TreeNode("1");
        root.right = new TreeNode("3");
        System.out.println(isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE));
    }

}
