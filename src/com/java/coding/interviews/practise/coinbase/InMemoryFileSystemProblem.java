package com.java.coding.interviews.practise.coinbase;

import java.util.*;

/**
 * Design a data structure that simulates an in-memory file system.
 *
 * Implement the FileSystem class:
 *
 * FileSystem() Initializes the object of the system.
 * List<String> ls(String path)
 * If path is a file path, returns a list that only contains this file's name.
 * If path is a directory path, returns the list of file and directory names in this directory.
 * The answer should in lexicographic order.
 * void mkdir(String path) Makes a new directory according to the given path.
 * The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
 * void addContentToFile(String filePath, String content)
 * If filePath does not exist, creates that file containing given content.
 * If filePath already exists, appends the given content to original content.
 * String readContentFromFile(String filePath) Returns the content in the file at filePath.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
 * [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
 * Output
 * [null, [], null, null, ["a"], "hello"]
 *
 * Explanation
 * FileSystem fileSystem = new FileSystem();
 * fileSystem.ls("/");                         // return []
 * fileSystem.mkdir("/a/b/c");
 * fileSystem.addContentToFile("/a/b/c/d", "hello");
 * fileSystem.ls("/");                         // return ["a"]
 * fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 */
public class InMemoryFileSystemProblem {
    private class File{
        private String name;
        private String content;

        public File(String name, String content) {
            this.name = name;
            this.content = content;
        }

        public void addContent(String content){
            this.content+=" "+content;
        }
    }

    private class Directory{
        Map<String, Directory> directories = new HashMap<>();
        Map<String,File> files = new HashMap<>();
        private String name;

        public Directory(String name) {
            this.name = name;
        }
    }

    Directory root = new Directory("/");

    public List<String> ls(String path){
        String[] paths = path.split("/");
        List<String> result = new ArrayList<>();
        Directory dir = root;
        for(String P : paths){
            if(!P.isEmpty()){
                if(dir.directories.containsKey(P)){
                    dir=dir.directories.get(P);
                }else{
                    result.add(paths[paths.length-1]);
                    return result;
                }
            }
        }
        return ls(dir);
    }


    private List<String> ls(Directory dir){
        List<String> result = new ArrayList<>();
        if(!dir.files.isEmpty()){
            for(String F : dir.files.keySet()) {
                result.add(F);
            }
        }
        if(!dir.directories.isEmpty()){
            for(String D : dir.directories.keySet()){
                result.add(D);
            }
        }
        Collections.sort(result);
        return result;
    }

    public void mkdir(String path){
        String[] paths = path.split("/");
        Directory dir = root;
        for(String P : paths){
            if(!P.isEmpty()) {
                if (!dir.directories.containsKey(P)) {
                    Directory newDir = new Directory(P);
                    dir.directories.put(P,newDir);
                }
                dir = dir.directories.get(P);
            }
        }
    }

    public void addContentToFile(String path, String content){
        String[] paths = path.split("/");
        Directory dir = root;
        int N = paths.length;
        for(int i=1;i<N-1;i++){
            dir=dir.directories.get(paths[i]);
            if(dir==null)
                break;
        }
        if(!dir.files.containsKey(paths[N-1])){
            File newFile = new File(paths[N-1],content);
            dir.files.put(paths[N-1],newFile);
        }else{
            dir.files.get(paths[N-1]).addContent(content);
        }
    }

   public String readContentFromFile(String path){
        String[] paths = path.split("/");
        Directory dir = root;
        int N = paths.length;;
        for(int i=1;i<N-1;i++){
            dir=dir.directories.get(paths[i]);
            if(dir==null)
                break;

        }
        if(dir!=null && dir.files.containsKey(paths[N-1])){
            return dir.files.get(paths[N-1]).content;
        }
        return null;
   }

    public static void main(String[] args) {
        InMemoryFileSystemProblem problem = new InMemoryFileSystemProblem();
        problem.mkdir("/foo/bar");
        problem.addContentToFile("/foo/bar/file.txt","In Memory File System");
        String content = problem.readContentFromFile("/foo/bar/file.txt");
        System.out.println(content);
        System.out.println(problem.ls("/foo"));
    }


}
