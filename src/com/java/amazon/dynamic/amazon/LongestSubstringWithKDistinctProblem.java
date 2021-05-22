package com.java.amazon.dynamic.amazon;

import java.util.Arrays;

/**
 * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: The substring is "ece" with length 3.
 * Example 2:
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: The substring is "aa" with length 2.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * 0 <= k <= 50
 */
public class LongestSubstringWithKDistinctProblem {

    private static final int MAX = 26;

    public static void main(String[] args) {
        String S="eceba";
        int K=2;
        System.out.println(lengthOfLongestSubstringKDistinct(S,K));
    }

    private static boolean isValid(int[] count,int k){
        int distinct=0;
        for(int i=0;i<MAX;i++){
            if(count[i]>0) {
                distinct++;
            }
        }
        return (distinct<=k);
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[MAX];
        int N = s.length();
        int distinct=0;
        for (int i = 0; i < N; i++) {
            if(count[s.charAt(i)-'a']==0){
                distinct++;
            }
            count[s.charAt(i)-'a']++;
        }
        if(distinct<k){
            return 0;
        }
        Arrays.fill(count,0);
        int max_window=1;
        int start=0,end=0;
        int max_window_start=0;
        count[s.charAt(0)-'a']++;
        for(int i=1;i<N;i++){
            count[s.charAt(i)-'a']++;
            end++;
            while(!isValid(count,k)){
                count[s.charAt(start) - 'a']--;
                start++;
            }
            if(max_window<(end-start+1)){
                max_window=end-start+1;
                max_window_start=start;
            }
        }
        System.out.println(s.substring(max_window_start,max_window_start+max_window));
        return max_window;
    }

}
