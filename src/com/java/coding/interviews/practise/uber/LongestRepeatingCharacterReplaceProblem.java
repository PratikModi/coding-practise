package com.java.coding.interviews.practise.uber;

/**
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achive this answer too.
 */
//Time Complexity : O(N)
public class LongestRepeatingCharacterReplaceProblem {

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB",2));
        System.out.println(characterReplacement("AABABBA",1));
    }

    public static int characterReplacement(String s, int k) {

        int left=0;
        int max=0;
        int mostFrequentLetter=0;
        int[] frequency = new int[26];

        for(int right=0;right<s.length();right++){
            frequency[s.charAt(right)-'A']++;
            mostFrequentLetter = Math.max(mostFrequentLetter,frequency[s.charAt(right)-'A']);

            int letterToChange = (right-left+1)-mostFrequentLetter;
            if(letterToChange>k){
                frequency[s.charAt(left)-'A']--;
                left++;
            }
            max = Math.max(max,right-left+1);
        }
        return max;
    }
}
