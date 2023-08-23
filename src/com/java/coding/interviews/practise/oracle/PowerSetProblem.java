package com.java.coding.interviews.practise.oracle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerSetProblem {

    public static void main(String[] args) {
        int[] A = {1,2,3};
        System.out.println(powerSet(A));
        A = new int[] {1,2,2};
        System.out.println(powerSet(A));
    }

    public static List<List<Integer>> powerSet(int[] A){
        List<List<Integer>> powerset = new ArrayList<>();
        if(A==null || A.length==0)
            return powerset;
        helper(powerset,A,new ArrayList<>(),0);
        return powerset;
    }

    private static void helper(List<List<Integer>> powerset, int[] A, List<Integer> subset, int index){
        if(index==A.length){
            powerset.add(new ArrayList<>(subset));
            return;
        }
        powerset.add(new ArrayList<>(subset));
        Set<Integer> used = new HashSet<>();
        for(int i=index;i<A.length;i++){
            if(!used.contains(A[i])){
                subset.add(A[i]);
                used.add(A[i]);
                helper(powerset,A,subset,i+1);
                subset.remove(subset.indexOf(A[i]));
            }
        }
    }

}
