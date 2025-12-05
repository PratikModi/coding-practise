package com.java.coding.interviews.practise.salesforce;

import java.util.*;

public class SetMergerProblem {


    public static List<Set<DataObject>> merge(List<Set<DataObject>> inputSets){
        DSU dsu = new DSU();
        // STEP 1: Register all DataObjects in DSU
        for(Set<DataObject> set : inputSets){
            for(var obj : set){
                dsu.add(obj.displayName);
            }
        }

        // STEP 2: Union all elements within the same set
        for(Set<DataObject> set : inputSets){
            List<DataObject> list = new ArrayList<>(set);
            for(int i=1;i<list.size();i++){
                dsu.union(list.get(0).displayName, list.get(i).displayName);
            }
        }

        // STEP 3: Build merged results
        Map<String,Set<DataObject>> result = new HashMap<>();
        for(Set<DataObject> set : inputSets){
            for(DataObject obj : set){
                String root = dsu.find(obj.displayName);
                result.computeIfAbsent(root,x->new HashSet<>()).add(obj);
            }
        }
        return new ArrayList<>(result.values());
    }

    public static void main(String[] args) {
        DataObject A = new DataObject("1","A");
        DataObject B = new DataObject("2","B");
        DataObject C = new DataObject("3","C");
        DataObject D = new DataObject("4","D");
        DataObject E = new DataObject("5","E");
        DataObject F = new DataObject("6","F");
        DataObject G = new DataObject("7","G");

        Set<DataObject> set1 = Set.of(A,B);
        Set<DataObject> set2 = Set.of(B,C);
        Set<DataObject> set3 = Set.of(D);
        Set<DataObject> set4 = Set.of(E);
        Set<DataObject> set5 = Set.of(F);
        Set<DataObject> set6 = Set.of(C,G);
        List<Set<DataObject>> input = List.of(set1,set2,set3,set4,set5,set6);
        System.out.println(merge(input));
    }
}

class DSU{
    Map<String,String> parent = new HashMap<>();
    Map<String,Integer> rank = new HashMap<>();

    public void add(String x){
        parent.putIfAbsent(x,x);
        rank.putIfAbsent(x,1);
    }

    public String find(String x){
        if(!parent.get(x).equals(x)){
            parent.put(x,find(parent.get(x)));
        }
        return parent.get(x);
    }

    public void union(String x, String y){
        String px = find(x);
        String py = find(y);
        if(rank.get(px)<rank.get(py)){
            parent.put(px,py);
        }else if(rank.get(px)>rank.get(py)){
            parent.put(py,px);
        }else{
            parent.put(py,px);
            rank.put(px,rank.get(px)+1);
        }
    }

}
class DataObject{
    String uniqueId;
    String displayName;

    DataObject(String uniqueId, String displayName){
        this.uniqueId=uniqueId;
        this.displayName=displayName;
    }

    @Override
    public String toString() {
        return this.displayName+"("+this.uniqueId+")";
    }
}
