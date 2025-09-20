package com.java.coding.interviews.practise.google;

public class MaxAreaIslandProblem {

    public static void main(String[] args) {
        int[][] grid =
                {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0}, {0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(findMaxArea(grid));

    }

    private static int findMaxArea(int[][] grid){
        if(grid==null || grid.length==0) return 0;
        int row = grid.length;
        int column = grid[0].length;
        boolean[][] visited = new boolean[row][column];
        int maxLength=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(grid[i][j]==1){
                    maxLength=Math.max(maxLength,dfs(grid,visited,i,j));
                }
            }
        }
        return maxLength;
    }

    private static int dfs(int[][] grid, boolean[][] visited, int r, int c){
        int row = grid.length;
        int column = grid[0].length;
        if(r<0 || r>=row || c<0 || c>=column || grid[r][c]==0 || visited[r][c])
            return 0;
        visited[r][c]=true;
        return 1+dfs(grid,visited,r+1,c)+
                     dfs(grid,visited,r-1,c)+
                    dfs(grid,visited,r,c+1)+
                   dfs(grid,visited,r,c-1);
    }

}
