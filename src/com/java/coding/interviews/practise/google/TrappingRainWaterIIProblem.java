package com.java.coding.interviews.practise.google;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 407. Trapping Rain Water II
 * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * Output: 4
 * Explanation: After the rain, water is trapped between the blocks.
 * We have two small ponds 1 and 3 units trapped.
 * The total volume of water trapped is 4.
 * Example 2:
 *
 *
 * Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * Output: 10
 *
 * 💡 Key Idea — Priority Queue (Min-Heap) + BFS
 *
 * We use a min-heap (priority queue) of boundary cells to simulate water “flooding in” from the edges.
 *
 * Algorithm summary:
 *
 * Push all border cells into a min-heap, mark them as visited.
 *
 * Maintain a variable maxHeight — the highest boundary we've seen so far.
 *
 * Pop the lowest cell from the heap:
 *
 * For each unvisited neighbor:
 *
 * If neighborHeight < maxHeight, water can be trapped here:
 * water += maxHeight - neighborHeight
 *
 * The effective boundary for this neighbor becomes
 * max(neighborHeight, maxHeight)
 *
 * Push this neighbor into the heap with that height.
 *
 * Continue until all cells are processed.
 *
 * ⏱️ Complexity Analysis
 * Type	Complexity
 * Time	O(m * n * log(m * n)) — every cell is pushed/popped once from heap
 * Space	O(m * n) for visited + heap storage
 */
public class TrappingRainWaterIIProblem {
    static class Cell{
        int row;
        int column;
        int height;
        Cell(int row, int column, int height){
            this.row=row;
            this.column=column;
            this.height=height;
        }
    }

    public static void main(String[] args) {
        int[][] heightMap = {
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        };
        System.out.println(trapRainWater(heightMap));
    }
    public static int trapRainWater(int[][] heightMap) {
            if(heightMap==null || heightMap.length==0 || heightMap[0].length==0)
                return 0;
            int m = heightMap.length;
            int n = heightMap[0].length;
            PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(c->c.height));
            boolean[][] visited = new boolean[m][n];
            int maxHeight = Integer.MIN_VALUE;
            // Step 1: Add all boundary cells into the min-heap
            for(int i=0;i<m;i++){
                pq.offer(new Cell(i,0,heightMap[i][0]));
                pq.offer(new Cell(i, n-1, heightMap[i][n-1]));
                visited[i][0]=true;
                visited[i][n-1]=true;
            }
            for(int j=0;j<n;j++){
                pq.offer(new Cell(0,j,heightMap[0][j]));
                pq.offer(new Cell(m-1,j,heightMap[m-1][j]));
                visited[0][j]=true;
                visited[m-1][j]=true;
            }
            int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
            int water=0;
            while(!pq.isEmpty()){
                Cell curr = pq.poll();
                maxHeight = Math.max(maxHeight,curr.height);
                int r = curr.row;
                int c = curr.column;
                for(int[] dir : directions){
                    int cr = r + dir[0];
                    int cc = c + dir[1];

                    if(cr<0 || cr>=m || cc<0 || cc>=n || visited[cr][cc]) continue;
                    visited[cr][cc]=true;
                    if(heightMap[cr][cc]<maxHeight) {
                        water += maxHeight - heightMap[cr][cc];
                    }
                    //The “effective boundary height” around this neighbor is determined by whichever is higher:
                    //The water height we have reached so far (maxHeight)
                   // The terrain height of the neighbor itself
                    pq.offer(new Cell(cr,cc,Math.max(maxHeight,heightMap[cr][cc])));
                }
            }
        return water;
    }
}
