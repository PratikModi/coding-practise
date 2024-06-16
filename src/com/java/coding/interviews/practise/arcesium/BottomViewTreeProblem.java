package com.java.coding.interviews.practise.arcesium;

import java.util.*;

public class BottomViewTreeProblem {

    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left=new Node(2);
        tree.right = new Node(3);
        tree.left.left=new Node(4);
        tree.left.right=new Node(5);
        bottomView(tree);
    }

    public static void bottomView(Node root){
        if(root==null)
            return;
        int hd=0;
        root.horizontalDistance=0;
        Queue<Node> queue = new LinkedList<>();
        Map<Integer,Node> map = new LinkedHashMap<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            hd = node.horizontalDistance;
            map.put(hd,node);
            if(node.left!=null){
                node.left.horizontalDistance=hd-1;
                queue.add(node.left);
            }
            if(node.right!=null){
                node.right.horizontalDistance=hd+1;
                queue.add(node.right);
            }
        }
        map.values().stream().forEach(e-> System.out.println(e.value));
    }


}
class Node{
    Node left;
    Node right;
    int horizontalDistance;
    int value;

    public Node(int value) {
        this.value = value;
        this.left=null;
        this.right=null;
    }

    public static void print(Node node){
        if(node==null)
            return;
        System.out.print(node.value+",");
        print(node.left);
        print(node.right);
    }
}
