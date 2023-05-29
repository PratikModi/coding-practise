package com.java.coding.interviews.practise.uber;

/**
 * Given list of folders, print the path of a given folder from root. If there is no path to the root folder then return an empty string.
 * Root level folders will have 0 as id. The structure of Folder is like this:
 * class Folder {
 *    int id;
 *    List<int> subfolders;
 *    String name;
 * }
 *
 * Ex:
 * list = [Folder(0, [7, 3], “abc”), Folder(0, [], “xyz”), Folder(3, [], “pqr”), Folder(8, [], “def”), Folder(7, [9], “ijk), Folder(9, [], “lmn”)]
 *
 * printPath(9) = “abc” -> “ijk” -> “lmn” printPath(8) = "def"
 * Clarification: There can be multiple root level folders. All other folders have unique ids.
 * Note: printPath method may be called multiple times. Design your solution taking that into account
 */

import java.util.*;

public class FileBuildPathProblem {

    private Map<Integer,Integer> parentMap;
    private Map<Integer,String> folderMap;

    public FileBuildPathProblem(List<FileFolder> folders) {
        parentMap = new HashMap<>();
        folderMap = new HashMap<>();
        populateMap(folders);
    }

    private void populateMap(List<FileFolder> folders){
        for(FileFolder folder : folders){
            for(Integer id : folder.subFolders){
                parentMap.putIfAbsent(id,folder.id);
            }
            folderMap.put(folder.id, folder.name);
        }
    }

    public String buildPath(int folderId){
        StringBuilder path = new StringBuilder("/");
        Stack<String> stackPath = new Stack<>();
        stackPath.push(folderMap.get(folderId));
        while(folderId==0 || parentMap.containsKey(folderId)){
            if(parentMap.containsKey(folderId)) {
                folderId = parentMap.get(folderId);
                stackPath.push(folderMap.get(folderId));
            }else{
                break;
            }
        }
        while(!stackPath.isEmpty()){
            path.append(stackPath.pop()).append("/");
        }
        return path.toString();
    }


    public static void main(String[] args) {
        List<FileFolder> folders = new ArrayList<>();
        folders.add(new FileFolder(0, Arrays.asList(7, 3), "abc"));
        //folders.add(new FileFolder(0, new ArrayList<>(), "xyz"));
        folders.add(new FileFolder(3, new ArrayList<>(), "pqr"));
        folders.add(new FileFolder(8, new ArrayList<>(), "def"));
        folders.add(new FileFolder(7, Arrays.asList(9), "ijk"));
        folders.add(new FileFolder(9, new ArrayList<>(), "lmn"));

        FileBuildPathProblem fp = new FileBuildPathProblem(folders);
        System.out.println(fp.folderMap);
        System.out.println(fp.parentMap);
        System.out.println(fp.buildPath(9));
        System.out.println(fp.buildPath(8));
    }




}

class FileFolder{
    int id;
    List<Integer> subFolders;
    String name;

    public FileFolder(int id, List<Integer> subFolders, String name) {
        this.id = id;
        this.subFolders = subFolders;
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileFolder{" +
                "id=" + id +
                ", subFolders=" + subFolders +
                ", name='" + name + '\'' +
                '}';
    }
}
