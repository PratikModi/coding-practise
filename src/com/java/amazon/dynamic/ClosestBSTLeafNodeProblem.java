package com.java.amazon.dynamic;

/**Find the closest leaf in a Binary Tree
 Last Updated: 13-02-2020
 Given a Binary Tree and a key ‘k’, find distance of the closest leaf from ‘k’.
 Examples:

                A
             /    \
           B       C
                 /   \
                E     F
              /       \
             G         H
           / \       /
          I   J     K

 Closest leaf to 'H' is 'K', so distance is 1 for 'H'
 Closest leaf to 'C' is 'B', so distance is 2 for 'C'
 Closest leaf to 'E' is either 'I' or 'J', so distance is 2 for 'E'
 Closest leaf to 'B' is 'B' itself, so distance is 0 for 'B'
 *
 */

public class ClosestBSTLeafNodeProblem {
    public static void main(String[] args) {
        TreeNode root =create("A");
        root.left=create("B");
        root.right=create("C");
        root.right.left=create("E");
        root.right.left.left=create("G");
        root.right.left.left.left=create("I");
        root.right.left.left.right=create("J");
        root.right.right=create("F");
        root.right.right.right=create("H");
        root.right.right.right.left=create("K");
        System.out.println(closestLeafNode(root,"H"));
        //System.out.println(closestLeafNode(root,"C"));
        //System.out.println(closestLeafNode(root,"E"));
        //System.out.println(closestLeafNode(root,"B"));
    }

    private static TreeNode create(String V){
        return new TreeNode(V);
    }

    public static int closestLeafNode(TreeNode root, String S){
        TreeNode[] ancestors = new TreeNode[100];
        return findClosestLeafNode(ancestors,S,0,root);
    }

    private static int findNodeMinDepth(TreeNode node){
        if(node==null)
            return Integer.MAX_VALUE;
        if(node.left==null && node.right==null){
            return 0;
        }
        return 1+Math.min(findNodeMinDepth(node.left),findNodeMinDepth(node.right));
    }

    private static int findClosestLeafNode(TreeNode[] ancestors, String S, int index, TreeNode node){
        if(node==null)
            return Integer.MAX_VALUE;
        if(node.value.equals(S)){
            int closest = findNodeMinDepth(node);
            for(int i=index-1;i>=0;i--){
                closest=Math.min(closest,index-i+findNodeMinDepth(ancestors[i]));
            }
            return closest;
        }else{
            ancestors[index]=node;
            return 1+Math.min(findClosestLeafNode(ancestors,S,index+1,node.left),findClosestLeafNode(ancestors,S,index+1,node.right));
        }
    }


}
