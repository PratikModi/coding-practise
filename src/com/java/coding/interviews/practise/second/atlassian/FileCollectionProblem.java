package com.java.coding.interviews.practise.second.atlassian;

import java.util.*;
import java.util.stream.Collectors;

public class FileCollectionProblem {

    public static List<String> topNCollectionsWithMaxFileSize(List<FileCollection> fileCollections, int n){
        List<String> topCollections = new ArrayList<>();
        if(fileCollections==null || fileCollections.isEmpty())
            return topCollections;
        Map<String,Integer> collectionMap = new HashMap<>();
        for(var collection : fileCollections){
            collectionMap.putIfAbsent(collection.getCollectionId(),0);
            collectionMap.put(collection.getCollectionId(), collectionMap.get(collection.getCollectionId())+collection.getSize());
        }
        System.out.println(collectionMap);
//        var sortedMap = collectionMap.entrySet().stream()
//                .sorted((a,b)->b.getValue()-a.getValue())
//                .collect(Collectors.toMap(Map.Entry::getKey,
//                                          Map.Entry::getValue,
//                        (e1,e2)->e1, LinkedHashMap::new));
        List<Map.Entry<String,Integer>> sortedEntryList = new ArrayList<>(collectionMap.entrySet());
        //If we have to filter NULL collection Ids
        sortedEntryList = sortedEntryList.stream().filter(e->e.getKey()!=null)
                .sorted((a,b)->b.getValue()-a.getValue())
                .collect(Collectors.toList());
        //sortedEntryList.sort((a, b) -> b.getValue() - a.getValue());
        for(int i=0;i<Math.min(n,sortedEntryList.size());i++){
            topCollections.add(sortedEntryList.get(i).getKey());
        }
        return topCollections;
    }

    public static List<String> topNCollectionsWithMaxFileSize2(List<FileCollection> fileCollections, int n){
        List<String> topCollections = new ArrayList<>();
        if(fileCollections==null || fileCollections.isEmpty())
            return topCollections;
        Map<String,Integer> collectionMap = new HashMap<>();
        for(var collection : fileCollections){
            if(!collection.getCollectionNames().isEmpty()) {
                for (var name : collection.getCollectionNames()) {
                        collectionMap.putIfAbsent(name,0);
                        collectionMap.put(name,collectionMap.get(name)+collection.getSize());
                    }
                }
            }
        System.out.println(collectionMap);
        List<Map.Entry<String,Integer>> sortedEntryList = new ArrayList<>(collectionMap.entrySet());
        sortedEntryList = sortedEntryList.stream().filter(e->e.getKey()!=null).sorted((a,b)->b.getValue()-a.getValue()).collect(Collectors.toList());
        for(int i=0;i<Math.min(n,sortedEntryList.size());i++){
            topCollections.add(sortedEntryList.get(i).getKey());
        }
        return topCollections;
    }
    /**
     * file1.txt (size: 100)
     * file2.txt (size: 200) in collection "collection1"
     * file3.txt (size: 200) in collection "collection1"
     * file4.txt (size: 300) in collection "collection2"
     * file5.txt (size: 10)
     */
    public static void main(String[] args) {
        FileCollection collection1 = new FileCollection("file1.txt",100);
        FileCollection collection2 = new FileCollection("collection1","file2.txt",200);
        FileCollection collection3 = new FileCollection("collection1","file3.txt",300);
        FileCollection collection4 = new FileCollection("collection2","file4.txt",400);
        FileCollection collection5 = new FileCollection("file5.txt",1000);
        List<FileCollection> fileCollections = List.of(collection1,collection2,collection3,collection4,collection5);
        System.out.println(topNCollectionsWithMaxFileSize(fileCollections,2));

        FileCollection collection6 = new FileCollection("file1.txt",100);
        FileCollection collection7 = new FileCollection(List.of("collection1","collection2"),"file2.txt",200);
        FileCollection collection8 = new FileCollection(List.of("collection1","collection3"),"file3.txt",300);
        FileCollection collection9 = new FileCollection(List.of("collection2","collection4"),"file4.txt",400);
        FileCollection collection10 = new FileCollection("file5.txt",1000);
        fileCollections = List.of(collection6,collection7,collection8,collection9,collection10);
        System.out.println(topNCollectionsWithMaxFileSize2(fileCollections,2));
    }

}

class FileCollection{
    private String collectionId;
    private List<String> collectionNames;
    private String fileName;
    private Integer size;

    public FileCollection(String fileName, Integer size) {
        this.fileName = fileName;
        this.size = size;
        this.collectionNames = new ArrayList<>();
    }

    public FileCollection(String collectionId, String fileName, Integer size) {
        this.collectionId = collectionId;
        this.fileName = fileName;
        this.size = size;
    }

    public FileCollection(List<String> collectionNames, String fileName, Integer size) {
        this.collectionNames = collectionNames;
        this.fileName = fileName;
        this.size = size;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getSize() {
        return size;
    }

    public List<String> getCollectionNames() {
        return collectionNames;
    }

    @Override
    public String toString() {
        return "FileCollection{" +
                "collectionId='" + collectionId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", size=" + size +
                '}';
    }
}
