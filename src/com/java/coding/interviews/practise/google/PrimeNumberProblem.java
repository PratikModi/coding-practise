package com.java.coding.interviews.practise.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a number n, print all primes smaller than or equal to n. It is also given that n is a small number.
 *
 * Example:
 *
 * Input : n =10
 * Output : 2 3 5 7
 *
 * Input : n = 20
 * Output: 2 3 5 7 11 13 17 19
 * The sieve of Eratosthenes is one of the most efficient ways to find all
 * primes smaller than n when n is smaller than 10 million or so
 */

public class PrimeNumberProblem {
    public static void main(String[] args) {
        System.out.println(findPrimeNumbers(3));
        System.out.println(findPrimePair(4));

    }

    public static List<Integer> findPrimeNumbers(int N){
        boolean[] AR = new boolean[N+1];
        Arrays.fill(AR,true);

        for(int i=2;i<Math.sqrt(N);i++){
            if(AR[i]) {
                for (int j = i * i; j <= N; j=j+i) {
                    if(AR[j]){
                        AR[j]=false;
                    }
                }
            }
        }
        var result = new ArrayList<Integer>();
        for(int i=2;i<=N;i++){
            if(AR[i]){
                result.add(i);
            }
        }


        return result;
    }

    public static List<Integer> findPrimePair(int N){
        List<Integer> primes = findPrimeNumbers(N);
        List<Integer> result = new ArrayList<>();
        int low = 0;
        int high = primes.size()-1;
        while(low<=high){
            int sum = primes.get(low)+primes.get(high);
            if(sum==N){
                result.add(primes.get(low));
                result.add(primes.get(high));
                return result;
            }else if(sum>N){
                high--;
            }else{
                low++;
            }
        }
        return result;
    }

}
