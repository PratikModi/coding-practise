package com.java.coding.interviews.practise.coupang;

public class TreeDiameterProblem {
    /**
     * In this post a new simple O(n) method is discussed. Diameter of a tree can be calculated by only using the height function,
     * because the diameter of a tree is nothing but maximum value of (left_height + right_height + 1) for each node.
     * So we need to calculate this value (left_height + right_height + 1) for each node and update the result. Time complexity – O(n)
     */
    private static int diameter=0;
    public static void main(String[] args) {

    }

    public static int findDiameter(TreeNode root){
        if(root==null)
            return 0;
        findDepth(root);
        return diameter;
    }

    private static int findDepth(TreeNode root){
        if(root==null)
            return 0;
        int left = findDepth(root.left);
        int right = findDepth(root.right);
        diameter = Math.max(diameter,left+right);
        return Math.max(left,right)+1;
    }

}

class TreeNode{
    TreeNode left;
    TreeNode right;
    int value;

    public TreeNode(int value) {
        this.value = value;
        this.left=null;
        this.right=null;
    }
}
