package com.java.coding.interviews.practise.google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode:1293. Shortest Path in a Grid with Obstacles Elimination
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle).
 * You can move up, down, left, or right from and to an empty cell in one step.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1)
 * given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6.
 * Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 *
 * Why BFS (not DFS or Dijkstra)?
 *
 * Moves have equal cost (1) → BFS ensures shortest path naturally.
 *
 * Dijkstra would only be needed if edges had different weights.
 *
 * ⏱️ Complexity
 * Type	Complexity	Explanation
 * Time	O(m * n * k)	Each cell may be visited up to k+1 times.
 * Space	O(m * n * k)	For visited states and BFS queue.
 */
public class ShortestPathGridObstaclesProblem {

    public static void main(String[] args) {
        int[][] grid = {{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        int k=1;
        System.out.println(shortestPath(grid,k));
    }

    public static int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList();
        boolean[][][] visited = new boolean[m][n][k+1];
        visited[0][0][k]=true;
        queue.offer(new int[]{0,0,k,0});
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        if(m==1 && n==1)
            return 0;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int x = current[0],y=current[1],remainingK=current[2],steps=current[3];

            for(int[] dir : directions){
                int nx = x+dir[0];
                int ny = y+dir[1];
                if(nx<0 || nx>=m || ny<0 || ny>=n) continue;
                if(nx==m-1 && ny==n-1) return steps+1;
                int nextRemainingK = remainingK - grid[nx][ny];
                if(nextRemainingK>=0 && !visited[nx][ny][nextRemainingK]){
                    visited[nx][ny][nextRemainingK]=true;
                    queue.offer(new int[]{nx,ny,nextRemainingK,steps+1});
                }
            }
        }
        return -1;
    }

}
