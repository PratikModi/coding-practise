package com.java.coding.interviews.practise.bloomberg;

import java.util.*;

/**
 * Description,
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
 *
 * Each process only has one parent process, but may have one or more children processes.
 * This is just like a tree structure. Only one process has PPID that is 0,
 * which means this process has no parent process. All the PIDs will be distinct positive integers.
 *
 * We use two list of integers to represent a list of processes,
 * where the first list contains PID for each process and the second list contains the corresponding PPID.
 *
 * Now given the two lists, and a PID representing a process you want to kill,
 * return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed,
 * all its children processes will be killed. No order is required for the final answer.
 *
 * Example 1:
 *
 * Input:
 * pid =  [1, 3, 10, 5]
 * ppid = [3, 0, 5, 3]
 * kill = 5
 * Output: [5,10]
 */
public class KillProcessProblem {

    public static void main(String[] args) {
        List<Integer> pIds = List.of(1,3,10,5);
        List<Integer> ppIds = List.of(3,0,5,3);
        int kill =5;
        System.out.println(killProcess(ppIds,pIds,kill));
    }

    public static List<Integer> killProcess(List<Integer> ppIds, List<Integer> pIds, int kill){
        List<Integer> killedProcess = new ArrayList<>();
        Map<Integer,List<Integer>> processMap = new HashMap<>();
        for(int i=0;i<ppIds.size();i++){
            if(ppIds.get(i)>0) {
                List<Integer> childProcesses = processMap.getOrDefault(ppIds.get(i), new ArrayList<>());
                childProcesses.add(pIds.get(i));
                processMap.put(ppIds.get(i),childProcesses);

            }
        }
        System.out.println(processMap);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(kill);
        while(!queue.isEmpty()){
            int process = queue.poll();
            killedProcess.add(process);
            if(processMap.containsKey(process)){
                for(int proc : processMap.get(process)){
                    queue.add(proc);
                }
            }
        }
        return killedProcess;
    }
}
