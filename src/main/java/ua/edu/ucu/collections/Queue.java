package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue extends ImmutableLinkedList{
    ImmutableLinkedList queue = new ImmutableLinkedList();
    public Queue(){
    }

    public Object peek(){ //Returns the object at the beginning of the Queue without removing it
        return queue.getFirst();
    }

    public Object dequeue(){ //Removes and returns the object at the beginning of the Queue
        Object obj = queue.getFirst();
        queue.removeFirst();
        return obj;
    }

    public void enqueue(Object e){ //Adds an object to the end of the Queue.
        queue.addLast(e);
    }
}
