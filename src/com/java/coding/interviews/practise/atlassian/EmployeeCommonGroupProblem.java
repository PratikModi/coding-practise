package com.java.coding.interviews.practise.atlassian;

import java.util.*;

/**
 *
 * Imagine you are the team that maintains the Atlassian employee directory.
 *
 * At Atlassian, there are multiple groups, and each can have one or more groups.
 * Every employee is part of a group.
 *
 * You are tasked with designing a system that could find the closest common parent group,
 * given a target set of employees in the organization.
 * Time Complexity: O(N) -- build Group -- N is the number of Groups
 *                            O(D) -- getAncesstor -- D is average length
 *                            O(k*D) -- LCA -- K Number of employee in the query
 *
 *   Questions: -
 *   1. What defines a “group”?
 *   2. Do employees ever belong to more than one group?
 *   3. Is the structure strictly a tree? One Parent Group and multiple Child Group
 *   4. What should be returned if:
 *       An employee is not found?
 *       The employee list is empty?
 *       The only employee in the list is valid — do we return their own group?
 *   5. Do we need to handle multiple queries efficiently?
 *   6. “Are group relationships strictly hierarchical (tree), or can they be shared (DAG)?”
 *
 *   Approach:1
 *   Approach Summary
 * 	1.	Map each employee to their group
 * 	2.	Build a parent map: Map<Group, Group> during traversal
 * 	3.	For each employee:
 * 	•	Walk up the group hierarchy using the parent map
 * 	•	Store all ancestors in a set
 * 	4.	Do set intersection to find the common ancestor
 * 	5.	Return the deepest (closest) such group
 *
 * 	Efficient for repeated queries
 * 	•	Supports fast upward traversal
 * 	•	Easier to extend (e.g., multi-tenant orgs, dynamic trees)
 *
 * 	Approach: 2
 * 	1. Build a tree structure for group hierarchy
 * 	2.	For each employee, map them to their group
 * 	3.	Find the lowest common ancestor of their groups in the tree
 * 	Why Not this approach:
 * 	1. Repeated Tree Traversal for Every Query
 * 	   Inefficient when there are many queries or a large tree.
 * 	2. No Reuse of Previous Computations
 * 	   You recompute the same paths over and over again for frequently queried employees/groups.
 * 	3. Not Designed for Scalability
 * 	   If you anticipate millions of employees and real-time analytics, this method will become a bottleneck.
 *
 *
 *
 */

public class EmployeeCommonGroupProblem {
    static class Group{
        String name;
        List<Group> subGroups;
        List<String> employees;

        Group(String name){
            this.name = name;
            this.subGroups = new ArrayList<>();
            this.employees = new ArrayList<>();
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
    Map<String,Group> employeeToGroupMap = new HashMap<>();
    Map<Group,Group> parentMap = new HashMap<>();
    public void buildGroups(Group root){
           dfs(root,null);
    }

    private void dfs(Group current, Group parent){
        if(current == null) return;
        parentMap.put(current, parent);
        for(String employee : current.employees){
            employeeToGroupMap.put(employee, current);
        }
        for(Group subGroup : current.subGroups){
            dfs(subGroup, current);
        }
    }

    private List<Group> getAncestors(Group group){
        List<Group> ancestors = new ArrayList<>();
        while(group != null){
            ancestors.add(group);
            group = parentMap.get(group);
        }
        return ancestors;
    }

    public Group getClosestGroup(List<String> employees){
        if(employees == null || employees.isEmpty()) return null;
        List<List<Group>> paths = new ArrayList<>();
        for(String employee : employees){
            Group empGroup = employeeToGroupMap.get(employee);
            if(empGroup == null) return null;
            paths.add(getAncestors(empGroup));
        }
        List<Group> firstPath = paths.get(0);
        Set<Group> commonSet = new HashSet<>(firstPath);
        for(int i=1;i<paths.size();i++){
            commonSet.retainAll(paths.get(i));
        }
        for(Group g : firstPath){
            if(commonSet.contains(g)) return g;
        }
        return null;
    }

    public static void main(String[] args) {
        EmployeeCommonGroupProblem employeeCommonGroupProblem = new EmployeeCommonGroupProblem();
        Group engineering = new Group("Engineering");
        Group platform = new Group("Platform");
        Group product = new Group("Product");
        Group devOps = new Group("DevOps");
        Group backend = new Group("Backend");
        Group design = new Group("Design");

        engineering.subGroups.addAll(List.of(platform, product));
        platform.subGroups.addAll(List.of(devOps, backend));
        product.subGroups.add(design);

        backend.employees.addAll(List.of("Alice", "Bob"));
        devOps.employees.add("Eve");
        design.employees.add("Charlie");

        employeeCommonGroupProblem.buildGroups(engineering);

        // Queries
        System.out.println("Alice & Bob → " + employeeCommonGroupProblem.getClosestGroup(List.of("Alice", "Bob")));        // Backend
        System.out.println("Alice & Charlie → " + employeeCommonGroupProblem.getClosestGroup(List.of("Alice", "Charlie"))); // Engineering
        System.out.println("Eve & Charlie → " + employeeCommonGroupProblem.getClosestGroup(List.of("Eve", "Charlie")));     // Engineering
        System.out.println("Alice, Eve, Charlie → " + employeeCommonGroupProblem.getClosestGroup(List.of("Alice", "Eve", "Charlie"))); // Engineering

    }

}
