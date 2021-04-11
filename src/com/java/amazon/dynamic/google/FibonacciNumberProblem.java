package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 27-06-2020.
 */

/**
 * How to check if a given number is Fibonacci number?
 * Given a number ‘n’, how to check if n is a Fibonacci number.
 * First few Fibonacci numbers are 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 141, ..

 Examples :

 Input : 8
 Output : Yes

 Input : 34
 Output : Yes

 Input : 41
 Output : No
 */

/**
 * Approach: -
 * A number is Fibonacci if and only if one or both of
 * (5*n2 + 4) or (5*n2 – 4) is a perfect square.
 */
public class FibonacciNumberProblem {
    public static void main(String[] args) {
        System.out.println(isFibonacciNumber(8));
        System.out.println(isFibonacciNumber(41));
        System.out.println(isFibonacciNumber(34));
    }

    private static boolean isPerfectSquare(int X){
        int sqr = (int)Math.sqrt(X);
        return sqr*sqr==X;
    }

    public static boolean isFibonacciNumber(int N){
        return isPerfectSquare((5*(N*N))-4) || isPerfectSquare((5*(N*N))+4);
    }
}
