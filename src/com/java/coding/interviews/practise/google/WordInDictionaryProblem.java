package com.java.coding.interviews.practise.google;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string of words if a target (for example "breath") is present such that each letter appears in a different word,return true or false,
 * The order of words have to be maintained.
 * Example:
 * input:"abc prq def at pqr the hello" target: breath
 *
 * a[b]c
 * p[r]q
 * d[e]f
 * [a]t pqr
 * [t]he
 * [h]ello
 *
 * output:true
 *
 * Follow up: what if words can be upto k distance apart? that is <=k
 * k characters apart from the currently matched character !!
 */

public class WordInDictionaryProblem {

    public static void main(String[] args) {
        String[] dictionary = new String[] {"abc","prq","def","at pqr","the","hello"};
        String word ="breath";
        int K=2;
        System.out.println(find(dictionary,word,K));
    }

    public static boolean find(String[] dictionary, String word, int K){
        if(word.length()>dictionary.length)
            return false;
        List<Set<Character>> characterSet = new ArrayList<>();
        for(String D : dictionary){
            Set<Character> characters = new HashSet<>();
            for(char c : D.toCharArray()) {
                characters.add(c);
            }
            characterSet.add(characters);
        }
        boolean[][] DP = new boolean[word.length()][dictionary.length];

        for(int dictId=0;dictId<dictionary.length;dictId++){
            if(contains(characterSet,dictId,word.charAt(0))){
                DP[0][dictId]=true;
            }
        }

        for(int wordId=1;wordId<word.length();wordId++){
            for(int dictId=0;dictId<dictionary.length;dictId++){
                if(!contains(characterSet,dictId,word.charAt(wordId))) continue;
                for(int i=dictId-1;i>=Math.max(0,dictId-K);i--){
                    if(DP[wordId-1][i]){
                        DP[wordId][dictId]=true;
                    }
                }
            }
        }

        for(int dictId=0;dictId<dictionary.length;dictId++){
            if(DP[word.length()-1][dictId])
                return true;
        }

        return false;
    }

    private static boolean contains(List<Set<Character>> characterSet, int dictId, char c){
        return characterSet.get(dictId).contains(c);
    }

}
