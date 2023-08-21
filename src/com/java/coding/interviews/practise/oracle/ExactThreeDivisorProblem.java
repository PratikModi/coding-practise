package com.java.coding.interviews.practise.oracle;

/**
 * Given a number N, print all numbers in the range from 1 to N having exactly 3 divisors.
 *
 * Examples:
 *
 * Input: N = 16
 * Output: 4 9
 * Explanation: 4 and 9 have exactly three divisors.
 *
 * Input: N = 49
 * Output: 4 9 25 49
 * Explanation: 4, 9, 25 and 49 have exactly three divisors.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm: We can generate all primes within a set using any sieve method efficiently and then we should take all primes i, such that i*i <=N.
 *
 * Follow the below steps to solve the problem:
 *
 * Generate the prime numbers from 1 to N using any sieve method efficiently
 * Print all the prime numbers(X) between 1 to N, such as X2 is less than or equal to N
 */
//Time Complexity:- O(Sqrt(N^2))
//Space Complexity:- O(1)

public class ExactThreeDivisorProblem {

    public static void main(String[] args) {
        System.out.println(findNumberWithExactThreeDivisor(16));
        System.out.println(findNumberWithExactThreeDivisor(49));
    }

    public static List<Integer> findNumberWithExactThreeDivisor(int N){
        List<Integer> result = new ArrayList<>();
        for(int i=2;i*i<=N;i++){
            if(isPrime(i)){
                result.add(i*i);
            }
        }
        return result;
    }

    private static boolean isPrime(int N){
        if(N<2)
            return false;
        for(int i=2;i*i<=N;i++){
            if(N%i==0){
                return false;
            }
        }
        return true;
    }

}
