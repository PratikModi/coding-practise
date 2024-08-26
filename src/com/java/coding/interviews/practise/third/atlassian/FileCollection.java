package com.java.coding.interviews.practise.third.atlassian;

import java.util.List;

public class FileCollection {

    private String collectionName;
    private String fileName;
    private Double size;
    private List<String> collectionNames;

    public FileCollection(String collectionName, String fileName, Double size) {
        this.collectionName = collectionName;
        this.fileName = fileName;
        this.size = size;
    }

    public FileCollection(List<String> collectionNames,String fileName, Double size) {
        this.fileName = fileName;
        this.size = size;
        this.collectionNames = collectionNames;
    }

    public FileCollection(String fileName, Double size) {
        this.fileName = fileName;
        this.size = size;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getFileName() {
        return fileName;
    }

    public Double getSize() {
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
                ", collectionNames=" + collectionNames +
                '}';
    }
}
