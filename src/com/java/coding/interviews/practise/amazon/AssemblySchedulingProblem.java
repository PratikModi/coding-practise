package com.java.coding.interviews.practise.amazon;

/**
 * Assembly Line Scheduling | DP-34
 * Difficulty Level : Medium
 * Last Updated : 25 May, 2021
 * A car factory has two assembly lines, each with n stations. A station is denoted by Si,j where i is either 1 or 2 and
 * indicates the assembly line the station is on, and j indicates the number of the station. The time taken per station is denoted by ai,j.
 * Each station is dedicated to some sort of work like engine fitting, body fitting, painting, and so on.
 * So, a car chassis must pass through each of the n stations in order before exiting the factory.
 * The parallel stations of the two assembly lines perform the same task.
 * After it passes through station Si,j, it will continue to station Si,j+1 unless it decides to transfer to the other line.
 * Continuing on the same line incurs no extra cost, but transferring from line i at station j – 1 to station j on the other line takes time ti,j.
 * Each assembly line takes an entry time ei and exit time xi which may be different for the two lines.
 * Give an algorithm for computing the minimum time it will take to build a car chassis.
 *
 * The below figure presents the problem in a clear picture:
 *
 *
 *
 *
 * The following information can be extracted from the problem statement to make it simpler:
 *
 * Two assembly lines, 1 and 2, each with stations from 1 to n.
 * A car chassis must pass through all stations from 1 to n in order(in any of the two assembly lines).
 * i.e. it cannot jump from station i to station j if they are not at one move distance.
 * The car chassis can move one station forward in the same line, or one station diagonally in the other line.
 * It incurs an extra cost ti, j to move to station j from line i. No cost is incurred for movement in same line.
 * The time taken in station j on line i is ai, j.
 * Si, j represents a station j on line i.
 */
public class AssemblySchedulingProblem {

    private static final int LINE = 2;
    private static final int STATION = 4;

    public static void main(String[] args) {
        int F[][] = {{4, 5, 3, 2},
                {2, 10, 1, 4}};
        int S[][] = {{0, 7, 4, 5},
                {0, 9, 2, 8}};
        int E[] = {10, 12}, X[] = {18, 7};

        System.out.println(carAssembly(E,X,F,S));
        System.out.println(carAssembly2(E,X,F,S));

    }

    private static int carAssembly(int[] E, int[] X, int[][] F, int[][] S){
        int[] FL = new int[STATION];
        int[] SL = new int[STATION];

        FL[0] = E[0] + F[0][0];
        SL[0] = E[1] + F[0][1];

        for(int i=1;i<STATION;i++){
            FL[i] = Math.min((FL[i-1]+F[0][i]), (SL[i-1]+S[1][i]+F[0][i]));
            SL[i] = Math.min(SL[i-1]+F[1][i], (FL[i-1]+S[0][i]+F[1][i]));
        }
        return Math.min(FL[STATION-1]+X[0], SL[STATION-1]+X[1]);
    }

    private static int carAssembly2(int[] E, int[] X, int[][] F, int[][] S){
        int first = E[0] + F[0][0];
        int second = E[1] + F[0][1];

        for(int i=1;i<STATION;i++){
            int up = Math.min((first+F[0][i]), (second+S[1][i]+F[0][i]));
            int down = Math.min(second+F[1][i], (first+S[0][i]+F[1][i]));
            first=up;
            second=down;
        }

        return Math.min(first+X[0], second+X[1]);
    }


}
