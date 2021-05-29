package com.java.coding.interviews.practise;

import java.util.*;

/**
 * Created by Pratik1 on 07-06-2020.
 */
public class StringBrokenProblem {
    public static void main(String[] args) {
        System.out.println(splitStringPerPerson("abcd",3));
    }


    public static String splitStringPerPerson(String S, int P){
        String result="";
        Map<Integer,String> stringPerPerson = new HashMap<>();
        int person=1;
        Stack<String> stack = new Stack<>();
        stringPerPerson.put(person++,S);
        stack.push(S);
        while(!stack.isEmpty()){
            String temp = stack.pop();
            if(temp.length()%2==0) {
                List<String> list = breakString(temp);
                stringPerPerson.put(person++, list.get(0));
                stringPerPerson.put(person++, list.get(1));
                stack.push(list.get(1));
                stack.push(list.get(0));
            }
        }
        if(stringPerPerson.containsKey(P)){
            result=stringPerPerson.get(P);
        }
        System.out.println(stringPerPerson);
        return result;
    }


    private static List<String> breakString(String input){
        List<String> list = new ArrayList<>();
        list.add(input.substring(0,input.length()/2));
        list.add(input.substring(input.length()/2));
        //return Arrays.asList(input.substring(0,input.length()/2),input.substring(input.length()/2));
        return list;
    }
}
