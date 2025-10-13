package com.java.coding.interviews.practise.uber;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Walls and Gates
 *
 * Solution
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 * Example:
 *
 * Given the 2D grid:
 *
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * After running your function, the 2D grid should be:
 *
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 */

public class WallsAndGateProblem {
    public static void main(String[] args) {
        int[][] rooms = new int[][] {
                                        {Integer.MAX_VALUE,-1,0,Integer.MAX_VALUE},
                                        {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,-1},
                                        {Integer.MAX_VALUE,-1,Integer.MAX_VALUE,-1},
                                        {0,-1,Integer.MAX_VALUE,Integer.MAX_VALUE}
                                    };
        wallsAndGates(rooms);
        Arrays.stream(rooms).forEach(e-> System.out.println(Arrays.toString(e)));
        rooms = new int[][] {
                {Integer.MAX_VALUE,-1,0,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,-1},
                {Integer.MAX_VALUE,-1,Integer.MAX_VALUE,-1},
                {0,-1,Integer.MAX_VALUE,Integer.MAX_VALUE}};
        wallsAndGatesBFS(rooms);
        Arrays.stream(rooms).forEach(e-> System.out.println(Arrays.toString(e)));
    }

    private static void wallsAndGates(int[][] rooms){
        if(rooms==null || rooms.length==0)
            return;
        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[i].length;j++){
                if(rooms[i][j]==0){
                    dfs(rooms,i,j,0);
                }
            }
        }
    }

    private static void dfs(int[][] rooms, int i, int j, int count){
        if(i<0 || i>=rooms.length || j<0 || j>=rooms[i].length || rooms[i][j]<count)
            return;
        rooms[i][j]=count;
        dfs(rooms,i-1,j,count+1);
        dfs(rooms,i+1,j,count+1);
        dfs(rooms,i,j-1,count+1);
        dfs(rooms,i,j+1,count+1);
    }

    private static void wallsAndGatesBFS(int[][] rooms){
        if(rooms==null || rooms.length==0) return;
        int m = rooms.length;
        int n = rooms[0].length;
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j =0;i<n;i++){
                if(rooms[i][j]==0){
                    queue.offer(new int[]{i,j});
                }
            }
        }

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            for(int[] dir : directions){
                int r = current[0]+dir[0];
                int c = current[1]+dir[1];
                if(r<0 || r>=m || c<0 || c>=n || rooms[r][c]!=Integer.MAX_VALUE) continue;
                rooms[r][c]=rooms[current[0]][current[1]]+1;
                queue.offer(new  int[]{r,c});
            }
        }

    }
}
