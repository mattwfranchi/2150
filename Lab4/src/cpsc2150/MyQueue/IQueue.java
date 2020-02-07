package cpsc2150.MyQueue;

/**
 * A queue containing integers.
 * A queue is a data structure where the first item added to the
 structure is the first item removed from the structure
 * This queue is bounded by MAX_DEPTH
 */
public interface IQueue {
    /**
     * @invariant MAX_DEPTH is positive
     */
    int MAX_DEPTH = 100;


    /**
     * @pre constructor has been run, leaving the queue in a usable state
     * @pre x is an Integer
     * @param x Integer value to be added to the queue
     * @post x is added at the end of the queue
     */
    public void add(Integer x);


    /**
     * @pre constructor has been run
     * @pre queue has >=1 elements
     * @return Integer value at front of the queue [index 0]
     * @post first element of queue is removed, size of queue -=1
     */
    public Integer pop();


    /**
     * @pre constructor has been run
     * @return int representing the size of the queue
     */
    public int size();
}


