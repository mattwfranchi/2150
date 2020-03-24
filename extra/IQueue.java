package cpsc2150.MyQueue;

/**
 * A queue containing integers.
 * A queue is a data structure where the first item added to the
 structure is the first item removed from the structure
 * This queue is bounded by MAX_DEPTH
 */
public interface IQueue<T> {
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
    public void add(T x);


    /**
     * @pre constructor has been run
     * @pre queue has >=1 elements
     * @return Integer value at front of the queue [index 0]
     * @post first element of queue is removed, size of queue -=1
     */
    public T pop();


    /**
     * @pre constructor has been run
     * @return int representing the size of the queue
     */
    public int size();


    /**
     * @pre queue has >= 1 elements
     * @return number at the front of the queue
     * @post queue is unchanged
     */
    default T peek(){
        T el = pop();
        add(el);
        for(int n = 0; n < size()-1; n++){
            add(pop());
        }
        return el;
    }


    /**
     * @pre queue has >=1 elements
     * @return number at the end of the queue
     * @post queue is unchanged
     */
    default T endOfQueue(){
        T el = null;
        for(int n = 1; n <= size(); n++){
            if(n == size()){
                el = pop();
                add(el);
                continue;
            }

            add(pop());
        }
        return el;
    }


    /**
     * @pre the position is within the current range of the queue
     * @param x the number to be inserted
     * @param pos the position to insert the number
     * @post specified number is inserted into the specified position of the queue
     */
    default void insert(T x, int pos){
        int size = size()+1;
        for(int n = 1; n <= size; n++) {
            if (n == pos) {
                add(x);
                continue;
            }
            add(pop());
        }
    }


    /**
     * @pre queue has >=1 elements
     * @param pos position to remove from queue
     * @return the number that was removed
     * @post the queue is one element smaller
     * @post all elements after the removed element are shifted left
     */
    default T remove(int pos){
        T el = null;
        int size = size();
        for(int n = 1; n <= size; n++){
            if(n == pos){
                el = pop();
                continue;
            }
            add(pop());
        }
        return el;
    }


    /**
     * @pre queue has >=1 elements
     * @param pos position to get from queue
     * @return number stored at the specified position in the queue
     * @post queue is unchanged
     */
    default T get(int pos){
        T el = null;
        for(int n = 1; n <= size(); n++){
            if(n == pos){
                el = pop();
                add(el);
                continue;
            }
            add(pop());
        }
        return el;
    }


}


