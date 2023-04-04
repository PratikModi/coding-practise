package com.java.coding.interviews.practise.atlassian;

import java.util.*;

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

        List<List<String>> votesList = new ArrayList<>();
        List<String> vote1 = Arrays.asList("A","B","C");
        List<String> vote2 = Arrays.asList("A","B","D");
        List<String> vote3 = Arrays.asList("B","C","A");
        votesList.add(vote1);
        votesList.add(vote2);
        votesList.add(vote3);

        //System.out.println(findWinnerWithDifferentVoteWeight(votesList));
        System.out.println(findWinnerWithDifferentStrategy(votesList));
    }

    public static String findWinner(String[] votes){
        if(votes==null || votes.length==0)
            return "NO WINNER";
        int highestVotesSoFar =0;
        String winner="";
        Map<String, Integer> election = new HashMap<>();
        for(String vote : votes){
            election.putIfAbsent(vote,0);
            election.put(vote,election.get(vote)+1);
            int noOfVotes = election.get(vote);
            if(noOfVotes>=highestVotesSoFar){
                if((noOfVotes == highestVotesSoFar && winner.compareTo(vote)>0) || noOfVotes>highestVotesSoFar){
                    highestVotesSoFar=noOfVotes;
                    winner = vote;
                }
            }
        }
        return winner;
    }

    public static String findWinnerWithDifferentVoteWeight(List<List<String>> votes){
        if(votes==null || votes.isEmpty()){
            return "NO WINNER";
        }
        int highestVotesSoFar=0;
        String winner="";
        Map<String,Integer> votesPerCandidate = new HashMap<>();
        for(List<String> voteList : votes){
            int voteWeight = votes.size();
            for(String vote : voteList){
                votesPerCandidate.putIfAbsent(vote,0);
                votesPerCandidate.put(vote,votesPerCandidate.get(vote)+voteWeight);
                int noOfVotes = votesPerCandidate.get(vote);
                if(noOfVotes>highestVotesSoFar || (noOfVotes==highestVotesSoFar && winner.compareTo(vote)>0)){
                    highestVotesSoFar=noOfVotes;
                    winner=vote;
                }
                voteWeight--;
            }
        }
        System.out.println(votesPerCandidate);
        return winner;
    }

    public static String findWinnerWithDifferentStrategy(List<List<String>> votes){
        if(votes==null || votes.isEmpty())
            return "NO WINNER";
        int highestVoteSoFar=0;
        String winner="";
        Map<String,Map<Integer,Integer>> votesPerCandidate = new HashMap<>();
        for(List<String> voteList : votes){
            int voteWeight=voteList.size();
            int index=-1;
            for(String vote : voteList){
                index++;
                votesPerCandidate.putIfAbsent(vote,new HashMap<>());
                var voteMap = votesPerCandidate.get(vote);
                voteMap.put(index,voteMap.getOrDefault(index,0)+voteWeight);
                voteWeight--;
            }

        }
        System.out.println(votesPerCandidate);
        for(var entry : votesPerCandidate.entrySet()){
            var candidate = entry.getKey();
            int noOfVotes = entry.getValue().values().stream().reduce((a,b)->a+b).get();
            if(noOfVotes>=highestVoteSoFar){
                if(noOfVotes>highestVoteSoFar){
                    highestVoteSoFar=noOfVotes;
                    winner=candidate;
                }else{
                    var oldWinner = votesPerCandidate.get(winner);
                    var newWinner = votesPerCandidate.get(candidate);
                    for(int i=0;i<votes.size();i++){
                        int oldVotes = oldWinner.getOrDefault(i,0);
                        int newVotes = newWinner.getOrDefault(i,0);
                        if(oldVotes!=newVotes && newVotes>oldVotes){
                            highestVoteSoFar= newVotes;
                            winner=candidate;
                            break;
                        }
                    }
                }

            }
        }
        return winner;
    }
}
