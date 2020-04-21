package cpsc2150.sets;


/**
 * correspondences size = depth AND this = set
 * @invariants [set contains no duplicate values] AND depth >= 0
 */

public class ArraySet<T> extends SetAbs<T> implements ISet<T>
{

    private T[] set;
    private int depth;

    /**
     * @post [Set is empty] AND [Set capacity = MAX_SIZE] AND [depth = 0]
     */
    public ArraySet()
    {
        set = (T[]) new Object[MAX_SIZE];
        depth = 0;
    }


    public void add(T val){ set[depth++] = val;}


    // remove last node, decrement by 1
    // --depth instead of depth-- because depth is incremented
    // after each add
    public T remove() { return set[--depth];}



    public boolean contains(T val)
    {
        for(T el: set)
        {
            if(val.equals(el)) { return true; }
        }
        return false;
    }

    public int getSize() { return depth; }

}
