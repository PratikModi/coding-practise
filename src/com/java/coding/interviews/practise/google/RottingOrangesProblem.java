package com.java.coding.interviews.practise.google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 */
public class RottingOrangesProblem {

    static class Pair{
        int x;
        int y;
        int t;

        Pair(int x, int y, int t){
            this.x=x;
            this.y=y;
            this.t=t;
        }
    }

    public static void main(String[] args) {
        int[][] oranges={{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(oranges));
        oranges = new int[][] {{2,1,1},{0,1,1},{1,0,1}};
        System.out.println(orangesRotting(oranges));
    }

    public static int orangesRotting(int[][] oranges){
        int m = oranges.length;
        int n = oranges[0].length;
        Queue<Pair> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(oranges[i][j]==2){
                    queue.offer(new Pair(i,j,0));
                }
            }
        }
        int time=0;
        while(!queue.isEmpty()) {
            Pair pair = queue.poll();
            int x = pair.x;
            int y = pair.y;
            int t = pair.t;
            time = t;
            int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : direction) {
                int cx = x + dir[0];
                int cy = y + dir[1];
                if (cx >= 0 && cx < m && cy >= 0 && cy < n && oranges[cx][cy] == 1) {
                    oranges[cx][cy] = 2;
                    queue.offer(new Pair(cx, cy, t + 1));
                }
            }
        }
            int fresh=0;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(oranges[i][j]==1){
                        fresh++;
                    }
                }
            }
            return fresh>0 ?-1:time;
        }

}
