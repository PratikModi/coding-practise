package com.java.amazon.dynamic.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Valid Ip Addresses
 * Asked in:
 * Amazon
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * A valid IP address must be in the form of A.B.C.D, where A,B,C and D are numbers from 0-255. The numbers cannot be 0 prefixed unless they are 0.
 *
 * Example:
 *
 * Given “25525511135”,
 *
 * return [“255.255.11.135”, “255.255.111.35”]
 */
public class ValidIPAddressProblem {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(validIPAddresses("25525511135")));
    }

    private static String[] validIPAddresses(String S){
        List<String> IP = new ArrayList<>();
        if(S==null || S.length()<=3)
            return new String[0];
        int N = S.length();
        for(int i=0;i<=3&&i<N;i++){
            String part1 = S.substring(0,i);
            if(!isValid(part1))
                continue;
            for(int j=i+1;j<=i+3&&j<N;j++){
                String part2 = S.substring(i,j);
                if(!isValid(part2))
                    continue;
                for(int k=j+1;k<=j+3&&k<N;k++){
                    String part3 = S.substring(j,k);
                    if(!isValid(part3))
                        continue;
                    String part4 = S.substring(k);
                    if(!isValid(part4))
                        continue;
                    String IPAddress = part1+"."+part2+"."+part3+"."+part4;
                    IP.add(IPAddress);
                }
            }
        }
        return IP.toArray(new String[IP.size()]);
    }


    private static boolean isValid(String part){
        return part.length()>0 && part.length()<=3 && !(part.startsWith("0") && part.length()>1) && Integer.parseInt(part)<=255;
    }
}
