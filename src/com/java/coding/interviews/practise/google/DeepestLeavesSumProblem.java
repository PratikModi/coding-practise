package com.java.coding.interviews.practise.google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1302. Deepest Leaves Sum
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 *
 *
 * Example 1:
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 *
 * Example 2:
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 19
 *
 * 🧠 Explanation
 * 	•	Traverse the tree level by level.
 * 	•	At each level:
 * 	•	Reset sum = 0
 * 	•	Add up all node values at that level.
 * 	•	After processing the final (deepest) level, sum holds the desired result.
 *
 * Why reset sum?
 * Because we only care about the last level — resetting ensures we keep only the latest level’s sum.
 *
 * ⏱️ Complexity
 * Type        Complexity          Reason
 * Time        O(N)                   Each node is visited once
 * Space      O(W)                   Queue stores nodes per level (worst case: max width of tree)
 *
 * Input:
 *         1
 *        / \
 *       2   3
 *      / \   \
 *     4   5   6
 *    /         \
 *   7           8
 *
 * Output: 15
 * Explanation: Deepest leaves are 7 and 8 → 7 + 8 = 15
 */
public class DeepestLeavesSumProblem {

    public static void main(String[] args) {
            TreeNode root = new TreeNode(1);
            root.left=new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);
            root.left.left.left = new TreeNode(7);
            root.right.right = new TreeNode(6);
            root.right.right.right = new TreeNode(8);
        System.out.println(deepestLeavesSum(root));
    }

    public static int deepestLeavesSum(TreeNode root) {
        if(root==null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            sum=0;
            for(int i=0;i<size;i++){
                TreeNode current = queue.poll();
                sum+=current.data;
                if(current.left!=null) queue.offer(current.left);
                if(current.right!=null) queue.offer(current.right);
            }
        }
        return sum;
    }

}
