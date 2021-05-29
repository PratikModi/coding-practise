package com.java.coding.interviews.practise;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Pratik1 on 20-04-2020.
 */
public class TreeSerializationDeserialization {

    static String NULL_VALUE = "X";
    static String DELIMITER = ",";

    public static void main(String[] args) {
        TreeNode root = new TreeNode("1");
        root.left = new TreeNode("2");
        root.right = new TreeNode("3");
        root.print(deserialization(serialization(root)));
    }

    public static String serialization(TreeNode root){
        if(root==null)
            return NULL_VALUE+DELIMITER;
        String leftValue = serialization(root.left);
        String rightValue = serialization(root.right);
        return root.value+DELIMITER+leftValue+rightValue;
    }

    public static TreeNode deserialization(String data){
        System.out.println("Serialized Tree Data:-->"+data);
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
        return helper(queue);
    }

    private static TreeNode helper(Queue<String> dataQueue){
        String nodeValue = dataQueue.remove();
        if(nodeValue.equalsIgnoreCase(NULL_VALUE))
            return null;
        TreeNode root = new TreeNode(nodeValue);
        root.left = helper(dataQueue);
        root.right = helper(dataQueue);
        return root;
    }
}
