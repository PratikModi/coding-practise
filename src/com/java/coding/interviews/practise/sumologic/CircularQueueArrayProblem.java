package com.java.coding.interviews.practise.sumologic;

import java.util.ArrayList;
import java.util.List;

/**
 * Circular Queue | Set 1 (Introduction and Array Implementation)
 * Difficulty Level : Easy
 * Last Updated : 28 Jun, 2021
 *
 * Prerequisite – Queues
 * Circular Queue is a linear data structure in which the operations are performed based on FIFO (First In First Out)
 * principle and the last position is connected back to the first position to make a circle. It is also called ‘Ring Buffer’.
 *
 *
 * circularqueues
 *
 *
 * In a normal Queue, we can insert elements until queue becomes full. But once queue becomes full, we can not insert the next element even
 * if there is a space in front of queue.
 *
 *
 * Operations-on-Circular queue
 *
 * Operations on Circular Queue:
 *
 *
 *
 *
 *
 * Front: Get the front item from queue.
 * Rear: Get the last item from queue.
 * enQueue(value) This function is used to insert an element into the circular queue. In a circular queue, the new element is always inserted at Rear position.
 * Check whether queue is Full – Check ((rear == SIZE-1 && front == 0) || (rear == front-1)).
 * If it is full then display Queue is full. If queue is not full then, check if (rear == SIZE – 1 && front != 0) if it is true then set rear=0 and insert element.
 * deQueue() This function is used to delete an element from the circular queue. In a circular queue, the element is always deleted from front position.
 * Check whether queue is Empty means check (front==-1).
 * If it is empty then display Queue is empty. If queue is not empty then step 3
 * Check if (front==rear) if it is true then set front=rear= -1 else check if (front==size-1), if it is true then set front=0 and return the element.
 */
public class CircularQueueArrayProblem {
    public static void main(String[] args) {
        CircularQueueArrayProblem q = new CircularQueueArrayProblem(5);
        q.enQueue(14);
        q.enQueue(22);
        q.enQueue(13);
        q.enQueue(-6);

        q.displayQueue();
        int x = q.deQueue();

        // Checking for empty queue.
        if(x != -1)
        {
            System.out.print("Deleted value = ");
            System.out.println(x);
        }

        x = q.deQueue();

        // Checking for empty queue.
        if(x != -1)
        {
            System.out.print("Deleted value = ");
            System.out.println(x);
        }

        q.displayQueue();

        q.enQueue(9);
        q.enQueue(20);
        q.enQueue(5);

        q.displayQueue();

        q.enQueue(20);
    }

    private List<Integer> Queue = new ArrayList<>();
    int size,front,rear;

    public CircularQueueArrayProblem(int size) {
        this.size = size;
        this.front=-1;
        this.rear=-1;
    }

    public void enQueue(int data){
        if((front==0 && rear==size-1) || (rear==(front-1)%(size-1))){
            System.out.println("Queue is full");
            return;
        }else if(front==-1){
            front=0;
            rear=0;
            Queue.add(rear,data);
        }else if(rear==size-1 && front!=-1){
            rear=0;
            Queue.set(rear,data);
        }else{
            rear++;
            if(front<=rear){
                Queue.add(rear,data);
            }else{
                Queue.set(rear,data);
            }
        }
    }

    public int deQueue(){
        int temp=0;
        if(front==-1){
            System.out.println("Queue is empty");
            return -1;
        }
        temp=Queue.get(front);
        if(front==rear){
            front=-1;
            rear=-1;
        }else if(front==size-1){
            front=0;
        }else {
            front++;
        }
        return temp;
    }

    public void displayQueue(){
        if(front==-1){
            System.out.println("Queue is empty.");
        }
        System.out.println("Element in circular queue are::");
        if(rear>=front){
            // Loop to print elements from front to rear.
            for(int i=front;i<=rear;i++){
                System.out.print(Queue.get(i));
                System.out.print(" ");
            }
        }else{
            // Loop for printing elements from
            // front to max size or last index
            for(int i=front;i<size;i++){
                System.out.print(Queue.get(i));
                System.out.print(" ");
            }
            // Loop for printing elements from
            // 0th index till rear position
            for(int i=0;i<=rear;i++){
                System.out.print(Queue.get(i));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
