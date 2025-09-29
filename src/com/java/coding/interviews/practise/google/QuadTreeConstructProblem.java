package com.java.coding.interviews.practise.google;

/**
 * 427. Construct Quad Tree
 * Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.
 *
 * Return the root of the Quad-Tree representing grid.
 *
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 *
 * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
 * isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
 * class Node {
 *     public boolean val;
 *     public boolean isLeaf;
 *     public Node topLeft;
 *     public Node topRight;
 *     public Node bottomLeft;
 *     public Node bottomRight;
 * }
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 *
 * If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
 * If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
 * Recurse for each of the children with the proper sub-grid.
 *
 * If you want to know more about the Quad-Tree, you can refer to the wiki.
 *
 * Quad-Tree format:
 *
 * You don't need to read this section for solving the problem. This is only if you want to understand the output format here. The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.
 *
 * It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
 *
 * If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
 * Explanation: The explanation of this example is shown below:
 * Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.
 *
 * Example 2:
 *
 *
 *
 * Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
 * Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
 * The topLeft, bottomLeft and bottomRight each has the same value.
 * The topRight have different values so we divide it into 4 sub-grids where each has the same value.
 */

/**
 * 🔹 Complexity
 * 	•	Time: O(n²) → each cell is visited once when checking uniformity.
 * 	•	Space: O(n²) worst case recursion tree (if no large uniform blocks).
 */
public class QuadTreeConstructProblem {

    public static void main(String[] args) {

    }

    public static QNode construct(int[][] grid){
        return build(grid,0,0, grid.length);
    }

    private static QNode build(int[][] grid, int row,int col, int size){
        if(isUniform(grid,row,col,size)){
            return new QNode(grid[row][col]==1,true);
        }

        int newSize = size/2;
        QNode topLeft = build(grid,row,col,newSize);
        QNode topRight = build(grid,row,col+newSize,newSize);
        QNode bottomLeft = build(grid,row+newSize,col,newSize);
        QNode bottomRight = build(grid,row+newSize,col+newSize,newSize);

        return new QNode(true,false,topLeft,topRight,bottomLeft,bottomRight);

    }

    private static boolean isUniform(int[][] grid, int row, int col, int size){
        int value = grid[row][col];
        for(int i=row;i<row+size;i++){
            for(int j=col;j<col+size;j++){
                if(grid[i][j]!=value) return false;
            }
        }
        return true;
    }



}

class QNode {
    public boolean val;
    public boolean isLeaf;
    public QNode topLeft;
    public QNode topRight;
    public QNode bottomLeft;
    public QNode bottomRight;

    public QNode() {}

    public QNode(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
    }

    public QNode(boolean val, boolean isLeaf, QNode topLeft, QNode topRight, QNode bottomLeft, QNode bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
