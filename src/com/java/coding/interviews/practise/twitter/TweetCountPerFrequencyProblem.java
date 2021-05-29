package com.java.coding.interviews.practise.twitter;

import java.util.*;

/**
 * A social media company is trying to monitor activity on their site by analyzing the number of tweets that occur in select periods of time.
 * These periods can be partitioned into smaller time chunks based on a certain frequency (every minute, hour, or day).
 *
 * For example, the period [10, 10000] (in seconds) would be partitioned into the following time chunks with these frequencies:
 *
 * Every minute (60-second chunks): [10,69], [70,129], [130,189], ..., [9970,10000]
 * Every hour (3600-second chunks): [10,3609], [3610,7209], [7210,10000]
 * Every day (86400-second chunks): [10,10000]
 * Notice that the last chunk may be shorter than the specified frequency's chunk size and will always end with the end time of the period (10000 in the above example).
 *
 * Design and implement an API to help the company with their analysis.
 *
 * Implement the TweetCounts class:
 *
 * TweetCounts() Initializes the TweetCounts object.
 * void recordTweet(String tweetName, int time) Stores the tweetName at the recorded time (in seconds).
 * List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) Returns a list of integers representing the number of tweets with
 * tweetName in each time chunk for the given period of time [startTime, endTime] (in seconds) and frequency freq.
 * freq is one of "minute", "hour", or "day" representing a frequency of every minute, hour, or day respectively.
 *
 *
 * Example:
 *
 * Input
 * ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 * [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
 *
 * Output
 * [null,null,null,null,[2],[2,1],null,[4]]
 *
 * Explanation
 * TweetCounts tweetCounts = new TweetCounts();
 * tweetCounts.recordTweet("tweet3", 0);                              // New tweet "tweet3" at time 0
 * tweetCounts.recordTweet("tweet3", 60);                             // New tweet "tweet3" at time 60
 * tweetCounts.recordTweet("tweet3", 10);                             // New tweet "tweet3" at time 10
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]; chunk [0,59] had 2 tweets
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2,1]; chunk [0,59] had 2 tweets, chunk [60,60] had 1 tweet
 * tweetCounts.recordTweet("tweet3", 120);                            // New tweet "tweet3" at time 120
 * tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]; chunk [0,210] had 4 tweets
 */
public class TweetCountPerFrequencyProblem {

    private static Map<String,TreeMap<Integer,Integer>> tweetMap;

    static {
        tweetMap = new HashMap<>();
    }

    public static void main(String[] args) {
        recordTweet("tweet3", 0);
        recordTweet("tweet3", 60);
        recordTweet("tweet3", 10);
        recordTweet("tweet3", 10);
        List<Integer> result = getTweetCountsPerFrequency("minute", "tweet3", 0, 59);
        System.out.println(result);
        result = getTweetCountsPerFrequency("minute", "tweet3", 0, 60);
        System.out.println(result);
        recordTweet("tweet3", 120);
        result = getTweetCountsPerFrequency("hour", "tweet3", 0, 210);
        System.out.println(result);
    }

    public static void recordTweet(String tweetName, int time) {
        tweetMap.putIfAbsent(tweetName,new TreeMap<>());
        tweetMap.get(tweetName).putIfAbsent(time,0);
        tweetMap.get(tweetName).put(time,tweetMap.get(tweetName).get(time)+1);
    }

    public static List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> result = new ArrayList<>();
        if (!tweetMap.containsKey(tweetName)){
            result.add(0);
            return result;
        }
        int delta = getOffSet(freq);
        TreeMap<Integer,Integer> tweetTimes = tweetMap.get(tweetName);
        TreeSet<Integer> times = new TreeSet<>(tweetTimes.keySet());
        for(int i=0;i<=(endTime-startTime)/delta;i++){
            result.add(0);
        }
        Integer time = startTime-1;
        while(time!=null && time<=endTime){
            time = times.ceiling(time+1);
            if(time==null || time > endTime) break;
            int index = (time-startTime)/delta;
            result.set(index,result.get(index)+tweetTimes.get(time));
        }
        return result;
    }

    private static int getOffSet(String freq){
        int delta=1;
        switch (freq){
            case "minute":
                delta= 60;
                return delta;
            case "hour":
                delta= 60*60;
                return delta;
            case "day":
                delta= 60*60*24;
                return delta;
        }
        return delta;
    }



}
