package com.java.coding.interviews.practise.jpmc;

/**
 * Given a number n, find count of all numbers from 1 to n that have 4 as a digit.
 *
 * Examples :
 *
 * Input:   n = 5
 * Output:  1
 * Only 4 has '4' as digit
 *
 * Input:   n = 50
 * Output:  14
 *
 * Input:   n = 328
 * Output:  60
 */

//Time Complexity: O(N log N)
//Space Complexity: O(1)
public class DigitCountProblem {

    public static void main(String[] args) {
        System.out.println(countDigit(11,1));
        System.out.println(countDigit(21,1));
        System.out.println(countDigit(21,4));
    }

    public static int countDigit(int N, int D){
        int result=0;
        if(N<=0) return 0;
        for (int i = 1; i <=N ; i++) {
            int num=i;
            while(num!=0){
                if(num%10==D)
                    result++;
                num=num/10;
            }
        }
        return result;
    }

}
