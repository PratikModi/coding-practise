package com.java.coding.interviews.practise.coupang;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return the max sliding window.
 * Example 1:
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
 * Input: nums = [1], k = 1
 * Output: [1]
 */

/*
APPROACH

Follow the given steps to solve the problem:

Create a deque to store K elements.
Run a loop and insert the first K elements in the deque. Before inserting the element, check if the element at the back of the queue is smaller than the current element,
 if it is so remove the element from the back of the deque until all elements left in the deque are greater than the current element. Then insert the current element,
 at the back of the deque.
Now, run a loop from K to the end of the array.
Print the front element of the deque.
Remove the element from the front of the queue if they are out of the current window.
Insert the next element in the deque. Before inserting the element, check if the element at the back of the queue is smaller than the current element,
if it is so remove the element from the back of the deque until all elements left in the deque are greater than the current element.
Then insert the current element, at the back of the deque.
Print the maximum element of the last window.
*/

public class MaximumWindowProblem {

    public static void main(String[] args) {
        int[] numArray = new int[]{1,-1};
        int k = 1;
        System.out.println(Arrays.toString(findMaximum(numArray,k)));
    }

    public static int[] findMaximum(int[] numArray, int k){
        if(numArray==null || numArray.length==0)
            return numArray;
        Deque<Integer> deque = new LinkedList<>();
        int n = numArray.length;
        int[] output = new int[n-k+1];
        int i;
        for(i=0;i<k;i++){
            while(!deque.isEmpty() && numArray[i]>=numArray[deque.peekLast()])
                deque.removeLast();
            deque.addLast(i);
        }
        output[i-k] = numArray[deque.peek()];
        for(;i<n;i++){
            while(!deque.isEmpty() && deque.peek()<=i-k)
                deque.removeFirst();
            while(!deque.isEmpty() && numArray[i]>=numArray[deque.peekLast()])
                deque.removeLast();
            deque.addLast(i);
            output[i-k+1] = numArray[deque.peek()];
        }

        return output;
    }





}
