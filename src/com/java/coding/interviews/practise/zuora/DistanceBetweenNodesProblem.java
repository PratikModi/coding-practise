package com.java.coding.interviews.practise.zuora;

/**
 * 🧩 LeetCode: 1740. Find Distance in a Binary Tree
 * Given a binary tree and two nodes p and q, find the distance (number of edges) between them.
 *
 * 💡 Key Idea
 *
 * The distance between two nodes =
 * 👉 distance(root, p) + distance(root, q) - 2 * distance(root, LCA(p, q))
 *
 * Where LCA(p, q) = Lowest Common Ancestor of p and q.
 *
 * ⚙️ 3️⃣ Combined Complexity
 * Operation                            Complexity
 * Find LCA                              O(n)
 * Find Level (a)                       O(n)
 * Find Level (b)                       O(n)
 * Total (worst case)                 O(3n) = O(n)
 *
 * So overall, the time complexity remains linear, i.e.
 * ✅ O(n) — proportional to the number of nodes in the tree.
 *
 * 💾 Space Complexity
 * Aspect                       Complexity                 Reason
 * Recursion Stack             O(h)                      Depth of tree (h = height)
 * Extra Data Structures    O(0)                       No extra DS used
 */
public class DistanceBetweenNodesProblem {

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val=val;
            this.left=null;
            this.right=null;
        }
    }

    /**
     *         1
     *        / \
     *       2   3
     *      / \  / \
     *     4  5 6  7
     * @param args
     */
    public static void main(String[] args) {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);
            root.right.left = new TreeNode(6);
            root.right.right = new TreeNode(7);
        System.out.println(findDistance(root,4,7));
    }

    public static int findDistance(TreeNode root, int a, int b){
        TreeNode lca = findLCA(root,a,b);
        int d1 = findLevel(lca,a,0);
        int d2 = findLevel(lca,b,0);
        return d1+d2;
    }

    private static TreeNode findLCA(TreeNode root, int a, int b){
        if(root==null) return null;
        if(root.val==a || root.val==b) return root;
        TreeNode left = findLCA(root.left,a,b);
        TreeNode right = findLCA(root.right,a,b);
        if(left!=null && right!=null) return root;
        return left==null?right:left;
    }

    private static int findLevel(TreeNode root, int a, int level){
        if(root==null) return -1;
        if(root.val==a) return level;
        int left =  findLevel(root.left,a,level+1);
        if(left!=-1) return left;
        return findLevel(root.right,a,level+1);

    }



}
