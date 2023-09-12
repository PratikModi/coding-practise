package com.java.coding.interviews.practise.rippling;

import java.util.*;

/**
 * Questions: -
 * 1. Can we have a cycle in synonyms?
 *  DFS method to use
 */
public class SynonymProblem {

    private static Map<String,String> synonymMapSimple(List<String[]> synonyms){
        Map<String, String> synonymMap = new HashMap<>();
        for(String[] synonym : synonyms){
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

    private static Map<String,String> synonymMap(List<String[]> synonyms){
        Map<String, String> syncMap = new HashMap<>();
        Map<String,Set<String>> map = new HashMap<>();
        Set<String> allWordsSet = new HashSet<>();
        for(String[] synonym:synonyms){
            String word1 = synonym[0];
            String word2 = synonym[1];

            allWordsSet.add(word1);
            allWordsSet.add(word2);

            Set<String> sync1 = map.getOrDefault(word1,new HashSet<>());
            sync1.add(word2);
            map.put(word1,sync1);

            Set<String> sync2 = map.getOrDefault(word2,new HashSet<>());
            sync2.add(word1);
            map.put(word2,sync2);
        }
        System.out.println("ADJ LIST:::"+map);
        List<String> allWordsList = new ArrayList<>(allWordsSet);

        while(!allWordsList.isEmpty()){
            Queue<String> queue = new LinkedList<>();
            queue.add(allWordsList.remove(0));
            Set<String> synonymSet = new HashSet<>();
            while(!queue.isEmpty()){
                String pop = queue.poll();
                synonymSet.add(pop);
                for(String s : map.get(pop)){
                    if(!synonymSet.contains(s)){
                        synonymSet.add(s);
                        queue.add(s);
                        allWordsList.remove(s);
                    }
                }
            }
            System.out.println("Synonym Set:::"+synonymSet);
            String head=null;
            for(String s : synonymSet){
                if(head==null)
                    head=s;
                syncMap.put(s,head);
            }
        }
        return syncMap;
    }

    private static Map<String,Boolean> checkSimilarity(List<List<String>> sentences, Map<String,String> synonymMap){
        Map<String,Boolean> map = new HashMap<>();

        for(List<String> sentence : sentences){
            String[] sentence1 = sentence.get(0).split(" ");
            String[] sentence2 = sentence.get(1).split(" ");

            if(sentence1.length!=sentence2.length){
                map.put(sentence.get(0)+"|"+sentence.get(1),false);
                continue;
            }

            int i=-1;
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

    public static List<Map<String,List<String>>> checkSimilarity2(List<String> sentences, Map<String,String> synonymMap){
        List<Map<String,List<String>>> resultMap = new ArrayList<>();
        Map<String,Boolean> visited = new HashMap<>();
        sentences.stream().forEach(e->visited.put(e,false));
        for(String sentence :sentences) {
            if (!visited.get(sentence)) {
                Map<String, List<String>> map = new HashMap<>();
                map.put(sentence, new ArrayList<>());
                for(String s : sentences) {
                    if (!visited.containsKey(s) || !visited.get(s)) {
                        String[] sentence1 = sentence.split(" ");
                        String[] sentence2 = s.split(" ");
                        int i = -1;
                        boolean result = true;
                        while (result && ++i < sentence1.length) {
                            if (sentence1[i].equals(sentence2[i])) {
                                continue;
                            }
                            if (sentence1.length != sentence2.length) {
                                result = false;
                            } else if (!synonymMap.containsKey(sentence1[i]) || !synonymMap.containsKey(sentence2[i]) || !synonymMap.get(sentence1[i]).equals(synonymMap.get(sentence2[i]))) {
                                result = false;
                            }
                        }
                        visited.put(sentence, true);
                        if (result) {
                            map.get(sentence).add(s);
                            visited.put(s, true);
                        }
                    }
                }
                resultMap.add(new HashMap<>(map));
            }
        }
        return resultMap;
    }

    public static void main(String[] args) {
        String[] syn1 = {"a", "b"};
        String[] syn2 = {"c", "d"};
        String[] syn3 = {"e", "f"};
        String[] syn4 = {"c", "f"};
        String[] syn5 = {"f", "b"};
        String[] syn6 = {"x", "y"};
        String[] syn7 = {"p", "q"};
        List<String[]> list = new ArrayList<>();
        list.add(syn1); list.add(syn2); list.add(syn3); list.add(syn4);
        list.add(syn5); list.add(syn6); list.add(syn7);

        var synonymMap = synonymMap(list);

        List<List<String>> sentenceList = new ArrayList<>();

        List<String> sentence1 = new ArrayList<>();
        sentence1.add("c is e");
        sentence1.add("f is d");
        sentenceList.add(sentence1);

        List<String> sentence2 = new ArrayList<>();
        sentence2.add("x is p");
        sentence2.add("x is y");
        sentenceList.add(sentence2);

        List<String> sentence3 = new ArrayList<>();
        sentence3.add("c is e");
        sentence3.add("f is m");
        sentenceList.add(sentence3);

        Map<String, Boolean> stringBooleanMap = checkSimilarity(sentenceList, synonymMap);
        System.out.println(stringBooleanMap);

        String[] split = sentence1.get(0).split(" ");
        Arrays.sort(split, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(split));

        String[] syn8 = {"main", "primary"};
        String[] syn9 = {"rating", "score"};
        String[] syn10 = {"primary", "secondary"};

        List<String[]> list2 = List.of(syn8,syn9,syn10);
        synonymMap = synonymMap(list2);
        System.out.println(synonymMap);
        List<String> sentences = new ArrayList<>();
        sentences.add("primary email");
        sentences.add("main email");
        sentences.add("performance rating");
        sentences.add("performance score");
        sentences.add("secondary email");
        sentences.add("bank account country");

        var result = checkSimilarity2(sentences,synonymMap);
        System.out.println(result);

    }



}
