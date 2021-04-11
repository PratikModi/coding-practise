package com.java.amazon.dynamic.amazon;

import java.util.*;

/**
 * Maximum Sum Combinations
 * Asked in:
 * Amazon
 * Liv.ai
 * Problem Description
 *
 * Given two equally sized 1-D arrays A, B containing N integers each.
 *
 * A sum combination is made by adding one element from array A and another element of array B.
 *
 * Return the maximum C valid sum combinations from all the possible sum combinations.
 * Problem Constraints
 * 1 <= N <= 105
 * 1 <= A[i] <= 103
 * 1 <= C <= N
 *
 * Input Format
 * First argument is an one-dimensional integer array A of size N.
 * Second argument is an one-dimensional integer array B of size N.
 * Third argument is an integer C.

 * Output Format
 * Return a one-dimensional integer array of size C denoting the top C maximum sum combinations.
 * NOTE:
 *
 * The returned array must be sorted in non-increasing order.
 *
 * Example Input
 * Input 1:
 *
 *  A = [3, 2]
 *  B = [1, 4]
 *  C = 2
 * Input 2:
 *
 *  A = [1, 4, 2, 3]
 *  B = [2, 5, 1, 6]
 *  C = 4
 *
 *  Example Output
 * Output 1:
 *
 *  [7, 6]
 * Output 1:
 *
 *  [10, 9, 9, 8]
 */
public class MaximumSumCombinationProblem {
    public static void main(String[] args) {
        int[] A = {1,4,3,2};
        int[] B = {2,5,1,6};
        int C=4;
        System.out.println(findCombinations(A,B,C));
    }

    private static List<Integer> findCombinations(int[] A, int[] B, int C){
        List<Integer> result = new ArrayList<>();
        if(A==null || B==null || A.length==0 || B.length==0)
            return result;
        Arrays.sort(A);
        Arrays.sort(B);
        PriorityQueue<PairSum> PQ = new PriorityQueue<PairSum>();
        Set<Pair> pairs = new HashSet<>();
        int N = A.length;
        int index1=N-1;
        int index2=N-1;
        PairSum pairSum = new PairSum(A[index1]+B[index2],index1,index2);
        Pair pair = new Pair(index1,index2);
        PQ.offer(pairSum);
        pairs.add(pair);
        for(int i=0;i<C;i++){
            PairSum sum = PQ.poll();
            result.add(sum.sum);
            index1=sum.index1-1;
            index2=sum.index2;
            pair = new Pair(index1,index2);
            if(index1>=0 && index2>=0 && !pairs.contains(pair)){
                pairs.add(pair);
                pairSum=new PairSum(A[index1]+B[index2],index1,index2);
                PQ.offer(pairSum);
            }
            index1=sum.index1;
            index2=sum.index2-1;
            pair = new Pair(index1,index2);
            if(index1>=0 && index2>=0 && !pairs.contains(pair)){
                pairs.add(pair);
                pairSum=new PairSum(A[index1]+B[index2],index1,index2);
                PQ.offer(pairSum);
            }
        }

        return result;
    }

}

class PairSum implements Comparable<PairSum>{
    int sum;
    int index1;
    int index2;

    public PairSum(int sum, int index1, int index2) {
        this.sum = sum;
        this.index1 = index1;
        this.index2 = index2;
    }
    @Override
    public int compareTo(PairSum pairSum) {
        return Integer.compare(pairSum.sum, sum);
    }
}

class Pair{
    int index1;
    int index2;

    public Pair(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return index1 == pair.index1 &&
                index2 == pair.index2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index1, index2);
    }
}