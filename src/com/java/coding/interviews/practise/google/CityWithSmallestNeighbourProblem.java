package com.java.coding.interviews.practise.google;

import java.util.Arrays;

/**
 * 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 *
 * There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities from i and to i,
 * and given the integer distanceThreshold.
 *
 * Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities,
 * return the city with the greatest number.
 *
 * Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
 *
 * Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * Output: 3
 * Explanation: The figure above describes the graph.
 * The neighboring cities at a distanceThreshold = 4 for each city are:
 * City 0 -> [City 1, City 2]
 * City 1 -> [City 0, City 2, City 3]
 * City 2 -> [City 0, City 1, City 3]
 * City 3 -> [City 1, City 2]
 * Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
 */
public class CityWithSmallestNeighbourProblem {

    public static void main(String[] args) {
        int result = findCity( new int[][]{{0,1,3},{1,2,1},{1,3,4},{2,3,1}},4,4);
        System.out.println(result);
        result = findCity(new int[][] {{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}},5,2);
        System.out.println(result);
    }

    //Time Complexity O(V^3)
    //Space Complexity O(V^2)
    public static int findCity(int[][] edges, int n, int threshold){
        int[][] distance = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(distance[i],Integer.MAX_VALUE);
        }

        for(int[] edge : edges){
            int src = edge[0];
            int dest = edge[1];
            int weight = edge[2];
            distance[src][dest]=weight;
            distance[dest][src]=weight;
        }
        floydWarshall(distance,n);
        int result=-1;
        int minCity = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int count=0;
            for(int j=0;j<n;j++) {
                if (i != j && distance[i][j] <= threshold)
                    count++;
            }
            if(count<=minCity){
                minCity=count;
                result=i;
            }
        }
        return result;
    }

    private static void floydWarshall(int[][] distance, int n){
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    distance[i][j]=Math.min(distance[i][j],distance[i][k]+distance[k][j]);
                }
            }
        }
    }
}
