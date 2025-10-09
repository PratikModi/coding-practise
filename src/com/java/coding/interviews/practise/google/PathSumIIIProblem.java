package com.java.coding.interviews.practise.google;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 437. Path Sum III
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the
 * sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards
 * (i.e., traveling only from parent nodes to child nodes).
 *
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * Example 2:
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 *
 * ⏱️ Complexity
 * Operation	Complexity
 * Time	O(N) — each node visited once
 * Space	O(H) for recursion + O(N) for prefix map
 */
public class PathSumIIIProblem {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right.right = new TreeNode(11);
        int targetSum = 8;
        System.out.println(pathSum(root,targetSum));
    }

    public static int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0L,1); // Base: one way to have sum = 0
        return dfs(root,0,targetSum,prefixMap);
    }

    private static int dfs(TreeNode node, long currSum, int targetSum, Map<Long, Integer> prefixMap){
        if(node==null) return 0;
        currSum+=node.data;
        int count = prefixMap.getOrDefault(currSum-targetSum,0);
        prefixMap.put(currSum, prefixMap.getOrDefault(currSum,0)+1);
        count+=dfs(node.left,currSum,targetSum,prefixMap);
        count+=dfs(node.right,currSum,targetSum,prefixMap);
        //Back Track
        //🧠 Why Backtrack?
        //Because when we go back up the recursion, we must remove the current prefix sum —
        //otherwise, paths from unrelated branches will overlap incorrectly.
        prefixMap.put(currSum,prefixMap.get(currSum)-1);
        return count;
    }

}
