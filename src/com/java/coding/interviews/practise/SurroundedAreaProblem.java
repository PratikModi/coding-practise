package com.java.coding.interviews.practise;

import java.util.Arrays;

/**
 * Given a matrix of ‘O’ and ‘X’, replace ‘O’ with ‘X’ if surrounded by ‘X’
 * 31-12-2014
 * Given a matrix where every element is either ‘O’ or ‘X’, replace ‘O’ with ‘X’
 * if surrounded by ‘X’. A ‘O’ (or a set of ‘O’) is considered to be by surrounded by ‘X’
 * if there are ‘X’ at locations just below, just above, just left and just right of it.
 * Examples:
 *
 *
 * Input: mat[M][N] =  {{'X', 'O', 'X', 'X', 'X', 'X'},
 *                      {'X', 'O', 'X', 'X', 'O', 'X'},
 *                      {'X', 'X', 'X', 'O', 'O', 'X'},
 *                      {'O', 'X', 'X', 'X', 'X', 'X'},
 *                      {'X', 'X', 'X', 'O', 'X', 'O'},
 *                      {'O', 'O', 'X', 'O', 'O', 'O'},
 *                     };
 * Output: mat[M][N] =  {{'X', 'O', 'X', 'X', 'X', 'X'},
 *                       {'X', 'O', 'X', 'X', 'X', 'X'},
 *                       {'X', 'X', 'X', 'X', 'X', 'X'},
 *                       {'O', 'X', 'X', 'X', 'X', 'X'},
 *                       {'X', 'X', 'X', 'O', 'X', 'O'},
 *                       {'O', 'O', 'X', 'O', 'O', 'O'},
 *                     };
 *
 * Input: mat[M][N] =  {{'X', 'X', 'X', 'X'}
 *                      {'X', 'O', 'X', 'X'}
 *                      {'X', 'O', 'O', 'X'}
 *                      {'X', 'O', 'X', 'X'}
 *                      {'X', 'X', 'O', 'O'}
 *                     };
 *
 *
 * Input: mat[M][N] =  {{'X', 'X', 'X', 'X'}
 *                      {'X', 'X', 'X', 'X'}
 *                      {'X', 'X', 'X', 'X'}
 *                      {'X', 'X', 'X', 'X'}
 *                      {'X', 'X', 'O', 'O'}
 *                     };
 *
 */

public class SurroundedAreaProblem {
    public static void main(String[] args) {
        char[][] matrix = {{'X', 'X', 'X', 'X'},
                           {'X', 'O', 'X', 'X'},
                           {'X', 'O', 'O', 'X'},
                           {'X', 'O', 'X', 'X'},
                           {'X', 'X', 'O', 'O'}
                          };
        Arrays.stream(matrix).forEach(e->System.out.println(Arrays.toString(e)));
        floodFillArea(matrix,5,4);
        System.out.println("=================================================");
        Arrays.stream(matrix).forEach(e->System.out.println(Arrays.toString(e)));
        System.out.println("=================================================");
        matrix=new char[][]
                {{'X', 'O', 'X', 'X', 'X', 'X'},
                 {'X', 'O', 'X', 'X', 'O', 'X'},
                 {'X', 'X', 'X', 'O', 'O', 'X'},
                 {'O', 'X', 'X', 'X', 'X', 'X'},
                 {'X', 'X', 'X', 'O', 'X', 'O'},
                 {'O', 'O', 'X', 'O', 'O', 'O'}
               };
        floodFillArea(matrix,6,6);
        Arrays.stream(matrix).forEach(e->System.out.println(Arrays.toString(e)));
        System.out.println("==================================================");

        matrix = new char[][]
                {       {'X','O','X','O','X','X'},
                        {'X','O','X','X','O','X'},
                        {'X','X','X','O','X','X'},
                        {'O','X','X','X','X','X'},
                        {'X','X','X','O','X','O'},
                        {'O','O','X','O','O','O'}
                };
        floodFillArea(matrix,6,6);
        Arrays.stream(matrix).forEach(e->System.out.println(Arrays.toString(e)));
    }

    public static void floodFillArea(char[][] matrix, int M, int N){
        if(matrix==null || matrix.length==0)
            return;
        /*for(int i=0;i<M;i++){
            dfs(matrix,i,0,M,N);
            dfs(matrix,i,N-1,M,N);
        }
        for(int i=0;i<N;i++){
            dfs(matrix,0,i,M,N);
            dfs(matrix,M-1,i,M,N);

        }*/
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(((i>0 || i<M-1) && (j==0 || j==N-1)) || ((i==0 || i==M-1)))
                    if(matrix[i][j]=='O'){
                        dfs(matrix,i,j,M,N);
                    }
            }

        }

        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(matrix[i][j]=='O'){
                    matrix[i][j]='X';
                }else if(matrix[i][j]=='1'){
                    matrix[i][j]='O';
                }
            }
        }

    }

    private static void dfs(char[][] matrix, int row,int col, int M, int N) {
        if(row<0 || row>=M || col<0 || col>=N || matrix[row][col]!='O')
            return;
        matrix[row][col] = '1';
        dfs(matrix, row + 1, col, M, N);
        dfs(matrix, row - 1, col, M, N);
        dfs(matrix, row, col + 1, M, N);
        dfs(matrix, row, col - 1, M, N);
    }

}
