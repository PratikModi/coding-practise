package com.java.coding.interviews.practise.coinbase;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Example 2:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 */
public class StockBuySaleProblem {

    public static void main(String[] args) {
        int[] price = {7,1,5,3,6,4};
        System.out.println(maxProfit(price));
        System.out.println(maxProfit2(price));
    }
    //O(N) --- only 1 transaction
    public static int maxProfit(int[] price){
        int profit=0;
        int lastPrice = Integer.MAX_VALUE;
        for(int i=0;i<price.length;i++){
            if(price[i]<lastPrice){
                lastPrice=price[i];
            }else{
                profit=Math.max(profit,price[i]-lastPrice);
            }
        }
        return profit;
    }
    //O(N) -- as many as possible transactions
    public static int maxProfit2(int[] price){
        int profit=0;
        if(price.length<2)
            return profit;
        for(int i=1;i<price.length;i++){
          if(price[i]>price[i-1]){
              profit+=price[i]-price[i-1];
          }
        }
        return profit;
    }


}
