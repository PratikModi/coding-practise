package com.java.coding.interviews.practise.deliveroo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * There is an authentication system that works with authentication tokens.
 * For each session, the user will receive a new authentication token that will expire timeToLive seconds after the currentTime.
 * If the token is renewed, the expiry time will be extended to expire timeToLive seconds after the (potentially different) currentTime.
 *
 * Implement the AuthenticationManager class:
 *
 * AuthenticationManager(int timeToLive) constructs the AuthenticationManager and sets the timeToLive.
 * generate(string tokenId, int currentTime) generates a new token with the given tokenId at the given currentTime in seconds.
 * renew(string tokenId, int currentTime) renews the unexpired token with the given tokenId at the given currentTime in seconds.
 * If there are no unexpired tokens with the given tokenId, the request is ignored, and nothing happens.
 * countUnexpiredTokens(int currentTime) returns the number of unexpired tokens at the given currentTime.
 * Note that if a token expires at time t, and another action happens on time t (renew or countUnexpiredTokens),
 * the expiration takes place before the other actions.
 */
public class AuthenticationManagerProblem {
    int timeToLive=0;
    Map<String,Integer> map;
    public AuthenticationManagerProblem(int timeToLive) {
        this.timeToLive = timeToLive;
        this.map = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        map.put(tokenId,currentTime+timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        if(!map.containsKey(tokenId) || map.get(tokenId)<=currentTime){
            return;
        }
        generate(tokenId,currentTime);
    }

    public int countUnexpiredTokens(int currentTime) {
        Set<String> tokenSet = new HashSet<>(map.keySet());
        for(String tokenId : tokenSet){
            if(map.get(tokenId)<=currentTime){
                map.remove(tokenId);
            }
        }
        return map.size();
    }

    public static void main(String[] args) {
        AuthenticationManagerProblem test = new AuthenticationManagerProblem(5);

    }

}
