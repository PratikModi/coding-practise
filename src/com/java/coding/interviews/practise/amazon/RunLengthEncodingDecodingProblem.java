package com.java.coding.interviews.practise.amazon;

/**
 * Run-length encoding is a fast and simple method of encoding strings.
 * The basic idea is to represent repeated successive characters as a single count and character.
 * For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
 *
 * Implement run-length encoding and decoding.
 * You can assume the string to be encoded have no digits and consists solely of alphabetic characters.
 * You can assume the string to be decoded is valid.
 */
public class RunLengthEncodingDecodingProblem {

    public static void main(String[] args) {
        String encodedString = encode("AAAABBBCCDAA");
        System.out.println(encodedString);
        String decodedString = decode(encodedString);
        System.out.println(decodedString);
    }

    public static String encode(String S){
        String encodedString="";
        if(S==null || S.isEmpty())
            return S;
        char previousChar=S.charAt(0);
        int charCount=1;
        for(int i=1;i<S.length();i++){
            if(previousChar==S.charAt(i)){
                charCount++;
            }else{
                encodedString+=charCount+""+previousChar;
                charCount=1;
                previousChar=S.charAt(i);
            }
        }
        encodedString+=charCount+""+previousChar;
        return encodedString;
    }

    public static String decode(String S){
        StringBuilder decodedString=new StringBuilder("");
        if(S==null || S.isEmpty())
            return S;
        int charCount=0;
        for(int i=0;i<S.length();i++){
            if(i%2==0){
                charCount=Integer.parseInt(S.charAt(i)+"");
            }else{
                decodedString.append(String.valueOf(S.charAt(i)).repeat(charCount));
                charCount=0;
            }
        }
        return decodedString.toString();
    }

}
