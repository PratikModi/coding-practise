package com.java.coding.interviews.practise.common;

import java.util.*;

public class SynonymProblem {

    public static void main(String[] args) {
        String[] synonym1 = {"primary","main"};
        String[] synonym2 = {"main", "prioritized"};
        String[] synonym3 = {"rating", "score"};

        List<String[]> synonyms = List.of(synonym1,synonym3);
        var synonymMap = getSynonymsSimple(synonyms);
        List<String> sentences = List.of("primary mail","main mail","performance rating","performance score","bank account country");
        var result = checkSimilarity(sentences,synonymMap);
        System.out.println(result);

        synonyms = List.of(synonym1,synonym2,synonym3);
        synonymMap = getSynonyms(synonyms);
        sentences = List.of("primary mail","main mail","performance rating","performance score","bank account country","prioritized mail");
        result = checkSimilarity(sentences,synonymMap);
        System.out.println(result);
    }

    public static Map<String,String> getSynonymsSimple(List<String[]> synonyms){
        Map<String,String> synonymMap = new HashMap<>();
        if(synonyms==null || synonyms.isEmpty()){
            return synonymMap;
        }
        for(String[] synonym :synonyms){
            String w1 = synonym[0];
            String w2 = synonym[1];

            if(synonymMap.containsKey(w1) || synonymMap.containsKey(w2)){
                String value = synonymMap.get(w1);
                if(value==null){
                    value = synonymMap.get(w2);
                }
                synonymMap.put(w1,value);
                synonymMap.put(w2,value);
            }else{
                synonymMap.put(w1,w1);
                synonymMap.put(w2,w1);
            }
        }
        return synonymMap;
    }

    public static Map<String,String> getSynonyms(List<String[]> synonyms){
        Map<String,String>synonymMap = new HashMap<>();
        if(synonyms==null || synonyms.isEmpty())
            return synonymMap;
        Map<String, Set<String>> adjList = new HashMap<>();
        Set<String> allWords = new HashSet<>();
        for(String[] synonym : synonyms){
            String w1 = synonym[0];
            String w2 = synonym[1];

            allWords.add(w1);
            allWords.add(w2);

            Set<String> syn1 = adjList.getOrDefault(w1,new HashSet<>());
            syn1.add(w2);
            adjList.put(w1,syn1);

            Set<String> syn2 = adjList.getOrDefault(w2,new HashSet<>());
            syn2.add(w1);
            adjList.put(w2,syn2);
        }
        List<String> allWordsList = new ArrayList<>(allWords);
        while(!allWordsList.isEmpty()){
            Queue<String> queue = new LinkedList<>();
            queue.add(allWordsList.remove(0));
            Set<String> synonymSet = new HashSet<>();
            while(!queue.isEmpty()){
                String poll = queue.poll();
                synonymSet.add(poll);
                Set<String> set = adjList.get(poll);
                for(String s : set){
                    if(!synonymSet.contains(s)){
                        synonymSet.add(s);
                        queue.add(s);
                    }
                }
            }
            String parent = null;
            for(String s : synonymSet){
                if(parent==null) {
                    parent = s;
                }
                synonymMap.put(s,parent);
            }
        }
        return synonymMap;
    }

    public static Map<String,List<String>> checkSimilarity(List<String> sentences, Map<String,String> synonymMap){
        Map<String,List<String>> resultMap = new HashMap<>();
        for(String sentence : sentences){
            String normalized = normalize(sentence,synonymMap);
            if(resultMap.containsKey(normalized)){
                resultMap.get(normalized).add(sentence);
            }else{
                List<String> list = new ArrayList<>();
                list.add(sentence);
                resultMap.put(normalized,list);
            }
        }
        return resultMap;
    }

    public static String normalize(String sentence, Map<String,String> synonymMap){
        String[] split = sentence.split(" ");
        StringBuilder normalized = new StringBuilder();
        for(String s : split){
            if(!synonymMap.containsKey(s)){
                normalized.append(s).append(" ");
            }else{
                normalized.append(synonymMap.get(s)).append(" ");
            }
        }
        return normalized.toString();
    }

}
