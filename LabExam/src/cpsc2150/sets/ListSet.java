package cpsc2150.sets;
import java.util.*;

/**
 * correspondences size = set.size() and this = set
 * @invariants [set contains no duplicate values]
 */
public class ListSet<T> extends SetAbs<T> implements ISet<T> {
    private List<T> set;

    /**
     * @post: [Set is empty]
     */
    public ListSet()
    {
        set = new ArrayList<>();
    }

    public void add(T val){
        set.add(val);
    }

    public T remove()
    {
        /*Sets are unordered, so removing from a particular position is meaningless
        To make sure you don't assume that remove removes from a particular position
        I am using random numbers. There is no "real" reason to do this, but it will
        help you identify issues in your code that could pop up with a different implementation
        I am forcing you to consider what would happen if you had no idea which
        position would be removed by the remove method
        YOU MAY NOT DELETE THE RANDOMIZATION CODE TO MAKE YOUR DEFAULT METHODS WORK
        */
        Random rand = new Random();
        int r_num = rand.nextInt(this.getSize());
        return set.remove(r_num);

    }

    public boolean contains(T val){
        return set.contains(val);
    }

    public int getSize()
    {
        return set.size();
    }


}
