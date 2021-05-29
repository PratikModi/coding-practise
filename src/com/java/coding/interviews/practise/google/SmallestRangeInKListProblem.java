package com.java.coding.interviews.practise.google;

import java.util.*;

/**
 * Given k sorted lists of integers of size n each, find the smallest range that includes
 * at least element from each of the k lists. If more than one smallest ranges are found,
 * print any one of them.
 *
 * Example:
 *
 * Input: K = 3
 * arr1[] : [4, 7, 9, 12, 15]
 * arr2[] : [0, 8, 10, 14, 20]
 * arr3[] : [6, 12, 16, 30, 50]
 * Output:
 * The smallest range is [6 8]
 *
 * Explanation: Smallest range is formed by
 * number 7 from the first list, 8 from second
 * list and 6 from the third list.
 *
 * Input: k = 3
 * arr1[] : [4, 7]
 * arr2[] : [1, 2]
 * arr3[] : [20, 40]
 * Output:
 * The smallest range is [2 20]
 *
 * Explanation:The range [2, 20] contains 2, 4, 7, 20
 * which contains element from all the three arrays.
 */
public class SmallestRangeInKListProblem {

    public static void main(String[] args) {
        List<List<Integer>> L = new ArrayList<>();
        L.add(Arrays.asList(new Integer[]{4,7,9,12,15}));
        L.add(Arrays.asList(new Integer[]{0,8,10,14,20}));
        L.add(Arrays.asList(new Integer[]{6,12,16,30,50}));
        //System.out.println(findSmallestRange(L));
        System.out.println(findSmallestRangeUsingHeap(L));
        //[[-5,-4,-3,-2,-1,1],[1,2,3,4,5]]
        /*L = new ArrayList<>();
        L.add(Arrays.asList(new Integer[]{-5,-4,-3,-2,-1}));
        L.add(Arrays.asList(new Integer[]{1,2,3,4,5}));
        System.out.println(findSmallestRange(L));
        System.out.println(findSmallestRangeUsingHeap(L));*/
    }
    //O(K*N)
    public static List<Integer> findSmallestRange(List<List<Integer>> L){
        List<Integer> result = new ArrayList<>();
        int MIN_RANGE = Integer.MAX_VALUE;
        int K=L.size();
        int MAX_ITR=Integer.MAX_VALUE;
        int MIN_ELEMENT=0;
        int MAX_ELEMENT=0;
        for(List<Integer> lst:L){
            MAX_ITR=Math.min(MAX_ITR,lst.size());
        }
        int[] pointers = new int[K];
        while (true){
            boolean isEnd=false;
            int minValue = Integer.MAX_VALUE;
            int maxValue = Integer.MIN_VALUE;
            int minIndex=Integer.MAX_VALUE;
            for(int i=0;i<K;i++){
                //if End of List
                if(pointers[i]==MAX_ITR){
                    isEnd=true;
                    break;
                }
                //Find Minimum Value
                if(pointers[i]<MAX_ITR && L.get(i).get(pointers[i])<minValue){
                    minIndex=i;
                    minValue=L.get(i).get(pointers[i]);
                }

                //Find Maximum Value
                if(pointers[i]<MAX_ITR && L.get(i).get(pointers[i])>maxValue){
                    maxValue=L.get(i).get(pointers[i]);
                }
            }
            if(isEnd)
                break;
            pointers[minIndex]++;

            if((maxValue-minValue)<MIN_RANGE){
                MIN_ELEMENT=minValue;
                MAX_ELEMENT=maxValue;
                MIN_RANGE=maxValue-minValue;
            }
        }
        result.add(MIN_ELEMENT);
        result.add(MAX_ELEMENT);
        return result;
    }

    public static List<Integer> findSmallestRangeUsingHeap(List<List<Integer>> L){
        List<Integer> result = new ArrayList<>();
        result.add(0,Integer.MAX_VALUE);
        int MAX_VALUE=Integer.MIN_VALUE;

        PriorityQueue<Pair> PQ = new PriorityQueue<>(Comparator.comparingInt(e -> e.val));
        for(List<Integer> LST: L){
            Integer I = LST.get(0);
            result.set(0,Math.min(result.get(0),I)); //Find MIN value in range
            MAX_VALUE=Math.max(MAX_VALUE,I);//Find Max Value in Range
            PQ.add(new Pair(I,LST.iterator()));
        }
        result.add(1,MAX_VALUE);
        //System.out.println(PQ);
        while(!PQ.isEmpty()){
            System.out.println(PQ);
            Pair P = PQ.poll();
            Integer I = P.val;
            Iterator<Integer>ITR = P.iterator;
            if(result.get(1)-result.get(0)>MAX_VALUE-I){
                result.set(0,I);
                result.set(1,MAX_VALUE);
            }
            if(ITR.hasNext()){
                Integer next = ITR.next();
                MAX_VALUE=Math.max(MAX_VALUE,next);
                PQ.add(new Pair(next,ITR));
            }else
                break;

        }
        return result;
    }

}

class Pair{
    public int val;
    public Iterator<Integer> iterator;
    public Pair(int val, Iterator<Integer> iterator) {
        this.val = val;
        this.iterator = iterator;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "val=" + val +
                '}';
    }
}
