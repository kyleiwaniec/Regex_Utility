package edu.mccc.cos210.ds;
public class Stack<E> implements StackInt<E> {

    // Data Fields
    /** Storage for stack. */
    E[] theData;
    /** Index to top of stack. */
    int topOfStack = -1; // Initially empty stack.
    private static final int INITIAL_CAPACITY = 10;

    /** The current size */
    private int size = 0;
    /** The current capacity */
    private int capacity = 0;
    /**
     * Construct an empty stack with the default
     * initial capacity.
     */
    public Stack() {
        theData = (E[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Insert a new item on top of the stack.
     * @post The new item is the top item on the stack.
     *       All other items are one position lower.
     * @param obj The item to be inserted
     * @return The item that was inserted
     */
    @Override
    public E push(E obj) {
        if (topOfStack == theData.length-1) {
            reallocate();
        }
        topOfStack++;
        theData[topOfStack] = obj;
        size++;
        return obj;
    }

    /**
     * Remove and return the top item on the stack.
     * @pre The stack is not empty.
     * @post The top item on the stack has been
     *       removed and the stack is one item smaller.
     * @return The top item on the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() throws java.util.EmptyStackException{
        if (empty()) {
            throw new java.util.EmptyStackException();
        }
        size--;
        return theData[topOfStack--];
    }
    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     * @return The top item on the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() throws java.util.EmptyStackException{
        if (empty()) {
            throw new java.util.EmptyStackException();
        }
        return theData[topOfStack];
    }
    /** Tests if this stack is empty.
    * @return true if emmpty, false if not
    */
    @Override
    public boolean empty(){
        if(theData.length == 0){
            return true;
        }else{
            return false;
        }
    }
    public int getSize(){
        return this.size;
    }
    private void reallocate(){
        capacity = 2*theData.length;
        E[] newData = (E[]) new Object[capacity];
        System.arraycopy(theData, 0, newData, 0, topOfStack+1);
        theData = newData;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            sb.append(", "+theData[i]);
        }
        if(sb.length()>=2){sb.delete(0,2);};
        if(size == 0){sb.append("* empty stack *");};
        return sb.toString();

    }

}