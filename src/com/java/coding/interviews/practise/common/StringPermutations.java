package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pratik1 on 25-04-2020.
 */
public class StringPermutations {

    public static void main(String[] args) {
        System.out.println(permutations("ABC"));
        System.out.println("ABC".substring(0,0));
    }


    public static List<String> permutations(String str){
        List<String> permutations = new ArrayList<>();
        helper("",str,permutations);
        return permutations;
    }

    public static void helper(String prefix, String remaining, List<String> permutations){
        if(remaining.length()==0)
            permutations.add(prefix);
        for(int i=0;i<remaining.length();i++){
            String remain = remaining.substring(0,i)+remaining.substring(i+1);
            //System.out.println(remain);
            helper(prefix+remaining.charAt(i),remain,permutations);
        }
    }

}
