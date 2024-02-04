package com.java.coding.interviews.practise.atlassian;


import java.util.List;

public class FileCollection {

    private String collectionName;
    private String fileName;
    private Integer size;

    private List<String> collectionNames;

    public FileCollection(String fileName, Integer size) {
        this.collectionName=null;
        this.fileName = fileName;
        this.size = size;
    }


    public FileCollection(String collectionName, String fileName, Integer size) {
        this.collectionName = collectionName;
        this.fileName = fileName;
        this.size = size;
    }

    public FileCollection(List<String> collectionNames,String fileName, Integer size ) {
        this.fileName = fileName;
        this.size = size;
        this.collectionNames = collectionNames;
    }

    public String getCollectionName() {
        return collectionName;
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
                "collectionName='" + collectionName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", size=" + size +
                '}';
    }
}
