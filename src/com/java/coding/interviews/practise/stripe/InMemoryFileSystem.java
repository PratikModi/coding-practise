package com.java.coding.interviews.practise.stripe;

import java.util.*;

/**
 * Design an in-memory file system to simulate the following functions:
 *
 * ls: Given a path in string format. If it is a file path, return a list that only contains this file's name.
 * If it is a directory path, return the list of file and directory names in this directory.
 * Your output (file and directory names together) should in lexicographic order.
 *
 * mkdir: Given a directory path that does not exist, you should make a new directory according to the path.
 * If the middle directories in the path don't exist either, you should create them as well.
 * This function has void return type.
 *
 * addContentToFile: Given a file path and file content in string format.
 * If the file doesn't exist, you need to create that file containing given content.
 * If the file already exists, you need to append given content to original content.
 * This function has void return type.
 *
 * readContentFromFile: Given a file path, return its content in string format.
 */
public class InMemoryFileSystem {
    private class File{
        private String name;
        private String content;

        public File(String name, String content) {
            this.name = name;
            this.content = content;
        }

        @Override
        public String toString() {
            return "File{" +
                    "name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        public void addContent(String c){
            this.content+=c;
        }
    }
    //Directory can contain sub directories and files
    private class Directory{
        private String path;
        private Map<String, File> files = new HashMap<>();
        private Map<String,Directory> directories = new HashMap<>();

        public Directory(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return "Directory{" +
                    "path='" + path + '\'' +
                    ", files=" + files +
                    ", directories=" + directories +
                    '}';
        }
    }

    Directory root = new Directory("/");

    public InMemoryFileSystem() {
    }

    public List<String> ls(String path) {
        List<String> result = new ArrayList<>();
        String[] Paths = path.split("/");
        Directory dir = root;
        for(String P : Paths){
            if(dir.directories.containsKey(P)){
                dir=dir.directories.get(P);
            }else{
                result.add(Paths[Paths.length-1]);
                return result;
            }
        }
        return ls(dir);
    }

    private List<String> ls(Directory path){
        System.out.println("LS DIR-->"+path);
        List<String> result = new ArrayList<>();
        if(!path.files.isEmpty()){
            for(String F : path.files.keySet())
            result.add(F);
        }
        if(!path.directories.isEmpty()){
            System.out.println("IN IF");
            System.out.println(path.directories.keySet());
            for(String D : path.directories.keySet()){
                result.add(D);
            }
        }
        Collections.sort(result);
        return result;
    }

    public void mkdir(String path) {
        Directory dir = root;
        String[] paths = path.split("/");
        System.out.println(Arrays.toString(paths));
        for(String P : paths){
            if(!P.isEmpty()) {
                System.out.println("Directory:-->" + P);
                if (!dir.directories.containsKey(P)) {
                    Directory newDir = new Directory(P);
                    dir.directories.put(P, newDir);
                }
                dir = dir.directories.get(P);
            }
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] paths = filePath.split("/");
        int N = paths.length;
        Directory dir = root;
        for(int i=1;i<N-1;i++){
            dir = dir.directories.get(paths[i]);
        }
        if(dir.files.containsKey(paths[N-1])){
            File file = dir.files.get(paths[N-1]);
            file.addContent(content);
        }else{
            File file = new File(paths[N-1],content);
            dir.files.put(paths[N-1],file);
        }
    }

    public String readContentFromFile(String filePath) {
        String[] paths = filePath.split("/");
        int N = paths.length;
        Directory dir = root;
        for(int i=1;i<N-1;i++){
            dir = dir.directories.get(paths[i]);
        }
        if(dir.files.containsKey(paths[N-1])){
            File file = dir.files.get(paths[N-1]);
            return file.content;
        }
        return null;
    }
}

class Test{
    public static void main(String[] args) {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();
        System.out.println(fileSystem.ls("/"));
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "Hello");
        System.out.println(fileSystem.ls("/"));
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d"));
    }
}
