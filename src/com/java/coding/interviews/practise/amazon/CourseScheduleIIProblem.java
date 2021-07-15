package com.java.coding.interviews.practise.amazon;

import java.util.*;

/**
 * 210. Course Schedule II
 * Medium
 *
 * 3927
 *
 * 172
 *
 * Add to List
 *
 * Share
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 */
public class CourseScheduleIIProblem {

    private static final int UNPROCESSED=1;
    private static final int PROCESSING=2;
    private static final int PROCESSED=3;


    public static void main(String[] args) {

        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(findOrder(4,prerequisites)));
        prerequisites = new int[][]{{0,1},{1,0}};
        System.out.println(Arrays.toString(findOrder(2,prerequisites)));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        Map<Integer, List<Integer>> adjMatrix = new HashMap<>();
        Map<Integer,Integer> visited = new HashMap<>();
        for(int i=0;i<numCourses;i++){
            visited.put(i,UNPROCESSED);
        }
        if(prerequisites==null || prerequisites.length==0){
            return visited.keySet().stream().mapToInt(i->i).toArray();
        }
        for(int i=0;i<prerequisites.length;i++){
            adjMatrix.putIfAbsent(prerequisites[i][0],new ArrayList<>());
            adjMatrix.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        System.out.println(adjMatrix);
        System.out.println(visited);
        Set<Integer> set = adjMatrix.keySet();
        Iterator<Integer> iterator = set.iterator();
        List<Integer> courseSchedule = new ArrayList<>();
        while(iterator.hasNext()){
            int course = iterator.next();
            if(visited.get(course)==UNPROCESSED){
                if(isCyclic(adjMatrix,visited,course,courseSchedule)) {
                    System.out.println("CYCLIC");
                    return result;
                }
            }
        }
        for(int i=0;i<numCourses;i++){
            if(visited.get(i)==UNPROCESSED){
                if(isCyclic(adjMatrix,visited,i,courseSchedule))
                    return new int[]{};
            }
        }
        return courseSchedule.stream().mapToInt(i->i).toArray();
    }

    public static boolean isCyclic(Map<Integer,List<Integer>> adjMatrix, Map<Integer,Integer> visited, int course, List<Integer> result){
        if(visited.get(course)==PROCESSING)
            return true;
        visited.put(course,PROCESSING);
        if(adjMatrix.containsKey(course)) {
            for (int i=0;i<adjMatrix.get(course).size();i++){
                if(visited.get(adjMatrix.get(course).get(i))!=PROCESSED){
                    if(isCyclic(adjMatrix,visited,adjMatrix.get(course).get(i),result))
                        return true;
                }
            }
        }
        result.add(course);
        visited.put(course,PROCESSED);
        return false;
    }

}
