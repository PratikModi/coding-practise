package com.java.amazon.dynamic;

import java.util.HashMap;

/**
 * Created by Pratik1 on 16-02-2020.
 */
public class StringNonRepeating {

    public static Character findFirstNonRepeating(String s){
        if(s==null || s.length()==0){
            return null;
        }
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c:s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(char c:s.toCharArray()){
            if(map.get(c)==1) return c;
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(findFirstNonRepeating("AAABCBCZ"));
    }

}
