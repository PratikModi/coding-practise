package com.java.coding.interviews.practise.booking;

import java.util.*;

/**
 * Find the top K closest hotels within a price range. In the input grid 0 means a blocker which you can't pass through,
 * 1 means road which you can use to navigate, any value above 1 is the price of the hotel located at x,y.
 * You will be given this grid, price range and origin coordinates. You should return K hotels within price range and closest to the given origin.
 *
 * Input:
 * [[1,2,0,1],
 * [1,3,0,1],
 * [0,2,5,1]
 * ]
 */

public class TopKNearestHotel {

    public static List<Hotel> findTopKHotels(int[][] grid, int[] priceRange, int k, int[] origin){
        List<Hotel> result = new ArrayList<>();
        PriorityQueue<Hotel> pq = new PriorityQueue<>((a,b)->{
            if(a.price!=b.price)
                return a.price-b.price;
            return a.distance-b.distance;
        });
        bfs(grid,origin[0],origin[1],priceRange,pq);
        while(!pq.isEmpty()&&k-->0){
            result.add(pq.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,2,0,1},{1,3,0,1},{0,2,5,1}};
        var result = findTopKHotels(grid,new int[]{2,3},3,new int[]{0,0});
        System.out.println(result);

    }

    private static void bfs(int[][] grid, int x, int y, int[] priceRange, PriorityQueue<Hotel> pq){
        Queue<int[]> queue = new LinkedList<>();
        if(grid[x][y]>=priceRange[0] && grid[x][y]<=priceRange[1]){
            pq.add(new Hotel(new int[]{x,y},grid[x][y],0));
        }
        grid[x][y]=0;
        queue.add(new int[]{x,y});
        int distance=0;
        int[][] direction = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        while(!queue.isEmpty()){
            int size=queue.size();
            distance++;
            while(size-->0){
                int[] loc = queue.poll();
                for(int[] dir : direction) {
                    int newX = dir[0] + loc[0];
                    int newY = dir[1] + loc[1];
                    if (newX >= 0 && newY >= 0 && newX < grid.length && newY < grid[0].length && grid[newX][newY] != 0) {
                        if ( grid[newX][newY] >= priceRange[0] && grid[newX][newY] <= priceRange[1]) {
                            pq.add(new Hotel(new int[]{newX, newY}, grid[newX][newY], distance));
                        }
                        queue.add(new int[]{newX, newY});
                        grid[newX][newY] = 0;
                    }
                }
            }
        }
    }

}

class Hotel{
    int[] location;
    int price;
    int distance;

    public Hotel(int[] location, int price, int distance) {
        this.location = location;
        this.price = price;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "location=" + Arrays.toString(location) +
                ", price=" + price +
                ", distance=" + distance +
                '}';
    }
}
