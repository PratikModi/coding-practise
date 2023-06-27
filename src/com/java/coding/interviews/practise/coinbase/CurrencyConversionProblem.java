package com.java.coding.interviews.practise.coinbase;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class CurrencyConversionProblem {
    public static void main(String[] args) {
        ExchangeRate E1 = new ExchangeRate("USD","JPY",110);
        ExchangeRate E2 = new ExchangeRate("USD","AUD",1.45);
        ExchangeRate E3 = new ExchangeRate("JPY","GBP",0.0070);
        List<ExchangeRate> rates = Arrays.asList(E1,E2,E3);
        System.out.println(findMaximumValue(rates,"GBP","AUD"));
    }

    public static double findMaximumValue(List<ExchangeRate> rate, String src, String dest){
        if(rate==null || rate.isEmpty() || src==null || dest==null)
            return -1;
        Map<String,Map<String,Double>> adjMatrix = new HashMap<>();
        double result = -1;
        for(ExchangeRate E : rate){
            adjMatrix.putIfAbsent(E.src,new HashMap<>());
            adjMatrix.get(E.src).put(E.dest,E.exchangeRate);
            adjMatrix.putIfAbsent(E.dest,new HashMap<>());
            adjMatrix.get(E.dest).put(E.src,1.0/E.exchangeRate);
        }
        System.out.println(adjMatrix);
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> currency = new LinkedList<>();
        Queue<Double> value = new LinkedList<>();
        currency.offer(src);
        value.offer(1d);
        while(!currency.isEmpty()){
            String crc = currency.poll();
            double val = value.poll();
            if(visited.contains(crc)) continue;
            visited.add(crc);
            System.out.println(visited);
            if(adjMatrix.containsKey(crc)){
                Map<String,Double> next = adjMatrix.get(crc);
                for(String key : next.keySet()){
                    if(!visited.contains(key)){
                        currency.offer(key);
                        if(key.equals(dest)){
                            result = Math.max(result,val*next.get(key));
                        }
                        value.offer(val*next.get(key));
                    }
                }
            }

        }
        return new BigDecimal(result).setScale(2,RoundingMode.CEILING).doubleValue();
    }
}

class ExchangeRate{
    String src;
    String dest;
    double exchangeRate;

    public ExchangeRate(String src, String dest, double exchangeRate) {
        this.src = src;
        this.dest = dest;
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "src='" + src + '\'' +
                ", dest='" + dest + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
