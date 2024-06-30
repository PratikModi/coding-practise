package com.java.coding.interviews.practise.intuit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseScheduleProblem {

    private static final int PROCESSED =0;
    private static final int PROCESSING =1;
    private static final int UNPROCESSED =2;

    public static void main(String[] args) {

    }

    public static List<Integer> findCourseOrder(int numCourses, int[][] prerequisites){
        List<Integer> courseOrder = new ArrayList<>();
        Map<Integer,List<Integer>> adjMatrix = new HashMap<>();
        Map<Integer,Integer> visited = new HashMap<>();
        for(int i=0;i<numCourses;i++){
            visited.put(i,UNPROCESSED);
        }

        return courseOrder;
    }



}
