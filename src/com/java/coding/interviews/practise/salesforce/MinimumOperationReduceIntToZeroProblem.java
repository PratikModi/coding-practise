package com.java.coding.interviews.practise.salesforce;

/**
 * LeetCode: 2571. Minimum Operations to Reduce an Integer to 0
 * You are given a positive integer n, you can do the following operation any number of times:
 *
 * Add or subtract a power of 2 from n.
 * Return the minimum number of operations to make n equal to 0.
 *
 * A number x is power of 2 if x == 2i where i >= 0.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 39
 * Output: 3
 * Explanation: We can do the following operations:
 * - Add 20 = 1 to n, so now n = 40.
 * - Subtract 23 = 8 from n, so now n = 32.
 * - Subtract 25 = 32 from n, so now n = 0.
 * It can be shown that 3 is the minimum number of operations we need to make n equal to 0.
 * Example 2:
 *
 * Input: n = 54
 * Output: 3
 * Explanation: We can do the following operations:
 * - Add 21 = 2 to n, so now n = 56.
 * - Add 23 = 8 to n, so now n = 64.
 * - Subtract 26 = 64 from n, so now n = 0.
 * So the minimum number of operations is 3.
 *
 * So the strategy is:
 * 	1.	Find the closest power of 2 to n
 * 	2.	You have two choices:
 * 	•	Reduce n → n - closestPower
 * 	•	Increase n → closestPower - n
 * 	3.	Both paths ultimately reach zero.
 * 	4.	Take the minimum operations between the two paths.
 *
 * ✅ We have two choices?
 * You are allowed to do exactly one operation:
 * Replace n with |n - 2^k| for any integer k
 * So from n, you can move to:
 * n - 2^k   (if 2^k ≤ n)
 * 2^k - n   (if 2^k ≥ n)
 * This means:
 * ✔ If you pick a power of 2 smaller than n
 * You subtract it:
 * n → n - 2^k
 * ✔ If you pick a power of 2 larger than n
 * You add until you reach that power:
 * n → 2^k - n
 *
 * ✅ Approach 2: Iterative DP (Greedy BFS Style)
 * Time Complexity: O(log n)
 * Why?
 * 	•	Each iteration finds the nearest power of 2 using:
 * 	which takes O(log n) worst case the first time.
 * 	Total:
 * 	O(log n) iterations × O(1) work inside loop = O(log n)
 *
 * 	Space Complexity: O(1)
 * 	•	No recursion, no memo.
 */
public class MinimumOperationReduceIntToZeroProblem {

    public static void main(String[] args) {
        System.out.println(minOperations(15));
        System.out.println(minOperations(39));
        System.out.println(minOperations(54));
    }

    public static int minOperations(int n) {
        int ops =0;
        while(n!=0){
            int power=1;
            // find power of 2 just >= n
            while(power<n) power=power*2; //(p<<=1)
            int diff1 = power-n; // using next power
            int diff2 = n-(power/2); // using previous power (p>>1)
            // Greedy: choose smaller next state
            n=Math.min(diff1,diff2);
            ops++;
        }
        return ops;
    }
}
