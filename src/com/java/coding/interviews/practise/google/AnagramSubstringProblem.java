package com.java.coding.interviews.practise.google;

/**
 * Created by Pratik1 on 17-05-2020.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Given a word W and a string S, find all starting indices in S which are anagrams of W.

 For example, given that W is "ab", and S is "abxaba", return 0, 3, and 4.
 modified version of Robin Karp Algorithm
 */
public class AnagramSubstringProblem {

    private static int MAX = 256;

    public static void main(String[] args) {
        System.out.println(findSubStringIndexes("abxaba","ab"));
        System.out.println(findAnagrams("abxaba","ab"));
    }

    private static boolean compare(int[] A, int[] B){
        for(int i=0;i<MAX;i++){
            if(A[i]!=B[i]){
                return false;
            }
        }
        return true;
    }

    public static List<Integer> findSubStringIndexes(String S, String P){
        List<Integer> result = new ArrayList<>();
        if(S==null || P==null || S.length()==0 || P.length()==0 || P.length()>S.length())
            return result;
        int[] stringPatternCharCount = new int[MAX];
        int[] stringTextWindowCount = new int[MAX];

        int M = P.length();
        int N = S.length();
        for(int i=0;i<M;i++){
            stringPatternCharCount[P.charAt(i)]++;
            stringTextWindowCount[S.charAt(i)]++;
        }
        for(int i=M;i<N;i++){
            if(compare(stringPatternCharCount,stringTextWindowCount)){
                result.add(i-M);
            }
            stringTextWindowCount[S.charAt(i)]++;
            stringTextWindowCount[S.charAt(i-M)]--;
        }
        if(compare(stringPatternCharCount,stringTextWindowCount)) {
            result.add(N-M);
        }
        return result;
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s.length()<p.length()) return result;
        int[] count = new int[26];
        for(char c : p.toCharArray()){
            count[c-'a']++;
        }
        int left=0, right=0, needed=p.length();
        while(right<s.length()){
            if(count[s.charAt(right)-'a']>0){
                needed--;
            }
            count[s.charAt(right)-'a']--;
            right++;
            if(needed==0){
                result.add(left);
            }
            if(right-left==p.length()){
                if(count[s.charAt(left)-'a']>=0) {
                    needed++;
                }
                count[s.charAt(left)-'a']++;
                left++;
            }
        }
        return result;
    }

}
