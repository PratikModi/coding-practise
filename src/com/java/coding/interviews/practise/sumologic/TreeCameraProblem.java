package com.java.coding.interviews.practise.sumologic;

/**
 * 968. Binary Tree Cameras
 * Hard
 *
 * 1853
 *
 * 28
 *
 * Add to List
 *
 * Share
 * You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Return the minimum number of cameras needed to monitor all nodes of the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as shown.
 * Example 2:
 *
 *
 * Input: root = [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 */
public class TreeCameraProblem {
    //1 = Covered by Child(Do not need Camera)
    //0 = Itself a camera(Has Camera)
    //-1 = Covered by None(Need Camera)
    private static int camera=0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        root.left.left.left.right = new TreeNode(0);
        System.out.println(minCameraCover(root));
    }

    public static int minCameraCover(TreeNode root) {
        return dfs(root)==-1?camera++:camera;
    }

    private static int dfs(TreeNode node){
        if(node==null)
            return 1;
        int left = dfs(node.left);
        int right = dfs(node.right);

        if(left==-1 || right==-1){
            camera++;
            return 0;   //Root becoming the camera since any or both of his child needs a camera
        }else if(left==0 || right==0){
            return 1; //I'm covered by my child and parent you see for yourself
        }
                                     //left == 1 && right == 1
        return -1;                  //Asking parent to cover him, since both of his child are covered by their child

    }

}

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}