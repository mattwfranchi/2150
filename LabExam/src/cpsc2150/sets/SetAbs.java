package cpsc2150.sets;

import java.util.*;

public abstract class SetAbs<T> implements ISet<T> {

    public abstract void add(T val);
    public abstract T remove();
    public abstract boolean contains(T val);
    public abstract int getSize();

    /**
     *
     * @return a string representation of the set
     * @pre: none
     * @post: none
     */
    @Override
    public String toString(){
        String s = "";

        List<T> temp = new ArrayList<>();
        //fill intermediate list with everything in this set
        //We have to do this because there is no guarantee where remove will get the value from
        int size = getSize();
        for(int i=0; i<size; i++)
        {
            temp.add(remove());
        }

        //make string and refill this
        for(int i=0; i<size; i++)
        {
            s += temp.get(i) + ", ";
            //add the value back into the set
            add(temp.get(i));
        }


        return s;
    }


}
