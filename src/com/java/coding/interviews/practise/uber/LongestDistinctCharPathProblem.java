package com.java.coding.interviews.practise.uber;


/**
 * Given a tree with nodes having characters, find the length of the longest path such that all characters in the path are distinct.
 *
 * Questions:
 * 1. Is the input given as an edge list, or in some other format (like adjacency map)?
 * 2. Are all characters guaranteed to be lowercase letters ('a' to 'z')?
 *1️⃣ Clarify the Problem
 *
 * “Just to confirm: we’re given an undirected, acyclic graph (a tree), where each node has a lowercase character, and we need to find the longest path such that no two adjacent nodes in that path have the same character. Is that right?”
 *
 * ✅ Why: Shows you’re confirming constraints and thinking precisely.
 *
 * ⸻
 *
 * 2️⃣ State High-Level Idea
 *
 * “Since this is a tree, I know there’s exactly one path between any two nodes, and no cycles. So, I’ll do a depth-first search (DFS) to explore paths, and I’ll track the longest valid one under the character constraint.”
 * “I’ll treat this like a diameter-of-tree problem, but I’ll add a check to skip branches where adjacent characters are the same.”
 *
 * ✅ Why: Quickly shows that you understand the core algorithmic principle involved.
 *
 * ⸻
 *
 * 3️⃣ Explain Recursion Logic (DFS)
 *
 * “During DFS, for each node, I compute the longest valid branch that starts from each child — only if the child character is different from the current node’s.”
 *
 * “I’ll track the top two longest valid branches (max1, max2) among all children, and use them to potentially form a longer path through the current node: max1 + max2 + 1.”
 * “I update a global max length if this is the longest I’ve seen so far.”
 *
 * “Finally, I return max1 + 1 upward to allow the parent to consider this path as an extension.”
 *
 *
 * 🎯 What This Means
 *
 * This is a strategic approach for finding the longest path in a tree, where the path:
 * 	•	Goes through the current node, connecting two subtrees
 * 	•	Must only include nodes with different adjacent characters
 *
 * We are effectively simulating a tree diameter algorithm, with filtering logic for invalid branches.
 *
 * ⸻
 *
 * 🔍 In Detail
 *
 * 🌳 Tree Analogy
 *
 * Imagine you’re at a node A, and it has multiple children:
 *
 *        A
 *      /  |  \
 *     B  C  D
 *
 *     Let’s say:
 * 	•	You recursively computed the longest valid path starting from:
 * 	•	B = 3 nodes
 * 	•	C = 1 node
 * 	•	D = 2 nodes
 * 	•	And all child characters are different from A
 *
 * Then:
 * 	•	max1 = 3 (longest child branch: from B)
 * 	•	max2 = 2 (second longest: from D)
 *
 * Now:
 *
 * 💡 Why Two Longest?
 *
 * If the longest overall path passes through A, then it will:
 * 	•	Come from one child, go up to A, and then
 * 	•	Go down to another child
 *
 *
 * 🧠 Summary
 * 	Term
 * Meaning
 * max1
 * Longest valid path from children (char differs from parent)
 * max2
 * Second longest valid path
 * max1 + max2 + 1
 * Longest path that goes through the current node
 * Why two?
 * To check all possible combinations of valid paths from different subtrees
 * When used?
 * To update global maxPath
 * Returned?
 * Only max1 + 1 is returned to parent — only one path can be extended upward
 *
 */
/*
5️⃣ Time and Space Complexity

“This runs in linear time O(N), since we do a DFS visiting each node once. Space is O(N) for the recursion stack and the adjacency list.”
 */

import java.util.*;

public class LongestDistinctCharPathProblem {
    static int maxPath = 0;

    public static void main(String[] args) {
        int n = 5;
        char[] values = {'a', 'b', 'a', 'c', 'd'}; // node values
        int[][] edges = {
                {0, 1},
                {0, 2},
                {2, 3},
                {2, 4}
        };
        System.out.println(findLongestPath(values, edges)); // Expected: 3
        values = new char[] { 'a','b','b','b','c','d','e','g' };
        edges = new int[][] {
                {0, 1},
                {0, 2},
                {1, 4},
                {2, 3},
                {2, 5},
                {2, 6},
                {5, 7}
        };
        System.out.println(findLongestPath(values, edges));
    }

        public static int findLongestPath(char[] nodes, int[][] edges){

        Map<Integer,List<Integer>> graph = new HashMap<>();
            for(int[] edge : edges){
                graph.putIfAbsent(edge[0], new ArrayList<>());
                graph.get(edge[0]).add(edge[1]);
                graph.putIfAbsent(edge[1], new ArrayList<>());
                graph.get(edge[1]).add(edge[0]);
            }
            dfs(0,-1, graph, nodes);
            return maxPath;
        }

        public static int dfs(int node, int parent, Map<Integer,List<Integer>> graph,char[] nodes){
            int max1=0;
            int max2=0;
          if(!graph.containsKey(node)) return 1;
          for(int neighbour : graph.get(node)){
              if(neighbour==parent) continue;
              int pathLength = dfs(neighbour, node, graph, nodes);
              if(nodes[neighbour] != nodes[node]) {
                  if(pathLength > max1) {
                      max2 = max1;
                      max1 = pathLength;
                  } else if(pathLength > max2) {
                      max2 = pathLength;
                  }
              }
          }
          maxPath = Math.max(maxPath, max1 + max2 + 1);
          return max1+1;
        }


        public static int longestPath(int[] parent, String nodes){
            Map<Integer,List<Integer>> graph = new HashMap<>();
            for(int i=1;i<parent.length;i++){
                int j = parent[i];
                graph.putIfAbsent(j, new ArrayList<>());
                graph.get(j).add(i);
            }
            dfs(0,graph,nodes);
            return maxPath;
         }

         private static int dfs(int node, Map<Integer,List<Integer>> graph,String nodes){
                int max1 = 0;
                int max2 = 0;
                if(!graph.containsKey(node)) return 1;
                for(int neighbour : graph.get(node)){
                    int pathLength = dfs(neighbour,graph,nodes);
                    if(nodes.charAt(neighbour)!=nodes.charAt(node)){
                        if(pathLength > max1){
                            max2 = max1;
                            max1 = pathLength;
                        }else if(pathLength > max2){
                            max2 = pathLength;
                        }
                    }
                }
            maxPath = Math.max(maxPath, max1 + max2 + 1);
                return max1+1;
         }





}
