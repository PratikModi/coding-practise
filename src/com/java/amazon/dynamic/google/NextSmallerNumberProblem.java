package com.java.amazon.dynamic.google;

import java.util.Arrays;

/**
 * Given an array of integers, return a new array where each element in the new array is the number of
 * smaller elements to the right of that element in the original input array. *
 * For example, given the array [3, 4, 9, 6, 1], return [1, 1, 2, 1, 0], since:
 *
 * There is 1 smaller element to the right of 3
 * There is 1 smaller element to the right of 4
 * There are 2 smaller elements to the right of 9
 * There is 1 smaller element to the right of 6
 * There are no smaller elements to the right of 1
 */

public class NextSmallerNumberProblem {
    public static void main(String[] args) {
        int[] A = new int[] {3,4,9,6,1};
        System.out.println(Arrays.toString(findNextSmallerElement(A)));
    }

    public static int[] findNextSmallerElement(int[] A){
        if(A==null || A.length==0)
            return A;
        int N = A.length;
        int[] output = new int[A.length];
        BSTNode root =null;
        for(int i=N-1;i>=0;i--){
            root=insertBST(root,output,i,0,A[i]);
        }
        return output;

    }

    private static BSTNode insertBST(BSTNode root, int[] output, int index, int sum, int value){
        if(root==null){
            output[index]=sum;
            return new BSTNode(value);
        }
        if(root.value<value){
            sum+=root.count+1;
            root.right=insertBST(root.right,output,index,sum,value);
        }else{
            root.count+=1;
            root.left=insertBST(root.left,output,index,sum,value);
        }
        return root;
    }
}

class BSTNode{
    BSTNode left;
    BSTNode right;
    int count;
    int  value;

    BSTNode(int value){
        this.value=value;
    }
}
