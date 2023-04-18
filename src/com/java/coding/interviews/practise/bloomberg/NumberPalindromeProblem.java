package com.java.coding.interviews.practise.bloomberg;

/**
 * Given a positive integer, write a function that returns true if the given number is a palindrome, else false.
 * For example, 12321 is a palindrome,but 1451 is not a palindrome.
 */
public class NumberPalindromeProblem {

    public static void main(String[] args) {
        System.out.println(isPalindrome(2002));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(1451));
    }


    public static boolean isPalindrome(int number){
        if(number>=0 && number<10) return true;
        if(number!=0 && number%10==0) return false;
        int check=0;
        while(number>check){
            check = check*10 + number%10;
            number=number/10;
        }
        return number==check || number==check/10;
    }
}
