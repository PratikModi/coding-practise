package com.java.coding.interviews.practise.google;

/**
 * Allocate minimum number of pages
 * Last Updated: 25-07-2019
 * Given number of pages in n different books and m students.
 * The books are arranged in ascending order of number of pages.
 * Every student is assigned to read some consecutive books.
 * The task is to assign books in such a way that the maximum number of pages assigned to a student is minimum.
 *
 * Example :
 *
 * Input : pages[] = {12, 34, 67, 90}
 *         m = 2
 * Output : 113
 * Explanation:
 * There are 2 number of students. Books can be distributed
 * in following fashion :
 *   1) [12] and [34, 67, 90]
 *       Max number of pages is allocated to student
 *       2 with 34 + 67 + 90 = 191 pages
 *   2) [12, 34] and [67, 90]
 *       Max number of pages is allocated to student
 *       2 with 67 + 90 = 157 pages
 *   3) [12, 34, 67] and [90]
 *       Max number of pages is allocated to student
 *       1 with 12 + 34 + 67 = 113 pages
 *
 * Of the 3 cases, Option 3 has the minimum pages = 113.
 */
public class BookAllocationProblem {
    public static void main(String[] args) {
        int[] P = new int[]{14,19,31,75};
        int S=2;
        int result = findMinimumPages(P,S);
        System.out.println(result);
    }

    public static int findMinimumPages(int[] P, int S){
        if(P==null || P.length==0 || S==0 || P.length < S)
            return -1;
        int N = P.length;
        int sum=0;
        for(int i=0;i<N;i++){
            sum+=P[i];
        }
        int start=0;
        int result = Integer.MAX_VALUE;
        int end=sum;
        while(start<=end){
            int mid=(start+end)/2;
            if(isPossible(P,S,N,mid)){
                result=Math.min(result,mid);
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return result;
    }

    private static boolean isPossible(int[] P, int S, int N, int L){
        int pSum=0;
        int student=1;
        for(int i=0;i<N;i++){
            if(P[i]>L){
                return false;
            }
            if(pSum+P[i]>L){
                student++;
                pSum=P[i];
                if(student>S){
                    return false;
                }
            }else{
                pSum+=P[i];
            }
        }
        return true;
    }



}
