package com.java.coding.interviews.practise.uipath;

/**
 * BST Sum pair without using Hashing
 */

import java.util.*;

public class BSTSumPairProblem {
    private static int sum=0;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(40);
        root.left = new TreeNode(10);
        root.right = new TreeNode(50);
        root.right.left = new TreeNode(45);
        root.right.right = new TreeNode(55);
        System.out.println(findPair(root,60));
        System.out.println(findPairUsingHashing(root,60));
    }

    public static List<Integer> findPair(TreeNode root, int K){
        List<Integer> result = new ArrayList<>();
        if(root==null)
            return result;
        Stack<TreeNode> S1 = new Stack<>();
        Stack<TreeNode> S2 = new Stack<>();

        TreeNode T = root;
        while(T!=null){
            S1.add(T);
            T=T.left;
        }
        T=root;
        while(T!=null){
            S2.add(T);
            T=T.right;
        }
        while(S1.peek()!=S2.peek()){
            int A = S1.peek().value;
            int B = S2.peek().value;
            if((A+B) < K){
                T= S1.peek().right;
                S1.pop();
                while(T!=null){
                    S1.add(T);
                    T=T.left;
                }
            }else if((A+B)>K){
                T=S2.peek().left;
                S2.pop();
                while(T!=null){
                    S2.add(T);
                    T=T.right;
                }
            }else{
                result.add(A);
                result.add(B);
                return result;
            }
        }
        return result;
    }

    public static List<Integer> findPairUsingHashing(TreeNode root, int K){
        List<Integer> result = new ArrayList<>();
        if(root==null)
            return result;
        recursion(result,root,K,new HashSet<Integer>());
        return result;
    }

    private static void recursion(List<Integer> result, TreeNode root, int K, Set<Integer> set){
        if(root==null)
            return;
        if(set.contains(K-root.value)){
            result.add(K- root.value);
            result.add(root.value);
            return;
        }
        set.add(root.value);
        recursion(result,root.left,K,set);
        recursion(result,root.right,K,set);
    }


}
