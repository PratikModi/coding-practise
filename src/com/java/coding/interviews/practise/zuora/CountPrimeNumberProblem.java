package com.java.coding.interviews.practise.zuora;

import java.util.Arrays;

/**
 * 204. Count Primes
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 * Example 3:
 *
 * Input: n = 1
 * Output: 0
 *
 * ✅ Approach — Sieve of Eratosthenes
 *
 * 🧠 Idea:
 *
 * Instead of checking each number individually, we:
 * 	1.	Assume all numbers from 2 to n−1 are prime initially.
 * 	2.	Start from 2, the first prime.
 * 	3.	Eliminate all multiples of 2 (mark as not prime).
 * 	4.	Move to the next unmarked number (3), eliminate its multiples.
 * 	5.	Continue until √n.
 *
 * 	At the end, count how many are still marked as prime.
 *
 * 	⚙️ Time & Space Complexity
 *
 * Operation              Complexity
 *    Time                  O(n log log n)
 *    Space                O(n)
 *
 1️⃣ Outer loop

 Runs for all i up to √n.

 But the inner loop does not run n times for each i.
 It runs approximately:
 •	n/2 times for i=2
 •	n/3 times for i=3
 •	n/5 times for i=5
 •	n/7 times for i=7
 •	… only for prime i.

 Because composite i are skipped (isPrime[i] == false).

 ⸻

 2️⃣ Total operations

 So, the total number of inner loop operations is roughly:

 n \times \left(\frac{1}{2} + \frac{1}{3} + \frac{1}{5} + \frac{1}{7} + \cdots + \frac{1}{p}\right)
 where the denominators are prime numbers ≤ n.

 ⸻

 3️⃣ Harmonic sum over primes

 Mathematically, the sum of reciprocals of primes up to n is approximately:
 \ln(\ln(n))

 Hence, total work is:
 O(n ln(ln(n))) */
public class CountPrimeNumberProblem {

    public static void main(String[] args) {
        System.out.println(countPrimes(10));
        System.out.println(countPrimes(0));
        System.out.println(countPrimes(1));
    }

    public static int countPrimes(int n) {
        if (n <= 2) return 0; // No primes less than 2
        boolean[] primes = new boolean[n];
        Arrays.fill(primes,true);
        primes[0]=false;
        primes[1]=false;

        for(int i=2;i*i<n;i++){
            if(primes[i]){
                // Mark all multiples of i starting from i*i
                for(int j=i*i;j<n;j+=i){
                    primes[j]=false;
                }
            }
        }
        int count=0;
        for(int i=0;i<primes.length;i++){
            if(primes[i]) count++;
        }
        return count;
    }

}
