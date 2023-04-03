package com.java.coding.interviews.practise.jpmc;

/**
 * aaabbbcccdd -> a3b3c3d2
 */
public class StringCompressProblem {

    private static String compressString(String S){
        if(S==null || S.length()==0)
            return S;
        int charCount=1;
        char prevChar = S.charAt(0);
        StringBuilder result = new StringBuilder();
        for(int i=1;i<S.length();i++){
            char c = S.charAt(i);
            if(c==prevChar){
                charCount++;
            }else{
                result.append(prevChar).append(charCount);
                prevChar=c;
                charCount=1;
            }
        }
        result.append(prevChar).append(charCount);
        return result.toString();
    }

    private static String decompressString(String S){
        if(S==null || S.length()==0)
            return S;
        int index=0;
        StringBuilder result = new StringBuilder();
        while(index<S.length()){
            char ch = S.charAt(index);
            if(Character.isDigit(ch)){
                if((index-1)>=0 && !Character.isDigit(S.charAt(index-1))){
                    for(int i=0;i<Character.getNumericValue(ch);i++) {
                        result.append(S.charAt(index - 1));
                    }
                }
            }
            index++;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String S = "aaabbbcccdd";
        System.out.println(compressString(S));
        S = "a3b3c3d2";
        System.out.println(decompressString(S));

    }
}
