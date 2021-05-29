package com.java.coding.interviews.practise.adobe;

import java.util.*;

/**
 Burn the binary tree starting from the target node
 Difficulty Level : Hard
 Last Updated : 01 Feb, 2021
 Given a binary tree and target node. By giving the fire to the target node and fire starts to spread in a complete tree. The task is to print the sequence of the burning nodes of a binary tree.
 Rules for burning the nodes :

 Fire will spread constantly to the connected nodes only.
 Every node takes the same time to burn.
 A node burns only once.
 Examples:

 Input :
                    12
                  /   \
                 13   10
                 /     \
                14       15
              /   \     /  \
             21   24   22   23
 target node = 14

 Output :
 14
 21, 24, 10
 15, 12
 22, 23, 13

 Explanation : First node 14 burns then it gives fire to it's
 neighbors(21, 24, 10) and so on. This process continues until
 the whole tree burns.


 Input :
                12
             /     \
            19     82
           /     /   \
         41     15   95
           \    /   /  \
            2  21  7   16
 target node = 41

 Output :
 41
 2, 19
 12
 82
 15, 95
 21, 7, 16

 */
public class BurnBinaryTreeProblem {
    public static void main(String[] args) {
        Node root = new Node(12);
        root.left=new Node(13);
        root.right=new Node(10);
        root.left.left=new Node(14);
        root.left.left.left=new Node(21);
        root.left.left.right=new Node(24);
        root.right.right=new Node(15);
        root.right.right.left=new Node(22);
        root.right.right.right=new Node(23);
        List<List<Integer>> result = getTreeBurningSequence(root,14);
        System.out.println(result);
    }

    public static List<List<Integer>> getTreeBurningSequence(Node root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null)
            return result;
        Map<Integer,Node> parentMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        traverseAndMap(root,parentMap);
        //System.out.println(parentMap);
        Node targetNode;
        if(!parentMap.containsKey(target)){
            return result;
        }else{
            Node parent = parentMap.get(target);
            if(parent.left!=null && parent.left.value==target)
                targetNode=parent.left;
            else
                targetNode=parent.right;
        }
        Queue<Node> Q = new LinkedList<>();
        Q.add(targetNode);
        visited.add(target);
        while(!Q.isEmpty()) {
            //System.out.println("IN WHILE");
            int size = Q.size();
            List<Integer> pair = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node temp = Q.poll();
                pair.add(temp.value);
                if (temp.left != null && !visited.contains(temp.left.value)) {
                    visited.add(temp.left.value);
                    Q.add(temp.left);
                }
                if(temp.right!=null && !visited.contains(temp.right.value)){
                    visited.add(temp.right.value);
                    Q.add(temp.right);
                }
                if(parentMap.containsKey(temp.value) && !visited.contains(parentMap.get(temp.value).value)){
                    visited.add(parentMap.get(temp.value).value);
                    Q.add(parentMap.get(temp.value));
                }
            }
            result.add(pair);
        }

        return result;
    }

    private static void traverseAndMap(Node root, Map<Integer,Node> M){
        if(root!=null){
            if(root.left!=null)
                M.put(root.left.value,root);
            if(root.right!=null)
                M.put(root.right.value,root);
            traverseAndMap(root.left,M);
            traverseAndMap(root.right,M);
        }
    }


}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left=null;
        this.right=null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
