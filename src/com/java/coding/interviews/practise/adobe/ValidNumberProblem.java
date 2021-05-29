package com.java.coding.interviews.practise.adobe;

/**
 * Is the Number Valid
 * Problem Statement
 * Given an input string, determine if it makes a valid number or not. For simplicity, assume that white spaces are not present in the input.
 *
 * 4.325 is a valid number.
 * 1.1.1 is NOT a valid number.
 * 222 is a valid number.
 * 22. is NOT a valid number.
 * 0.1 is a valid number.
 * 22.22. is NOT a valid number.
 * 1. is NOT a valid number.
 * =======================================================================
 *  The following cases need to be handled in the code.
 *
 *  Ignore the leading and trailing white spaces.
 *  Ignore the ‘+’, ‘-‘ and’.’ at the start.
 *  Ensure that the characters in the string belong to {+, -, ., e, [0-9]}
 *  Ensure that no ‘.’ comes after ‘e’.
 *  A dot character ‘.’ should be followed by a digit.
 *  The character ‘e’ should be followed either by ‘+’, ‘-‘, or a digit.
 *  =======================================================================
 */
public class ValidNumberProblem {
    public static void main(String[] args) {
        System.out.println(isValidNumber("4.325"));
        System.out.println(isValidNumber("1.1.1"));
        System.out.println(isValidNumber("222"));
        System.out.println(isValidNumber("22."));
        System.out.println(isValidNumber("0.1"));
        System.out.println(isValidNumber("22.22."));
        System.out.println(isValidNumber("1.0"));
    }

    public static boolean isValidNumber(String S){
        if(S==null || S.length()==0)
            return false;
        S=S.trim();
        int N = S.length();
        if(!isValidCharacter(S.charAt(0)))
            return false;
        if(S.length()==1 && !Character.isDigit(S.charAt(0)))
            return false;
        boolean foundDotOrE=false;
        for(int i=1;i<N;i++){
            if(!isValidCharacter(S.charAt(i)))
                return false;
            if(S.charAt(i)=='.') {
                if (foundDotOrE)
                    return false;
                foundDotOrE = true;
                if ((i + 1) >= N)
                    return false;
                if (!Character.isDigit(S.charAt(i + 1)))
                    return false;
            }
            if(S.charAt(i)=='e'){
                foundDotOrE=true;
                if(!Character.isDigit(S.charAt(i-1)))
                    return false;
                if((i+1)>=N)
                    return false;
                if(S.charAt(i+1)!='.' && S.charAt(i+1)!='+' && !Character.isDigit(S.charAt(i+1)))
                    return false;
            }
        }
        return true;
    }

    private static boolean isValidCharacter(char c){
        if(c!='.' && c!='+' && c!='e' && !Character.isDigit(c))
            return false;
        return true;
    }
}
