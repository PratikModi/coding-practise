package com.java.coding.interviews.practise.sumologic;

/**
 * Total area of two overlapping rectangles
 * Difficulty Level : Medium
 * Last Updated : 27 Apr, 2021
 * Given two overlapping rectangles on a plane. We are given bottom left and top right points of the two rectangles.
 * We need to find the total area (Green and pink areas in the below diagram).
 *
 * Examples:
 *
 * Input : Point l1 = {2, 2}, r1 = {5, 7};
 *         Point l2 = {3, 4}, r2 = {6, 9};
 * Output :Total Area = 24
 *
 * Input : Point l1 = {2, 1}, r1 = {5, 5};
 *         Point l2 = {3, 2}, r2 = {5, 7};
 * Output :Total Area = 16
 */
public class OverlappingRectangleProblem {

    public static void main(String[] args) {
        Point L1 = new Point(2,1);
        Point R1 = new Point(5,5);
        Point L2 = new Point(3,2);
        Point R2 = new Point(5,7);
        System.out.println(isOverlapping(L1,R1,L2,R2));
        System.out.println(calculateArea(L1,R1,L2,R2));
    }

    /**
     * Total Area = (Area of 1st rectangle +
     *               Area of 2nd rectangle) -
     *               Area of Intersecting part
     * Area of Rectangle = x_distance * y_distance
     *
     * Where,
     * x_distance for 1st rectangle = abs(l1.x – r1.x)
     * y_distance for 1st rectangle = abs(l1.y – r1.y)
     *
     * Similarly, we can compute area of 2nd rectangle.
     *
     * For area of intersecting part,
     * x_distance for intersecting rectangle = min(r1.x, r2.x) – max(l1.x, l2.x)
     * y_distance for 1st rectangle = min(r1.y, r2.y) – max(l1.y, l2.y)
     */
    public static int calculateArea(Point L1, Point R1, Point L2, Point R2){
        int area1 = Math.abs(L1.x-R1.x) * Math.abs(L1.y-R1.y);
        int area2 = Math.abs(L2.x-R2.x) * Math.abs(L2.y-R2.y);
        int areaI = (Math.max(L1.x,L2.x)-Math.min(R1.x,R2.x))*(Math.max(L1.y,L2.y)-Math.min(R1.y,R2.y));
        return (area1+area2)-areaI;
    }

    /**
     * Following is a simpler approach. Two rectangles do not overlap if one of the following conditions is true.
     * 1) One rectangle is above top edge of other rectangle.
     * 2) One rectangle is on left side of left edge of other rectangle.
     */
    public static boolean isOverlapping(Point L1, Point R1, Point L2, Point R2){
        if (L1.x == R1.x || L1.y == R1.y || L2.x == R2.x || L2.y == R2.y)
        {
            // the line cannot have positive overlap
            return false;
        }


        // If one rectangle is on left side of other
        if (L1.x >= R2.x || L2.x >= R1.x) {
            return false;
        }

        // If one rectangle is above other
        if (L1.y >= L2.y || L2.y >= R1.y) {
            return false;
        }
        return true;
    }
}

class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
