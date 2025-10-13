package com.java.coding.interviews.practise.google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 490. The Maze
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
 * The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and
 * destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * Example 2:
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination
 * but you cannot stop there.
 *
 * 🧠 Intuition
 *
 * This is not a simple step-by-step BFS because:
 * You don’t move one cell at a time.
 * The ball keeps moving in a straight line until it hits a wall.
 * So, at each BFS/DFS step:
 * From current position (x, y)
 * Try rolling in each of 4 directions until:
 * You hit a wall or boundary.
 * Stop one step before the wall → (nx, ny)
 * If (nx, ny) not visited → add to queue / recurse.
 * We continue until we reach the destination.
 *
 * ⚙️ Complexity Analysis
 * Type	Complexity	Explanation
 * Time	O(m × n)	Each cell visited once
 * Space	O(m × n)	Visited matrix + queue
 */
public class TheMazeProblem {

    public static void main(String[] args) {
        int[][] maze = new int[][]{{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int[] start = {0,4};
        int[] end = {3,2};
        System.out.println(hasPath(maze,start,end));
        maze = new int[][]{{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        start= new int[]{0,4};
        end = new int[]{4,4};
        System.out.println(hasPath(maze,start,end));
    }

    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0],start[1]});
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]]=true;
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            // If we've reached the destination, return true
            if(current[0]==destination[0] && current[1]==destination[1]) return true;
            for(int[] dir : directions){
                int row = current[0];
                int col = current[1];
                // Roll until hitting wall or boundary
                while(row + dir[0]>=0 && row + dir[0]<m && col + dir[1]>=0 && col + dir[1]<n && maze[row + dir[0]][col + dir[1]]==0){
                    row+=dir[0];
                    col+=dir[1];
                }
                if(!visited[row][col]){
                    visited[row][col]=true;
                    queue.offer(new int[]{row,col});
                }
            }
        }
        return false;
    }

}
