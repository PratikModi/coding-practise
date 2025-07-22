package com.java.coding.interviews.practise.atlassian;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Problem Statement
 *
 * You are given a stream of commodity price updates in the form:
 * You need to:
 * 	1.	Store price updates for each commodity.
 * 	2.	Retrieve the latest price at any given timestamp.
 * 	3.	(Optional) Find the price of a commodity at a given time or in a given time range.
 * 	Updates:
 * (1, "Gold", 100)
 * (2, "Silver", 50)
 * (3, "Gold", 120)
 * (5, "Silver", 70)
 * (6, "Gold", 130)
 * Queries
 * 	•	Latest price of “Gold” at time 4 → 120
 * 	•	Latest price of “Silver” at time 3 → 50
 * 	•	Latest price of “Gold” at time 6 → 130
 */
public class CommodityPriceProblem {

    private Map<String, TreeMap<Integer,Double>> priceMap;
    static class PriceData{
        TreeMap<Integer,Double> priceMap = new TreeMap<>();
        TreeMap<Integer,Double> prefixSumMap = new TreeMap<>();
    }
    private Map<String,PriceData> data;
    public CommodityPriceProblem(){
        this.priceMap = new HashMap<>();
        this.data = new HashMap<>();
    }

    public void updatePrice2(String commodityName,int timestamp, double price){
        data.putIfAbsent(commodityName, new PriceData());
        PriceData priceData = data.get(commodityName);
        priceData.priceMap.put(timestamp, price);
        double lastSum = priceData.prefixSumMap.isEmpty()?0:priceData.prefixSumMap.lastEntry().getValue();
        priceData.prefixSumMap.put(timestamp, lastSum + price);
    }

    public Double getAveragePrice(String commodityName,int t1, int t2){
        if(!data.containsKey(commodityName)) return null;
        PriceData priceData = data.get(commodityName);
        NavigableMap<Integer,Double> sub = priceData.priceMap.subMap(t1,true,t2,true);
        if(sub.isEmpty()) return null;
        double sumT2 = findPrefixSumAt(priceData,t2);
        double sumT1 = findPrefixSumBefore(priceData,t1);
        double sum = sumT2-sumT1;
        return sum/(double) sub.size();
    }

    private double findPrefixSumAt(PriceData priceData,int timestamp){
        Map.Entry<Integer, Double> lastEntry = priceData.prefixSumMap.floorEntry(timestamp);
        return lastEntry==null?0:lastEntry.getValue();
    }

    private double findPrefixSumBefore(PriceData priceData,int timestamp){
        Map.Entry<Integer, Double> lastEntry = priceData.prefixSumMap.lowerEntry(timestamp);
        return lastEntry==null?0:lastEntry.getValue();
    }



    public void updatePrice(String commodity, int timestamp, double price){
        priceMap.putIfAbsent(commodity, new TreeMap<>());
        priceMap.get(commodity).put(timestamp, price);
    }

    public Double getPriceAt(String commodity, int timestamp){
        if(!priceMap.containsKey(commodity)) return null;
        Map.Entry<Integer,Double> price = priceMap.get(commodity).floorEntry(timestamp);
        return price!=null?price.getValue():null;
    }

    public static void main(String[] args) {
        CommodityPriceProblem tracker = new CommodityPriceProblem();
        tracker.updatePrice("Gold", 1, 100);
        tracker.updatePrice("Silver", 2, 50);
        tracker.updatePrice("Gold", 3, 120);
        tracker.updatePrice("Silver", 5, 70);
        tracker.updatePrice("Gold", 6, 130);

        System.out.println("Gold price at time 4: " + tracker.getPriceAt("Gold", 4)); // 120
        System.out.println("Silver price at time 3: " + tracker.getPriceAt("Silver", 3)); // 50
        System.out.println("Gold price at time 6: " + tracker.getPriceAt("Gold", 6)); // 130
        System.out.println("Silver price at time 1: " + tracker.getPriceAt("Silver", 1)); // null

        tracker.updatePrice2("Gold", 1, 100);
        tracker.updatePrice2("Gold", 3, 120);
        tracker.updatePrice2("Gold", 5, 130);
        tracker.updatePrice2("Gold", 7, 150);

        System.out.println("Gold price at time 4: " + tracker.getPriceAt("Gold", 4)); // 120
        System.out.println("Average Gold price between 2 and 6: " + tracker.getAveragePrice("Gold", 2, 6));
    }
}
