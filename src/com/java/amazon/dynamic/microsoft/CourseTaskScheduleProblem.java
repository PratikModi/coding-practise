package com.java.amazon.dynamic.microsoft;

import java.util.*;

/**
 * Created by Pratik1 on 30-05-2020.
 */

/**
 * Given a course with dependencies find it we can take all courses
 * [0,1][1,2] ==> TRUE ==> 2,1,0
 * [0,1][1,2][2,0] ==> FALSE ==> Cyclic Dependencies
 */
public class CourseTaskScheduleProblem {

    private static final int UNPROCESSED = 1;
    private static final int PROCESSING = 2;
    private static final int PROCESSED = 3;

    public static void main(String[] args) {
        List<List<Integer>> dependencies = new ArrayList<>();
        List<Integer> D1 = new ArrayList<>();
        D1.add(0);D1.add(1); dependencies.add(D1);
        List<Integer> D2 = new ArrayList<>();
        D2.add(1);D2.add(2); dependencies.add(D2);
        List<Integer> D3 = new ArrayList<>();
        D3.add(2);D3.add(3); dependencies.add(D3);
        List<Integer> D4 = new ArrayList<>();
        D4.add(3);D4.add(4); dependencies.add(D4);
        List<Integer> D5 = new ArrayList<>();
        D5.add(4);D5.add(0); dependencies.add(D5);
        System.out.println(canTakeAllCourses(dependencies,5));
    }

    public static boolean canTakeAllCourses(List<List<Integer>> dependencies, int noOfCourses){
        if(dependencies==null || dependencies.size()==0)
            return false;
        Map<Integer,List<Integer>> adjMatrix = new HashMap<>();
        Map<Integer,Integer> visited = new HashMap<>();
        //Creating Adjacency Matrix
        for(int i=0;i<dependencies.size();i++){
            if(adjMatrix.containsKey(dependencies.get(i).get(0))){
                adjMatrix.get(dependencies.get(i).get(0)).add(dependencies.get(i).get(1));
            }else{
                adjMatrix.put(dependencies.get(i).get(0), Arrays.asList(dependencies.get(i).get(1)));
                visited.put(dependencies.get(i).get(0),UNPROCESSED);
                visited.put(dependencies.get(i).get(1),UNPROCESSED);
            }
        }
        //System.out.println(adjMatrix);
        //System.out.println(visited);
        Set<Integer> entrySet = visited.keySet();
        Iterator<Integer> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            int course = iterator.next();
            if(visited.get(course)==UNPROCESSED){
                if(isCyclic(adjMatrix,visited,course))
                    return false;
            }
        }
        return true;
    }

    private static boolean isCyclic(Map<Integer,List<Integer>> adjMatrix, Map<Integer,Integer> visited, Integer course){
        if(visited.get(course)==PROCESSING)
            return true; //Found Cycle
        visited.put(course,PROCESSING);
        if(adjMatrix.containsKey(course)) {
            for (int i = 0; i < adjMatrix.get(course).size(); i++) {
                if (visited.get(adjMatrix.get(course).get(i)) != PROCESSED) {
                    if (isCyclic(adjMatrix, visited, adjMatrix.get(course).get(i)))
                        return true;
                }
            }
        }
        visited.put(course,PROCESSED);
        return false;
    }

}
