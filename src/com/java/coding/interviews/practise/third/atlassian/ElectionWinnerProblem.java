package com.java.coding.interviews.practise.third.atlassian;

import java.util.HashMap;
import java.util.Map;

/**
 * Questions
 * =======
 * 1. Can two candidates have same votes? if so which one to return.
 * 2. Is name of candidate case-sensitive?
 *
 * Talking Points
 * ==========
 * 1. We will discuss multiple approach to solve the problem and including brute force in fact we will start with brute force only
 * 2. We will pick the best approach in terms of time + space complexity, we will discuss the reason for it.
 * 3. We will go over the working code and run the code to see if its working.
 *
 * Approach-1 Brute Force
 * ==================
 * 1. Create the Object named Candidate with attributed name and totalVotes
 * 2. Iterate through the input array and create the List of Candidate object using along with Map<String, Candidate>
 * 3. Take the list and sort the candidate list based on totalVotes.
 * 4. This is not efficient as it takes O(N) + O(NlogN) = O(NLogN)
 *
 * Approach-2
 * ========
 * 1. we can use Map data structure to store total votes against each candidate::: Map<String,Integer>
 * 2. Populate the Map with candidate and vote details -- O(N)
 * 3. Again iterate over map to find the candidate with highest votes -- O(N)
 * 4. O(N) + O(N) == O(N)
 * 5. But this can cause the problem when given input is too large. Performance will hit when you have to iterate twice.
 *
 * Approach-3
 * ========
 * 1. This is very similar to Approach-2 only we will use the same Map Data Structure.
 * 2. Difference is while populating the map itself we will keep maintaining the Highest voted candidate name.
 * 3. Why this approach is efficient because we need to iterate over input only once.
 * 4. Time Complexity : O(N)
 */

public class ElectionWinnerProblem {

    public static void main(String[] args) {
        String[] votes = {"john", "johnny", "jackie",
                "johnny", "john", "jackie",
                "jamie", "jamie", "john",
                "johnny", "jamie", "johnny",
                "john"};
        System.out.println(findElectionWinner1(votes));
        System.out.println("==================");
        System.out.println(findElectionWinner2(votes));
        System.out.println("==================");
        String[][] votes_multi = new String[][]{
                {"A","B","C"},{"B","C","A"},{"C","A","B"}
        };
        System.out.println(findElectionWinnerWithDifferentStrategy1(votes_multi));
    }

    public static String findElectionWinner1(String[] votes){
        if(votes==null || votes.length==0)
            return "";
        Map<String,Integer> voteMap = new HashMap<>();
        for(String vote : votes){
            voteMap.putIfAbsent(vote,0);
            voteMap.put(vote,voteMap.get(vote)+1);
        }
        System.out.println(voteMap);
        String winner="";
        Integer highestVotesSoFar=0;
        for(var entry : voteMap.entrySet()){
            if(entry.getValue()>=highestVotesSoFar){
                if(entry.getValue()>highestVotesSoFar || (entry.getValue()==highestVotesSoFar && winner.compareTo(entry.getKey())>0)){
                    highestVotesSoFar=entry.getValue();
                    winner=entry.getKey();
                }
            }
        }
        return winner;
    }


    public static String findElectionWinner2(String[] votes){
        if(votes==null || votes.length==0)
            return "";
        Map<String,Integer> voteMap = new HashMap<>();
        String winner="";
        Integer highestVoteSoFar=0;
        for(String vote : votes){
            voteMap.putIfAbsent(vote,0);
            voteMap.put(vote,voteMap.get(vote)+1);
            Integer totalCandidateVotes = voteMap.get(vote);
            if(totalCandidateVotes>=highestVoteSoFar){
                if(totalCandidateVotes>highestVoteSoFar || (totalCandidateVotes==highestVoteSoFar && winner.compareTo(vote)>0)){
                    winner=vote;
                    highestVoteSoFar = totalCandidateVotes;
                }
            }
        }
        return winner;
    }

    public static String findElectionWinnerWithDifferentStrategy1(String[][] votes){
        if(votes==null || votes.length==0)
            return "";
        Map<String,Integer> voteMap = new HashMap<>();
        String winner="";
        Integer highestVoteSoFar=0;
        for(int i=0;i<votes.length;i++){
            for(int j=0;j<votes[i].length;j++){
                int weight = votes[i].length-j;
                String vote = votes[i][j];
                voteMap.putIfAbsent(vote,0);
                voteMap.put(vote,voteMap.get(vote)+weight);
                int candidateVote = voteMap.get(vote);
                if(candidateVote>=highestVoteSoFar){
                    if(candidateVote>highestVoteSoFar || (candidateVote==highestVoteSoFar && winner.compareTo(vote)>0)){
                        winner=vote;
                        highestVoteSoFar=candidateVote;
                    }
                }
            }
        }
        System.out.println(voteMap);
        return winner;
    }



}
