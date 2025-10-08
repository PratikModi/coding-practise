package com.java.coding.interviews.practise.google;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.TreeMap;

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
public class SlidingWindowMedianUsingTreeProblem {

    static TreeMap<Integer,Integer> low = new TreeMap<>();
    static TreeMap<Integer,Integer> high = new TreeMap<>();
    static int smallSize=0, largeSize=0;

    public static void main(String[] args) {
        int[] nums = new int[]{-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
        int k=3;
        for(double d : medianSlidingWindow(nums,k)){
            System.out.println(BigDecimal.valueOf(d).toPlainString());
        }
        //System.out.println(Arrays.toString(medianSlidingWindow(nums,k)));
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n-k+1];
        for(int i=0;i<n;i++){
            addNum(nums[i]);
            if(i>=k){
                int out = nums[i-k];
                if(!low.isEmpty() && out<=low.lastKey()){
                    removeNum(low,out);
                    smallSize--;
                }else{
                    removeNum(high,out);
                    largeSize--;
                }
            }
            balance();
            if(i>=k-1){
                result[i-k+1]=getMedian(k);
            }
        }
        return result;
    }

    private static void addNum(int num){
        if(low.isEmpty() || num<=low.lastKey()){
            low.put(num,low.getOrDefault(num,0)+1);
            smallSize++;
        }else{
            high.put(num,high.getOrDefault(num,0)+1);
            largeSize++;
        }
        balance();
    }

    private static void removeNum(TreeMap<Integer,Integer> map,int num){
        int toRemove = map.getOrDefault(num,0);
        if(toRemove<=1) map.remove(num);
        else{
            map.put(num,toRemove-1);
        }
    }

    private static void move(TreeMap<Integer,Integer> map, int num){
        map.put(num,map.getOrDefault(num,0)+1);
    }

    private static void balance(){
        if(smallSize>largeSize+1){
            int move = low.lastKey();
            removeNum(low,move);
            smallSize--;
            move(high,move);
            largeSize++;
        }else if(largeSize>smallSize){
            int move = high.firstKey();
            removeNum(high,move);
            largeSize--;
            move(low,move);
            smallSize++;
        }
    }

    private static double getMedian(int k){
        if(k%2==1) return (double)low.lastKey();
        else{
            return ((double)low.lastKey()+(double) high.firstKey())/2.0;
        }
    }

}
