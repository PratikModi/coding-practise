package com.java.coding.interviews.practise.google;

import java.util.PriorityQueue;

/**
 * LeetCode: 1102. Path With Maximum Minimum Value
 * Given an m x n integer matrix grid, return the maximum score of a path starting at (0, 0) and ending at (m - 1, n - 1)
 * moving in the 4 cardinal directions.
 *
 * The score of a path is the minimum value in that path.
 *
 * For example, the score of the path 8 → 4 → 5 → 9 is 4.
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[5,4,5],[1,2,6],[7,4,6]]
 * Output: 4
 * Explanation: The path with the maximum score is highlighted in yellow.
 * Example 2:
 *
 *
 * Input: grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
 * Output: 2
 *
 * Approach:
 * 	•	Use a max-heap (priority queue) to always expand the path through the currently largest valued cell.
 * 	•	Track the minimum value seen so far along the path.
 * 	•	When you reach the destination, that minimum is the answer.
 *
 * 	🔹 Complexity
 * 	•	Time: O(m*n log(m*n)) → each cell pushed/popped at most once from heap.
 * 	•	Space: O(m*n) for visited + heap.
 */
public class MaximumMinimumPathValueProblem {

    public static void main(String[] args) {
        int[][] grid = {{5,4,5},{1,2,6},{7,4,6}};
        System.out.println(maximumMinimumPath(grid));
    }

    public static int maximumMinimumPath(int[][] grid) {
            int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
            int m=grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[0]-a[0]);
            pq.offer(new int[]{grid[0][0],0,0});
            visited[0][0]=true;
            int maxScore=grid[0][0];
            while(!pq.isEmpty()){
                int[] current=pq.poll();
                int value = current[0],row=current[1],column=current[2];
                maxScore = Math.min(maxScore,value);
                if(row==m-1 && column==n-1){
                    return maxScore;
                }
                for(int[] dir : directions){
                    int cr = row+dir[0], cc = column+dir[1];
                    if(cr>=0 && cr<m && cc>=0 && cc<n && !visited[cr][cc]){
                        pq.offer(new int[]{grid[cr][cc],cr,cc});
                        visited[cr][cc]=true;
                    }
                }
            }

        return -1;
    }
}
