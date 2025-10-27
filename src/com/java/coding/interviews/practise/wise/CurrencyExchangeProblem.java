package com.java.coding.interviews.practise.wise;

import java.util.*;

/**
 * Question
 * Parameters:
 *
 * array of currency conversion rates. E.g. ['USD', 'GBP', 0.77] which means 1 USD is equal to 0.77 GBP
 * an array containing a 'from' currency and a 'to' currency
 * Given the above parameters, find the conversion rate that maps to the 'from' currency to the 'to' currency.
 * Your return value should be a number.
 *
 * Example:
 * You are given the following parameters:
 *
 * Rates: ['USD', 'JPY', 110] ['USD', 'AUD', 1.45] ['JPY', 'GBP', 0.0070]
 * To/From currency ['GBP', 'AUD']
 * Find the rate for the 'To/From' currency. In this case, the correct result is 1.89.
 */

public class CurrencyExchangeProblem {

    public static void main(String[] args) {

    }

    private static Map<String, Map<String,Double>> getAdjMatrix(List<ExchangeRate> exchangeRates){
        Map<String,Map<String,Double>> currencyMap = new HashMap<>();
        for(ExchangeRate rate : exchangeRates){
            currencyMap.putIfAbsent(rate.src,new HashMap<>());
            currencyMap.get(rate.src).put(rate.dest,rate.rate);
            currencyMap.putIfAbsent(rate.dest,new HashMap<>());
            currencyMap.get(rate.dest).put(rate.src,rate.rate);
        }
        return currencyMap;
    }

    public static double findShortestPath(Map<String,Map<String,Double>> currencyMap, String src, String dest){
        if(currencyMap==null || currencyMap.isEmpty() || src==null || src.isEmpty() || dest==null || dest.isEmpty())
            return 0.0;
        if(src.equals(dest))
            return 1.0;
        if(!currencyMap.containsKey(src))
            return 0.0;
        Set<String> visited = new HashSet<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(src,1.0d));
        visited.add(src);
        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            String currency = pair.currency;
            double rate = pair.rate;
            if(currency.equals(dest))
                return rate;
            if(currencyMap.get(currency).containsKey(dest)){
                return rate*currencyMap.get(currency).get(dest);
            }
            Map<String,Double> next = currencyMap.get(currency);
            for(var entry : next.entrySet()){
                if(!visited.contains(entry.getKey())){
                    Double newRate = pair.rate* entry.getValue();
                    visited.add(entry.getKey());
                    queue.offer(new Pair(entry.getKey(),newRate));
                }
            }
        }

        return 0.0;
    }

    static class ExchangeRate{
        String src;
        String dest;
        double rate;

        ExchangeRate(String src, String dest,double rate){
            this.src = src;
            this.dest = dest;
            this.rate=rate;
        }

        @Override
        public String toString() {
            return "ExchangeRate{" +
                    "src='" + src + '\'' +
                    ", dest='" + dest + '\'' +
                    ", rate=" + rate +
                    '}';
        }
    }

    static class Pair implements Comparable<Pair>{
        String currency;
        double rate;

        Pair(String currency, double rate){
            this.currency=currency;
            this.rate=rate;
        }

        /**
         * @param o
         * @return
         */
        @Override
        public int compareTo(Pair o) {
            return Double.compare(this.rate,o.rate);
        }
    }
}
