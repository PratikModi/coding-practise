package com.java.coding.interviews.practise.bloomberg;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Return any possible rearrangement of s or return "" if not possible.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: s = "aaab"
 * Output: ""
 */
public class ReorganizeStringProblem {

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));
    }

    public static String reorganizeString(String S){
        if(S==null || S.length()==0)
            return S;
        PriorityQueue<Char> pq = new PriorityQueue<>((a,b)->b.freq-a.freq);
        Map<Character,Integer> charMap = new HashMap<>();
        for(char c : S.toCharArray()){
            charMap.putIfAbsent(c,0);
            charMap.put(c,charMap.get(c)+1);
        }
        for(char c : charMap.keySet()){
            pq.offer(new Char(c,charMap.get(c)));
        }
        Char previous = new Char('@',0);
        StringBuilder result = new StringBuilder();
        while(!pq.isEmpty()){
            Char ch = pq.poll();
            ch.freq--;
            result.append(ch.ch);
            if(previous.freq>0)
                pq.add(previous);
            previous=ch;
        }
        return result.toString().length()==S.length()?result.toString():"";
    }

}
class Char{
    char ch;
    int freq;

    public Char(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "Char{" +
                "ch=" + ch +
                ", freq=" + freq +
                '}';
    }
}