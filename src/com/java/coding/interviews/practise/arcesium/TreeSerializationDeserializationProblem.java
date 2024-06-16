package com.java.coding.interviews.practise.arcesium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TreeSerializationDeserializationProblem {

    private static String NULL = "X";
    private static String DELIMITER = ",";

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.print(deserialize(serialize(root)));
    }

    public static String serialize(Node root){
        if(root==null)
            return NULL+DELIMITER;
        String left = serialize(root.left);
        String right = serialize(root.right);
        return root.value+DELIMITER+left+right;
    }

    public static Node deserialize(String data){
        System.out.println(data);
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
        return helper(queue);
    }

    private static Node helper(Queue<String> queue){
        String data = queue.remove();
        if(data.equals(NULL))
            return null;
        Node node = new Node(Integer.valueOf(data));
        node.left = helper(queue);
        node.right = helper(queue);
        return node;
    }

}
