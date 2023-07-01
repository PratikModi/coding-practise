package com.java.coding.interviews.practise.google;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example :
 * [1,1,2] have the following unique permutations:
 *
 * [1,1,2]
 * [1,2,1]
 * [2,1,1]
 */

public class  NumberPermutationsProblem {

    public static void main(String[] args) {
        List<Integer> L = Arrays.asList(new Integer[]{1,2,3,4});
        //System.out.println(permutations2(L));
        L = Arrays.asList(new Integer[]{1,2,3});
        System.out.println(permutations2(L));
        L = Arrays.asList(new Integer[]{1,2,3});
        System.out.println(numberPermutations(L));
        L = Arrays.asList(new Integer[]{1,2,3});
        System.out.println(numberPermutations3(L));
        L = Arrays.asList(new Integer[]{1,2,2});
        System.out.println(numberPermutations(L));
        L = Arrays.asList(new Integer[]{1,2,2});
        System.out.println(numberPermutations3(L));
    }


    public static List<List<Integer>> permutations2(List<Integer> L){
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> X = Arrays.stream(new int[]{1,2,3}).boxed().collect(Collectors.toList());
        //result.add(new ArrayList<>(L));
        backTrack(result,L,0,L.size()-1);
        //   System.out.println(result.size());
        return result;
    }

    private static void backTrack(List<List<Integer>> result,List<Integer> I, int L, int R){
        if(L==R){
            //System.out.println(I);
            result.add(new ArrayList<>(I));
        }
        List<Integer> used = new ArrayList<>();
        for(int i=L;i<=R;i++){
            if(used.contains(I.get(i)))
                continue;
            used.add(I.get(i));
            swap(I,i,L);
            backTrack(result, I, L+1, R);
            swap(I,i,L);
        }
    }

    private static void swap(List<Integer> I, int L, int R){
//        System.out.println(L+"--"+R);
        int temp = I.get(L);
        I.set(L,I.get(R));
        I.set(R,temp);
        //System.out.println(I);
    }

    public static List<List<Integer>> numberPermutations(List<Integer> L){
        List<List<Integer>> result = new ArrayList<>();
        permutationUtil(L,0,result);
        return result;
    }

    public static void permutationUtil(List<Integer> L, int index, List<List<Integer>> result){
        if(index>=L.size()) {
            result.add(new ArrayList<>(L));
            return;
        }else {
            List<Integer> used = new ArrayList<>();
            for (int i = index; i < L.size(); i++) {
                if(used.contains(L.get(i)))
                    continue;
                used.add(L.get(i));
                swap(L, index, i);
                permutationUtil(L, index + 1, result);
                swap(L, index, i);
            }
        }
    }

    public static List<List<Integer>> numberPermutations3(List<Integer> L){
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(L,result,track);
        return result;
    }

    private static void backtrack(List<Integer> L, List<List<Integer>> result,LinkedList<Integer> track){
        if(track.size() == L.size()){
            result.add(new ArrayList<>(track));
            return;
        }
        for(int i=0;i<L.size();i++){
            if(track.contains(L.get(i)))continue;
            track.add(L.get(i));
            backtrack(L,result,track);
            track.removeLast();
        }

    }

}
