package com.java.coding.interviews.practise.google;

import java.util.*;

public class ArithmeticEquationProblem {
    public static void main(String[] args){
        //Input = {x1:[x2, x3], x2: [x4, x3], x3: [5], x4: [1]}
        //Output = {x1: 11, x2: 6, x3: 5, x4: 1}
        Map<String, List<String>> input = new HashMap<>();
        input.put("x1", Arrays.asList("x2","x3"));
        input.put("x2", Arrays.asList("x4","x3"));
        input.put("x3", Arrays.asList("5"));
        input.put("x4", Arrays.asList("1"));

        Map<String, List<String>> rslt = getResultFromVariables(input);
        System.out.println(rslt.toString());
    }
    public static Map<String, List<String>> getResultFromVariables(Map<String, List<String>> input) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, List<String>> children = new HashMap<>();
        //Map<String, List<String>> rslt = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        Deque<String> order = new ArrayDeque();
        System.out.println("INPUT==>"+input);
        // build a graph
        for (HashMap.Entry<String, List<String>> entry : input.entrySet()) {

            indegree.putIfAbsent(entry.getKey(), 0);

            for (String str : entry.getValue()) {
                if(input.containsKey(str)){
                    graph.putIfAbsent(str, new ArrayList());
                    graph.get(str).add(entry.getKey());

                    indegree.put(entry.getKey(), indegree.get(entry.getKey())+1);
                    children.putIfAbsent(str, new ArrayList());
                    children.get(str).add(entry.getKey());
                }

            }
        }

        System.out.println("InDegree==>"+indegree);
        System.out.println("Graph==>"+graph);
        System.out.println("Children==>"+children);

        // topological sort
        Deque<String> queue = new ArrayDeque<>();

        for (HashMap.Entry<String, List<String>> entry : graph.entrySet()) {
            if(indegree.get(entry.getKey()) == 0){
                queue.offer(entry.getKey());
            }
        }
        //System.out.println("Queue==>"+queue);

        while(!queue.isEmpty()){
            String curr = queue.poll();
            order.add(curr);
            for(String next : graph.getOrDefault(curr, new ArrayList<>())){
                indegree.put(next, indegree.get(next)-1);
                if(indegree.get(next) == 0){
                    queue.offer(next);
                }
            }
            //System.out.println("Queue==>"+queue);
        }
        //System.out.println("Order==>"+order);
        // look up & replace
        while (!order.isEmpty()) {
            String currVar = order.poll();
            System.out.println(currVar);
            List<String> _children = children.getOrDefault(currVar, new ArrayList());
            System.out.println("_Children==>"+_children);
            for (String child : _children) {
                List<String> vars = input.getOrDefault(child, new ArrayList());
                System.out.println("Vars==>"+vars);
                Boolean isStr = false;
                for(int i = 0 ; i < vars.size() ; i++){
                    if(vars.get(i).equals(currVar)){
                        vars.set(i, input.get(currVar).get(0));
                    }
                    isStr = isStr || input.containsKey(vars.get(i));
                }
                System.out.println(isStr);
                System.out.println("Vars==>"+vars);
                if (!isStr) {
                    System.out.println("HERE");
                    int sum = 0;
                    for (String var : vars) {
                        sum += Integer.parseInt(var);
                    }
                    input.put(child, new ArrayList());
                    input.get(child).add("" + sum);
                }
                System.out.println(input);
            }
        }

        return input;
    }
}
