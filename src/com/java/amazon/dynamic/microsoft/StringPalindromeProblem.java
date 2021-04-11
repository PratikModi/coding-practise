package com.java.amazon.dynamic.microsoft;

public class StringPalindromeProblem {

    public static void main(String[] args) {
        boolean result = isPalindrome(".");
        System.out.println(result);
    }

    public static boolean isPalindrome(String s) {

        if(s==null || s.trim().length()==0)
            return true;
        int high = s.length()-1;
        int low=0;
        s=s.toLowerCase();
        while(low<=high){
            System.out.println(low+"=="+high);
            while(low<=high && !Character.isLetterOrDigit(s.charAt(low)))
                low++;
            while(low<=high && !Character.isLetterOrDigit(s.charAt(high)))
                high--;
            if(low<=high && s.charAt(low)!=s.charAt(high)){
                return false;
            }else{
                low++;
                high--;
            }

        }
        return true;

    }
}
