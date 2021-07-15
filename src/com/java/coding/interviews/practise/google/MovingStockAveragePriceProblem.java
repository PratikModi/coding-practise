package com.java.coding.interviews.practise.google;

import java.util.*;

/**
 * Design and build a moving average stock data stream class, which implements the moving average of a stock symbol and price.
 * The average will be defined as the user of the data stream. The moving average should be the average of the last N in the data stream.
 *
 * Example: StockSymbolMovingAverage stockSymbolMovingAverage = new StockSymbolMovingAverage (3) → Follow up where he added two number (3 ,4)
 * StockSymbolMovingAverage.next(“SPY”, 1) //<1,1>
 * StockSymbolMovingAverage.next(“Amazon”, 10) //10
 * StockSymbolMovingAverage.next(“SPY” 10) // (1+10)/2 = 5.5 <5.5, 5.5>
 * StockSymbolMovingAverage.next(“SPY” 3) // (1+10+3) /3= <4.67, 4.67>
 * StockSymbolMovingAverage.next(“SPY” 5) // (1 + 1 (10 + 3 + 5))/[3,4] = <6, 4.75>
 *
 * This is similar to moving average from data stream question and product of the last k-numbers question but
 * this is taking in two numbers ( stockSymbolMovingAverage = new StockSymbolMovingAverage (3,4))
 * and when called .next function it is taking in string and integer.
 */
public class MovingStockAveragePriceProblem {

    private int[] duration;
    Map<String, List<Double>> stockPriceMap = new HashMap<>();
    Map<String, Queue<Double>> stockPriceMap2 = new HashMap<>();
    Map<String, Double> priceSum = new HashMap<>();
    private int N=3;
    public MovingStockAveragePriceProblem(int[] duration) {
        this.duration = duration;
    }
    public MovingStockAveragePriceProblem(int duration) {
        this.N = duration;
    }

    public static void main(String[] args) {
        MovingStockAveragePriceProblem priceProblem = new MovingStockAveragePriceProblem(new int[]{3,4});
        System.out.println(priceProblem.next("SPY",1));
        //System.out.println(priceProblem.next("Amazon",10));
        System.out.println(priceProblem.next("SPY",10));
        System.out.println(priceProblem.next("SPY",3));
        System.out.println(priceProblem.next("SPY",5));
        System.out.println(priceProblem.next2("SPY",1));
        //System.out.println(priceProblem.next("Amazon",10));
        System.out.println(priceProblem.next2("SPY",10));
        System.out.println(priceProblem.next2("SPY",3));
        System.out.println(priceProblem.next2("SPY",5));
    }

    private List<Double> next(String stock, double price){
        List<Double> result = new ArrayList<>();
        if(!stockPriceMap.containsKey(stock)){
            stockPriceMap.put(stock,new ArrayList<Double>());
            stockPriceMap.get(stock).add(0d);
        }
        List<Double> runningPrice = stockPriceMap.get(stock);
        Double lastRunningPrice = runningPrice.get(runningPrice.size()-1);
        Double lastPrice = lastRunningPrice+price;
        runningPrice.add(lastPrice);
        for(int D : duration){
            int duration = Math.min(D,runningPrice.size()-1);
            Double previousPrice = runningPrice.get(runningPrice.size()-duration-1);
            Double currentPrice = runningPrice.get(runningPrice.size()-1);
            result.add((double)(currentPrice-previousPrice)/duration);
        }
        return result;
    }

    private Double next2(String stock,double price){
        stockPriceMap2.putIfAbsent(stock,new LinkedList<>());
        priceSum.put(stock,priceSum.getOrDefault(stock,0d)+price);
        System.out.println(stockPriceMap2);
        if(stockPriceMap2.get(stock).size()==N){
            System.out.println("in if");
            priceSum.put(stock,priceSum.get(stock)-stockPriceMap2.get(stock).poll());
        }
        stockPriceMap2.get(stock).add(price);
        int duration = Math.min(stockPriceMap2.get(stock).size(),N);
        return priceSum.get(stock)/duration;
    }


}
