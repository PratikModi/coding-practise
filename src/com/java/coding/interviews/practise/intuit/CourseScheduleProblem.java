package com.java.coding.interviews.practise.intuit;

import java.util.*;

public class CourseScheduleProblem {

    private static final int PROCESSED =0;
    private static final int PROCESSING =1;
    private static final int UNPROCESSED =2;

    public static void main(String[] args) {
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(findCourseOrder(4,prerequisites));
    }

    public static List<Integer> findCourseOrder(int numCourses, int[][] prerequisites){
        List<Integer> courseOrder = new ArrayList<>();
        Map<Integer,List<Integer>> adjMatrix = new HashMap<>();
        Map<Integer,Integer> visited = new HashMap<>();
        for(int i=0;i<numCourses;i++){
            visited.put(i,UNPROCESSED);
        }

        for(int[] pair : prerequisites){
            adjMatrix.putIfAbsent(pair[0],new ArrayList<>());
            adjMatrix.get(pair[0]).add(pair[1]);
        }
        System.out.println(adjMatrix);
        System.out.println(visited);
        Set<Integer> set = adjMatrix.keySet();
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()){
            int course = iterator.next();
            if(visited.get(course)==UNPROCESSED){
                if(isCyclic(adjMatrix,courseOrder,course,visited)){
                    System.out.println("CYCLIC");
                    return courseOrder;
                }
            }
        }
        for(int i=0;i<numCourses;i++){
            if(visited.get(i)==UNPROCESSED){
                if(isCyclic(adjMatrix,courseOrder,i,visited))
                    return new ArrayList<>();
            }
        }
        return courseOrder;
    }

    private static boolean isCyclic(Map<Integer,List<Integer>> adjMatrix, List<Integer> courseOrder, int course, Map<Integer,Integer> visited){
        if(visited.get(course)==PROCESSING)
            return true;
        visited.put(course,PROCESSING);
        if(adjMatrix.containsKey(course)){
            for(int next : adjMatrix.get(course)){
                if(visited.get(next)!=PROCESSED){
                    if(isCyclic(adjMatrix, courseOrder, next, visited))
                        return true;
                }
            }
        }
        visited.put(course,PROCESSED);
        courseOrder.add(course);
        return false;
    }


    public static List<Integer> findCourseOrder2(int numCourses, int[][] prerequisites){
        List<Integer> courseOrder = new ArrayList<>();

        return courseOrder;
    }


}
