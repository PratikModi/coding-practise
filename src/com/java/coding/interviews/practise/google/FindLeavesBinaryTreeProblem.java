package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.List;

/**
 * 366. Find Leaves of Binary Tree
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 *
 *
 * Example 1:
 *
 *        1
 *        / \
 *       2   3
 *      / \
 *     4   5
 * Input: root = [1,2,3,4,5]
 * Output: [[4,5,3],[2],[1]]
 * Explanation:
 * [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 *
 * 💡 Intuition
 *
 * Instead of actually deleting nodes layer by layer (which would be inefficient),
 * we can assign a “height” to each node, where:
 * height = distance from the node to its farthest leaf
 * Leaves have height 0.
 * Their parent will have height 1, and so on.
 * Then, we can group nodes by height — all nodes with the same height will be removed together in one round.
 *
 * 🧠 Step-by-Step Approach
 * 	1.	Use DFS (post-order) to compute height of each node.
 * 	2.	Add each node’s value to a list corresponding to its height.
 * 	3.	Return the list of lists.
 *
 *🧮 Time & Space Complexity
 * 	•	Time Complexity: O(N) — each node visited once.
 * 	•	Space Complexity: O(N) — for recursion + result list.
 */
public class FindLeavesBinaryTreeProblem {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(findLeaves(root));
    }

    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        getHeight(root,result);
        return result;
    }

    private static int getHeight(TreeNode node, List<List<Integer>> result){
        if(node==null) return -1; // base case: null nodes have height -1

        int left = getHeight(node.left,result);
        int right = getHeight(node.right,result);

        int currHeight = Math.max(left,right)+1; // current node's height

        // Ensure there's a list for this height
        if(result.size()==currHeight){
            result.add(new ArrayList<>());
        }
        result.get(currHeight).add(node.data);
        return currHeight;
    }

}
