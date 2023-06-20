package com.java.coding.interviews.practise.thetradedesk;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 * Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...].
 * Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list,
 * which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends.
 * Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline.
 * For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable;
 * the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 *
 * Example 1:
 *
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 * Example 2:
 *
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 */
public class SkyLineProblem {

    public static void main(String[] args) {
        int[][] buildings = new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        var skyLine = getSkyline(buildings);
        System.out.println(skyLine);
    }

    //Time Complexity:- O(n^2)
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> skyLine = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();

        transformBuilding(buildings,heights);
        Collections.sort(heights,(a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        PriorityQueue<Integer> PQ = new PriorityQueue<>((a,b)->b-a);
        PQ.offer(0);
        int prevMax=0;
        for(int[] height : heights){
            if(height[1]<0)
                PQ.offer(Math.abs(height[1]));
            else
                PQ.remove(height[1]);
            int currentMax = PQ.peek();
            if(currentMax!=prevMax){
                List<Integer> coordinates = new ArrayList<>();
                coordinates.add(height[0]);
                coordinates.add(currentMax);
                skyLine.add(coordinates);
                prevMax = currentMax;
            }
        }
        return skyLine;
    }

    private static void transformBuilding(int[][] buildings, List<int[]> heights){
        for(int[] height : buildings){
            heights.add(new int[] {height[0],-height[2]});
            heights.add(new int[] {height[1],height[2]});
        }
    }
}
