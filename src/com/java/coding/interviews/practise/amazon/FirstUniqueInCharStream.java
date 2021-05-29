package com.java.coding.interviews.practise.amazon;

import java.util.*;

public class FirstUniqueInCharStream {
    public static void main(String[] args) {
        System.out.println(firstNonUnique("jpxvxivxkkthvpqhhhjuzhkegnzqriokhsgea"));
    }

    public static String firstNonUnique(String A){
        if(A==null || A.length()==0)
            return A;
        int[] COUNT = new int[26];
        Queue<Character> Q = new ArrayDeque<>();
        int N = A.length();
        char[] ans = new char[N];
        for(int i=0;i<N;i++){
            char c = A.charAt(i);
            COUNT[c-'a']++;
            if(COUNT[c-'a']==1){
                Q.add(c);
            }
            while(!Q.isEmpty() && COUNT[Q.peek()-'a']>1){
                Q.poll();
            }
            if(Q.isEmpty())
                ans[i]='#';
            else{
                ans[i]=Q.peek();
            }
        }
        return new String(ans);
    }

}
