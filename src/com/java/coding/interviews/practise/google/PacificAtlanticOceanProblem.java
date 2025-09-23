package com.java.coding.interviews.practise.google;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 417. Pacific Atlantic Water Flow
 * Medium
 * Topics
 * Companies
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * The island is partitioned into a grid of square cells.
 * You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west
 * if the neighboring cell's height is less than or equal to the current cell's height.
 * Water can flow from any cell adjacent to an ocean into the ocean.
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci)
 * to both the Pacific and Atlantic oceans.
 * Example 1:
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 * [0,4]: [0,4] -> Pacific Ocean
 *        [0,4] -> Atlantic Ocean
 * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 *        [1,3] -> [1,4] -> Atlantic Ocean
 * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
 *        [1,4] -> Atlantic Ocean
 * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
 *        [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
 * [3,0]: [3,0] -> Pacific Ocean
 *        [3,0] -> [4,0] -> Atlantic Ocean
 * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
 *        [3,1] -> [4,1] -> Atlantic Ocean
 * [4,0]: [4,0] -> Pacific Ocean
 *        [4,0] -> Atlantic Ocean
 * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 * Example 2:
 *
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 *
 *
 * Constraints:
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */

//Complexity
  //      Time complexity: O(n∗m)
   //     Space complexity: O(n∗m)
public class PacificAtlanticOceanProblem {

    private static final int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};

    public static void main(String[] args) {
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(pacificAtlantic(heights));
    }

    private static List<List<Integer>> pacificAtlantic(int[][] heights){
        List<List<Integer>> result = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] arctic = new boolean[m][n];
        for(int i=0;i<n;i++){
            dfs(heights,0,i,pacific); //First Row Pacific
            dfs(heights,m-1,i,arctic); // Last Row Arctic
        }
        for(int i=0;i<m;i++){
            dfs(heights,i,0,pacific); // First Column Pacific
            dfs(heights,i,n-1,arctic); // Last Column Arctic
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(pacific[i][j] && arctic[i][j]){
                    result.add(List.of(i,j));
                }
            }
        }
        return result;
    }

    private static void dfs(int[][] heights, int row, int column, boolean[][] ocean){
        if(ocean[row][column]) return;
        ocean[row][column]=true;
        for(int[] dir : direction){
                int cRow = row+dir[0];
                int cCol= column+dir[1];
                if(cRow<0 || cRow>=heights.length || cCol <0 || cCol>=heights[cRow].length) continue;
                if(heights[cRow][cCol]>=heights[row][column]){
                    dfs(heights,cRow,cCol,ocean);
                }
        }
    }

}
