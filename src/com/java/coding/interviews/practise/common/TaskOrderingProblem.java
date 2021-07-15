package com.java.coding.interviews.practise.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pratik1 on 26-04-2020.
 */
public class TaskOrderingProblem {

    public static void main(String[] args) {
        System.out.println(orderTasks(new int[][]{{}, {0}, {0}, {1, 2}, {1, 2, 3}}));
    }

    public static List<Integer> orderTasks(int[][] tasks){
        List<Integer> result = new ArrayList<>();
        if(tasks==null || tasks.length==0)
            return result;
        Set<Integer> visiting = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for(int i=0;i<tasks.length;i++){
            if(!visited.contains(i)){
                visit(i,tasks,visiting,visited,result);
            }
        }

        return result;
    }


    public static void visit(int task,int[][] tasks, Set<Integer> visiting,Set<Integer> visited, List<Integer> result){
       if(visiting.contains(task))
           throw new RuntimeException("There is cyclic dependency");
        if(!visited.contains(task)){
            visiting.add(task);
            for(int i : tasks[task]){
                visit(i,tasks,visiting,visited,result);
            }
        }
        visited.add(task);
        visiting.remove(task);
        result.add(task);
    }
}
