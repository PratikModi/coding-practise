package com.java.coding.interviews.practise.google;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode: 365. Water and Jug Problem
 *
 * You are given two jugs with capacities x liters and y liters. You have an infinite water supply. Return whether the total amount of water in both jugs may reach target using the following operations:
 *
 * Fill either jug completely with water.
 * Completely empty either jug.
 * Pour water from one jug into another until the receiving jug is full, or the transferring jug is empty.
 *
 *
 * Example 1:
 *
 * Input: x = 3, y = 5, target = 4
 *
 * Output: true
 *
 * Explanation:
 *
 * Follow these steps to reach a total of 4 liters:
 *
 * Fill the 5-liter jug (0, 5).
 * Pour from the 5-liter jug into the 3-liter jug, leaving 2 liters (3, 2).
 * Empty the 3-liter jug (0, 2).
 * Transfer the 2 liters from the 5-liter jug to the 3-liter jug (2, 0).
 * Fill the 5-liter jug again (2, 5).
 * Pour from the 5-liter jug into the 3-liter jug until the 3-liter jug is full. This leaves 4 liters in the 5-liter jug (3, 4).
 * Empty the 3-liter jug. Now, you have exactly 4 liters in the 5-liter jug (0, 4).
 * Reference: The Die Hard example.
 *
 * Example 2:
 *
 * Input: x = 2, y = 6, target = 5
 *
 * Output: false
 *
 * Example 3:
 *
 * Input: x = 1, y = 2, target = 3
 *
 * Output: true
 *
 * Explanation: Fill both jugs. The total amount of water in both jugs is equal to 3 now.
 *
 * ⚙️ Key Idea 2 — BFS (State-space search)
 *
 * Each state can be represented as (a, b) = (water in jugX, water in jugY).
 * From each state, we can perform 6 operations:
 *
 * Fill jug X → (x, b)
 * Fill jug Y → (a, y)
 * Empty jug X → (0, b)
 * Empty jug Y → (a, 0)
 * Pour X → Y → (newA, newB)
 * Pour Y → X → (newA, newB)
 *
 * We do BFS until we reach any state where a == target or b == target or a + b == target.
 *
 * ⏱️ Time Complexity:
 *
 * O(x * y) — because each state (a, b) is bounded by jug capacities.
 *
 * 🧮 Space Complexity:
 *
 * O(x * y) — for the visited set.
 */
public class WaterJugProblem {

    public static void main(String[] args) {
        int x = 3, y=5, target=4;
        System.out.println(canMeasureWater(x,y,target));
        x=2;
        y=6;
        target=5;
        System.out.println(canMeasureWater(x,y,target));
    }

    public static boolean canMeasureWater(int x, int y, int target) {
        if(target>x+y) return false;
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int a = current[0], b=current[1];
            if(a==target || b==target || a+b==target) return true;
            // Generate all possible next states
            int[][] next ={
                    {x,b}, //Fill the Jug X
                    {a,y}, //Fill the Jug Y
                    {0,b}, //Empty Jug X
                    {a,0}, // Empty Jug Y
                    pour(a,b,x,y,true), // Fill from Jug X -> Y
                    pour(a,b,x,y,false), // Fill from Jug Y -> X
            };
            for(int[] n: next){
                if(!visited.contains(n[0]+","+n[1])){
                    visited.add(n[0]+","+n[1]);
                    queue.offer(n);
                }
            }
        }
        return false;
    }

    private static int[] pour(int a, int b, int x, int y, boolean fromXToY){
        if(fromXToY){
            int pour = Math.min(a,y-b);
            return new int[]{a-pour, b+pour};
        }else{
            int pour = Math.min(b,x-a);
            return new int[]{a+pour, b-pour};
        }
    }

}
