package com.java.amazon.dynamic.google;

import java.util.Arrays;

/**
 * Given a number of friends who have to give or take some amount of money from one another.
 * Design an algorithm by which the total cash flow among all the friends is minimized.
 * Example:
 * Following diagram shows input debts to be settled.
 * cashFlow
 *
 * Above debts can be settled in following optimized way
 * cashFlow
 * https://www.geeksforgeeks.org/minimize-cash-flow-among-given-set-friends-borrowed-money/
 */
public class CashFlowMinimizationProblem {

    public static void main(String[] args) {
        int[][] cashFlow = new int[][] {{0,1000,2000},{0,0,5000},{0,0,0}};
        int result = cashFlow(cashFlow,3);
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

    public static int cashFlowRec(int[] amount,int result){
        int maxDebit = minimum(amount);
        int maxCredit = maximum(amount);
        if(maxCredit==maxDebit)
            return result;
        int min = Math.min(Math.abs(amount[maxDebit]),amount[maxCredit]);
        amount[maxCredit]-=min;
        amount[maxDebit]+=min;
        System.out.println("Person "+maxDebit+" paying "+min+" amount to "+maxCredit);
        result++;
        return cashFlowRec(amount,result);
    }

    public static int cashFlow(int[][] cashFlow, int noOfPeople){
        if(cashFlow==null || cashFlow.length==0 || noOfPeople==0)
            return -1;
        int[] netAmount = new int[noOfPeople];
        for(int P=0;P<noOfPeople;P++){
            for(int I=0;I<noOfPeople;I++){
                netAmount[P]+=cashFlow[I][P]-cashFlow[P][I];
            }
        }
        System.out.println(Arrays.toString(netAmount));
        return cashFlowRec(netAmount,0);
    }

}

