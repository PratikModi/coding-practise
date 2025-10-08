package com.java.coding.interviews.practise.google;

/**
 * 1373. Maximum Sum BST in Binary Tree
 * ⚙️ Approach
 *
 * For each node:
 *
 * Recursively get info from left and right subtrees:
 *
 * Is subtree BST?
 *
 * Min, Max, Sum of that subtree
 *
 * A node forms a BST if:
 *
 * left.isBST && right.isBST &&
 * node.val > left.max && node.val < right.min
 *
 *
 * If it’s BST:
 *
 * Current sum = left.sum + right.sum + node.val
 *
 * Update global maxSum
 *
 * Return new min, max, sum, and isBST = true
 *
 * If not BST:
 *
 * Return isBST = false, and sum doesn’t matter.
 *
 * ⏱️ Complexity
 * Metric	Complexity	Reason
 * Time	O(N)	Visit each node once
 * Space	O(H)	Call stack (H = height of tree)
 */
public class MaximumSumBSTBinaryTreeProblem {
    static int maxSum=0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right= new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(4);
        root.right.right.right = new TreeNode(6);
        System.out.println(maxSumBST(root));
    }

    public static int maxSumBST(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private static TreeNodeInfo dfs(TreeNode node){
        if(node==null)
            return new TreeNodeInfo(true,Integer.MAX_VALUE,Integer.MIN_VALUE,0);

        TreeNodeInfo left = dfs(node.left);
        TreeNodeInfo right = dfs(node.right);

        if(left.isBST && right.isBST && node.data>left.max && node.data< right.min){
            int sum = left.sum + right.sum + node.data;
            maxSum = Math.max(maxSum,sum);
            //Why?
            //Because the parent node will need these to check BST validity later.
            return new TreeNodeInfo(true,Math.min(left.min,node.data),
                    Math.max(right.max,node.data),sum);
        }
        return new TreeNodeInfo(false,0,0,0);
    }

    private static
    class TreeNodeInfo{
        boolean isBST;
        int min;
        int max;
        int sum;

        TreeNodeInfo(boolean isBST, int min, int max, int sum){
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.sum = sum;
        }
    }

}
