package com.java.coding.interviews.practise.google;

import java.util.HashMap;
import java.util.Map;

public class LongestStringNonRepeatingProblem {

    public static void main(String[] args) {
        System.out.println(longestSubStringNonRepeating("GEEKSFORGEEKS"));
    }

    public static int longestSubStringNonRepeating(String S){
        if(S==null || S.isEmpty())
            return 0;
        Map<Character,Integer> COUNT = new HashMap<>();
        int i=0;
        int j=0;
        int max=0;
        int start=0;
        int end=0;
        while(j<S.length()){
            if(COUNT.containsKey(S.charAt(j)) && COUNT.get(S.charAt(j))>=i) {
                i = COUNT.get(S.charAt(j)) + 1;
            }
            else{
                COUNT.put(S.charAt(j),j++);
            }
            if((j-i)>max){
                start=i;
                end=j;
                max=j-i;
            }
            //max=Math.max(max,j-i);
        }
        System.out.println(S.substring(start,end));
        return max;
    }
}
