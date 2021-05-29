package com.java.coding.interviews.practise;

import java.util.PriorityQueue;

/**
 * Created by Pratik1 on 21-02-2020.
 */
public class RunningMedian {

    public static double findRunningMedian(int[] A){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);
        int n=A.length;
        for(int i=0;i<n;i++){
            addNumber(A[i],minHeap,maxHeap);
            reBalance(minHeap,maxHeap);
            System.out.println(findMedian(minHeap,maxHeap));
        }
        return -1;
    }

    private static void addNumber(int num, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap){
        if(maxHeap.size()==0 || maxHeap.peek()>=num){
            maxHeap.add(num);
        }else{
            minHeap.add(num);
        }
    }

    private static void reBalance(PriorityQueue<Integer>minHeap, PriorityQueue<Integer> maxHeap){
        if(minHeap.size()-maxHeap.size()>=2)
            maxHeap.add(minHeap.poll());
        else if(maxHeap.size()-minHeap.size()>=2)
            minHeap.add(maxHeap.poll());
        else
            return;
    }

    private static double findMedian(PriorityQueue<Integer>minHeap, PriorityQueue<Integer> maxHeap){
        System.out.println("MIN==>"+minHeap);
        System.out.println("MAX==>"+maxHeap);
        if(minHeap.size()==maxHeap.size()){
            return (double)(minHeap.peek()+maxHeap.peek())/2;
        }else if(minHeap.size()>maxHeap.size()){
            return (double)minHeap.peek();
        }else{
            return (double)maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        int[] A = {1,2,3};
        findRunningMedian(A);
    }
}
