package com.java.coding.interviews.practise.uber;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We have a directed n ary tree, where each node is an alphabet. Print the top 2 longest distinct path in the tree with no same adjacent alphabets.
 *
 *   - You can create a tree without writing complete code.
 *   - no cycle
 *   - it;s a directed n-ary tree
 *
 *   Time Complexity: O(n × h)
 * Where:
 *
 * 	•	n = number of nodes
 * 	•	h = height of the tree
 *
 * 	Space Complexity: O(n × h) (for storing all valid paths)
 * Plus O(h) for recursion and temp list
 */
public class Top2DistinctCharPathProblem {


static class TreeNAryNode {
    char value;
    List<TreeNAryNode> children;

    public TreeNAryNode(char value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    @Override
    public String toString() {
        return value + "-->" + children;
    }
}


static class PathResult{
    List<Character> path;
    int length;
    PathResult(List<Character> path){
        this.path = new ArrayList<>(path);
        this.length = path.size();
    }

    @Override
    public String toString() {
        return path.toString();
    }
}

static PriorityQueue<PathResult> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.length, a.length));

private static void dfs(TreeNAryNode node, List<Character> currentPath, Character prevChar){
    if(prevChar!=null && node.value==prevChar) return;
    currentPath.add(node.value);
    pq.add(new PathResult(currentPath));
    for(TreeNAryNode child : node.children){
        dfs(child, new ArrayList<>(currentPath), node.value);
    }
    currentPath.remove(currentPath.size() - 1);
}

    public static void main(String[] args) {
        TreeNAryNode[] nodes = new TreeNAryNode[8];
        nodes[0] = new TreeNAryNode('a');
        nodes[1] = new TreeNAryNode('b');
        nodes[2] = new TreeNAryNode( 'a');
        nodes[3] = new TreeNAryNode( 'c');
        nodes[4] = new TreeNAryNode( 'd');
        nodes[5] = new TreeNAryNode( 'd');
        nodes[6] = new TreeNAryNode( 'e');
        nodes[7] = new TreeNAryNode('f');

        // Step 2: Build the tree (directed)
        nodes[0].children.add(nodes[1]);
        nodes[0].children.add(nodes[2]);
        nodes[0].children.add(nodes[3]);
        nodes[1].children.add(nodes[4]);
        nodes[3].children.add(nodes[5]);
        nodes[5].children.add(nodes[6]);
        nodes[5].children.add(nodes[7]);

        dfs(nodes[0], new ArrayList<>(), null);
        System.out.println(pq);
        System.out.println(pq.poll());
        System.out.println(pq.poll());

    }

}
