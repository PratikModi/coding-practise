package com.java.coding.interviews.practise;

/**
 * Created by Pratik1 on 11-04-2020.
 */
public class UnivalTreeProblem {

    public static int count=0;

    public static void main(String[] args) {
        Tree root = new Tree(1);
        root.left = new Tree(4);
        root.right = new Tree(5);
        root.left.left = new Tree(4);
        root.left.right = new Tree(4);
        root.right.right = new Tree(5);
        countUnivalSubTree(root);
        System.out.println(count);
    }

    private static boolean countUnivalSubTree(Tree root){
        if(root==null)
            return true;
        boolean left = countUnivalSubTree(root.left);
        boolean right = countUnivalSubTree(root.right);
        if(!left || !right)
            return false;
        if(root.left!=null && root.value!=root.left.value)
            return false;
        if(root.right!=null && root.value!=root.right.value)
            return false;
        count++;
        return true;
    }



}
