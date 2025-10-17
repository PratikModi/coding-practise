package com.java.coding.interviews.practise.google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091. Shortest Path in Binary Matrix
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 * 🧠 Intuition
 *
 * This is a shortest path in an unweighted grid —
 * so the most natural approach is BFS (Breadth-First Search).
 * 	•	Each BFS level = one step in the path.
 * 	•	Explore 8 directions from each cell.
 * 	•	Track visited cells to avoid reprocessing.
 *
 * ⏱️ Complexity
 * Type               Complexity              Reason
 * Time               O(N²)                     Each cell processed at most once
 * Space             O(N²)                     Queue + grid modifications
 *
 * Example 1:
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 *
 * Example 2:
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 *
 * Example 3:
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 */
public class ShortestPathBinaryMatrixProblem {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,1},{1,0}};
        System.out.println(shortestPathBinaryMatrix(grid));
        grid = new int[][]{{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(shortestPathBinaryMatrix(grid));
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1; // blocked start/end
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        grid[0][0] = 1; // mark visited (store path length)
        int[][] directions = {
                {-1,0},{1,0},{0,-1},{0,1},
                {-1,-1},{1,1},{-1,1},{1,-1}
        };
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            int distance=grid[row][col];
            if(row==n-1 && col==n-1) return distance;
            for(int[] dir : directions){
                int nr = row+dir[0];
                int nc = col+dir[1];
                if(nr>=0 && nr<n && nc>=0 && nc<n && grid[nr][nc]==0){
                    queue.offer(new int[]{nr,nc});
                    grid[nr][nc]=distance+1;// mark visited + store distance
                }
            }
        }
        return -1;

    }
}
