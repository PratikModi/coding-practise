package com.java.coding.interviews.practise.google;

/**
 * Sentence Screen Fitting
 *
 * Given a rows x cols screen and a sentence represented as a list of strings, return the number of times the given sentence can be fitted on the screen.
 *
 * The order of words in the sentence must remain unchanged, and a word cannot be split into two lines. A single space must separate two consecutive words in a line.
 *
 * Example 1:
 *
 * Input: sentence = ["hello","world"], rows = 2, cols = 8
 * Output: 1
 * Explanation:
 * hello---
 * world---
 * The character '-' signifies an empty space on the screen.
 * Example 2:
 *
 * Input: sentence = ["a", "bcd", "e"], rows = 3, cols = 6
 * Output: 2
 * Explanation:
 * a-bcd-
 * e-a---
 * bcd-e-
 * The character '-' signifies an empty space on the screen.
 * Example 3:
 *
 * Input: sentence = ["i","had","apple","pie"], rows = 4, cols = 5
 * Output: 1
 * Explanation:
 * i-had
 * apple
 * pie-i
 * had--
 * The character '-' signifies an empty space on the screen.
 *
 */

public class SentenceScreenFitProblem {

    public static void main(String[] args) {
        String[] sentence = new String[] {"a", "bcd", "e"};
        System.out.println(screenFit(sentence,3,6));
    }

    public static int screenFit(String[] sentence, int row, int col){
        StringBuilder SB = new StringBuilder();
        for(String S : sentence){
            SB.append(S).append(" ");
        }
        int start=0;
        for(int i=0;i<row;i++){
            start+=col;
            if(SB.charAt(start%SB.length())==' '){
                start++;
            }else{
                while (start>0 && SB.charAt((start-1)%SB.length())!=' ')
                    start--;
            }
        }
        return start/SB.length();
    }
}
