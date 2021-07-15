package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem:
 * Given a string, split it into as few strings as possible such that each string is a
 * palindrome.
 * For example, given the input string "racecarannakayak", return
 * ["racecar", "anna", "kayak"].
 * Given the input string "abc", return ["a", "b", "c"].
 */
public class PalindromePartitionProblem {

    public static void main(String[] args) {
        System.out.println(findPalindromePartitions("racecarannakayak"));
        System.out.println(findPalindromePartitions("abc"));
    }

    //O(N^2)
    public static List<String> findPalindromePartitions(String S){
        List<String> result = new ArrayList<>();
        if(S==null || S.length()==0)
            return result;
        Tuple[] DP = new Tuple[S.length()];
        for(int i=0;i<S.length();i++){
            DP[i] = new Tuple(i,i+1);
        }
        for(int i=0;i<S.length();i++){
            recursion(S,i,i,DP);
            if(i+1<=S.length()-1)
                recursion(S,i,i+1,DP);
        }
        int i=S.length()-1;
        while(i>=0){
            result.add(S.substring(DP[i].index,i+1));
            i=DP[i].index-1;
        }
        //System.out.println(Arrays.toString(DP));
        Collections.reverse(result);
        return result;
    }


    private static void recursion(String S, int left, int right, Tuple[] DP){
        int N = S.length();
        if(S.charAt(left)!=S.charAt(right))
            return;
        while(left>=0 && right<=N-1){
            if(S.charAt(left)==S.charAt(right)){
                Tuple newTuple = new Tuple(left, left>0?DP[left-1].count+1:1);
                if(DP[right].count >= newTuple.count) {
                    DP[right] = newTuple;
                }
                left-=1;
                right+=1;
            }else{
                break;
            }
        }
    }
}

class Tuple{
    int index;
    int count;

    public Tuple(int index, int count) {
        this.index = index;
        this.count = count;
    }
    @Override
    public String toString() {
        return "Tuple{" +
                "index=" + index +
                ", count=" + count +
                '}';
    }
}
