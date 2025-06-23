package com.java.coding.interviews.practise.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two inputs,
 *
 * First input is the location map, a 2D array
 *
 * | O | E | E | E | X |
 * | E | O | X | X | X |
 * | E | E | E | E | E |
 * | X | E | O | E | E |
 * | X | E | X | E | X |
 *
 * O = Robot, E = Empty, X = blocker
 *
 * Second input is the query. It’s a 1D array consisting of distance to the closest blocker in the order from left, top, bottom and right
 *
 * [2, 2, 4, 1] -> This means distance of 2 to the left blocker, 2 to the top blocker, 4 to the bottom blocker and 1 to the right blocker
 *
 * Note: The location map boundary is also considered blocker, meaning if the robot hits the boundary it also means it’s hitting the blocker.
 *
 * Write a function that takes these two inputs and returns the index of the robots (if any) that matches the query that we’re looking for.
 *
 * Solution for the example above would be the robot located at index [1, 1]
 *   -1 -2   -3  -4  -5
 * | O | E | E | E | X |
 * | E | O | X | X | X |
 * | E | E | E | E | E |
 * | X | E | O | E | E |
 * | X | E | X | E | X |
 */

/**
 * Question:
 * 1. Is the boundary considered a blocker even if there is no 'X'?
 * 2. why [1,1] is solution ?
 * 3. what if 2 robots are together, is one robot blocker to another robot?
 * 4. Can multiple robots match the same query?
 * 5. is grid always rectangular or can be square also? --- although solution should work for both
 */

/**
 * Let:
 * 	•	n = number of rows
 * 	•	m = number of columns
 * 	•	k = number of robot positions ('O')
 *
 * We scan the entire grid: O(n * m)
 * 	For each 'O', we call distanceToBlocker 4 times.
 * 	Each call can take up to O(max(n, m)) in worst case.
 * 	 Time Complexity: O(n * m * max(n, m))
 *  If n ≈ m, then it becomes O(n³) in the worst case.
 *
 *  Space Complexity: O(K)
 */
public class FindRobotProblem {

    public static List<int[]> findMatchingRobots(char[][] grid, int[] query) {
        List<int[]> result = new ArrayList<>();

        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 'O') {
                    int left = distanceToBlocker(grid, r, c, 0, -1);
                    int top = distanceToBlocker(grid, r, c, -1, 0);
                    int bottom = distanceToBlocker(grid, r, c, 1, 0);
                    int right = distanceToBlocker(grid, r, c, 0, 1);

                    System.out.println("Robot at (" + r + ", " + c + ") distances: " +
                            "left=" + left + ", top=" + top +
                            ", bottom=" + bottom + ", right=" + right);

                    if (left == query[0] && top == query[1] &&
                            bottom == query[2] && right == query[3]) {
                        result.add(new int[]{r, c});
                    }
                }
            }
        }
        return result;
    }

    private static int distanceToBlocker(char[][] grid, int r, int c, int dr, int dc) {
        int distance = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        int nr = r + dr;
        int nc = c + dc;

        while (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
            if (grid[nr][nc] == 'X') break;
            distance++;
            nr += dr;
            nc += dc;
        }

        return distance+1; // +1 to include the blocker itself or boundary
    }

    // For testing
    public static void main(String[] args) {
        char[][] grid = {
                {'O', 'E', 'E', 'E', 'X'},
                {'E', 'O', 'X', 'X', 'X'},
                {'E', 'E', 'E', 'E', 'E'},
                {'X', 'E', 'O', 'E', 'E'},
                {'X', 'E', 'X', 'E', 'X'}
        };

        int[] query = {2, 2, 4, 1};

        List<int[]> result = findMatchingRobots(grid, query);
        for (int[] pos : result) {
            System.out.println(Arrays.toString(pos));
        }
    }



}
