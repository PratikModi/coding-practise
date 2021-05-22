package com.java.amazon.dynamic.twitter;

import java.util.*;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Example 3:
 *
 * Input: nums = [1,-1], k = 1
 * Output: [1,-1]
 * Example 4:
 *
 * Input: nums = [9,11], k = 2
 * Output: [11]
 * Example 5:
 *
 * Input: nums = [4,-2], k = 2
 * Output: [4]
 */
public class SlidingWindowMaximumProblem {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(nums));
        System.out.println("==========================");
        int K = 3;
        int[] result = maxSlidingWindow(nums, K);
        System.out.println(Arrays.toString(result));
        result = maxSlidingWindowUsingDQ(nums,K);
        System.out.println(Arrays.toString(result));

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int res[] = new int[len - k + 1];
        int left[] = new int[len];
        int right[] = new int[len];
        for (int i = 0; i < len; i++) {
            if (i % k == 0) left[i] = nums[i];// splitting the left window & it is start of the window
            else left[i] = Math.max(left[i - 1], nums[i]);
        }
        //System.out.println(Arrays.toString(left));
        for (int i = len - 1; i > -1; --i) {
            if ((i + 1) % k == 0 || i == len - 1)
                right[i] = nums[i]; // splitting the right window & it is start of the window
            else right[i] = Math.max(right[i + 1], nums[i]);
        }
        //System.out.println(Arrays.toString(right));
        for (int i = 0; i < len - k + 1; i++) {
            res[i] = Math.max(right[i], left[i + k - 1]);
        }
        return res;
    }

    // Time Complexity O(N)
    public static int[] maxSlidingWindowUsingDQ(int[] nums, int k) {
        int N = nums.length;
        Deque<Pair> DQ = new LinkedList<>();
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            System.out.println(DQ);
            if (!DQ.isEmpty() && DQ.peekFirst().index <= (i - k)) // Maintain the range of sliding window
                DQ.pollFirst();
            while (!DQ.isEmpty() && DQ.peekLast().value < nums[i]) { // Maintain the DQ in descending order
                DQ.pollLast();
            }
            DQ.offerLast(new Pair(nums[i], i));
            if(i>=k-1){ // only pop if i is withing K size window
                resultList.add(DQ.peekFirst().value);
            }

        }
        int[] result = new int[resultList.size()];
        for(int i=0;i<resultList.size();i++){
            result[i]=resultList.get(i);
        }
        return result;
    }
}

    class Pair{
        int value;
        int index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "value=" + value +
                    ", index=" + index +
                    '}';
        }
    }


