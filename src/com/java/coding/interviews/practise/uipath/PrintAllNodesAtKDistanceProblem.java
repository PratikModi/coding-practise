package com.java.coding.interviews.practise.uipath;

import java.util.*;

/**
 * Print all nodes at distance k from a given node
 * Difficulty Level : Hard
 * Last Updated : 20 Apr, 2021
 *
 * Given a binary tree, a target node in the binary tree, and an integer value k, print all the nodes that are at distance k from the given target node.
 * No parent pointers are available.
 *
 *
 * BinaryTree
 *
 *
 *
 * Consider the tree shown in diagram
 * Input: target = pointer to node with data 8.
 * root = pointer to node with data 20.
 * k = 2.
 * Output : 10 14 22
 * If target is 14 and k is 3, then output
 * should be “4 20”
 */
public class PrintAllNodesAtKDistanceProblem {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left= new TreeNode(5);
        root.right= new TreeNode(1);
        root.left.left= new TreeNode(6);
        root.left.right= new TreeNode(2);
        root.left.right.left= new TreeNode(7);
        root.left.right.right= new TreeNode(4);
        root.right.left= new TreeNode(0);
        root.right.right= new TreeNode(8);
        System.out.println(findKDistanceNode(root,root.left,2));
        System.out.println(findKDistanceNode2(root,root.left,2));
    }

    public static List<TreeNode> findKDistanceNode(TreeNode root, TreeNode target, int K){
        List<TreeNode> result = new ArrayList<>();
        findBelow(target,result,K);
        findAbove(root,target,K,result);
        return result;
    }

    public static void findBelow(TreeNode root, List<TreeNode> result, int K){
        if(root==null)
            return;
        if(K==0)
            result.add(root);
        findBelow(root.left,result,K-1);
        findBelow(root.right,result,K-1);
    }

    public static int findAbove(TreeNode root, TreeNode target, int K, List<TreeNode> result) {
        if (root != null) {
            if (root == target)
                return 0;
            int left = findAbove(root.left, target, K, result);
            //System.out.println("LEFT==>"+left);
            int right = findAbove(root.right, target, K, result);
            //System.out.println("Right==>"+right);
            //System.out.println("Root==>"+root);
            //System.out.println("K==>"+K);

            if (K > 1) {
                if (left >= 0) {
                    findBelow(root.right, result, K - 2 - left);
                }
                if (right >= 0) {
                    findBelow(root.left, result, K - 2 - right);
                }
            }
            if (left == -1 && right == -1)
                return -1;

            int distance = Math.max(left, right) + 1;

            if (distance == K)
                result.add(root);
            return distance;
        }
        return -1;
    }

    public static void findParent(Map<TreeNode,TreeNode> map, TreeNode root){
        if(root==null)
            return;
        if(root.left!=null){
            map.put(root.left,root);
        }
        if(root.right!=null){
            map.put(root.right,root);
        }
        findParent(map,root.left);
        findParent(map,root.right);
    }

    public static List<TreeNode> findKDistanceNode2(TreeNode root, TreeNode target, int K){
        List<TreeNode> result = new ArrayList<>();
        if(root==null)
            return result;
        Map<TreeNode,TreeNode> parentMap = new HashMap<>();
        findParent(parentMap,root);
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(target);
        Set<TreeNode> visited = new HashSet<>();
        while(!Q.isEmpty()){
            int size = Q.size();
            for(int i=0;i<size;i++){
                TreeNode node = Q.poll();
                visited.add(node);
                if(K==0){
                    result.add(node);
                }
                if(node.left!=null && !visited.contains(node.left)){
                    Q.add(node.left);
                }
                if(node.right!=null && !visited.contains(node.right)){
                    Q.add(node.right);
                }
                if(parentMap.containsKey(node) && !visited.contains(parentMap.get(node))){
                    Q.add(parentMap.get(node));
                }
            }
            K--;
            if(K<0) break;
        }
        return result;
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

    @Override
    public String toString() {
        return ""+value;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return value == treeNode.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }*/
}