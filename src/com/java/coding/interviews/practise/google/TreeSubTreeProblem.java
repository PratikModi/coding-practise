package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 17-05-2020.
 */

/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure
 * and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of
 * this node's descendants. The tree s could also be considered as a subtree of itself.
 *  Tree 2
           10
          /  \
         4    6
          \
           30


 Tree 1
           26
         /   \
        10    3
      /  \    \
     4   6     3
      \
      30
 */
public class TreeSubTreeProblem {

    public static void main(String[] args) {
        Node S = new Node(10);
        S.left=new Node(4);
        S.left.right=new Node(30);
        S.right=new Node(6);

        Node T = new Node(26);
        T.left=new Node(10);
        T.left.left=new Node(4);
        T.left.left.right=new Node(30);
        T.left.right=new Node(6);
        T.right=new Node(3);
        T.right.right=new Node(3);
        System.out.println(isSubtree(T,S));
    }

    public static boolean isIdentical(Node node1, Node node2){
        if(node1==null && node2==null)
            return true;
        if(node1==null || node2==null)
            return false;
        return (node1.data==node2.data
                && isIdentical(node1.left,node2.left)
                && isIdentical(node1.right,node2.right));
    }

    private static boolean isSubtree(Node T, Node S){
        if(S==null)
            return true;
        if(T==null)
            return false;
        if(isIdentical(T,S))
            return true;
        return isSubtree(T.left,S) || isSubtree(T.right,S);
    }

}

class Node{
    Node left;
    Node right;
    int data;
    Node(int data){
        this.data=data;
        left=null;
        right=null;
    }
}
