package com.java.coding.interviews.practise.third.atlassian.impl;

import com.java.coding.interviews.practise.third.atlassian.MostPopular;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Questions
 * =========
 * 1. What if two contentId have same popularity? If so which one to return?
 * 2. Do we need to take care of Concurrency? Means what if we get up/down vote for same contentId at the same time?
 * 3. I am making assumption that we are writing code for non-distributed environment but we will discuss how we can make it work for
 *    distributed environment.
 * 4. When Time is introduced. What if contentId-1 has popularity 2 and contentId 2 had popularity 3. ContentId 1 is latest which has
 *    got increase priority action. Now if contentId 2 has got decrease priority and due to that both have same popularity level 2.
 *    which one to return?
 *
 * Talking Point
 * =============
 * 1. We will discuss multiple approach to solve the problem and including brute force in fact we will start with brute force only
 * 2. We will pick the best approach in terms of time + space complexity, we will discuss the reason for it.
 * 3. We will go over the working code and run the code to see if its working.
 *
 * Approach-1
 * ==========
 * 1. We can use Map data structure to store the popularity against each contentId. Map<Integer,Integer>. We can use ConcurrentHashMap to
 *    support concurrency. We can make increase/decrease popularity operation as O(1) and make most popular as O(NLogN). We can also make
 *    most popular as O(1) and make increase and decrease as O(NLogN) time complexity.
 *    This approach is not completely scalable and adoptable as in future if we want to support more attribute for content then it would
 *    be difficult and would require lots of changes.
 *
 * Approach-2
 * =========
 * 2. We can still use the same Map Data structure but wrap all the content attributes into Object names Content. Create a Map<Integer,Content>
 *    This is scalable and adoptable.
 *
 */
public class MostPopularImpl implements MostPopular {
    private Map<Integer, Content> popularityMap;

    public MostPopularImpl() {
        this.popularityMap = new ConcurrentHashMap<>();
    }

    @Override
    public void decreasePopularity(int contentId) {
       if(popularityMap.containsKey(contentId)){
           popularityMap.get(contentId).popularity--;
           popularityMap.get(contentId).time=Instant.now().toEpochMilli();
           if(popularityMap.get(contentId).popularity==0)
               popularityMap.remove(contentId);
       }else{
           throw new RuntimeException("Invalid Content Id!!");
       }
    }

    @Override
    public void increasePopularity(int contentId) {
        popularityMap.putIfAbsent(contentId,new Content(contentId,0));
        popularityMap.get(contentId).popularity++;
        popularityMap.get(contentId).time=Instant.now().toEpochMilli();
    }

    @Override
    public Integer mostPopular() {
        System.out.println(popularityMap);
        if(!popularityMap.isEmpty()){
            List<Content> sortedContent=popularityMap.values().stream().sorted().collect(Collectors.toList());
            return sortedContent.get(0).contentId;
        }
        return -1;
    }
}

class Content implements Comparable<Content>{
    Integer contentId;
    Integer popularity;
    Long time;

    public Content(Integer contentId, Integer popularity) {
        this.contentId = contentId;
        this.popularity = popularity;
        this.time = Instant.now().toEpochMilli();
    }

    public Content(Integer contentId, Integer popularity, Long time) {
        this.contentId = contentId;
        this.popularity = popularity;
        this.time = time;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public Long getTime() {
        return time;
    }

    @Override
    public int compareTo(Content content) {
        if(content.popularity!=this.popularity)
            return content.popularity.compareTo(this.popularity);
        else return content.time.compareTo(this.time);
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentId=" + contentId +
                ", popularity=" + popularity +
                ", time=" + time +
                '}';
    }
}
