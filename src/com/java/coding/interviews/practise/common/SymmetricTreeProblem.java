package com.java.coding.interviews.practise.common;

/**
 * Created by Pratik1 on 05-03-2020.
 */
public class SymmetricTreeProblem {
    public static boolean isSymmetric(TreeNode node){
        if(node==null)
            return true;
        mirror(node.left);
        boolean result = isSame(node.left,node.right);
        mirror(node.left);
        return result;
    }

    public static void mirror(TreeNode node){
        if(node==null)
            return;
        TreeNode temp=node.left;
        node.left=node.right;
        node.right=temp;

        mirror(node.left);
        mirror(node.right);
    }

    public static boolean isSame(TreeNode node1,TreeNode node2){
        if(node1==null && node2==null)
            return true;
        if(node1!=null && node2!=null && node1.value == node2.value
                && isSame(node1.left,node2.left)
                && isSame(node1.right,node2.right))
            return true;
        return false;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode("1");
        node.left = new TreeNode("2");
        node.left.left = new TreeNode("3");

        System.out.println(isSymmetric(node));
        TreeNode node2 = new TreeNode("1");
        node2.left = new TreeNode("2");
        node2.right = new TreeNode("3");
        System.out.println(isSymmetric(node2));

    }
}
