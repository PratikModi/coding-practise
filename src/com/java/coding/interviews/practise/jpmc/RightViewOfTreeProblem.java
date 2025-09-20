package com.java.coding.interviews.practise.jpmc;


/**
 * Created by Pratik1 on 06-03-2020.
 *
 *      10
      /   \
     2     3
   /  \   /  \
  7    8 12  15
 /
14
 Output : 10 3 15 14
 */

public class RightViewOfTreeProblem {

    private static int max_level=0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(7);
        root.left.right=new TreeNode(8);
        root.right.left=new TreeNode(12);
        root.right.right=new TreeNode(15);
        root.left.left.left= new TreeNode(14);
        rightView(root);
    }

    private static void rightView(TreeNode root){
        helper(root,1);

    }


    private static void helper(TreeNode node,int current_level){
        if(node==null)
            return;
        if(max_level<current_level){
            System.out.print(node.value+" ");
            max_level=current_level;
        }
        helper(node.right,current_level+1);
        helper(node.left,current_level+1);
    }

}

class TreeNode{
    TreeNode left;
    TreeNode right;
    int value;

    TreeNode(int value){
        this.value=value;
        this.left=null;
        this.right=null;
    }
}
