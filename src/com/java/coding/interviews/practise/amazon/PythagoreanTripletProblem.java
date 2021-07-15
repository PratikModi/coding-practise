package com.java.coding.interviews.practise.amazon;

import java.util.*;

/**
 * Input : int[] array = {3,7, 2,24,12,1,5,6,4,9,13,10,25}
 *
 * Expected Output with java toString :
 * [[3, 4, 5], [5, 12, 13], [7, 24, 25]]
 */

//O(n^2)
public class PythagoreanTripletProblem {

    public static void main(String[] args) {
        int[] input = {3,7, 2,24,12,1,5,6,4,9,13,10,25};
        System.out.println(findPythagoreanTriplets(input));
    }

    public static List<List<Integer>> findPythagoreanTriplets(int[] input)  {
        List<List<Integer>> triplets = new ArrayList<>();
        Map<Integer,Integer> squares = new HashMap<>();
        for(int i : input){
            squares.put(i*i,i);
        }
        int N = input.length;
        Arrays.sort(input);
        for(int i=N-1;i>=2;i--){
            int L=0;
            int R=i-1;
            while(L<R){
                int value = (input[L]*input[L])+(input[R]*input[R]);
                if(squares.containsKey(value)){
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(input[L]);
                    triplet.add(input[R]);
                    triplet.add(squares.get(value));
                    triplets.add(triplet);
                    L++;
                    R--;
                }else if(value<input[i]*input[i]){
                    L++;
                }else
                    R--;
            }
        }
        return triplets;
    }

}
