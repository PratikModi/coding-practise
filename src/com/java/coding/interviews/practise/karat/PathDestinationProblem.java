package com.java.coding.interviews.practise.karat;

import java.lang.invoke.StringConcatFactory;
import java.util.*;
import java.util.function.IntFunction;

/**
 * /*
 * You work in an automated doll factory. Once dolls are assembled, they are sent to the shipping center via a series of autonomous delivery carts, each of which moves packages on a one-way route.
 *
 * Given input that provides the (directed) steps that each cart takes as pairs,
 * write a function that identifies all the start locations, and a collection of all of the possible ending locations for each start location.
 *
 * In this diagram, starting locations are at the top and destinations are at the bottom - i.e. the graph is directed exclusively downward.
 *
 *    A         E      J       Key:  [Origins]
 *   / \       / \      \             \
 *  B   C     F   L      M            [Destinations]
 *   \ /  \  /
 *    K     G
 *         / \
 *        H   I
 *
 * paths = [
 *   ["A", "B"],
 *   ["A", "C"],
 *   ["B", "K"],
 *   ["C", "K"],
 *   ["E", "L"],
 *   ["F", "G"],
 *   ["J", "M"],
 *   ["E", "F"],
 *   ["G", "H"],
 *   ["G", "I"],
 *   ["C", "G"]
 * ]
 *
 * Expected output (unordered):
 * [ "A": ["K", "H", "I"],
 *   "E:" ["H", "L", "I"],
 *   "J": ["M"] ]
 */
public class PathDestinationProblem {

    public static void main(String[] args) {
        String[][] paths = new String[][] {
                {"A", "B"},
                {"A", "C"},
                {"B", "K"},
                {"C", "K"},
                {"E", "L"},
                {"F", "G"},
                {"J", "M"},
                {"E", "F"},
                {"G", "H"},
                {"G", "I"},
                {"C", "G"},
        };
        System.out.println(findDestination(paths));
    }

    public static Map<String, Set<String>> findDestination(String[][] paths){
        Map<String,Set<String>> result = new HashMap<>();
        Map<String,Set<String>> map = new HashMap<>();
        Arrays.sort(paths,(s1,s2)->s1[1].compareTo(s2[1]));

        Set<String> source = new HashSet<>();
        Set<String> destinations = new HashSet<>();

        for(String[] path : paths){
            map.putIfAbsent(path[0], new HashSet<>());
            map.get(path[0]).add(path[1]);
            destinations.add(path[1]);
            if(!destinations.contains(path[0])){
                source.add(path[0]);
            }
        }

        Queue<String> Q = new LinkedList<>();
        Iterator<String> iterator = source.iterator();
        while(iterator.hasNext()) {
            Q.add(iterator.next());
            result.put(Q.peek(),new LinkedHashSet<>());
            String start = Q.peek();
            while (!Q.isEmpty()) {
                Set<String> endLocations = map.get(Q.poll());
                Iterator<String> setIterator = endLocations.iterator();
                while(setIterator.hasNext()){
                    String end = setIterator.next();
                    if(!map.containsKey(end)){
                        result.get(start).add(end);
                    }else{
                        Q.add(end);
                    }
                }
            }
        }
        return result;
    }


}
