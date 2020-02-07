package cpsc2150.MyQueue;

public class ArrayQueue implements IQueue {
    // where the data is stored. myQ[0] is the front of the queue
    private Integer[] myQ;
    //tracks how many items in the queue
    // also used to find the end of the queue
    /**
     * @invariant depth >= 0 && depth <= MAX_LENGTH
     * Correspondence queue stored as array
     */
    private int depth;
    //complete the class

    /**
     * Correspondence queue has space for MAX_DEPTH elements
     */
    public ArrayQueue(){
        myQ = new Integer[MAX_DEPTH];
        depth = 0;
    }


    /**
     * Correspondence cannot add elements past MAX_DEPTH
     * @param x Integer value to be added to the queue
     */
    public void add(Integer x){
        if(depth < MAX_DEPTH){
            myQ[depth++] = x;
        }
    }


    /**
     * Correspondence cannot pop when <1 elements
     *
     */
    public Integer pop(){
        Integer x = myQ[0];
        for(int n = 1; n < depth; n++){
            myQ[n-1] = myQ[n];
        }
        depth--;
        return x;
    }


    /**
     * Correspondence returns the depth of the array
     *
     */
    public int size(){
        return depth;
    }
}
