package com.java.coding.interviews.practise.arcesium;

public class LeftViewTreeProblem {

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(7);
        root.left.right = new Node(8);
        root.right.left = new Node(12);
        root.right.right = new Node(15);
        root.left.left.left = new Node(14);
        leftView(root);
    }
    private static int max_level=0;
    public static void leftView(Node root){
        helper(root,1);
    }

    private static void helper(Node node,int current_level){
        if(node==null)
            return;
        if(max_level<current_level){
            System.out.print(node.value+" ");
            max_level=current_level;
        }
        helper(node.left,current_level+1);
        helper(node.right,current_level+1);
    }

}
