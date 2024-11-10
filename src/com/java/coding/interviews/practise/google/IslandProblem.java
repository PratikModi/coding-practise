package com.java.coding.interviews.practise.google;

public class IslandProblem {

    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        System.out.println(closedIsland(grid));
        System.out.println(closedIsland(new int[][] {{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}}));
    }
    //Time Complexity::(m*n)
    public static int closedIsland(int[][] grid){
        if(grid==null || grid.length==0)
            return 0;
        int closedIsland=0,rows=0,columns=0;
        rows = grid.length;
        columns = grid[0].length;
        for(int i=1;i<rows-1;i++){
            for(int j=1;j<columns-1;j++){
                    if(grid[i][j]==0){
                        if(dfs(grid,i,j,rows,columns))
                            closedIsland++;
                    }
            }
        }
        return closedIsland;
    }

    private static boolean dfs(int[][] grid,int i, int j, int rows,int columns){

        if(grid[i][j]==1 || grid[i][j]==-1) return true;

        if(i<=0 || j<=0 || i>=rows-1 || j>=columns-1) return false;

        grid[i][j]=-1;

        boolean top = dfs(grid,i-1,j,rows,columns);
        boolean bottom = dfs(grid,i+1,j,rows,columns);
        boolean left = dfs(grid,i,j-1,rows,columns);
        boolean right = dfs(grid,i,j+1,rows,columns);

        return top && bottom && left && right;
    }
}
