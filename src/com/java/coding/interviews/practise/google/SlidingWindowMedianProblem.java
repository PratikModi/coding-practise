package com.java.coding.interviews.practise.google;

import java.math.BigDecimal;
import java.util.*;

/**
 * 480. Sliding Window Median
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 * Example 2:
 *
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 * Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 *
 * 🧠 Key Idea
 *
 * The challenge:
 * We must efficiently insert and remove elements while being able to find the median quickly.
 *
 * Approach:
 * Use two heaps:
 *
 * maxHeap (left side) → stores the smaller half of numbers
 *
 * minHeap (right side) → stores the larger half of numbers
 *
 * The median is:
 *
 * If window size is odd → top of maxHeap
 *
 * If window size is even → average of tops of both heaps
 *
 * But unlike standard median problems, here we must also remove elements that slide out of the window —
 * that’s why we use “lazy removal” with a HashMap.
 * ⏱️ Time Complexity
 *
 * Insert → O(log k)
 *
 * Remove (lazy) → O(log k)
 *
 * Get median → O(1)
 *
 * Total → O(n log k)
 */
public class SlidingWindowMedianProblem {

    static PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Double> minHeap = new PriorityQueue<>();
    static Map<Double,Integer> delayed = new HashMap<>();
    static int smallSize=0, largeSize=0;
    public static void main(String[] args) {
        int[] nums = new int[]{-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
        int k=3;
        //System.out.println(Arrays.toString(medianSlidingWindow(nums,k)));
        for(double d : medianSlidingWindow(nums,k)){
            System.out.println(BigDecimal.valueOf(d).toPlainString());
        }
        maxHeap.clear();
        minHeap.clear();
        delayed.clear();
        smallSize=0;
        largeSize=0;
        System.out.println("=============================");
        //nums = new int[]{1,2,3,4,2,3,1,4,2};
        //System.out.println(Arrays.toString(medianSlidingWindow(nums,k)));
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];

        for (int i = 0; i < n; i++) {
            // Add new element
            addNum(nums[i]);

            // Remove element that slid out
            if (i >= k) {
                removeNum(nums[i - k]);
            }

            // Balance heaps
            balanceHeaps();

            // If we have at least k elements, record median
            if (i >= k - 1) {
                result[i - k + 1] = getMedian(k);
            }
        }
        return result;
    }



    private static void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer((double)num);
            smallSize++;
        } else {
            minHeap.offer((double)num);
            largeSize++;
        }
        balanceHeaps();
    }

    private static void removeNum(int num) {
        // Mark the number for delayed removal
        delayed.put((double)num, delayed.getOrDefault(num, 0) + 1);

        // Adjust sizes logically
        if (num <= maxHeap.peek()) {
            smallSize--;
            if (num == maxHeap.peek()) prune(maxHeap);
        } else {
            largeSize--;
            if (num == minHeap.peek()) prune(minHeap);
        }
        balanceHeaps();
    }

    private static void balanceHeaps() {
        // Ensure both heaps are balanced
        if (smallSize > largeSize + 1) {
            minHeap.offer(maxHeap.poll());
            smallSize--;
            largeSize++;
            prune(maxHeap);
        } else if (largeSize > smallSize) {
            maxHeap.offer(minHeap.poll());
            smallSize++;
            largeSize--;
            prune(minHeap);
        }
    }

    private static void prune(PriorityQueue<Double> heap) {
        while (!heap.isEmpty()) {
            double num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) delayed.remove(num);
                heap.poll();
            } else {
                break;
            }
        }
    }

    private static double getMedian(int k) {
        if (k % 2 == 1) return (double) maxHeap.peek();
        else return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
    }
}
