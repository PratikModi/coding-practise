package com.java.coding.interviews.practise.bloomberg;

import java.util.*;

/**
 * Given the Welsh Alphabet, which is slightly different than the English one, design a function that sorts a list of words by that alphabet:
 * "a b c ch d dd e f ff g ng h i j l ll m n o p ph r rh s t th u w y"
 * Note: "double" letters supersede single letters so "ng" would be considered in its current order and not as an "n g".
 */
public class WelshSortingProblem {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("ddr");
        words.add("nah");
        words.add("dea");
        words.add("dd");
        words.add("ngah");
        System.out.println(words);
        welshSort(words);
        System.out.println("=================================");
        System.out.println(words);
    }

    public static void welshSort(List<String> words){
        if(words==null || words.isEmpty())
            return;
        String[] alpha = "a b c ch d dd e f ff g ng h i j l ll m n o p ph r rh s t th u w y".split(" ");
        Map<String,Integer> alphaIndex = new HashMap<>();
        int index=0;
        for(String s : alpha){
            alphaIndex.put(s,index++);
        }
        Collections.sort(words,(e1,e2)->{
            List<Integer> alpha1 = split(e1.toCharArray(),alphaIndex);
            List<Integer> alpha2 = split(e2.toCharArray(),alphaIndex);
            int i=0,j=0;
            while(i<alpha1.size() && j<alpha2.size()){
                if(alpha1.get(i)!=alpha2.get(j)){
                    return alpha1.get(i)-alpha2.get(j);
                }else{
                    i++;
                    j++;
                }
            }
            return alpha1.size()>alpha2.size()?1:-1;
        });
    }

    private static List<Integer> split(char[] chars , Map<String,Integer> alphaIndex){
        List<Integer> splitChars = new ArrayList<>();
        int index=0;
        while(index<chars.length-1){
            if(alphaIndex.containsKey(""+chars[index]+chars[index+1])){
                splitChars.add(alphaIndex.get(""+chars[index]+chars[index+1]));
                index+=2;
            }else{
                splitChars.add(alphaIndex.get(""+chars[index]));
                index+=1;
            }
        }
        if(index< chars.length){
            splitChars.add(alphaIndex.get(""+chars[index]));
        }
        return splitChars;
    }



}
