package com.java.coding.interviews.practise;

/**
 * Created by Pratik1 on 15-02-2020.
 */
public class MaximumProfit {

    public static int calculateMaxProfit(int[] price){
        int profit=0;
        for(int i=1;i<price.length;i++){
            profit+=Math.max(price[i]-price[i-1],0);
        }
        return profit;
    }


    public static void main(String[] args) {
        System.out.println(calculateMaxProfit(new int[]{5,2,10}));
    }
}
