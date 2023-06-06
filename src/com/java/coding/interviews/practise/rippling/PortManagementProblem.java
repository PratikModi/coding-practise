package com.java.coding.interviews.practise.rippling;

import java.util.*;

/**
 * Assume you are writing the firmware for a network bridge.
 * There are N ports each with an unique ID.
 * We want to write a class with the get and free methods as shown in the example.
 * Let’s start by optimizing for runtime complexity.
 *
 * Required methods:
 *
 * get() → Return a currently free port.
 * Once a port is returned it is considered busy.
 * free(port_id) → Frees the given port.
 * Nothing should happen if you free a port which is already free.
 * // For example:
 * // - N = 3
 * // - ports = [0,1,2]
 * // - get() → 1
 * // - get() → 0
 * // - get() → 2
 * // - get() → None
 * // - free(1) → None
 * // - get() → 1
 * // - get() → None
 *
 * I implemented boolean array based solution and set based solution.
 * boolean array solution: Time Complexity for get(): O(n) worse case, Space complexity: O(n)
 * set solution: Time Complexity for get(): O(1) worse case, Space complexity: O(n)
 *
 * The interviewer suggests that space complexity of set solution is 8bit * n, and the space complexity if boolean array solution is 1bit * n.
 * The interviewer suggests the device is space limited and wants me to improve the get() time complexity of boolean array based solution to O(lgn).
 */
//Time Complexity: O(1) , Space Complexity: O(N)
public class PortManagementProblem {

    public Map<Integer, Set<Integer>> ports;
    public int totalPorts;
    public int lastOccupiedPort=0;
    public static void main(String[] args) {
        PortManagementProblem portManagementProblem = new PortManagementProblem(3);
        System.out.println(portManagementProblem.get());
        System.out.println(portManagementProblem.get());
        System.out.println(portManagementProblem.get());
        System.out.println(portManagementProblem.get());
        portManagementProblem.free(1);
        System.out.println(portManagementProblem.get());
        System.out.println(portManagementProblem.get());
        System.out.println("======================================");
        System.out.println(portManagementProblem.get_2());
        System.out.println(portManagementProblem.get_2());
        System.out.println(portManagementProblem.get_2());
        System.out.println(portManagementProblem.get_2());
        portManagementProblem.free_2(1);
        System.out.println(portManagementProblem.get_2());
        System.out.println(portManagementProblem.get_2());
    }
    public PortManagementProblem(int n){
        this.totalPorts = n;
        this.ports = new TreeMap<>();
        this.ports.put(0,new TreeSet<>());
        this.ports.put(1,new TreeSet<>());
        for(int i=0;i<n;i++){
            ports.get(0).add(i);
        }
    }

    public int get(){
        int port = -1;
        if(ports.get(0).size()>0)
        {
            port = ports.get(0).stream().findFirst().get();
            ports.get(0).remove(port);
            ports.get(1).add(port);
        }
        return port;
    }

    public void free(int port){
        if(ports.get(1).contains(port)){
            ports.get(1).remove(port);
            ports.get(0).add(port);
        }
    }

    public int get_2(){
        int port = -1;
        if(lastOccupiedPort==totalPorts)
            return port;
        else{
            lastOccupiedPort++;
            port = lastOccupiedPort;

        }
        return port;
    }

    public void free_2(int port){
        if(lastOccupiedPort!=0){
            lastOccupiedPort--;
        }
    }

}
