package com.java.coding.interviews.practise.atlassian;

import java.util.*;

public class FileCollectionChecker {

    public static List<String> findTopNCollectionWithMaxFiles(List<FileCollection> fileCollections, int n){
        if(fileCollections==null || fileCollections.isEmpty())
            return Collections.emptyList();
        List<String> topCollections = new ArrayList<>();
        Map<String,Integer> collectionMap = new HashMap<>();
        for(FileCollection collection : fileCollections){
            collectionMap.putIfAbsent(collection.getCollectionName(),0);
            collectionMap.put(collection.getCollectionName(),collectionMap.get(collection.getCollectionName())+ collection.getSize());
        }

        List<Map.Entry<String,Integer>> entryList = new ArrayList<>(collectionMap.entrySet());
        Collections.sort(entryList,(a,b)->b.getValue()-a.getValue());

        System.out.println(entryList);
        for(int i=0;i<Math.min(n, entryList.size());i++){
            if(entryList.get(i).getKey()!=null)
                topCollections.add(entryList.get(i).getKey());
        }
        return topCollections;
    }

    public static List<String> findTopNCollectionsWithMultipleNames(List<FileCollection> fileCollections, int n){
        if(fileCollections==null || fileCollections.isEmpty())
            return Collections.emptyList();
        List<String> topCollections = new ArrayList<>();
        Map<String, Integer> collectionMap = new HashMap<>();
        for(var collection : fileCollections){
            if(collection.getCollectionNames()!=null && !collection.getCollectionNames().isEmpty()){
                for(String coll : collection.getCollectionNames()){
                    collectionMap.putIfAbsent(coll,0);
                    collectionMap.put(coll,collectionMap.get(coll)+ collection.getSize());
                }
            }
        }
        List<Map.Entry<String,Integer>> entryList = new ArrayList<>(collectionMap.entrySet());
        Collections.sort(entryList,(a,b)->b.getValue()-a.getValue());
        System.out.println(entryList);
        for(int i=0;i<Math.min(n, entryList.size());i++){
            topCollections.add(entryList.get(i).getKey());
        }

        return topCollections;
    }

    public static void main(String[] args) {
        var collection1 = new FileCollection("", "file1.txt",100);
        var collection2 = new FileCollection("Collection1", "file2.txt",200);
        var collection3 = new FileCollection("Collection1", "file3.txt",200);
        var collection4 = new FileCollection("Collection2", "file4.txt",300);
        var collection5 = new FileCollection("", "file5.txt",10);

        List<FileCollection> fileCollections = new ArrayList<>();
        fileCollections.add(collection1);
        fileCollections.add(collection2);
        fileCollections.add(collection3);
        fileCollections.add(collection4);
        fileCollections.add(collection5);
        System.out.println(findTopNCollectionWithMaxFiles(fileCollections,2));
        List<FileCollection> fileCollections2 = new ArrayList<>();
        var collection6 = new FileCollection(List.of("Collection1","Collection2"),"file1.txt",100);
        var collection7 = new FileCollection(List.of("Collection1","Collection3"),"file1.txt",200);
        var collection8 = new FileCollection(List.of("Collection2","Collection3"),"file1.txt",300);
        var collection9 = new FileCollection(List.of("Collection1","Collection2"),"file1.txt",400);
        var collection10 = new FileCollection(List.of("Collection1","Collection3"),"file1.txt",500);
        fileCollections2.add(collection6);
        fileCollections2.add(collection7);
        fileCollections2.add(collection8);
        fileCollections2.add(collection9);
        fileCollections2.add(collection10);
        System.out.println(findTopNCollectionsWithMultipleNames(fileCollections2,2));
    }

}
