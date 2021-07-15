package com.java.coding.interviews.practise.common;

/**
 * Program to find GCD or HCF of two numbers
 * Difficulty Level : Easy
 * Last Updated : 22 Mar, 2021
 * GCD (Greatest Common Divisor) or HCF (Highest Common Factor) of two numbers is the largest number that divides both of them.
 *
 *
 *
 * For example GCD of 20 and 28 is 4 and GCD of 98 and 56 is 14.
 */
public class GCDProblem {

    public static void main(String[] args) {
        System.out.println(98%56);
        System.out.println(findGCD(98,56));
        System.out.println(findGCD2(56,98));
    }

    public static int findGCD(int A, int B){
        if(A==0)
            return B;
        if(B==0)
            return A;
        if(A==B)
            return A;
        if(A>B)
            return findGCD(A-B,B);
        return findGCD(A,B-A);
    }
    //O(Log min(a, b))
    public static int findGCD2(int A, int B){
        System.out.println(A+"--"+B);
        if(B==0)
            return A;
        return findGCD2(B,A%B);
    }
}
