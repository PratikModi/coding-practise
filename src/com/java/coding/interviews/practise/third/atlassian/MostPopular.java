package com.java.coding.interviews.practise.third.atlassian;

public interface MostPopular {
    void decreasePopularity(int contentId);
    void increasePopularity(int contentId);
    Integer mostPopular();
}
