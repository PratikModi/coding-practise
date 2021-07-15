package com.java.coding.interviews.practise.amazon;

import java.util.*;

/**
 * Given a list of Product Id pairs, group them according to their categories and return the new list containing categorized Product Ids.
 *
 * Input: ((1,2), (2,5), (3,4), (4,6), (6,8), (5,7), (5,2), (5,2))
 *
 * Output: ((1,2,5,7), (3,4,6,8))
 *
 * Test case I came up with:
 *
 * Input: ((1,2), (2,5), (3,4), (4,6), (6,8), (5,7), (5,2), (3,1))
 *
 * Output: ((1,2,5,7,3,4,6,8))
 */
public class GroupProductProblem {
    public static void main(String[] args) {
        List<List<Integer>> productList = new ArrayList<>();
        productList.add(Arrays.asList(1,2));
        productList.add(Arrays.asList(2,5));
        productList.add(Arrays.asList(3,4));
        productList.add(Arrays.asList(4,6));
        productList.add(Arrays.asList(6,8));
        productList.add(Arrays.asList(5,7));
        productList.add(Arrays.asList(5,2));
        productList.add(Arrays.asList(5,2));
        System.out.println(groupProducts(productList));
        List<List<Integer>> productList2 = new ArrayList<>();
        productList2.add(Arrays.asList(1,2));
        productList2.add(Arrays.asList(2,5));
        productList2.add(Arrays.asList(3,4));
        productList2.add(Arrays.asList(4,6));
        productList2.add(Arrays.asList(6,8));
        productList2.add(Arrays.asList(5,7));
        productList2.add(Arrays.asList(5,2));
        productList2.add(Arrays.asList(3,1));
        System.out.println(groupProducts(productList2));
    }

    public static List<List<Integer>> groupProducts(List<List<Integer>> productList){
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer,List<Integer>> adjMatrix = new HashMap<>();
        for(List<Integer> L : productList){
            adjMatrix.putIfAbsent(L.get(0),new ArrayList<>());
            adjMatrix.putIfAbsent(L.get(1),new ArrayList<>());
            adjMatrix.get(L.get(0)).add(L.get(1));
            adjMatrix.get(L.get(1)).add(L.get(0));
        }
        Set<Integer> visited = new HashSet<>();
        Iterator<Integer> iterator = adjMatrix.keySet().iterator();
        while(iterator.hasNext()){
            Integer key = iterator.next();
            if(!visited.contains(key)){
                List<Integer> group = new ArrayList<>();
                dfs(key,visited,adjMatrix,group);
                result.add(group);
            }
        }
        return result;
    }

    private static void dfs(int key, Set<Integer> visited, Map<Integer,List<Integer>> adjMatrix, List<Integer> group){
        if(!adjMatrix.containsKey(key)||visited.contains(key)) return;
        visited.add(key);
        group.add(key);
        for(Integer P : adjMatrix.get(key)){
            if(!visited.contains(P)){
                dfs(P,visited,adjMatrix,group);
            }
        }
    }

}
