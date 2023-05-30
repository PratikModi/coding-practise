package com.java.coding.interviews.practise.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are N people who want to visit the haunted house. The person will only go if at least L other people will go with him.
 * Additionally, that person doesn't want to go with more than R other people, since it would ruin the experience for them.
 * What is the maximum number of people that can visit the haunted house at one time so that no constraint is violated?
 *
 * Sample Input:
 *
 * // The number of people
 * 	6
 * //  L R
 * 	1 2
 * 	1 4
 * 	0 3
 * 	0 1
 * 	3 4
 * 	0 2
 * Sample Output
 *
 * 3
 */

public class HauntedHouseProblem {
    public static int maxVisits(int n, List<Pair<Integer, Integer>> prefs) {
        Map<Integer, Integer> d = new HashMap<>();

        for (Pair<Integer, Integer> p : prefs) {
            for (int i = p.getFirst(); i <= p.getSecond(); i++) {
                d.put(i, d.getOrDefault(i, 0) + 1);
            }
        }

        System.out.println(d);

        int res = 0;

        for (int i : d.keySet()) {
            int curr = Math.min(i + 1, d.get(i));
            res = Math.max(curr, res);
        }

        return res;
    }

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> prefs = new ArrayList<>();
        prefs.add(new Pair<>(0, 1));
        prefs.add(new Pair<>(1, 3));
        prefs.add(new Pair<>(0, 2));
        prefs.add(new Pair<>(1, 4));
        prefs.add(new Pair<>(3, 5));

        int result = maxVisits(5, prefs);
        System.out.println(result);
    }

    static class Pair<T, U> {
        private T first;
        private U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }
    }
}