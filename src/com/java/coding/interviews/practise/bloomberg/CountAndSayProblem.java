package com.java.coding.interviews.practise.bloomberg;

public class CountAndSayProblem {


    public static void main(String[] args) {
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));
    }

    public static String countAndSay(int N){
        if(N==1) return "1";
        if(N==2) return "11";

        String result = "11";
        for(int i=3;i<=N;i++){
            result+="$";
            String temp="";
            int count=1;
            int L = result.length();
            char[] C = result.toCharArray();
            for(int j=1;j<L;j++){
                if(C[j]!=C[j-1]){
                    temp+=count;
                    temp+=C[j-1];
                    count=1;
                }else{
                    count++;
                }
            }
            result=temp;
        }
        return result;
    }


}
