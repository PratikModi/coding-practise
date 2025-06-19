package com.java.coding.interviews.practise.uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure that simulates an in-memory file system.
 *
 * Should the file system support both absolute (/a/b) and relative (a/b) paths?
 * Should we handle special path components like . (current dir) and .. (parent dir)?
 * What should happen if we cd into a non-existent directory? Throw error or create it?
 * Should we support files as well, or only directories?
 */

/**
 * This is with  support of absolute and relative paths.
 */

public class InMemoryFileSystem {
    private class Directory{
        String name;
        Directory parent;
        Map<String, Directory> subDirectories = new java.util.HashMap<>();
        Directory(String name, Directory parent) {
            this.name = name;
            this.parent = parent;
        }
    }
    private Directory root;
    private Directory currrent;
    public InMemoryFileSystem() {
        this.root = new Directory("/", null);
        this.currrent = root;
    }

    public void mkdir(String path){
        if(path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }
        Directory temp = path.startsWith("/") ? root : currrent;
        String[] dirs = path.split("/");
        for(String dir: dirs){
            if(dir.equals(".") || dir.isEmpty()) continue;
            else if(dir.equals("..")){
                if(temp.parent!=null){
                    temp = temp.parent;
                }
            }else{
                temp.subDirectories.putIfAbsent(dir,new Directory(dir, temp));
                temp = temp.subDirectories.get(dir);
            }
        }
    }

    public void cd(String path){
        if(path==null || path.isEmpty()){
            throw new IllegalArgumentException("Path cannot be null or empty");
        }
        String[] dirs = path.split("/");
        for(String dir : dirs){
            if(dir.equals(".") || dir.isEmpty()) continue;
            else if(dir.equals("..")){
                if(currrent.parent!=null){
                    currrent=currrent.parent;
                }
            }else{
                if(currrent.subDirectories.containsKey(dir)){
                    currrent = currrent.subDirectories.get(dir);
                }else{
                    throw new IllegalArgumentException("Directory does not exist: " + dir);
                }
            }
        }
    }

    public void pwd(){
        List<String> paths = new ArrayList<>();
        Directory temp = currrent;
        while(temp!=null && temp.parent!=null){
            paths.add(temp.name);
            temp = temp.parent;
        }
        Collections.reverse(paths);
        System.out.println("/" + String.join("/", paths));
    }

    // Demo
    public static void main(String[] args) {
        InMemoryFileSystem fs = new InMemoryFileSystem();
        fs.mkdir("home");
        fs.cd("home");
        fs.mkdir("user");
        fs.cd("user");
        fs.pwd(); // Output: /home/user
        fs.cd("..");
        fs.pwd(); // Output: /home
        fs.cd(".");
        fs.pwd(); // Output: /home
        fs.cd("..");
        fs.pwd(); // Output: /
    }

}
