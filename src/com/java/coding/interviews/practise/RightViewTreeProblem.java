package com.java.coding.interviews.practise;

/**
 * Created by Pratik1 on 06-03-2020.
 *
     *     10
         /    \
        2      3
      /  \    /  \
     7    8  12  15
    /
   14
 Output : 10 3 15 14
 */
public class RightViewTreeProblem {
    static int max_level = 0;
    public static void rightView(TreeNode node){
        helper(node,1);
    }

    private static void helper(TreeNode node,int current_level){
        if(node==null)
            return;
        if(max_level<current_level){
            System.out.print(node.value+",");
            max_level=current_level;
        }
        helper(node.right,current_level+1);
        helper(node.left,current_level+1);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode("10");
        node.left = new TreeNode("2");
        node.right = new TreeNode("3");
        node.left.left = new TreeNode("7");
        node.left.right = new TreeNode("8");
        node.left.left.left = new TreeNode("14");
        node.right.left = new TreeNode("13");
        node.right.right = new TreeNode("15");
        rightView(node);
    }
}
