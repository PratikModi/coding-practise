package com.java.coding.interviews.practise.third.atlassian.impl;

import com.java.coding.interviews.practise.third.atlassian.MostPopular;

/**
 * Questions
 * =========
 * 1. What if two contentId have same popularity? If so which one to return?
 * 2. Do we need to take care of Concurrency? Means what if we get up/down vote for same contentId at the same time?
 * 3. I am making assumption that we are writing code for non-distributed environment but we will discuss how we can make it work for
 *    distributed environment.
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
    @Override
    public void decreasePopularity(int contentId) {

    }

    @Override
    public void increasePopularity(int contentId) {

    }

    @Override
    public Integer mostPopular() {
        return 0;
    }
}

class Content{
    Integer contentId;
    Integer popularity;

    public Content(Integer contentId, Integer popularity) {
        this.contentId = contentId;
        this.popularity = popularity;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getPopularity() {
        return popularity;
    }
}
