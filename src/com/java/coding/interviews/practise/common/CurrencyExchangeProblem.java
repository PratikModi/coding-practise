package com.java.coding.interviews.practise.common;

import java.util.*;

public class CurrencyExchangeProblem {

    public static void main(String[] args) {
        ExchangeRate rate1 = new ExchangeRate("USD", "JPY", 110.0);
        ExchangeRate rate2 = new ExchangeRate("USD", "AUD", 0.1);
        ExchangeRate rate3 = new ExchangeRate("USD", "MOS", 1.0);
        ExchangeRate rate4 = new ExchangeRate("AUD", "JPY", 4.0);
        ExchangeRate rate5 = new ExchangeRate("MOS", "JPY", 2.0);

        List<ExchangeRate> exchangeRateList = List.of(rate1,rate2,rate3,rate4,rate5);
        var adjList = getAdjList(exchangeRateList);
        var rate = getShortestPath("USD","JPY",adjList);
        System.out.println(rate);
        rate = getCheapestPath("USD","JPY",adjList);
        System.out.println(rate);
    }

    public static Map<String, Map<String,Double>> getAdjList(List<ExchangeRate> exchangeRates){
        Map<String,Map<String,Double>> adjList = new HashMap<>();
        if(exchangeRates==null || exchangeRates.isEmpty())
            return adjList;
        for(ExchangeRate rate : exchangeRates){
            adjList.putIfAbsent(rate.source,new HashMap<>());
            adjList.get(rate.source).put(rate.dest,rate.rate);
            adjList.putIfAbsent(rate.dest,new HashMap<>());
            adjList.get(rate.dest).put(rate.source,1/rate.rate);
        }
        return adjList;
    }

    public static Double getShortestPath(String source,String dest, Map<String,Map<String,Double>> adjList){
        if(source.equals(dest)){
            return 1.0;
        }
        if(!adjList.containsKey(source) || !adjList.containsKey(dest)){
            return 0.0;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(source,1.0));
        Set<String> visited = new HashSet<>();
        visited.add(source);
        while(!queue.isEmpty()){
            Pair poll = queue.poll();
            if(poll.currency.equals(dest))
                return poll.rate;
            if(!adjList.get(poll.currency).containsKey(dest)){
                return poll.rate*(adjList.get(poll.currency).get(dest));
            }
            Map<String,Double> next = adjList.get(poll.currency);
            for(var entry : next.entrySet()){
                double newRate = poll.rate*entry.getValue();
                if(!visited.contains(entry.getKey())){
                    visited.add(entry.getKey());
                    queue.add(new Pair(entry.getKey(),newRate));
                }
            }
        }
        return 0.0;
    }

    public static Double getCheapestPath(String source, String dest, Map<String,Map<String,Double>> adjList){
        if(source.equals(dest))
            return 1.0;
        if(!adjList.containsKey(source) || !adjList.containsKey(dest))
            return 0.0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(source,1.0));
        Map<String,Double> visited = new HashMap<>();
        while(!pq.isEmpty()){
            Pair poll = pq.poll();
            if(visited.containsKey(poll.currency) && visited.get(poll.currency)<=poll.rate)
                continue;
            visited.put(poll.currency,poll.rate);
            Map<String,Double> next = adjList.get(poll.currency);
            for(var entry : next.entrySet()){
                double newRate = poll.rate*entry.getValue();
                if(entry.getKey().equals(source))
                    continue;
                if(visited.containsKey(entry.getKey()) && visited.get(entry.getKey())>newRate){
                    visited.put(entry.getKey(),newRate);
                }
                pq.add(new Pair(entry.getKey(),newRate));
            }
        }
        return visited.get(dest);
    }
}


class ExchangeRate{
    String source;
    String dest;
    double rate;

    public ExchangeRate(String source, String dest, double rate) {
        this.source = source;
        this.dest = dest;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "source='" + source + '\'' +
                ", dest='" + dest + '\'' +
                ", rate=" + rate +
                '}';
    }
}

class Pair implements Comparable<Pair>{

    String currency;
    double rate;

    public Pair(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    @Override
    public int compareTo(Pair pair) {
        return Double.compare(this.rate,pair.rate);
    }
}