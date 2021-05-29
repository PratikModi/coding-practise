package com.java.coding.interviews.practise.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Shortest Bridge
 *
 * Solution
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 *
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 *
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 *
 *
 *
 * Example 1:
 *
 * Input: A = [[0,1],[1,0]]
 * Output: 1
 * Example 2:
 *
 * Input: A = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * Example 3:
 *
 * Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 2 <= A.length == A[0].length <= 100
 * A[i][j] == 0 or A[i][j] == 1
 */
public class ShortestBridgeProblem {
    public static void main(String[] args) {
        int[][] A = {{0,1}, {1,0}};
        int result = shortestBridge(A);
        System.out.println(result);
    }
    public static int shortestBridge(int[][] A) {
        int result=Integer.MAX_VALUE;
        if(A==null || A.length==0)
            return 0;
        List<List<Integer>> IA = new ArrayList<>();
        List<List<Integer>> IB = new ArrayList<>();
        floodFill(A,IA,IB);
        for(List<Integer> I1 : IA){
            for(List<Integer> I2: IB){
                result=Math.min(result,distance(I1,I2));
            }
        }
        return result;
    }
    private static void floodFill(int[][] A, List<List<Integer>> IA, List<List<Integer>> IB){
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[i].length;j++){
                if(A[i][j]==1) {
                    if (IA.isEmpty()){
                        dfs(A,IA,i,j);
                    }else if(IB.isEmpty() && !IA.contains(Arrays.asList(i,j))){
                        dfs(A,IB,i,j);
                    }
                }
            }
        }
    }

    private static void dfs(int[][] A, List<List<Integer>> I, int i, int j){
        if(i<0 || i>=A.length || j<0 || j>=A[i].length || A[i][j]==0)
            return;
        I.add(Arrays.asList(i,j));
        dfs(A,I,i-1,j);
        dfs(A,I,i+1,j);
        dfs(A,I,i,j-1);
        dfs(A,I,i,j+1);
    }

    private static int distance(List<Integer> IA, List<Integer> IB){
        return Math.abs(IA.get(0)-IB.get(0))+Math.abs(IA.get(1)-IB.get(1))-1;
    }

}
