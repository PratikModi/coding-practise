package com.java.coding.interviews.practise.twilio;

import java.util.ArrayList;
import java.util.List;

/**
 * Input is a string of characters that represents a text message.
 * You need to segment this message into chunks of messages each of length 160 characters and add suffix "(1/5)"
 * (representing pagination) at the end of each segmented message (Length of "(1/5)" is included in 160 length limit).
 *
 * Input: "njdksjfn jdfnds kjfdklsjf jsdofjsd f jdslkjfgdslkngdslkjg fljksdjflsfdsjfdslkfjdslkfmdsklmfgn ljsdglkdsfg d lkjgdslkgjdsljgdslkjgdsfjngds lkjsdlkgjdsgkldsjgsdlkg lkjdslkgjdslkgjdslgmnds glkjgdslkjgdslkjfgodsjfds g,mdsgkjdsngdlsknfgldsjfglkdsjfglkdsjglkdsjglkdsgjdsklgjdslk lkgjdslkgfjdslkgjdslkgjdsljfgdslkgjmdslkg kljghjdslkjgdslkjfg"
 *
 * Output: ['njdksjfn jdfnds kjfdklsjf jsdofjsd f jdslkjfgdslkngdslkjg fljksdjflsfdsjfdslkfjdslkfmdsklmfgn ljsdglkdsfg d lkjgdslkgjdsljgdslkjgdsfjngds (1/3)', 'lkjsdlkgjdsgkldsjgsdlkg lkjdslkgjdslkgjdslgmnds glkjgdslkjgdslkjfgodsjfds g,mdsgkjdsngdlsknfgldsjfglkdsjfglkdsjglkdsjglkdsgjdsklgjdslk (2/3)', 'lkgjdslkgfjdslkgjdslkgjdsljfgdslkgjmdslkg kljghjdslkjgdslkjfg(3/3)']
 */
public class SplitMessageProblem {
    private static int MESSAGE_SIZE = 160;
    public static void main(String[] args) {
        String message = "njdksjfn jdfnds kjfdklsjf jsdofjsd f jdslkjfgdslkngdslkjg fljksdjflsfdsjfdslkfjdslkfmdsklmfgn ljsdglkdsfg d lkjgdslkgjdsljgdslkjgdsfjngds lkjsdlkgjdsgkldsjgsdlkg lkjdslkgjdslkgjdslgmnds glkjgdslkjgdslkjfgodsjfds g,mdsgkjdsngdlsknfgldsjfglkdsjfglkdsjglkdsjglkdsgjdsklgjdslk lkgjdslkgfjdslkgjdslkgjdsljfgdslkgjmdslkg kljghjdslkjgdslkjfg";
        System.out.println(splitMessage(message));
    }

    public static List<String> splitMessage(String message){
        List<String> result = new ArrayList<>();
        if(message==null || message.isEmpty())
            return result;
        if(message.length()<160) {
            result.add(message);
            return result;
        }
        int MAX_LENGTH = 154;
        int s=0,e=s+MAX_LENGTH;
        while(e<message.length()){
            while (e >= s && message.charAt(e) != ' ' && message.charAt(e + 1) != ' ') {
                e--;
            }
            result.add(message.substring(s,e+1));
            s=e+1;
            e=s+MAX_LENGTH;
        }
        result.add(message.substring(s));
        for(int i=0;i<result.size();i++){
            result.set(i,result.get(i)+"("+(i+1)+"/"+result.size()+")");
        }
        return result;
    }

}
