package com.java.amazon.dynamic.atlassian;

import java.util.HashMap;
import java.util.Map;

/**
 * Find winner of an election where votes are represented as candidate names
 * Difficulty Level : Easy
 * Last Updated : 27 Jan, 2020
 * Given an array of names of candidates in an election. A candidate name in array represents a vote casted to the candidate.
 * Print the name of candidates received Max vote. If there is tie, print lexicographically smaller name.
 *
 * Examples:
 *
 * Input :  votes[] = {"john", "johnny", "jackie",
 *                     "johnny", "john", "jackie",
 *                     "jamie", "jamie", "john",
 *                     "johnny", "jamie", "johnny",
 *                     "john"};
 * Output : John
 * We have four Candidates with name as 'John',
 * 'Johnny', 'jamie', 'jackie'. The candidates
 * John and Johny get maximum votes. Since John
 * is alphabetically smaller, we print it.
 */

public class ElectionWinnerProblem {
    public static void main(String[] args) {
        String[] votes = {"john", "johnny", "jackie",
                          "johnny", "john", "jackie",
                          "jamie", "jamie", "john",
                          "johnny", "jamie", "johnny",
                          "john"};
        System.out.println(findWinner(votes));
    }

    public static String findWinner(String[] votes){
        int highestVotesSoFar =0;
        String winner="";
        Map<String, Integer> election = new HashMap<>();
        for(String vote : votes){
            election.putIfAbsent(vote,0);
            election.put(vote,election.get(vote)+1);
            int noOfVotes = election.get(vote);
            if(noOfVotes>=highestVotesSoFar){
                if(noOfVotes == highestVotesSoFar && winner.compareTo(vote)>0)
                    winner = vote;
                else{
                    highestVotesSoFar=noOfVotes;
                    winner=vote;
                }
            }
        }
        return winner;
    }
}
