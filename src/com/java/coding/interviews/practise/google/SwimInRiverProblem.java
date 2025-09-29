package com.java.coding.interviews.practise.google;

import java.util.PriorityQueue;

/**
 * LeetCode: 778. Swim in Rising Water
 * Hard
 * Topics
 * conpanies icon
 * Companies
 * Hint
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.
 *
 * You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * Example 2:
 *
 *
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] < n2
 * Each value grid[i][j] is unique.
 *
 * 🔹 Key Observations
 * 	•	Grid size = n x n, so total cells = n².
 * 	•	Each cell is pushed into the priority queue (min-heap) at most once.
 * 	•	Each push/pop on a heap costs O(log(size)).
 *
 * Since heap size ≤ n², each operation is O(log(n²)) = O(2·log n) = O(log n).
 *
 * 🔹 Space Complexity
 * 	•	visited[n][n] = O(n²).
 * 	•	PQ holds at most n² cells = O(n²).
 *
 * So total space = O(n²).
 *
 * ✅ Final Answer:
 * 	•	Time: O(n² log n)
 * 	•	Space: O(n²)
 */
public class SwimInRiverProblem {

    public static void main(String[] args) {
        int[][] grid = {{0,2},{1,3}};
        System.out.println(swimInWater(grid));
    }

    public static int swimInWater(int[][] grid){
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        pq.offer(new int[]{grid[0][0],0,0});
        visited[0][0]=true;
        int ans=0;
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            int height=current[0];
            int row = current[1];
            int col = current[2];
            //You can only move when the water level t is at least as high as the maximum elevation along your path.
            // Update max elevation seen so far
            ans = Math.max(ans,height);
            if(row==n-1 && col==n-1){
                return ans;
            }
            for(int[] dir : directions){
                int cr = row+dir[0];
                int cc = col+dir[1];
                if(cr>=0 && cr<n && cc>=0 && cc<n && !visited[cr][cc]){
                    pq.offer(new int[]{grid[cr][cc],cr,cc});
                }
            }
        }
        return -1;
    }

}
