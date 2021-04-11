package com.java.amazon.dynamic.facebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordFrequencyProblem {

    public static void main(String[] args) {
        add("CAR");
        add("ORANGE");
        add("CAR");
        add("CAR");
        delete("CAR");
        delete("ORANGE");
        add("CAR");
        System.out.println(wordMap);
        System.out.println(frequencyMap);
        System.out.println(check(1));
        System.out.println(check(2));
    }

    private static Map<String,Integer> wordMap = new HashMap<>();
    private static Map<Integer, Set<String>> frequencyMap = new HashMap<>();

    public static void add(String S){
        if(S==null || S.isEmpty())
            return;
        wordMap.putIfAbsent(S,0);
        wordMap.put(S,wordMap.get(S)+1);
        if(wordMap.get(S)>1){
            frequencyMap.get(wordMap.get(S)-1).remove(S);
        }
        frequencyMap.putIfAbsent(wordMap.get(S),new HashSet<>());
        frequencyMap.get(wordMap.get(S)).add(S);
    }

    public static void delete(String S){
        if(S==null || S.isEmpty())
            return;
        if(wordMap.containsKey(S)){
            Integer I = wordMap.get(S);
            if(frequencyMap.containsKey(I)){
                if(frequencyMap.get(I).size()==1 && I==1){
                    frequencyMap.remove(I);
                }else{
                    frequencyMap.get(I).remove(S);
                    frequencyMap.putIfAbsent(I-1,new HashSet<>());
                    frequencyMap.get(I-1).add(S);
                }
            }
            if(wordMap.get(S)==1){
                wordMap.remove(S);
            }else{
                wordMap.put(S,I-1);
            }
        }
    }

    public static Set<String> check(Integer I){
        if(frequencyMap.containsKey(I)){
            return frequencyMap.get(I);
        }
        else{
            return new HashSet<>();
        }

    }

}
