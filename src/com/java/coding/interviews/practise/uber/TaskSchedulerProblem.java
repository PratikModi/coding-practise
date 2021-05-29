package com.java.coding.interviews.practise.uber;

import java.util.*;

/**
 * Task Scheduler
 *
 * Solution
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task.
 * Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array),
 * that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *
 * Constraints:
 *
 * 1 <= task.length <= 104
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 */
public class TaskSchedulerProblem {
    public static void main(String[] args) {

        char[] tasks = new char[] {'A','B','A'};
        int n=2;
        int result = taskScheduler(tasks,n);
        System.out.println(result);
    }

    private static int taskScheduler(char[] tasks, int n){
        int result=0;
        if(tasks==null || tasks.length==0)
            return result;
        Map<Character,Integer> count = new HashMap<>();
        PriorityQueue<Integer> PQ = new PriorityQueue<>((a,b)->b-a);
        for(char t : tasks){
            count.put(t,count.getOrDefault(t,0)+1);
        }
        System.out.println(count);
        PQ.addAll(count.values());
        System.out.println(PQ);
        while(!PQ.isEmpty()){
            int times=0;
            List<Integer> pendingTasks = new ArrayList<>();
            for(int i=0;i<n+1;i++){
                if(!PQ.isEmpty()){
                    pendingTasks.add(PQ.remove()-1);
                    times++;
                }
            }
            System.out.println(pendingTasks);
            for(int t : pendingTasks){
                if(t>0){
                    PQ.add(t);
                }
            }
            result+=PQ.isEmpty()?times:n+1;
        }
        return result;
    }
}
