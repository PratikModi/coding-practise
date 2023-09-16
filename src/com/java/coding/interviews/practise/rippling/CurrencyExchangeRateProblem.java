package com.java.coding.interviews.practise.rippling;

import java.util.*;

/**
 * Question
 * Parameters:
 * array of currency conversion rates. E.g. ['USD', 'GBP', 0.77] which means 1 USD is equal to 0.77 GBP
 * an array containing a 'from' currency and a 'to' currency
 * Given the above parameters, find the conversion rate that maps to the 'from' currency to the 'to' currency.
 * Your return value should be a number.
 * Example:
 * You are given the following parameters:
 * Rates: ['USD', 'JPY', 110] ['USD', 'AUD', 1.45] ['JPY', 'GBP', 0.0070]
 * To/From currency ['GBP', 'AUD']
 * Find the rate for the 'To/From' currency. In this case, the correct result is 1.89.
 * Question:-
 * =========
 * 1. What to return of from/to currencies are same?
 * 2. What is there is no path exists?
 * 3. Are the rate bidirectional?
 * 4. Do we need to find the Shortest Path or Cheapest Path?
 * Talking Point:-
 * ==============
 * Adjacency Matrix
 * DFS
 * Time Complexity/Space Complexity
 */
public class CurrencyExchangeRateProblem {

    private static Map<String, Map<String,Double>> prepareAdjList(List<ExchangeRate> exchangeRateList){
        Map<String,Map<String,Double>> adjList = new HashMap<>();
        for(ExchangeRate rate : exchangeRateList){
            adjList.putIfAbsent(rate.from,new HashMap<>());
            adjList.get(rate.from).put(rate.to, rate.rate);
            adjList.putIfAbsent(rate.to,new HashMap<>());
            adjList.get(rate.to).put(rate.from,1/ rate.rate);
        }
        return adjList;
    }

    private static Double findShortestPath(String source, String dest, Map<String,Map<String,Double>> currencyMap){
        if(source.equals(dest)){
            return 1.0;
        }
        if(!currencyMap.containsKey(source) || !currencyMap.containsKey(dest)) {
            return 0.0;
        }
        Queue<Pair> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new Pair(source,1.0));
        visited.add(source);
        while(!queue.isEmpty()){
            Pair poll = queue.poll();
            if(poll.currency.equals(dest)){
                return poll.rate;
            }
            if(currencyMap.get(poll.currency).containsKey(dest)){
                return poll.rate*(currencyMap.get(poll.currency).get(dest));
            }
            Map<String,Double> next = currencyMap.get(poll.currency);
            for(Map.Entry<String,Double> entry : next.entrySet()) {
                Double newRate = poll.rate*entry.getValue();
                if (!visited.contains(poll.currency)) {
                    visited.add(entry.getKey());
                    queue.add(new Pair(entry.getKey(),newRate));
                }
            }
        }
        return 0.0;
    }

    public static Double findCheapestPath(String source, String dest, Map<String,Map<String,Double>> currencyMap){
        if(source.equals(dest)){
            return 1.0;
        }
        if(!currencyMap.containsKey(source) || !currencyMap.containsKey(dest)){
            return 0.0;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Map<String,Double> visited = new HashMap<>();
        pq.add(new Pair(source,1.0));
        while(!pq.isEmpty()){
            Pair poll = pq.poll();
            if(visited.containsKey(poll.currency) && visited.get(poll.currency)<= poll.rate)
                continue;
            visited.put(poll.currency,poll.rate);
            Map<String,Double> next = currencyMap.get(poll.currency);
            for(Map.Entry<String,Double> entry : next.entrySet()){
                Double newRate = poll.rate*entry.getValue();
                if(entry.getKey().equals(source))
                    continue;
                if(visited.containsKey(entry.getKey()) && visited.get(entry.getKey())> newRate){
                    visited.put(entry.getKey(),newRate);
                }
                pq.add(new Pair(entry.getKey(), newRate));
            }
        }
        return visited.get(dest);
    }

    public static void main(String[] args) {
        ExchangeRate rate1 = new ExchangeRate("USD", "JPY", 110.0);
        ExchangeRate rate2 = new ExchangeRate("USD", "AUD", 0.1);
        ExchangeRate rate3 = new ExchangeRate("USD", "MOS", 1.0);
        ExchangeRate rate4 = new ExchangeRate("AUD", "JPY", 4.0);
        ExchangeRate rate5 = new ExchangeRate("MOS", "JPY", 2.0);

        List<ExchangeRate> exchangeRateList = List.of(rate1,rate2,rate3,rate4,rate5);
        var adjList = prepareAdjList(exchangeRateList);
        var rate = findShortestPath("USD","JPY",adjList);
        System.out.println(rate);
        rate = findCheapestPath("USD","JPY",adjList);
        System.out.println(rate);
    }

}

class ExchangeRate{
    String from;
    String to;
    Double rate;

    public ExchangeRate(String from, String to, Double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", rate=" + rate +
                '}';
    }
}

class Pair implements Comparable<Pair>{
    String currency;
    Double rate;

    public Pair(String currency, Double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    @Override
    public int compareTo(Pair pair) {
        return Double.compare(this.rate,pair.rate);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }
}