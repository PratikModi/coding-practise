package com.java.coding.interviews.practise;

/**
 * Created by Pratik1 on 03-03-2020.
 */
public class LCAProblem {

    public static TreeNode findLCA(TreeNode root, String node1, String node2){
        if(root==null)
            return null;
        if(root.value.equalsIgnoreCase(node1) || root.value.equalsIgnoreCase(node2))
            return root;
        TreeNode left = findLCA(root.left,node1,node2);
        TreeNode right = findLCA(root.right,node1,node2);
        /*if(left==null && right==null)
            return null;*/
        if(left!=null && right!=null)
            return root;
        return left==null?right:left;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode("1");
        node.left=new TreeNode("2");
        node.right=new TreeNode("3");
        System.out.println(findLCA(node,"2","3"));
    }
}
