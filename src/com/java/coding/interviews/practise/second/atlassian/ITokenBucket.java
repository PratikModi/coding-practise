package com.java.coding.interviews.practise.second.atlassian;

public interface ITokenBucket {
    long capacity();
    long availableTokens();
    boolean tryConsume();
    boolean tryConsume(int numToken);
    void consume();
    void consume(int numToken);
}
