package com.java.coding.interviews.practise.common;

/**
 * Created by Pratik1 on 25-04-2020.
 *
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 */
public class IslandSinkProblem {
    public static void main(String[] args) {
        System.out.println(countIsland(new int[][]{{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}}));
        System.out.println(countIsland(new int[][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}}));
    }

    public static int countIsland(int[][] A){
        int result=0;
        if(A==null || A.length==0)
            return result;
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[i].length;j++){
                if(A[i][j]==1){
                    result+=dfs(A,i,j);
                }
            }
        }
        return result;
    }

    public static int dfs(int[][] A, int i, int j){

        if(i<0 || i>=A.length || j<0 || j>=A[i].length || A[i][j]==0)
            return 0;
        A[i][j]=0;
        dfs(A,i-1,j);//UP
        dfs(A,i+1,j);//DOWN
        dfs(A,i,j-1);//LEFT
        dfs(A,i,j+1);//RIGHT
        return 1;
    }

}
