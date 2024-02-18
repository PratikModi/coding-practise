package com.java.coding.interviews.practise.third.atlassian;

import java.util.*;

public class ElectionWinnerProblem {

    public static String findWinner(List<String> votes){
        if(votes==null || votes.isEmpty())
            return "";
        String winner="";
        int highestVotes=0;
        Map<String,Integer> votesPerCandidate = new HashMap<>();
        for(String vote : votes){
            votesPerCandidate.put(vote,votesPerCandidate.getOrDefault(vote,0)+1);
            int voteCount = votesPerCandidate.get(vote);
            if(voteCount>highestVotes || (voteCount==highestVotes && winner.compareTo(vote)>0)){
                winner=vote;
                highestVotes=voteCount;
            }
        }
        System.out.println(votesPerCandidate);
        return winner;
    }

    public static String findWinner2(List<List<String>> votes){
        if(votes==null || votes.isEmpty())
            return "";
        String winner="";
        int highestVotes=0;
        Map<String,Map<Integer,Integer>> votesPerCandidate = new HashMap<>();
        int maxVotesSize=0;
        for(var voteList : votes){
            int voteWeight = voteList.size();
            maxVotesSize = Math.max(0,voteList.size());
            for(int i=0;i<voteList.size();i++){
                votesPerCandidate.putIfAbsent(voteList.get(i),new HashMap<>());
                var voteMap = votesPerCandidate.get(voteList.get(i));
                voteMap.put(i,voteMap.getOrDefault(i,0)+voteWeight);
                votesPerCandidate.put(voteList.get(i),voteMap);
                voteWeight--;
            }
        }
        System.out.println(votesPerCandidate);
        for(var entry : votesPerCandidate.entrySet()){
            String candidate = entry.getKey();
            int totalVotes = entry.getValue().values().stream().reduce((a,b)->a+b).get();
            if(totalVotes>highestVotes){
                winner=candidate;
                highestVotes=totalVotes;
            }else if(totalVotes==highestVotes){
                var oldWinner = votesPerCandidate.get(winner);
                var newWinner = votesPerCandidate.get(candidate);
                for(int i=0;i<maxVotesSize;i++){
                    int oldVotes = oldWinner.getOrDefault(i,0);
                    int newVotes = newWinner.getOrDefault(i,0);
                    if(newVotes>oldVotes){
                        highestVotes=newVotes;
                        winner=candidate;
                    }
                }

            }
        }
        return winner;
    }

    public static void main(String[] args) {
        List<String> votes = List.of("john", "johnny", "jackie",
                "johnny", "john", "jackie",
                "jamie", "jamie", "john",
                "johnny", "jamie", "johnny",
                "john");
        System.out.println(findWinner(votes));

        List<List<String>> votesList = new ArrayList<>();
        List<String> vote1 = Arrays.asList("A","B","C");
        List<String> vote2 = Arrays.asList("A","B","D");
        List<String> vote3 = Arrays.asList("B","C","A");
        votesList.add(vote1);
        votesList.add(vote2);
        votesList.add(vote3);
        System.out.println(findWinner2(votesList));
    }

}
