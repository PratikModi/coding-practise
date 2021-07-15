package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * I had a Google phone screen last week with a Google software engineer. He was a team lead at Google from a few years.
 * We went directly in the coding question after greeting each other. No questions about the resume or my background.
 * The question was, given 2 points in a 2d array of all 0s, generate a random walk and return the grid.
 * Every time a random walk should be returned (the bigger the better, but not necessary).
 * You can walk in the 4 directions up, down, left and right. While determining a solution,
 * how would you make sure that a spiral path was not created,
 * how would you make sure you always end up at the destination and not in either corners or way inside the path you'e making.
 *
 * eg.
 * [
 * [0 0 0 0]
 * [0 0 0 0]
 * [0 0 0 0]
 * [0 0 0 0]
 * ]
 *
 * You can mark a position 1 when you have stepped over that position.
 * assume the start was (0, 0) and end was (3, 3).
 *
 * The paths can be
 * [
 * [1 1 0 0]
 * [0 1 1 1]
 * [0 1 1 1]
 * [0 0 0 1]
 * ]
 *
 * The above path goes (0,0) - > (0,1) - > (1,1) - > (2,1) - > (2,2) - > (1,2) - > (1,3) - > (2,3) - > (3,3)
 */
public class RandomWalkProblem {

    private static int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void main(String[] args) {
        int[][] grid = new int[4][4];
        int[] start = {0,0};
        int[] end = {3,3};
        List<int[]> result = randomWalk(grid,start,end);
        result.stream().forEach(e-> System.out.print(Arrays.toString(e)));
    }

    private static void shuffle(int N){
        Random R = new Random();
        for(int i=0;i<N;i++){
            int j = R.nextInt(i+1);
            int[] temp = directions[j];
            directions[j] = directions[i];
            directions[i] = temp;
        }
    }

    public static List<int[]> randomWalk(int[][] grid, int[] start, int[] end){
        shuffle(grid.length);
        boolean[][] visited = new boolean[grid.length][grid.length];
        List<int[]> result = new ArrayList<>();
        dfs(grid,start,end,visited,result);
        return result;
    }

    public static boolean dfs(int[][] grid, int[] start, int[] end, boolean[][] visited, List<int[]> result){
        if(start[0]==end[0] && start[1]==end[1]){
            result.add(new int[]{start[0],start[1]});
            return true;
        }
        visited[start[0]][start[1]]=true;
        shuffle(grid.length);
        result.add(new int[]{start[0],start[1]});
        for(int[] dir : directions){
            int di = start[0]+dir[0];
            int dj = start[1]+dir[1];
            if(di>=0 && di<grid.length && dj>=0 && dj<grid[0].length && !visited[di][dj]){
                if(dfs(grid,new int[]{di,dj},end,visited,result)){
                    return true;
                }
            }
        }
        visited[start[0]][start[1]]=false;
        result.remove(result.size()-1);
        return false;
    }




}
