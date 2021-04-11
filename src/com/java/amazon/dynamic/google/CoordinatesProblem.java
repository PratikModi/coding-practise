package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 19-05-2020.
 */

/**
 * You are in an infinite 2D grid where you can move in any of the 8 directions:

 (x,y) to
 (x+1, y),
 (x - 1, y),
 (x, y+1),
 (x, y-1),
 (x-1, y-1),
 (x+1,y+1),
 (x-1,y+1),
 (x+1,y-1)
 You are given a sequence of points and the order in which you need to cover the points.
 Give the minimum number of steps in which you can achieve it. You start from the first point.

 Example:

 Input: [(0, 0), (1, 1), (1, 2)]
 Output: 2
 It takes 1 step to move from (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).

 */
public class CoordinatesProblem {

    public static void main(String[] args) {
        int result = findMinimumSteps(new int[][]{{0,0},{1,1},{1,2}});
        System.out.println(result);
    }

    private static int shortestPath(int[] A, int[] B){
        int X = Math.abs(A[0]-B[0]);
        int Y = Math.abs(A[1]-B[1]);
        return Math.max(X,Y);
    }

    public static int findMinimumSteps(int[][] coordinates){
        int result = 0;
        if(coordinates==null || coordinates.length==0)
            return -1;
        int N = coordinates.length;
        for(int i=0;i<N-1;i++){
            result+=shortestPath(coordinates[i],coordinates[i+1]);
        }
        return result;
    }


}
