package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack extends ImmutableLinkedList{
    ImmutableLinkedList stack = new ImmutableLinkedList();
    public Stack(){

    }
    public Object peek() { //Returns the object from the top of the Stack without removing it
        return stack.getLast();
    }

    public Object pop(){ //Removes and returns the object from the top of the Stack
        Object obj= stack.getLast();
        stack.removeLast();
        return obj;
    }

    public void pop(Object e){ //Adds an object to the the top of the Stack
        stack.addLast(e);

    }

}
