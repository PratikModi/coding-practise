package com.java.coding.interviews.practise.google;

/**
 * 547. Number of Provinces
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected,
 * and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class ProvincesNumberProblem {

    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findProvinces(isConnected));
        isConnected = new int[][] {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(findProvinces(isConnected));
    }

    public static int findProvinces(int[][] isConnected){
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int result=0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                dfs(isConnected,visited,i);
                result++;
            }
        }
        return result;
    }

    private static void dfs(int[][] isConnected, boolean[] visited, int city){
        visited[city]=true;
        for(int i=0;i<isConnected.length;i++){
            if(isConnected[city][i]==1 && !visited[i]){
                dfs(isConnected,visited,i);
            }
        }
    }

}
