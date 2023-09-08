package com.java.coding.interviews.practise.rippling;

import java.util.*;

public class SynonymProblem {

    private static Map<String,String> synonymMap(List<String[]> synonyms){
        Map<String, String> syncMap = new HashMap<>();
        Map<String,Set<String>> map = new HashMap<>();
        List<String> allWords = new ArrayList<>();
        for(String[] synonym:synonyms){
            String word1 = synonym[0];
            String word2 = synonym[1];

            allWords.add(word1);
            allWords.add(word2);

            Set<String> sync1 = map.getOrDefault(word1,new HashSet<>());
            sync1.add(word2);
            map.put(word1,sync1);

            Set<String> sync2 = map.getOrDefault(word2,new HashSet<>());
            sync2.add(word1);
            map.put(word2,sync2);
        }

        while(!allWords.isEmpty()){
            Queue<String> queue = new LinkedList<>();
            queue.add(allWords.remove(0));
            Set<String> synonymSet = new HashSet<>();
            while(!queue.isEmpty()){
                String pop = queue.poll();
                synonymSet.add(pop);
                for(String s : map.get(pop)){
                    if(!synonymSet.contains(s)){
                        synonymSet.add(s);
                        queue.add(s);
                        allWords.remove(s);
                    }
                }
            }

            System.out.println(synonymSet);
            String head=null;
            for(String s : synonymSet){
                if(head==null)
                    head=s;
                syncMap.put(s,head);
            }

        }

        return syncMap;
    }

  private static Map<String,Boolean> checkSynonym(List<List<String>> sentences, Map<String,List<String>> synonymMap){
        Map<String,Boolean> map = new HashMap<>();

        for(List<String> sentence : sentences){
            String[] sentence1 = sentence.get(0).split(" ");
            String[] sentence2 = sentence.get(1).split(" ");

            if(sentence1.length!=sentence2.length){
                map.put(sentence.get(0)+"|"+sentence.get(1),false);
                continue;
            }

            int i=0;
            boolean result = true;
            while(result && ++i < sentence1.length){
                if(sentence1[i].equals(sentence2[i])) continue;

                if(!synonymMap.containsKey(sentence1[i])||!synonymMap.containsKey(sentence2[i])){
                    result=false;
                    map.put(sentence.get(0)+"|"+sentence.get(1),false);
                    continue;
                }

                if(!synonymMap.get(sentence1[i]).equals(synonymMap.get(sentence2[i]))){
                    result=false;
                    map.put(sentence.get(0)+"|"+sentence.get(1),false);
                }

            }
            if(result){
                map.put(sentence.get(0)+"|"+sentence.get(1),true);
            }
        }
        return map;
  }




}
