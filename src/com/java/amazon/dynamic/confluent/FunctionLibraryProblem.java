package com.java.amazon.dynamic.confluent;

import java.util.*;

/**
 * Suppose you are building a library and have following definition of a function and two methods register and findMatches.
 * Register method registers a function and its argumentTypes in the library and findMatches accepts an input argument list and
 * tries to find all the functions that match this input argument list.
 */

public class FunctionLibraryProblem {
    public static void main(String[] args) {
        Function funcA = new Function("FuncA", Arrays.asList("String", "Integer", "Integer"), false);
        Function funcB = new Function("FuncB", Arrays.asList("String", "Integer"), true);
        Function funcC = new Function("FuncC", Arrays.asList("Integer"), true);
        Function funcD = new Function("FuncD", Arrays.asList("Integer", "Integer"), true);
        Function funcE = new Function("FuncE", Arrays.asList("Integer", "Integer", "Integer"), false);
        Function funcF = new Function("FuncF", Arrays.asList("String"), false);
        Function funcG = new Function("FuncG", Arrays.asList("Integer"), false);

        List<Function> functions = Arrays.asList(funcA,funcB,funcC,funcD,funcE,funcF,funcG);
        FunctionLibrary FL = new FunctionLibrary();
        FL.register(functions);
        System.out.println(Arrays.asList("String")+"==>"+FL.matchingFunctions(Arrays.asList("String")));  // -> [FuncF]
        System.out.println(Arrays.asList("Integer")+"=="+FL.matchingFunctions(Arrays.asList("Integer"))); // -> [FuncC, FuncG]
        System.out.println(Arrays.asList("Integer","Integer","Integer","Integer")+"=="+FL.matchingFunctions(Arrays.asList("Integer","Integer","Integer","Integer"))); // -> [FuncC, FuncD]

        System.out.println(FL.matchingFunctions(Arrays.asList("Integer", "Integer", "Integer")));// -> [FuncC, FuncD, FuncE]
        System.out.println(FL.matchingFunctions(Arrays.asList("String", "Integer", "Integer", "Integer")));// -> [FuncB]
        System.out.println(FL.matchingFunctions(Arrays.asList("String", "Integer", "Integer")));// -> [FuncA, FuncB]

    }
}

class Function{
    String name;
    List<String> argumentTypes;
    boolean isVariadic;

    public Function(String name, List<String> argumentTypes, boolean isVariadic) {
        this.name = name;
        this.argumentTypes = argumentTypes;
        this.isVariadic = isVariadic;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +"\n"+
                ", argumentTypes=" + argumentTypes +"\n"+
                ", isVariadic=" + isVariadic +"\n"+
                '}';
    }
}

class FunctionLibrary{
    Map<String,List<Function>> variadicFunctions = new HashMap<>();
    Map<String,List<Function>> nonVariadicFunctions = new HashMap<>();
    public void register(List<Function> functions){
        for(Function F : functions){
            String key = buildKey(F.argumentTypes,F.argumentTypes.size());
            if(F.isVariadic){
                variadicFunctions.putIfAbsent(key,new ArrayList<>());
                variadicFunctions.get(key).add(F);
                /*key = buildKey(F.argumentTypes,F.argumentTypes.size()-1);
                variadicFunctions.putIfAbsent(key,new ArrayList<>());
                variadicFunctions.get(key).add(F);*/
            }else{
                nonVariadicFunctions.putIfAbsent(key,new ArrayList<>());
                nonVariadicFunctions.get(key).add(F);
                /*key = buildKey(F.argumentTypes,F.argumentTypes.size()-1);
                variadicFunctions.putIfAbsent(key,new ArrayList<>());
                variadicFunctions.get(key).add(F);*/
            }
        }

        //System.out.println(variadicFunctions);
        //System.out.println(nonVariadicFunctions);
    }

    public List<Function> matchingFunctions(List<String> arguments){
        List<Function> result = new ArrayList<>();
        String key=buildKey(arguments,arguments.size());
        if(variadicFunctions.containsKey(key)) {
            result.addAll(variadicFunctions.get(key));
        }
        if(nonVariadicFunctions.containsKey(key)) {
            result.addAll(nonVariadicFunctions.get(key));
        }
        int count=arguments.size();
        for(int i=arguments.size()-2;i>=0;i--){
            if(arguments.get(i).equalsIgnoreCase(arguments.get(i+1))) {
                count--;
            }else{
                break;
            }
            key=buildKey(arguments,count);
            if(variadicFunctions.containsKey(key)){
                result.addAll(variadicFunctions.get(key));
            }
        }
        return result;
    }

    public String buildKey(List<String> argumentTypes, int N){
        StringBuilder SB = new StringBuilder();
        for(int i=0;i<N;i++){
            SB.append(argumentTypes.get(i));
            SB.append("+");
        }
        return SB.toString();
    }
}