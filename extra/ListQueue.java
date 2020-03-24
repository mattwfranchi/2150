package cpsc2150.MyQueue;
import java.util.*;

public class ListQueue<T> extends AbsQueue<T> {
    //this time store the queue in a list
    //myQ.get(0) is the front of the queue
    private List<T> myQ;

    /**
     * @invariant MAX_DEPTH > 0
     * Correspondence queue stored as list
     */

    public ListQueue(){
        myQ = new ArrayList<>();
    }

    /**
     * Correspondence cannot add elements past MAX_DEPTH
     * @param x Integer value to be added to the queue
     */
    public void add(T x){
        if(myQ.size() <= MAX_DEPTH){
            myQ.add(x);
        }
    }

    /**
     * Correspondence cannot pop when <1 elements
     *
     */
    public T pop(){
        T x = myQ.get(0);
        myQ.remove(0);
        return x;
    }

    /**
     * Correspondence return size of list
     *
     */
    public int size(){
        return myQ.size();
    }

    //complete the class
}
