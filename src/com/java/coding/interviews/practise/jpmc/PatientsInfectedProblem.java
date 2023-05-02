package com.java.coding.interviews.practise.jpmc;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Maximum time required for all patients to get infected
 * Read
 * Discuss
 * Courses
 * Practice
 * Video
 *
 * Given a matrix arr[][], consisting of only 0, 1, and 2, that represents an empty ward, an uninfected patient, and an infected patient respectively. In one unit of time, an infected person at index (i, j) can infect an uninfected person adjacent to it i.e., at index (i – 1, j), (i + 1, j), (i, j – 1), and (i, j + 1). The task is to find the minimum amount of time required to infect all the patients. If it is impossible to infect all the patients, then print “-1”.
 *
 * Examples:
 *
 * Input: arr[][] = {{2, 1, 0, 2, 1}, {1, 0, 1, 2, 1}, {1, 0, 0, 2, 1}}
 * Output: 2
 * Explanation:
 * At time t = 1: The patients at positions {0, 0}, {0, 3}, {1, 3} and {2, 3} will infect patient at {{0, 1}, {1, 0}, {0, 4}, {1, 2}, {1, 4}, {2, 4}} during 1st unit time.
 * At time t = 2: The patient at {1, 0} will get infected and will infect patient at {2, 0}.
 *
 * After the above time intervals all the uninfected patients are infected. Therefore, the total amount of time required is 2.
 *
 *
 * Input: arr[][] = {{2, 1, 0, 2, 1}, {0, 0, 1, 2, 1}, {1, 0, 0, 2, 1}}
 * Output: -1
 */
public class PatientsInfectedProblem {

    public static void main(String[] args) {
        int[][] matrix = {{2, 1, 0, 2, 1}, {1, 0, 1, 2, 1}, {1, 0, 0, 2, 1}};
        System.out.println(findMinTime(matrix));
        matrix = new int[][]{{2, 1, 0, 2, 1}, {0, 0, 1, 2, 1}, {1, 0, 0, 2, 1}};
        System.out.println(findMinTime(matrix));
    }

    public static int findMinTime(int[][] matrix){
        if(matrix==null || matrix.length==0)
            return -1;
        int m = matrix.length;
        int n = matrix[0].length;
        //int[][] infectionTime = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        int unInfectedCount=0;
        int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};
        int time=0;
        Queue<Patient> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==2){
                    queue.add(new Patient(i,j,0));
                    visited[i][j]=true;
                }
                if(matrix[i][j]==1){
                    unInfectedCount++;
                }
            }
        }
        while(!queue.isEmpty()){
            Patient current = queue.poll();
            time = current.time;
            for(int[] dir : direction){
                int row = current.x+dir[0];
                int col = current.y+dir[1];
                if(row<0 || col<0 || row>=m || col>=n || visited[row][col] || matrix[row][col]!=1){
                    continue;
                }
                queue.add(new Patient(row,col,time+1));
                unInfectedCount--;
                visited[row][col]=true;
            }
        }
        if(unInfectedCount!=0)
            return -1;
        return time;
    }

}
class Patient{
    int x;
    int y;
    int time;

    public Patient(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}