package com.java.coding.interviews.practise.common;

/**
 * Created by Pratik1 on 04-03-2020.
 */
public class BSTDeserializePostOrder {

    public static TreeNode deserializeOptimized(int[] postOrder, int[] currentIndex, int min, int max){
        if(currentIndex[0]<0)
            return null;
        TreeNode root = null;
        if(postOrder[currentIndex[0]]>min && postOrder[currentIndex[0]]<max){
            root = new TreeNode(String.valueOf(postOrder[currentIndex[0]--]));
            //it's post order so it's L-R-N
            root.right = deserializeOptimized(postOrder,currentIndex,Integer.parseInt(root.value),max);
            root.left = deserializeOptimized(postOrder,currentIndex,min,Integer.parseInt(root.value));
        }
        return root;
    }


    private static int findDivision(int[] postOrder, int value, int low, int high){
        int i = high;
        for(;i>=low;i--){
            if(value > postOrder[i])
                break;
        }
        return i;
    }

    public static TreeNode deserialize(int[] postOrder, int low, int high){
        if(low>high){
            return null;
        }
        TreeNode root = new TreeNode(String.valueOf(postOrder[high]));
        int division = findDivision(postOrder,Integer.parseInt(root.value),low,high-1);
        root.left = deserialize(postOrder,low,division);
        root.right = deserialize(postOrder,division+1,high-1);
        return root;

    }

    public static void main(String[] args) {
        int[] postOrder = {1,3,2};
        int[] currentIndex = {postOrder.length-1};
        TreeNode root = deserializeOptimized(postOrder,currentIndex,Integer.MIN_VALUE,Integer.MAX_VALUE);
        root.print(root);
        System.out.println();
        TreeNode root_2 = deserialize(postOrder,0,postOrder.length-1);
        root_2.print(root_2);
    }
}
