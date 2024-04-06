package com.java.coding.interviews.practise.oracle;

public class SumLargeNumberProblem {

    public static void main(String[] args) {
        String s1 = "123";
        String s2 = "123";
        System.out.println(sumLargeNumbers(s1,s2));
        s1 = "3333311111111111";
        s2 = "44422222221111";
        System.out.println(sumLargeNumbers(s1,s2));
    }

    public static String sumLargeNumbers(String s1, String s2){
        if(s1.length() > s2.length()){
            String temp = s1;
            s1=s2;
            s2=temp;
        }
        s1 = new StringBuilder(s1).reverse().toString();
        s2 = new StringBuilder(s2).reverse().toString();
        String result="";
        int n1 = s1.length();
        int n2 = s2.length();
        int carry=0;
        for(int i=0;i<n1;i++){
            int sum = ((int)(s1.charAt(i)-'0') + (int)(s2.charAt(i)-'0')) + carry;
            result+=(char)(sum%10+'0');
            carry = sum/10;
        }

        for(int i=n1;i<n2;i++){
            int sum = ((int)(s2.charAt(i)-'0')+carry);
            result+=(char)(sum%10+'0');
            carry=carry/10;
        }

        if(carry>0){
            result+=(char)(carry+'0');
        }
        return new StringBuilder(result).reverse().toString();
    }



}
