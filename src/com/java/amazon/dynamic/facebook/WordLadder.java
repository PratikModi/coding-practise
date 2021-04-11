package com.java.amazon.dynamic.facebook;

import java.util.*;

/**
 * Given a start word, an end word, and a dictionary of valid words,
 * find the shortest transformation sequence from start to end such that only one letter is changed at each step of the sequence,
 * and each transformed word exists in the dictionary. If there is no possible transformation, return null.
 * Each word in the dictionary have the same length as start and end and is lowercase.
 *
 * For example, given start = "dog", end = "cat", and dictionary = {"dot", "dop", "dat", "cat"}, return ["dog", "dot", "dat", "cat"].
 *
 * Given start = "dog", end = "cat", and dictionary = {"dot", "tod", "dat", "dar"},
 * return null as there is no possible transformation from dog to cat.
 */
public class WordLadder {

    public static void main(String[] args) {
        String startWord = "dog";
        String endWord = "cat";
        List<String> dictionary = Arrays.asList(new String[]{"dot", "dop", "dat", "cat"});
        List<String> dictionary2 = Arrays.asList(new String[]{"dot", "dop", "dat", "cat"});
        System.out.println(findLadderLength(startWord,endWord,dictionary));
        System.out.println(findLadderLength2(startWord,endWord,dictionary2));
    }

    public static List<String> findLadderLength(String startWord, String endWord, List<String> dictionary){
        List<String> result = new ArrayList<>();
        Set<String> words = new HashSet<>();
        for(String word:dictionary){
            words.add(word);
        }
        if(!words.contains(endWord))
            return result;
        Queue<String> queue = new LinkedList<>();
        result.add(startWord);
        queue.offer(startWord);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                String current_word = queue.poll();
                char[] chars = current_word.toCharArray();
                for(int j=0;j<chars.length;j++){
                    char org_char = chars[j];
                    String validWord=null;
                    for(char c='a';c<='z';c++){
                        if(chars[j]==c)continue;
                        chars[j]=c;
                        String newWord = String.valueOf(chars);
                        if(words.contains(newWord)){
                            words.remove(newWord);
                            validWord=newWord;
                        }
                    }
                    if(validWord!=null) {
                        result.add(validWord);
                        queue.offer(validWord);
                    }
                    chars[j]=org_char;
                }
            }
        }
        return result;
    }

    public static List<String> findLadderLength2(String startWord, String endWord, List<String> dictionary){
        System.out.println(dictionary);
        List<String> result = new ArrayList<>();
        Set<String> words = new HashSet<>();
        for(String word:dictionary){
            words.add(word);
        }
        if(!words.contains(endWord))
            return result;
        Queue<String> queue = new LinkedList<>();
        result.add(startWord);
        queue.offer(startWord);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                String current_word = queue.poll();
                char[] chars = current_word.toCharArray();
                for(int j=0;j<chars.length;j++){
                    char org_char = chars[j];
                    String validWord=null;
                    for(char c='z';c>='a';c--){
                        if(chars[j]==c)continue;
                        chars[j]=c;
                        String newWord = String.valueOf(chars);
                        if(words.contains(newWord)){
                            words.remove(newWord);
                            validWord=newWord;
                            result.add(validWord);
                            queue.offer(validWord);
                            break;
                        }
                    }
                    /*if(validWord!=null) {
                        result.add(validWord);
                        queue.offer(validWord);
                    }*/
                    chars[j]=org_char;
                }
            }
        }
        return result;
    }


}
