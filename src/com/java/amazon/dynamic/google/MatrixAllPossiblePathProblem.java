package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 22-06-2020.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Print all possible paths from top left to bottom right of a mXn matrix
 * The problem is to print all the possible paths from top left to bottom right of a mXn matrix
 * with the constraints that from each cell you can either move only to right or down.
 Examples :

 Input : 1 2 3
         4 5 6
 Output : 1 4 5 6
          1 2 5 6
          1 2 3 6

 Input : 1 2
         3 4
 Output : 1 2 4
          1 3 4

 Approach:
          |-|-|
          |_|_|_
 */
public class MatrixAllPossiblePathProblem {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6}};
        System.out.println(findAllPossiblePaths(matrix));
        matrix = new int[][]{{1,2},{3,4}};
        System.out.println(findAllPossiblePaths(matrix));

    }

    public static List<List<Integer>> findAllPossiblePaths(int[][] matrix){
        List<List<Integer>> result = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        helper(matrix,m,n,0,0,0,result,new int[m+n-1]);
        return result;
    }

    private static void helper(int[][] matrix,int m,int n,int row,int column,int index,List<List<Integer>> result, int[] path){
        //System.out.println("helper("+m+","+n+","+row+","+column+")");
        path[index] = matrix[row][column];
        if(row==m-1){
            //System.out.println("1");
            for(int i=column+1;i<n;i++){
                path[index+i-column]=matrix[row][i];
            }
            result.add(Arrays.stream(path).boxed().collect(Collectors.toList()));
         //   System.out.println(result);
            return;
        }
        if(column==n-1){
            //System.out.println("2");
            for(int i=row+1;i<m;i++){
                path[index+i-row]=matrix[i][column];
            }
            result.add(Arrays.stream(path).boxed().collect(Collectors.toList()));
            //System.out.println(result);
            return;
        }

        helper(matrix,m,n,row+1,column,index+1,result,path);
        helper(matrix,m,n,row,column+1,index+1,result,path);
    }



}
