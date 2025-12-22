package com.java.coding.interviews.practise.point72;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Shortest Path in Maze (4-direction)
 * Problem
 *      -- 0 --> Empty Cell
 *      --1 --> blocked
 * Find the shortest path from (0,0) to (m-1,n-1).
 */
public class BinaryMatrixShortestPathProblem {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,1},{1,0}};
        System.out.println(shortestPath(grid));
        grid = new int[][]{{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(shortestPath(grid));
    }

    public static int shortestPath(int[][] matrix){
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        visited[0][0]=true;

        int steps = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] curr = queue.poll();
                int row = curr[0];
                int col = curr[1];
                if(row == m-1 && col ==n-1) return steps;
                for(int[] dir : directions){
                    int nr = row+dir[0], nc = col+dir[1];
                    if(nr>=0 && nr<m && nc>=0 && nc<n && matrix[nr][nc]==0 && !visited[nr][nc]){
                        visited[nr][nc]=true;
                        queue.offer(new int[]{nr,nc});
                    }
                }
            }
            steps++;
        }
        return -1;
    }

}
