package com.java.coding.interviews.practise.google;

import java.util.Stack;

/**
 * 1028. Recover a Tree From Preorder Traversal
 * We run a preorder depth-first search (DFS) on the root of a binary tree.
 * At each node in this traversal, we output D dashes (where D is the depth of this node),
 * then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.
 * The depth of the root node is 0.
 * If a node has only one child, that child is guaranteed to be the left child.
 * Given the output traversal of this traversal, recover the tree and return its root.
 *
 * Example 1:
 * Input: traversal = "1-2--3--4-5--6--7"
 * Output: [1,2,5,3,4,6,7]
 *
 * Example 2:
 * Input: traversal = "1-2--3---4-5--6---7"
 * Output: [1,2,5,3,null,6,null,4,null,7]
 *
 * Example 3:
 * Input: traversal = "1-401--349---90--88"
 * Output: [1,401,null,349,88,90
 *
 * 🧠 Intuition
 *
 * We traverse the string from left to right and:
 * 	1.	Determine node depth from number of dashes.
 * 	2.	Determine node value from digits.
 * 	3.	Maintain a stack that keeps track of the path (like DFS).
 * 	4.	When we see a node of depth d, we know its parent is at depth d-1.
 *
 * 🧮 Complexity
 * Time    O(N)   Each character processed once
 * Space  O(H)   Stack stores path (height ≤ N in worst case)
 *
 * "1-2--3--4-5--6--7"
 * Tokens (depth, value):
 * (0,1), (1,2), (2,3), (2,4), (1,5), (2,6), (2,7)
 *
 * Process:
 * 	1.	(0,1): create node 1.
 * Stack before pop: [] → pop none.
 * No parent (stack empty). Push 1.
 * Stack: [1]
 * 	2.	(1,2): create node 2.
 * Need stack.size()==1 (depth=1) → currently 1 so pop none. Parent = stack.peek() = 1.
 * parent.left == null → attach 2 as left child of 1. Push 2.
 * Stack: [1,2]
 * 	3.	(2,3): create node 3.
 * Need stack.size()==2 → currently 2. Parent = 2. Attach as left child. Push 3.
 * Stack: [1,2,3]
 * 	4.	(2,4): create node 4.
 * Need stack.size()==2 → currently 3. Pop once (pops 3). Now top is 2. Parent = 2. parent.left != null so attach as right child of 2. Push 4.
 * Stack: [1,2,4]
 * 	5.	(1,5): create node 5.
 * Need stack.size()==1 → currently 3. Pop until size==1 (pop 4 then 2). Now top is 1. Parent=1. parent.left exists so attach as right child of 1. Push 5.
 * Stack: [1,5]
 * 	6.	(2,6): create node 6.
 * Need stack.size()==2 → currently 2. Parent=5. Attach left child. Push 6.
 * Stack: [1,5,6]
 * 	7.	(2,7): create node 7.
 * Need stack.size()==2 → currently 3. Pop once (pop 6). Parent=5. attach as right child. Push 7.
 * Stack: [1,5,7]
 */
public class RecoverTreeFromPreOrderProblem {

    public static void main(String[] args) {
        String traversal = "1-2--3--4-5--6--7";
        System.out.println(recoverFromPreorder(traversal));
    }

    public static TreeNode recoverFromPreorder(String traversal) {
        int i=0;
        int n=traversal.length();
        Stack<TreeNode> stack = new Stack<>();
        while(i<n){
            int level=0;
            while(i<n && traversal.charAt(i)=='-'){
                level++;
                i++;
            }
            int value=0;
            while(i<n && Character.isDigit(traversal.charAt(i))){
                //Convert each digit into complete number
                value=value*10+(traversal.charAt(i)-'0');
                i++;
            }
            TreeNode node = new TreeNode(value);
            // Adjust stack to correct depth
            /*Why popping until stack.size() == depth is correct

            Imagine you have the path of nodes you have constructed so far.
            The stack stores nodes in preorder path order. When the traversal gives a node at depth d,
            it means we are placing a child of the node at depth d-1.
            Any nodes in the stack deeper than d-1 belong to subtrees that are already fully processed (their preorder was completed),
            so they are no longer on the path to attach the new node.
            Popping them restores the stack to exactly the parent where the new node must attach.*/
            while(stack.size()>level){
                stack.pop();
            }
            if(!stack.isEmpty()){
                TreeNode parent = stack.peek();
                if(parent.left==null) parent.left=node;
                else parent.right=node;
            }
            stack.push(node);
        }
        // Root node is the first element added (bottom of stack)
        while(stack.size()>1) stack.pop();
        return stack.peek();
    }

}
