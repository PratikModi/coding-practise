package com.java.coding.interviews.practise.atlassian;

import java.util.*;

/**
 * Agent Rating system:
 * Return all the agents and the average rating each one has received, ordered highest to lowest.
 *
 * Questions:
 * ========
 * 1. What is the input format? -- List/File/DB
 * 2. What data type should I use for ratings?
 *     Are ratings always integers (1–5), or can they be floats/decimals?
 * 3. How should ties be handled in sorting?
 *    If two agents have the same average rating, should we sort by agent ID?
 *
 * ✅ Time & Space Complexity
 * Parsing + Aggregation O(n)
 * Sorting O(k log k)
 * Space O(k)
 *
 */
public class AgentRatingSystem {
    public static void main(String[] args) {
        List<String[]> ratings = Arrays.asList(
                new String[]{"A1", "5"},
                new String[]{"A2", "4"},
                new String[]{"A1", "3"},
                new String[]{"A3", "5"},
                new String[]{"A2", "5"},
                new String[]{"A3", "2"}
        );
        List<AverageRating> averageRatings = computeAverageRatings(ratings);
        for (AverageRating aa : averageRatings) {
            System.out.println(aa.name + " → " + String.format("%.2f", aa.average));
        }
    }

    static class AverageRating{
        String name;
        double average;
        AverageRating(String name, double average) {
            this.name = name;
            this.average = average;
        }

        @Override
        public String toString() {
            return "AverageRating{" +
                    "name='" + name + '\'' +
                    ", average=" + average +
                    '}';
        }
    }

    public static List<AverageRating> computeAverageRatings(List<String[]> ratings){
        Map<String,int[]> ratingMap = new HashMap<>();
        for (String[] rating : ratings) {
            ratingMap.putIfAbsent(rating[0],new int[2]);
            ratingMap.get(rating[0])[0]+=Integer.parseInt(rating[1]); // Total Rating sum
            ratingMap.get(rating[0])[1]+=1; // count
        }
        List<AverageRating> averageRatings = new ArrayList<>();
        for(var entry : ratingMap.entrySet()){
            String agentId = entry.getKey();
            int[] data = entry.getValue();
            double average = (double)data[0]/data[1];
            averageRatings.add(new AverageRating(agentId, average));
        }

        averageRatings.sort((a,b)->Double.compare(b.average,a.average));
        return averageRatings;
    }

}
