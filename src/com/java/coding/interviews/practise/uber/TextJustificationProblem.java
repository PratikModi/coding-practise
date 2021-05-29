package com.java.coding.interviews.practise.uber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters
 * and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' '
 * when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 *
 * Example 1:
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 */

public class TextJustificationProblem {
    public static void main(String[] args) {
        String[] words = new String[] {"This", "is", "an", "example", "of", "text", "justification."};
        int maxLength=16;
        var result = justifyText(words,maxLength);
        result.stream().forEach(e-> System.out.println(e));
        //System.out.println(Stream.generate(() -> "str").limit(2).collect(Collectors.joining()));
    }

    public static List<String> justifyText(String[] words, int maxLength){
        List<String> result = new ArrayList<>();
        int i=0;
        int N =words.length;
        while(i<N){
            int j = i+1;
            int lineLength = words[i].length();
            while(j<N && lineLength+words[j].length()+(j-i-1)<maxLength){
                lineLength+=words[j].length();
                ++j;
            }
            int diff = maxLength-lineLength;
            int noOfWords=j-i;
            if(noOfWords==1 || j>=N){
                result.add(leftJustify(words,diff,i,j));
            }else{
                result.add(middleJustify(words,diff,i,j));
            }
            i=j;
        }

        return result;
    }

    private static String leftJustify(String[] words, int diff, int i, int j){
        int spaceOnRight = diff-(j-i-1);
        StringBuilder sb = new StringBuilder(words[i]);
        for(int k=i+1;k<j;k++){
            sb.append(" ").append(words[k]);
        }
        sb.append(" ".repeat(spaceOnRight));
        return sb.toString();
    }

    private static String middleJustify(String[] words, int diff, int i, int j){
        int spaceNeeded = j-i-1;
        //System.out.println(spaceNeeded);
        int spaces = diff/spaceNeeded;
        int extraSpace = diff%spaceNeeded;
        StringBuilder sb = new StringBuilder(words[i]);
        for(int k=i+1;k<j;k++){
            int spaceToApply = spaces+(extraSpace-->0?1:0);
            sb.append(" ".repeat(spaceToApply)).append(words[k]);
        }
        return sb.toString();
    }

}
