package com.java.coding.interviews.practise.atlassian;

import java.util.concurrent.TimeUnit;

public interface IRefillStrategy {

    long refill();
    long durationUntilNextRefill(TimeUnit unit);
}
