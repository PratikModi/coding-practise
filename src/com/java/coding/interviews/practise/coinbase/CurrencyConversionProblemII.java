package com.java.coding.interviews.practise.coinbase;

import java.util.*;

public class CurrencyConversionProblemII {

    public static void main(String[] args) {
        ExchangeRate E1 = new ExchangeRate("USD","JPY",110);
        ExchangeRate E2 = new ExchangeRate("USD","AUD",1.45);
        ExchangeRate E3 = new ExchangeRate("JPY","GBP",0.0070);
        ExchangeRate E4 = new ExchangeRate("USD","INR",74.12);
        ExchangeRate E5 = new ExchangeRate("INR","AUD",100);
        List<ExchangeRate> rates = Arrays.asList(E1,E2,E3,E4,E5);
        System.out.println(calculateMaxValue(rates,"GBP","AUD"));
    }
    //142.85714285714286*0.00909090909090909*1.45 -- 1.88
    //142.85714285714286*0.00909090909090909*74.12*100 =
    public static double calculateMaxValue(List<ExchangeRate> rates, String src, String dest){
        if(rates==null || rates.isEmpty() || src==null || dest==null)
            return -1;
        Map<String,Double> values = new HashMap<>();
        Map<String,Map<String, Double>> adjMatrix = new HashMap<>();
        PriorityQueue<ExchangeRate> PQ = new PriorityQueue<ExchangeRate>((e1,e2)->Double.compare(e1.exchangeRate,e2.exchangeRate)*-1);
        Set<String> visited = new HashSet<>();
        Map<String,String> predecessor = new LinkedHashMap<>();
        for(ExchangeRate ER : rates){
            adjMatrix.putIfAbsent(ER.src, new HashMap<>());
            adjMatrix.get(ER.src).put(ER.dest,ER.exchangeRate);
            adjMatrix.putIfAbsent(ER.dest,new HashMap<>());
            adjMatrix.get(ER.dest).put(ER.src,1.0/ER.exchangeRate);
            values.put(ER.src,Double.MIN_VALUE);
            values.put(ER.dest,Double.MIN_VALUE);
        }
        System.out.println(adjMatrix);
        visited.add(src);
        values.put(src,1.0);
        PQ.add(new ExchangeRate(src,dest,1d));
        while(!PQ.isEmpty()){
            ExchangeRate R = PQ.poll();
            Map<String,Double> next = adjMatrix.get(R.src);
            for(String S : next.keySet()){
                if(!visited.contains(S)){
                    double newValue = R.exchangeRate*next.get(S);
                    System.out.println(newValue+"-->"+values.get(S));
                    if(newValue>values.get(S)){
                        values.put(S,newValue);
                        predecessor.put(R.src,S);
                        PQ.add(new ExchangeRate(S,R.dest,newValue));
                    }
                    visited.add(R.src);
                }
            }
        }
        System.out.println(predecessor);
        System.out.println(values);
        return values.get(dest);
    }


}

class ExchangeNode{
    String currency;
    double rate;

    public ExchangeNode(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ExchangeNode{" +
                "currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }
}