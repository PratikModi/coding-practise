package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 17-05-2020.
 */

/**
 * Given a string of words delimited by spaces, reverse the words in string.
 * example, given "hello world here", return "here world hello"
 */
public class ReverseWords {

    public static void main(String[] args) {
        System.out.println(reverseWords("PRATIK MODI"));
        System.out.println(reverseWords("HELLO WORLD HERE"));
        System.out.println(reverseWords("       fwbpudnbrozzifml osdt ulc jsx kxorifrhubk ouhsuhf sswz qfho dqmy sn myq igjgip iwfcqq                 "));
    }

    private static String reverseString(String str){
        //System.out.println(str);
        String reverse = "";
        for(int i=str.length()-1;i>=0;i--){
            reverse+=str.charAt(i);
        }
        return reverse;
    }

    public static String reverseWords(String words){
        String output="";
        if(words==null || words.length()==0){
            return words;
        }
        int i=0;
        while(words.length()>0){
            int index = words.indexOf(" ");
            if(index!=-1) {
                output+=reverseString(words.substring(i,index))+" ";
                words=words.substring(index+1).trim();
            }else{
                output+=reverseString(words.substring(i));
                words="";
            }
        }
        return reverseString(output);
    }


}
