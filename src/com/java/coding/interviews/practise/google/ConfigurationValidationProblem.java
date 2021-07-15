package com.java.coding.interviews.practise.google;

import java.util.*;

/**
 * You have been given RULE String and value. Check if value it satisfying rules.
 * Example:-
 *   HAS_PREFIX("ABC") OR HAS_SUFFIX("XYZ"), ABCdef --> true
 */

public class ConfigurationValidationProblem {

    private static Set<String> config;
    private static Set<String> operator;

    public ConfigurationValidationProblem() {
        config = new HashSet<>();
        operator = new HashSet<>();
        config.add("HAS_PREFIX");
        config.add("HAS_SUFFIX");
        config.add("CONTAINS");
        config.add("EQUALS");
        operator.add("AND");
        operator.add("OR");
        operator.add("NOT");
    }

    public static void main(String[] args) {
        String rule = "HAS_PREFIX(\"ABC\") AND NOT HAS_SUFFIX(\"XYZ\")";
        String value = "ABCdef";
        ConfigurationValidationProblem problem = new ConfigurationValidationProblem();
        System.out.println(problem.configValidation(rule,value));
        System.out.println("===================================");
        rule = "NOT HAS_PREFIX(\"DEF\") OR NOT HAS_SUFFIX(\"XYZ\")";
        System.out.println(problem.configValidation(rule,value));
    }

    public boolean configValidation(String rule, String value){
        Stack<String> OPR = new Stack<>();
        if(rule==null || rule.isEmpty() || value==null || value.isEmpty())
            return false;
        String[] splitConfig = rule.split(" ");
        boolean previous = false;
        boolean isNot = false;
        System.out.println(Arrays.toString(splitConfig));
        for(String R : splitConfig){
            if(operator.contains(R))
                OPR.add(R);
            else{
                if(OPR.isEmpty()){
                    previous = execute(R,value,isNot);
                    System.out.println("Previous==>"+previous);
                }
                while (!OPR.isEmpty()){
                    System.out.println("in while");
                        if(OPR.peek().equals("AND")){
                            isNot=false;
                            previous = previous && execute(R,value,isNot);
                        }else if(OPR.peek().equals("OR")){
                            isNot=false;
                            previous = previous || execute(R,value,isNot);
                        }else{
                            isNot=true;
                            previous = execute(R,value,isNot);
                        }
                        OPR.pop();
                }
            }
        }
        return previous;
    }

    private boolean execute(String rule,String actual, boolean isNOT){
        String expected="";
        String def="";
        def=rule.substring(0,rule.indexOf("("));
        expected=rule.substring(rule.indexOf("\"")+1,rule.length()-2);

        System.out.println(def+"-->"+expected);

        switch (def){
            case "HAS_PREFIX":
                return hasPrefix(expected,actual,isNOT);
            case "HAS_SUFFIX":
                return hasSuffix(expected,actual,isNOT);
            case "CONTAINS":
                return contains(expected,actual,isNOT);
            case "EQUALS":
                return equalsString(expected,actual,isNOT);
        }
        return false;
    }


    private boolean hasPrefix(String expected, String actual, boolean isNot){
        System.out.println(expected+"--"+actual+"--"+isNot);
        return !isNot?actual.startsWith(expected):!actual.startsWith(expected);
    }
    private boolean hasSuffix(String expected, String actual, boolean isNot){
        System.out.println(expected+"--"+actual+"--"+isNot);
        return !isNot?actual.endsWith(expected):!actual.endsWith(expected);
    }
    private boolean contains(String expected, String actual, boolean isNot){
        return !isNot?actual.contains(expected):!actual.contains(expected);
    }
    private boolean equalsString(String expected, String actual, boolean isNot){
        return !isNot?actual.equals(expected):!actual.equals(expected);
    }


}
