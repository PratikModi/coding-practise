package com.java.coding.interviews.practise.second.atlassian;

import java.util.*;

public class ElectionWinnerProblem {

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static String findWinner(List<String> votes){
        String winner="";
        int highestVoteSoFar=0;
        Map<String,Integer> votePerCandidate = new HashMap<>();
        for(String vote : votes){
            votePerCandidate.putIfAbsent(vote,0);
            votePerCandidate.put(vote,votePerCandidate.get(vote)+1);
        }
        for(var entry : votePerCandidate.entrySet()){
            if(entry.getValue()>highestVoteSoFar || (entry.getValue()==highestVoteSoFar && winner.compareTo(entry.getKey())>0)){
                winner= entry.getKey();
                highestVoteSoFar = entry.getValue();
            }
        }
        return winner;
    }


    public static String findWinnerEfficient(List<String> votes){
        String winner="";
        int highestVoteSoFar=0;
        Map<String,Integer> votesPerCandidate = new HashMap<>();
        for(String vote : votes){
            votesPerCandidate.putIfAbsent(vote,0);
            votesPerCandidate.put(vote,votesPerCandidate.get(vote)+1);
            int voteCount = votesPerCandidate.get(vote);
            if(voteCount>highestVoteSoFar || (voteCount==highestVoteSoFar && winner.compareTo(vote)>0)){
                winner=vote;
                highestVoteSoFar = voteCount;
            }
        }
        return winner;
    }

    public static String findWinnerWithDifferentStrategy(List<List<String>> votes){
        String winner="";
        int highestVoteSoFar =0;
        Map<String,Map<Integer,Integer>> votesPerCandidate = new HashMap<>();
        for(var list : votes){
            int index=-1;
            int voteWeight = votes.size();
            for(String vote : list){
                votesPerCandidate.putIfAbsent(vote,new HashMap<>());
                index++;
                var voteMap = votesPerCandidate.get(vote);
                voteMap.putIfAbsent(index,0);
                voteMap.put(index,voteMap.get(index)+voteWeight);
                voteWeight--;
            }
        }
        System.out.println(votesPerCandidate);
        for(var entry : votesPerCandidate.entrySet()){
            var candidate = entry.getKey();
            int totalVotes = entry.getValue().values().stream().reduce((a,b)->a+b).get();
            if(totalVotes>=highestVoteSoFar){
                if(totalVotes>highestVoteSoFar){
                    highestVoteSoFar = totalVotes;
                    winner = entry.getKey();
                }else{
                    var oldWinner = votesPerCandidate.get(winner);
                    var newWinner = votesPerCandidate.get(candidate);
                    for(int i=0;i<votes.size();i++){
                        int oldWinnerVotes = oldWinner.getOrDefault(i,0);
                        int newWinnerVotes = newWinner.getOrDefault(i,0);
                        if(newWinnerVotes>oldWinnerVotes){
                            highestVoteSoFar=newWinnerVotes;
                            winner=candidate;
                        }
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
        System.out.println(findWinnerEfficient(votes));

        List<List<String>> votesList = new ArrayList<>();
        List<String> vote1 = Arrays.asList("A","B","C");
        List<String> vote2 = Arrays.asList("A","B","D");
        List<String> vote3 = Arrays.asList("B","C","A");
        votesList.add(vote1);
        votesList.add(vote2);
        votesList.add(vote3);

        System.out.println(findWinnerWithDifferentStrategy(votesList));
    }

}
