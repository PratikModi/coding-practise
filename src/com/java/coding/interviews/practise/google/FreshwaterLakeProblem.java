package com.java.coding.interviews.practise.google;
/*
Given an input matrix like this

private static int[][] input =
{{0,0,1,0,0,0,0,1,0,0,0,0,0}, // 0 ... 0
{0,0,0,0,0,0,0,1,1,1,0,0,0}, // 0 ... 0
{0,1,1,1,1,0,0,0,0,0,0,0,0},
{0,1,0,0,1,1,0,0,1,1,1,0,0},
{0,1,0,1,1,1,0,0,1,1,1,0,0},
{0,0,1,0,0,0,0,0,1,0,1,0,0},
{0,0,0,0,0,0,0,1,1,1,0,0,0},
{0,0,0,0,0,0,0,1,1,0,0,0,0}};

Here 0 indicates water and its value can be either saltwater or freshwater
1 is land

the input matrix is implicitly surrounded by an infinite ocean as shown in the comments part in the first and second row.

Here the condition is water moves vertically and horizontally (up down left right) but not diagonally
Write a method which takes this input matrix and return the total number of freshwater lakes. For the above matrix there are two freshwater island.
 */
//✅ Time = O(M × N)
//✅ Space = O(M × N)
public class FreshwaterLakeProblem {

    private static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) {
        int[][] input =
                {{0,0,1,0,0,0,0,1,0,0,0,0,0}, // 0 ... 0
                        {0,0,0,0,0,0,0,1,1,1,0,0,0}, // 0 ... 0
                        {0,1,1,1,1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        {0,1,0,1,1,1,0,0,1,1,1,0,0},
                        {0,0,1,0,0,0,0,0,1,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,1,1,0,0,0,0}};

        System.out.println(findFreshWaterLake(input));
    }

    private static int findFreshWaterLake(int[][] matrix){
        if(matrix==null || matrix.length==0)
            return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++){
            if(matrix[i][0]==0 && !visited[i][0]){
                dfs(matrix,visited,i,0,m,n);
            }
            if(matrix[i][n-1]==0 && !visited[i][n-1]){
                dfs(matrix,visited,i,n-1,m,n);
            }
        }

        for(int i=0;i<n;i++){
            if(matrix[0][i]==0 && !visited[0][i]){
                dfs(matrix,visited,0,i,m,n);
            }
            if(matrix[m-1][i]==0 && !visited[m-1][i]){
                dfs(matrix,visited,m-1,i,m,n);
            }
        }

        int lakes=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0 && !visited[i][j]){
                    dfs(matrix,visited,i,j,m,n);
                    lakes++;
                }
            }
        }
        return lakes;
    }


    private static void dfs(int[][] matrix, boolean[][] visited, int row, int column, int m, int n){
        if(row<0 || row>=m || column<0 || column>=n || matrix[row][column]!=0 || visited[row][column]){
            return;
        }
        visited[row][column]=true;
        for(int[] d : dir){
            dfs(matrix,visited,row+d[0],column+d[1],m,n);
        }
    }

}
