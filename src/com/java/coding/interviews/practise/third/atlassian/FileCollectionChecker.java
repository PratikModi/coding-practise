package com.java.coding.interviews.practise.third.atlassian;
/**
 *
 * Questions:-
 * ========
 * 1. Can we safely assume if empty string and no collection name as same as null?
 * 2. What could be the size of the file? Asking for data type. We can take double by keeping in mind it can grow in the future.
 * 3. Do we need to consider the files which are not belong to any collection? I mean what if total size is maximum for those files which are without collection name?
 * 4. If required I will keep asking you questions while coding the solution as we may come across more scenarios.
 *
 *  Talking Points:-
 *  ===========
 *  1. We will discuss multiple approach to solve the problem and including brute force in fact we will start with brute force only
 *  2. We will pick the best approach in terms of time + space complexity, we will discuss the reason for it.
 *  3. We will go over the working code and run the code to see if its working.
 *
 * Solution:
 * ======
 * 1. So basically we have to take care of two operations.
 * A. Grouping of data based on collection name and summing up the file size
 * B. Sort the grouped data and find the top N collection
 *
 * Approach-1:-
 * =========
 * 1. We can use Map data structure to store the file size against each collection name as grouped data. And then sort the map based on value in decreasing order and get the top N collections.
 *     Now to solve this problem we actually we don't need the file name at all hence we can skip it.
 *     Time Complexity:- O(n) (Grouping) + O(nlogn) (Sorting) == O(nlogn)
 *     Space Complexity:- O(n)
 *
 * 2. Although we don't need the file name to solve this problem still keeping future scope it's good to represent the File Collection as object and define size, collection name, file name as attribute.
 *    This can adopt the future changes in file collection attributes. Same Map data structure can be used to store grouped data.
 *    Time Complexity:- O(n) (Grouping) + O(nlogn) (Sorting) == O(nlogn)
 *    Space Complexity:- O(n)
 *
 * Why HashMap?
 * ==========
 * 1. HashMap gives you O(1) time look uptime it makes performance efficient.
 *
 */



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileCollectionChecker {

    public static List<String> findTopNCollection(List<FileCollection> fileCollections, int n){
        List<String> topCollections = new ArrayList<>();
        if(fileCollections==null || fileCollections.isEmpty()){
            return topCollections;
        }
        //Grouping
        Map<String,Double> fileCollectionMap = new HashMap<>();
        for(FileCollection collection : fileCollections){
            if(collection.getCollectionName()!=null && !collection.getCollectionName().isBlank()){
                fileCollectionMap.putIfAbsent(collection.getCollectionName(),0d);
                fileCollectionMap.put(collection.getCollectionName(), fileCollectionMap.get(collection.getCollectionName())+ collection.getSize());
            }else{
                fileCollectionMap.putIfAbsent(null,0d);
                fileCollectionMap.put(null, fileCollectionMap.get(null)+collection.getSize());
            }
        }
        //Debug
        System.out.println(fileCollectionMap);
        //Sorting
        List<Map.Entry<String,Double>> sortedEntryList = fileCollectionMap.entrySet().stream().sorted((a,b)->b.getValue().compareTo(a.getValue())).collect(Collectors.toUnmodifiableList());
        for(int i=0;i<Math.min(n,sortedEntryList.size());i++){
            topCollections.add(sortedEntryList.get(i).getKey());
        }
        return topCollections;
    }

    public static List<String> findTopNCollectionWithMultipleCollections(List<FileCollection> fileCollections, int n){
        List<String> topCollections = new ArrayList<>();
        if(fileCollections==null && fileCollections.isEmpty()){
            return topCollections;
        }
        //Grouping
        Map<String,Double> fileCollectionMap = new HashMap<>();
        for(FileCollection collection : fileCollections){
            if(collection.getCollectionNames()!=null && !collection.getCollectionNames().isEmpty()){
                for(String coll : collection.getCollectionNames()){
                    if(coll!=null && !coll.isBlank()){
                        fileCollectionMap.putIfAbsent(coll,0d);
                        fileCollectionMap.put(coll,fileCollectionMap.get(coll)+collection.getSize());
                    }else{
                        fileCollectionMap.putIfAbsent(null,0d);
                        fileCollectionMap.put(null,fileCollectionMap.get(null)+collection.getSize());
                    }
                }
            }else{
                fileCollectionMap.putIfAbsent(null,0d);
                fileCollectionMap.put(null,fileCollectionMap.get(null)+collection.getSize());
            }
        }
        //Debug
        System.out.println(fileCollectionMap);
        //Sorting
        List<Map.Entry<String,Double>> sortedEntryList = fileCollectionMap.entrySet().stream().sorted((a,b)->b.getValue().compareTo(a.getValue())).collect(Collectors.toUnmodifiableList());
        for(int i=0;i<Math.min(n,sortedEntryList.size());i++){
            topCollections.add(sortedEntryList.get(i).getKey());
        }
        return topCollections;
    }

    public static Double totalSize(List<FileCollection> fileCollections){
        if(fileCollections==null || fileCollections.isEmpty())
            return 0d;
        return fileCollections.stream().mapToDouble(e->e.getSize()).sum();
    }

    public static void main(String[] args) {
        var collection1 = new FileCollection("", "file1.txt",100d);
        var collection2 = new FileCollection("Collection1", "file2.txt",200d);
        var collection3 = new FileCollection("Collection1", "file3.txt",200d);
        var collection4 = new FileCollection("Collection2", "file4.txt",300d);
        var collection5 = new FileCollection("", "file5.txt",10d);

        List<FileCollection> fileCollections = new ArrayList<>();
        fileCollections.add(collection1);
        fileCollections.add(collection2);
        fileCollections.add(collection3);
        fileCollections.add(collection4);
        fileCollections.add(collection5);
        System.out.println(findTopNCollection(fileCollections,2));
        System.out.println(totalSize(fileCollections));

        List<FileCollection> fileCollections2 = new ArrayList<>();
        var collection6 = new FileCollection(List.of("Collection1","Collection2"),"file1.txt",100d);
        var collection7 = new FileCollection(List.of("Collection1","Collection3"),"file1.txt",200d);
        var collection8 = new FileCollection(List.of("Collection2","Collection3"),"file1.txt",300d);
        var collection9 = new FileCollection(List.of("Collection1","Collection2"),"file1.txt",400d);
        var collection10 = new FileCollection(List.of("Collection1","Collection3"),"file1.txt",500d);
        fileCollections2.add(collection6);
        fileCollections2.add(collection7);
        fileCollections2.add(collection8);
        fileCollections2.add(collection9);
        fileCollections2.add(collection10);
        System.out.println(findTopNCollectionWithMultipleCollections(fileCollections2,2));
        System.out.println(totalSize(fileCollections2));
    }



}
