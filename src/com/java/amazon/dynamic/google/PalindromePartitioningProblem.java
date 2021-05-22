package com.java.amazon.dynamic.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome. *
 * Return all possible palindrome partitioning of s. *
 * For example, given s = "aab",
 * Return *
 *   [
 *     ["aa","b"],
 *     ["a","a","b"]
 *   ]
 */
public class PalindromePartitioningProblem {
    public static void main(String[] args) {
        System.out.println(palindromePartition("aab"));
        System.out.println(palindromePartitionDP("aab"));
        System.out.println(palindromePartitionDP("abc"));
    }

    public static List<List<String>> palindromePartition(String S){
        List<String> partition = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        dfs(S,0,result,partition);
        return result;
    }

    private static void dfs(String S, int index, List<List<String>> result, List<String> partition){
        int N = S.length();
        if(index==N){
            result.add(new ArrayList<>(partition));
            return;
        }
        for(int i=index+1;i<=N;i++){
            System.out.println(index+"--"+i);
            String str = S.substring(index,i);
            if(isPalindrome(str)){
                partition.add(str);
                dfs(S,i,result,partition);
                partition.remove(partition.size()-1);
            }
        }
    }

    private static boolean isPalindrome(String S){
        if(S==null)
            return false;
        int N = S.length();
        for(int i=0;i<N/2;i++){
            if(S.charAt(i)!=S.charAt(N-i-1)){
                return false;
            }
        }
        return true;
    }

    public static List<List<String>> palindromePartitionDP(String S){
        if(S==null)
            return  null;
        int N = S.length();
        List<List<String>> result = new ArrayList<>();
        boolean[][] dp = new boolean[N][N];
        //dp[0][0]=true;
        for(int L=1;L<=N;L++){
            List<String> pStr = new ArrayList<>();
            for(int i=0;i<=N-L;i++){
                int j = i+L-1;
                if(S.charAt(i) == S.charAt(j)){
                    if(L==1 || L==2){
                        dp[i][j]=true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                    if(dp[i][j]==true) {
                        pStr.add(S.substring(i, j+1));
                    }
                }else{
                    dp[i][j]=false;
                    //pStr.add(S.substring(j));
                }
            }
            if(!pStr.isEmpty())
                result.add(new ArrayList(pStr));
        }
        //}
        //Arrays.stream(dp).forEach(e->System.out.println(Arrays.toString(e)));
        return result;
    }

}
