package com.java.coding.interviews.practise.facebook;

/**
 * Created by Pratik1 on 18-05-2020.
 */

/**
 * Given a string, return whether it represents a number. Here are the different kinds of numbers:

 "10", a positive integer
 "-10", a negative integer
 "10.1", a positive real number
 "-10.1", a negative real number
 "1e5", a number in scientific notation

 And here are examples of non-numbers:
 "a"
 "x 1"
 "a -2"
 "-"
 =======================================================================
 The following cases need to be handled in the code.

 Ignore the leading and trailing white spaces.
 Ignore the ‘+’, ‘-‘ and’.’ at the start.
 Ensure that the characters in the string belong to {+, -, ., e, [0-9]}
 Ensure that no ‘.’ comes after ‘e’.
 A dot character ‘.’ should be followed by a digit.
 The character ‘e’ should be followed either by ‘+’, ‘-‘, or a digit.
 =======================================================================
 */
public class StringDigitProblem {

    public static void main(String[] args) {
        boolean result = isValidNumber("123");
        System.out.println(result);
        result = isValidNumber("abc");
        System.out.println(result);
        result = isValidNumber("1e.1");
        System.out.println(result);
        result = isValidNumber("1.1.1");
        System.out.println(result);
    }

    public static boolean isValidNumber(String input){
        if(input==null || input.length()==0)
            return false;
        input = input.trim();
        if(input.charAt(0)!='+' && input.charAt(0)!='-'
                && input.charAt(0)!='.' && !Character.isDigit(input.charAt(0))){
            return false;
        }
        if(input.length()==1 && !Character.isDigit(input.charAt(0)))
            return false;
        boolean foundDotOrE=false;
        for(int i=1;i<input.length();i++){
            if(input.charAt(i)!='.' && !Character.isDigit(input.charAt(i)) &&
                    input.charAt(i)!='+' && input.charAt(i)!='-')
                return false;
            //Ensure that no ‘.’ comes after ‘e’.
            if(input.charAt(i)=='.'){
                if(foundDotOrE)
                    return false;
                foundDotOrE=true;
                if((i+1)>=input.length())
                    return false;
                if(!Character.isDigit(input.charAt(i+1)))
                    return false;
            }
            if(input.charAt(i)=='e'){
                foundDotOrE=true;
                if(!Character.isDigit(input.charAt(i-1)))
                    return false;
                if((i+1)>=input.length())
                    return false;
                if(input.charAt(i+1)!='-' && input.charAt(i+1)!='+'
                        && !Character.isDigit(input.charAt(i+1)))
                    return false;
            }
        }
        return true;
    }
}
