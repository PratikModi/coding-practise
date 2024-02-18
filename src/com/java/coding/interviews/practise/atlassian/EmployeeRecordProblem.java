package com.java.coding.interviews.practise.atlassian;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * We are working on a security system for a badged-access room in our company's building.
 * Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:
 * All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit.
 * (All employees are required to leave the room before the log ends.)
 * All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter.
 * (The room is empty when the log begins.)
 * Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.
 */
public class EmployeeRecordProblem{

    public static Map<String, Set<String>> checkEmployeeRecord(String[][] records){
        Map<String,Set<String>> employeeRecords= new HashMap<>();
        if(records!=null && records.length>0){
            Set<String> withoutExist = new HashSet<>();
            Set<String> withoutEntry = new HashSet<>();
            Set<String> names = new HashSet<>();
            for(String[] record : records){
                String name = record[0];
                String action = record[1];
                if(action.equalsIgnoreCase("exit")){
                    if(!names.remove(name)){
                        withoutEntry.add(name);
                    }
                }else{
                    if(!names.add(name)){
                        withoutExist.add(name);
                    }
                }
            }
            withoutExist.addAll(names);
            employeeRecords.put("entry",withoutExist);
            employeeRecords.put("exit",withoutEntry);
        }
        System.out.println(employeeRecords);
        return employeeRecords;
    }

    public static void main(String[] args) {
        String[][] records1 = {
                {"Paul", "enter"}, //no issue
                {"Pauline", "exit"}, // issue, means she entered the room w/o using badge, exit w/o enter
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Martha", "exit"},
                {"Joe", "enter"},
                {"Martha", "enter"},
                {"Steve", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Joe", "enter"},
                {"Curtis", "exit"},
                {"Curtis", "enter"},
                {"Joe", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
                {"Joe", "enter"},
                {"Joe", "enter"},
                {"Martha", "exit"},
                {"Joe", "exit"},
                {"Joe", "exit"},
        };
        checkEmployeeRecord(records1);
    }

}
