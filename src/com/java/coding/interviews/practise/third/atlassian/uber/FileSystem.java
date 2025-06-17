package com.java.coding.interviews.practise.third.atlassian.uber;

import com.java.coding.interviews.practise.third.atlassian.FileCollection;

import java.util.*;

public class FileSystem {
    private class Directory{
        String name;
        Directory parent;
        Map<String, Directory> subDirectories = new HashMap<>();

        Directory(String name, Directory parent){
            this.name = name;
            this.parent = parent;
        }
    }
    private Directory root;
    private Directory current;
    public FileSystem() {
        this.root = new Directory("/", null);
        this.current = root;
    }

    public void mkdir(String path){
        if(path == null || path.isEmpty())
            throw new IllegalArgumentException("Path cannot be null or empty");
        Directory temp  = path.startsWith("/")?root : current;
        String dirs[] = path.split("/");
        for(String dir : dirs){
            if(dir.equals(".") || dir.isEmpty()) continue;
            else if(dir.equals("..")){
                if(temp.parent!=null){
                    temp = temp.parent;
                }
            }else{
                temp.subDirectories.putIfAbsent(dir,new Directory(dir,temp));
                temp = temp.subDirectories.get(dir);
            }
        }
    }

    public void cd(String path){
        if(path==null || path.isEmpty()){
            throw new IllegalArgumentException("Path cannot be null or empty");
        }
        String dirs[] = path.split("/");
        for(String dir : dirs){
            if(dir.equals(".") || dir.isEmpty()) continue;
            else if(dir.equals("..")){
                if(current.parent!=null){
                    current = current.parent;
                }
            }else{
                if(current.subDirectories.containsKey(dir)){
                    current = current.subDirectories.get(dir);
                }else{
                    throw new IllegalArgumentException("Directory does not exist: " + dir);
                }
            }
        }
    }

    public void pwd(){
        Directory temp = current;
        List<String> path = new ArrayList<>();
        while(temp!=null && temp.parent!=null){
            path.add(temp.name);
            temp = temp.parent;
        }
        Collections.reverse(path);
        System.out.println("/"+String.join("/",path));
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
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
