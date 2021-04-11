package com.java.amazon.dynamic;

/**
 * Created by Pratik1 on 08-03-2020.
 */
public class TreeNodeDistanceProblem {

    public static TreeNode getNode(String value){
        return new TreeNode(value);
    }

    public static TreeNode findLCA(TreeNode root, String node1, String node2){
        if(root==null)
            return null;
        if(root.value.equalsIgnoreCase(node1) || root.value.equalsIgnoreCase(node2))
            return root;
        TreeNode left = findLCA(root.left,node1,node2);
        TreeNode right = findLCA(root.right,node1,node2);
        if(left==null && right==null)
            return null;
        if(left!=null && right!=null)
            return root;
        return left==null?right:left;
    }

    public static int findLevel(TreeNode lca, String node, int level){
        if(lca==null)
            return -1;
        if(lca.value.equalsIgnoreCase(node))
            return level;
        int left = findLevel(lca.left,node,level+1);
        if(left==-1)
            return findLevel(lca.right,node,level+1);
        return left;
    }

    public static void main(String[] args) {
        TreeNode root = getNode("1");
        root.left = getNode("2");
        root.left.left = getNode("3");
        root.right = getNode("4");
        root.right.right = getNode("5");
        System.out.println(findLevel(findLCA(root,"3","5"),"3",0)+findLevel(findLCA(root,"3","5"),"5",0));
    }
}
