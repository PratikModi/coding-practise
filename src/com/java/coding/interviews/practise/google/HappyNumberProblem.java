package com.java.coding.interviews.practise.google;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 *
 * Example 1:
 *
 * Input: n = 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 *
 * 🔑 Time Complexity
 * 	•	Each step = O(log n)
 * 	•	Steps = constant upper bound (≤ 810, or even tighter ≤ 243 for 32-bit int).
 * 	•	Total = O(log n)
 *
 * 	For a 32-bit integer, max is 2,147,483,647 (10 digits).
 * So max sum of squares = 9² × 10 = 810.
 * 	•	That means once you start, numbers quickly fall into the range 1–810.
 * 	•	After that, you’re just cycling or reaching 1.
 * 	•	So there are at most O(810) ≈ constant iterations before repeating or terminating.
 */
public class HappyNumberProblem {

    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println(isHappy(2));
    }

    public static boolean isHappy(int num){
        Set<Integer> visited = new HashSet<>();
        while(num!=1 && !visited.contains(num)){
            visited.add(num);
            num=sumOfSquare(num);
        }
        return num==1;
    }

    private static int sumOfSquare(int num){
        int total=0;
        while(num>0){
            int digit=num%10;
            total+=digit*digit;
            num=num/10;
        }
        return total;
    }

}
