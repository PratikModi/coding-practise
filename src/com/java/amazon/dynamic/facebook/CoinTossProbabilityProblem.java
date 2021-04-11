package com.java.amazon.dynamic.facebook;

/**
 * Created by Pratik1 on 06-06-2020.
 */

/**
 * Assume you have access to a function toss_biased() which returns 0 or 1 with a probability
 * that's not 50-50 (but also not 0-100 or 100-0). You do not know the bias of the coin.
 * Write a function to simulate an unbiased coin toss.
 *
 * Solution:
 * =========
 * We know foo() returns 0 with 60% probability. How can we ensure that 0 and 1 are returned
 * with 50% probability?
 * The solution is similar to this post. If we can somehow get two cases with equal probability,
 * then we are done. We call foo() two times. Both calls will return 0 with 60% probability.
 * So the two pairs (0, 1) and (1, 0) will be generated with equal probability
 * from two calls of foo(). Let us see how.
 * (0, 1): The probability to get 0 followed by 1 from two calls of foo() = 0.6 * 0.4 = 0.24
 * (1, 0): The probability to get 1 followed by 0 from two calls of foo() = 0.4 * 0.6 = 0.24
 */
public class CoinTossProbabilityProblem {
    public static void main(String[] args) {

    }

    public static int toss_biased(){
        // Some biased code here
        return 0;
    }

    public static int toss_unbiased(){
        int value1 = toss_biased();
        int value2 = toss_biased();
        if(value1==0 && value2==1)
            return 0;
        if(value1==1 && value2==0)
            return 1;
        return toss_unbiased();
    }

}
