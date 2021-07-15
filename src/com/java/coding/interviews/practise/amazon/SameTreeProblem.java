package com.java.coding.interviews.practise.amazon;

/**
 * 100. Same Tree
 *
 * Add to List
 *
 * Share
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 * Example 1:
 *
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * Example 2:
 *
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * Example 3:
 *
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 */
public class SameTreeProblem {

    public static void main(String[] args) {
        TreeNode P = new TreeNode(1);
        P.left=new TreeNode(2);
        P.right=new TreeNode(3);

        TreeNode Q = new TreeNode(1);
        Q.left=new TreeNode(2);
        Q.right=new TreeNode(3);

        System.out.println(isSameTree(P,Q));

    }

    public static boolean isSameTree(TreeNode P, TreeNode Q){
        if(P==null && Q==null)
            return true;
        else if(P==null)
            return false;
        else if(Q==null)
            return false;
        else if(P.value!=Q.value)
            return false;
        else
            return isSameTree(P.left,Q.left) && isSameTree(P.right,Q.right);
    }

}
