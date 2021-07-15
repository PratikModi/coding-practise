package com.java.coding.interviews.practise.common;

/**
 * Created by Pratik1 on 16-02-2020.
 */
public class MaximumPathSum {

    private static int max_path_sum = Integer.MIN_VALUE;
    public static int findMaxSum(TreeNode node){
        pathSum(node);
        return max_path_sum;
    }

    private static int pathSum(TreeNode root){
        if(root==null)
            return 0;
        int left=pathSum(root.left);
        int right=pathSum(root.right);
        System.out.println(left + "--"+right);
        int max1 = Math.max(Math.max(left,right)+Integer.parseInt(root.value),Integer.parseInt(root.value));
        System.out.println("M1==>"+max1);
        int max2 = Math.max(max1, Integer.parseInt(root.value)+left+right);
        System.out.println("M2==>"+max2);
        max_path_sum=Math.max(max2,max_path_sum);
        System.out.println("MPS==>"+max_path_sum);
        return max1;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode("10");
        node.left=new TreeNode("-5");
        node.right=new TreeNode("20");
        //System.out.println(findMaxSum(node));
        TreeNode2 node2 = new TreeNode2(3);
        node2.left=new TreeNode2(0);
        pathSum(node);
        System.out.println(max_path_sum);
    }

}

class TreeNode{
    String value;
    TreeNode left;
    TreeNode right;

    TreeNode(String value){
        this.value = value;
        this.left=null;
        this.right=null;
    }

    public void print(TreeNode node){
        if(node==null)
            return;

        System.out.print(node.value+",");
        print(node.left);
        print(node.right);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value='" + value + '\'' +
                '}';
    }
}

class TreeNode2{
    int val;
    TreeNode2 left;
    TreeNode2 right;

    TreeNode2(int value){
        this.val = value;
        this.left=null;
        this.right=null;
    }

    public void print(TreeNode2 node){
        if(node==null) {
            //System.out.print("null,");
            return;
        }

        System.out.print(node.val+",");
        print(node.left);
        print(node.right);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value='" + val + '\'' +
                '}';
    }
}
