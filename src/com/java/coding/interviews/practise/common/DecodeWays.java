package com.java.coding.interviews.practise.common;

import java.util.Arrays;

/**
 * Created by Pratik1 on 15-02-2020.
 */
public class DecodeWays {

    public static int decodeWays(String s){
        if(s==null || s.length()==0){
            return 0;
        }
        int[] dp = new int[s.length()+1];
        dp[0]=1;
        dp[1]=s.charAt(0)=='0'?0:1;

        for(int i=2;i<=s.length();i++){
            int oneDigit = Integer.parseInt(s.substring(i-1,i));
            System.out.println(Arrays.toString(dp));
            if(oneDigit>0)
                dp[i]+= dp[i-1];
            int twoDigit = Integer.parseInt(s.substring(i-2,i));
            if(twoDigit >9 && twoDigit<27){
                dp[i]+= dp[i-2];
            }
        }

        return dp[s.length()];
    }


    public static void main(String[] args) {
        System.out.println(decodeWays("123"));
    }
}
