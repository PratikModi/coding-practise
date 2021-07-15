package com.java.coding.interviews.practise.common;

/**
 * Unique Binary Search Trees II
 * Medium
 *
 * 3278
 *
 * 219
 *
 * Add to List
 *
 * Share
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n.
 * Return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 */

import java.util.LinkedList;
import java.util.List;

/**
 * ALGORITHM: -
 *
 *
 * Let's pick up number i out of the sequence 1 ..n and use it as the root of the current tree.
 * Then there are i - 1 elements available for the construction of the left subtree and n - i elements available for the right subtree.
 * As we already discussed that results in G(i - 1) different left subtrees and G(n - i) different right subtrees, where G is a Catalan number.
 *
 * BST
 *
 * Now let's repeat the step above for the sequence 1 ... i - 1 to construct all left subtrees, and then for the sequence i + 1 ... n to construct all right subtrees.
 *
 * This way we have a root i and two lists for the possible left and right subtrees.
 * The final step is to loop over both lists to link left and right subtrees to the root.
 */
public class UniqueBSTProblemII {

    public static void main(String[] args) {
        List<TreeNode2> result = generateTrees(3);
        result.stream().forEach(e->{
            e.print(e);
            System.out.println();
        });
    }

    public static List<TreeNode2> generateTrees(int n) {
        if(n==0)
            return new LinkedList<TreeNode2>();
        return generate(1,n);

    }

    private static List<TreeNode2> generate(int start, int end){
        List<TreeNode2> allTrees = new LinkedList<>();
        if(start>end){
            allTrees.add(null);
            return allTrees;
        }
        for(int i=start;i<=end;i++){
            List<TreeNode2> left = generate(start,i-1);
            List<TreeNode2> right = generate(i+1,end);
            for(TreeNode2 L : left){
                for(TreeNode2 R : right){
                    TreeNode2 current = new TreeNode2(i);
                    current.left=L;
                    current.right=R;
                    allTrees.add(current);
                }
            }
        }
        return allTrees;
    }

}
