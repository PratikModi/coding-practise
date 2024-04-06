package com.java.coding.interviews.practise.oracle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

//Time Complexity O(n^n)
public class DistinctCombinationProblem {

    public static void main(String[] args) {
        int[] capacity = new int[]{1,3,5,3,5};
        int desiredCapacity = 15;
        var result = findTriplets(capacity,desiredCapacity);
        System.out.println(result);
    }

    public static List<List<Integer>> findTriplets(int[] capacity, int desiredCapacity){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> triplet = new ArrayList<>();
        backTrack(result,triplet,capacity,0,desiredCapacity,1);
        return result;
    }

    public static void backTrack(List<List<Integer>> result, List<Integer> triplet, int[] capacity, int start, int desiredCapacity, int current){
        if(desiredCapacity==current && triplet.size()>2){
            result.add(new ArrayList<>(triplet));
            return;
        }
        for(int i=start;i<capacity.length;i++){
            if(i>start && capacity[i]==capacity[i-1])
                continue;
            triplet.add(capacity[i]);
            backTrack(result,triplet,capacity,i+1,desiredCapacity,current*capacity[i]);
            triplet.remove(triplet.size()-1);
        }
    }
}
