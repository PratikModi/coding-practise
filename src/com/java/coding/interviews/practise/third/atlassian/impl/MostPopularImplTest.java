package com.java.coding.interviews.practise.third.atlassian.impl;

import com.java.coding.interviews.practise.third.atlassian.MostPopular;

import java.util.concurrent.TimeUnit;

public class MostPopularImplTest {

    public static void main(String[] args) throws Exception {
        MostPopular popular = new MostPopularImpl();
        popular.increasePopularity(7);
        popular.increasePopularity(8);
        popular.increasePopularity(8);
        System.out.println(popular.mostPopular());
        TimeUnit.SECONDS.sleep(1);
        popular.increasePopularity(7);
        System.out.println(popular.mostPopular());
        popular.decreasePopularity(7);
        System.out.println(popular.mostPopular());
    }



}
