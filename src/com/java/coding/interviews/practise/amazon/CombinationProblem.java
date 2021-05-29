package com.java.coding.interviews.practise.amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 2 3 ... n.
 *
 * Make sure the combinations are sorted.
 *
 * To elaborate,
 *
 * Within every entry, elements should be sorted. [1, 4] is a valid entry while [4, 1] is not.
 * Entries should be sorted within themselves.
 * Example :
 * If n = 4 and k = 2, a solution is:
 *
 * [
 *   [1,2],
 *   [1,3],
 *   [1,4],
 *   [2,3],
 *   [2,4],
 *   [3,4],
 * ]
 */

public class CombinationProblem {

    public static void main(String[] args) {
        System.out.println(findCombinations(4,2));
    }

    public static List<List<Integer>> findCombinations(int N, int K){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combinations = new ArrayList<>();
        util(result,combinations,N,K,1);
        return result;
    }

    private static void util(List<List<Integer>> result, List<Integer> combinations, int N, int K, int SN){
        if(K==0){
            result.add(new ArrayList<>(combinations));
            return;
        }
        for(int i=SN;i<=N;i++){
            combinations.add(i);
            util(result,combinations,N,K-1,i+1);
            combinations.remove(combinations.size()-1);
        }
    }



}
