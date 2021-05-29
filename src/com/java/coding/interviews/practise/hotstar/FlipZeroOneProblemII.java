package com.java.coding.interviews.practise.hotstar;

/**
 * Given a binary array a[] and a number k, we need to find length of he longest subsegment of ‘1’s possible by changing at most k ‘0’s.
 *
 * Examples:
 *
 * Input : a[] = {1, 0, 0, 1, 1, 0, 1},
 *           k = 1.
 * Output : 4
 * Explanation : Here, we should only change 1
 * zero(0). Maximum possible length we can get
 * is by changing the 3rd zero in the array,
 * we get a[] = {1, 0, 0, 1, 1, 1, 1}
 *
 * Input : a[] = {1, 0, 0, 1, 0, 1, 0, 1, 0, 1},
 *          k = 2.
 * Output : 5
 * Output: Here, we can change only 2 zeros.
 * Maximum possible length we can get is by
 * changing the 3rd and 4th (or) 4th and 5th
 * zeros.
 */
public class FlipZeroOneProblemII {
    public static void main(String[] args) {
        int[] A = {1, 0, 0, 1, 0, 1, 0, 1, 0, 1};
        int B=2;
        System.out.println(findMaxConsecutiveOnes(A,B));
    }

    public static int findMaxConsecutiveOnes(int[] A, int B) {
        int start=0,end=0,zeroCount=0,lastZero=-1,max=0;
        int N = A.length;
        while(end<N){
            if(A[end]==0) {
                zeroCount++;
            }
            int counter=0;
            while(zeroCount > B) {
                System.out.println("ZC==>"+zeroCount+"=="+(++counter));
                if(A[start]==0)
                    zeroCount--;
                start++;
                System.out.println("Start-->"+zeroCount+"=="+start+"=="+end +"=="+(end-start+1));
            }
            max=Math.max(max,end-start+1);
            System.out.println("MAX-->"+max);
            end++;
        }
        return max;
    }
}
