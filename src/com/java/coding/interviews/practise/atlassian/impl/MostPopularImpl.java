package com.java.coding.interviews.practise.atlassian.impl;

import com.java.coding.interviews.practise.atlassian.MostPopular;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Map<Integer,Integer>  Key = contentid , Value-- No.Of Votes --- O(nLogN)
 * Sort It out based it's value --- Desc -- 1st
 * Increase the popolarity --- O(1)
 * if Map has content od -- current value+1 , 1 -- O(1)
 * if Map has content id -- currentValue-1 ,
 *
 * Questions:-
 * what if two contents have the same popularity?
 *
 */
public class MostPopularImpl implements MostPopular {

    static class Content{
        Integer contentId;
        Integer popularity;
        LocalDateTime dateTime;

        public Content(Integer contentId, Integer popularity, LocalDateTime dateTime) {
            this.contentId = contentId;
            this.popularity = popularity;
            this.dateTime = dateTime;
        }

        @Override
        public String toString() {
            return "Content{" +
                    "contentId=" + contentId +
                    ", popularity=" + popularity +
                    ", dateTime=" + dateTime +
                    '}';
        }
    }
    Map<Integer, Content> contents = new HashMap<Integer, Content>();

    /**
     * @param contentId
     */
    @Override
    public void increasePopularity(Integer contentId) {
        contents.putIfAbsent(contentId, new Content(contentId, 1, LocalDateTime.now()));
        contents.get(contentId).popularity++;
    }

    /**
     * @return
     */
    @Override
    public Integer mostPopular() {
        if(!contents.isEmpty()) {
            List<Content> contentList = contents.values().stream().collect(Collectors.toList());
            Collections.sort(contentList,(a,b)->b.popularity-a.popularity);
            return contentList.get(0).contentId;
        };
        return -1;
    }

    /**
     * @param contentId
     */
    @Override
    public void decreasePopularity(Integer contentId) {
        if(contents.containsKey(contentId)) {
            contents.get(contentId).popularity--;
            if(contents.get(contentId).popularity == 0) {
                contents.remove(contentId);
            }
        }else
            throw new IllegalArgumentException("Content id not found");
    }

    public static void main(String[] args) {
        MostPopular popular = new MostPopularImpl();
        popular.increasePopularity(1);
        popular.increasePopularity(2);
    }
}
