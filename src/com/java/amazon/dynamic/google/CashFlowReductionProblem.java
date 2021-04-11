package com.java.amazon.dynamic.google;

/**
 * Created by Pratik1 on 21-06-2020.
 */
/**
 * Given a number of friends who have to give or take some amount of money from one another.
 * Design an algorithm by which the total cash flow among all the friends is minimized.
 * https://www.geeksforgeeks.org/minimize-cash-flow-among-given-set-friends-borrowed-money/
 */
public class CashFlowReductionProblem {

    public static void main(String[] args) {
        int[][] cashFlow = {{0,1000,2000},{0,0,5000},{0,0,0}};
        int result = findMinimumCashFlow(cashFlow,3);
        System.out.println(result);
    }

    private static int minimum(int[] A){
        int minIndex=0;
        for(int i=1;i<A.length;i++){
            if(A[i]<A[minIndex])
                minIndex=i;
        }
        return minIndex;
    }

    private static int maximum(int[] A){
        int maxIndex=0;
        for(int i=1;i<A.length;i++){
            if(A[i]>A[maxIndex])
                maxIndex=i;
        }
        return maxIndex;
    }

    public static int findMinimumCashFlowRec(int[] amount, int result){
        int maxCredit,maxDebit;
        maxCredit=maximum(amount);
        maxDebit=minimum(amount);

        if(maxCredit==maxDebit)
            return result;

        int minimum = Math.min(-amount[maxDebit],amount[maxCredit]);
        amount[maxCredit]-=minimum;
        amount[maxDebit]+=minimum;
        result++;
        System.out.println("Person P"+maxDebit+" pays "+minimum+" to person P"+maxCredit);
        return findMinimumCashFlowRec(amount,result);
    }

    public static int findMinimumCashFlow(int[][] cashFlow, int noOfPeople){
        if(cashFlow==null || cashFlow.length==0 || noOfPeople==0)
            return -1;
        int[] netAmount = new int[noOfPeople];
        for(int P=0;P<noOfPeople;P++){
            for(int C=0;C<noOfPeople;C++){
                netAmount[P]+=cashFlow[C][P]-cashFlow[P][C];
            }
        }
        return findMinimumCashFlowRec(netAmount,0);
    }

}
