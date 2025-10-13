package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 305. Number of Islands II
 * You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land.
 * Initially, all the cells of grid are water cells (i.e., all the cells are 0's).
 *
 * We may perform an add land operation which turns the water at position into a land.
 * You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.
 *
 * Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 * Initially, the 2d grid is filled with water.
 * - Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
 * - Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
 * - Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
 * - Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
 * Example 2:
 *
 * Input: m = 1, n = 1, positions = [[0,0]]
 * Output: [1]
 *
 * So we can’t re-run DFS/BFS each time (that’d be O(m×n×k) = too slow).
 * Instead, we use a Union-Find (Disjoint Set Union) structure to efficiently merge connected land cells and count islands dynamically.
 * ✅ Approach (Union-Find / DSU)
 * 💡 Core Idea
 *
 * Treat each cell (r, c) as a node (index = r * n + c)
 * Initially all cells are water (-1) (not in DSU)
 * When a land cell is added:
 * Set its parent to itself → new island (+1)
 * Check its 4 neighbors (up, down, left, right)
 * If a neighbor is also land → union them (if they’re in different sets → one island merges, count -1)
 * Keep track of the running island count.
 *
 * ⚙️ Complexity Analysis
 * Type	Complexity	Explanation
 * Time	O(k × α(n×m))	α is inverse Ackermann (practically constant)
 * Space	O(m×n)	For parent & rank arrays
 */
public class NumberIslandIIProblem {

    public static void main(String[] args) {
        int m =3, n =3;
        int[][] positions = {{0,0},{0,1},{1,2},{2,1}};
        System.out.println(numIslands2(m,n,positions));
    }

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if(m<=0 || n<=0) return result;
        int[] parent = new int[m*n];
        int[] rank = new int[m*n];
        Arrays.fill(parent,-1); //Fill with water initially
        int count=0;
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int[] position : positions){
            int r = position[0], c = position[1];
            int idx = r*n + c;
            if(parent[idx]!=-1){ // already land, skip
                result.add(count);
                continue;
            }
            //Add land
            parent[idx]=idx;
            rank[idx]=0;
            count++;

            for(int[] dir : directions){
                int cr = r + dir[0];
                int cc = c + dir[1];
                int nIdx = cr*n + cc;
                if(cr>=0 && cr<m && cc >=0 && cc<n && parent[nIdx]!=-1) {
                    System.out.println("nIdx==>" + nIdx);
                    int rootA = find(parent, idx);
                    int rootB = find(parent, nIdx);
                    if (rootA != rootB) {
                        if (rank[rootA] > rank[rootB]) {
                            parent[rootB] = rootA;
                        } else if (rank[rootA] < rank[rootB]) {
                            parent[rootA] = rootB;
                        } else {
                            parent[rootB] = rootA;
                            rank[rootA]++;
                        }
                        count--; // two islands merged into one
                    }
                }
            }
            result.add(count);
        }
        return result;
    }

    private static int find(int[] parent, int i){

        if (i == -1 || parent[i] == -1) return -1; // invalid or water
        if(parent[i]!=i){
            parent[i]=find(parent,parent[i]);
        }
        return parent[i];
    }
}
