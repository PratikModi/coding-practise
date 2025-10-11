package com.java.coding.interviews.practise.google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 542. 01 Matrix
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two cells sharing a common edge is 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 * Example 2:
 *
 *
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 * Optimal Approach ✅ — Multi-source BFS
 *
 * Instead of starting BFS from every 1,
 * we start BFS from every 0 simultaneously!
 *
 * Think of all 0s as sources of “waves” spreading outward —
 * each cell gets its distance when the wave first reaches it.
 *
 * 🧮 Complexity
 * Operation	Complexity
 * Time	O(m × n) — each cell processed once
 * Space	O(m × n) for queue + visited
 */
public class ZeroOneMatrixProblem {

    public static void main(String[] args) {
        int[][] mat = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        Arrays.stream(updateMatrix(mat)).forEach(e-> System.out.println(Arrays.toString(e)));
        mat = new int[][]{
                {0,0,0},{0,1,0},{1,1,1}
        };
        Arrays.stream(updateMatrix(mat)).forEach(e-> System.out.println(Arrays.toString(e)));
    }

    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dist = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]==0){
                    visited[i][j]=true;
                    queue.offer(new int[]{i,j});
                }
            }
        }
        int distance =0;
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                int[] poll = queue.poll();
                int row = poll[0];
                int col = poll[1];
                dist[row][col]=distance;
                for(int[] dir : directions){
                    int cr = row + dir[0];
                    int cc = col + dir[1];
                    if(cr>=0 && cr<m && cc>=0 && cc<n && !visited[cr][cc]){
                        visited[cr][cc]=true;
                        queue.offer(new int[]{cr,cc});
                    }
                }
            }
            distance++;
        }
        return dist;
    }

}
