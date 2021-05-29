package com.java.coding.interviews.practise.adobe;

import java.util.*;

/**
 * Sort elements by frequency | Set 5 (using Java Map)
 * Difficulty Level : Medium
 * Last Updated : 11 Dec, 2018
 * Given an integer array, sort the array according to the frequency of elements in decreasing order,
 * if frequency of two elements are same then sort in increasing order
 *
 * Examples:
 *
 * Input: arr[] = {2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12}
 * Output: 3 3 3 3 2 2 2 12 12 4 5
 * Explanation :
 * No. Freq
 * 2  : 3
 * 3  : 4
 * 4  : 1
 * 5  : 1
 * 12 : 2
 *
 * Input: arr[] = {4, 4, 2, 2, 2, 2, 3, 3, 1, 1, 6, 7, 5}
 * Output: 2 2 2 2 1 1 3 3 4 4 5 6 7
 */

public class SortBasedOnFrequencyProblem {

    public static void main(String[] args) {
        int[] A ={2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12};
        System.out.println(Arrays.toString(sortArray(A)));
        System.out.println(Arrays.toString(sortArray2(A)));
    }

    public static int[] sortArray(int[] A){
        List<Integer> result = new ArrayList<>();
        Map<Integer, SortedSet<Integer>> frequency = new TreeMap<>((i1,i2)->i2-i1);
        Map<Integer,Integer> values = new HashMap<>();
        for(int i : A){
            values.put(i,values.getOrDefault(i,0)+1);
        }
        System.out.println(values);
        Set<Integer> keys = values.keySet();
        Iterator<Integer> iterator = keys.iterator();
        while(iterator.hasNext()){
            int key = iterator.next();
            frequency.putIfAbsent(values.get(key),new TreeSet<>());
            frequency.get(values.get(key)).add(key);
        }
        Iterator<Integer> freq = frequency.keySet().iterator();
        while(freq.hasNext()){
            int F = freq.next();
            SortedSet<Integer> TS = frequency.get(F);
            Iterator<Integer> valueIterator = TS.iterator();
            while(valueIterator.hasNext()){
                int value = valueIterator.next();
                for(int i=0;i<F;i++){
                    result.add(value);
                }
            }
        }
        int[] resultArray = result.stream().mapToInt(i->i).toArray();
        return resultArray;
    }

    public static int[] sortArray2(int[] A){
        List<Integer> result = new ArrayList<>();
        Map<Integer, SortedSet<Integer>> frequency = new TreeMap<>((i1,i2)->i2-i1);
        Map<Integer,Integer> values = new HashMap<>();
        for(int i : A){
            values.put(i,values.getOrDefault(i,0)+1);
            result.add(i);
        }
        SortComparator comparator = new SortComparator(values);
        Collections.sort(result,comparator);
        int[] resultArray = result.stream().mapToInt(i->i).toArray();
        return resultArray;
    }

}

class SortComparator implements Comparator<Integer>{
    private Map<Integer,Integer> frequency;
    public SortComparator(Map<Integer,Integer> frequency) {
        this.frequency=frequency;
    }

    @Override
    public int compare(Integer i1, Integer i2) {
        int valueCompare = i1.compareTo(i2);
        int freqCompare = frequency.get(i2).compareTo(frequency.get(i1));
        if(freqCompare==0)
            return valueCompare;
        else
            return freqCompare;
    }
}
