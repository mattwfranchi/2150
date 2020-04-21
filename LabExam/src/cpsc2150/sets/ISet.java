package cpsc2150.sets;

import java.util.*;

/**
 * Sets are an unordered collection that does not allow duplicate values.
 * Defines: size: Z - how many items are in the set
 * Initialization Ensures: the set is empty and size = 0
 * Constraints: [this contains no duplicate values]
 *
 */
// generified
public interface ISet<T> {
	int MAX_SIZE = 100;

    /**
     *
     * @param val the value to add to the set
     * @pre: !contains(val) and size < MAX_SIZE
     * @post: [val is added to the set]
     */
    void add(T val);

    /**
     *
     * @return the value removed from the set
	 * @pre size > 0
     * @post: remove = [a value from the set] and this = #this - remove
     */
    T remove();

    /**
     *
     * @param val a value to check
     * @return whether or not val is in the set
     * @pre: none needed
     * @post: contains iff [val is in this]
     */
    boolean contains(T val);

    /**
     *
     * @return the size of the set
     * @pre: none needed
     * @post: getSize = size
     */
    int getSize();

    /**
     *
     * @param unionWith the set to union with
     * @pre this.getSize() + unionWith.getSize() <= MAX_SIZE
     * @post this = #this U unionWith
     */
    default void union(ISet<T> unionWith)
    {
        // iterate through unionWith until it is empty
       while(unionWith.getSize() > 0)
       {
           // if el not contained within this, add it to this
           T el = unionWith.remove();
           if(!this.contains(el)) { this.add(el); }
       }
    }

    /**
     *
     * @param intersectWith the set to intersect with
     * @post this = [the intersection of #this and  intersectWith]
     */
    default void intersect(ISet<T> intersectWith)
    {
        // temporary list to hold the intersecting values
        List<T> intersections = new ArrayList<>();

        // iterate through ISet<T> this until it is empty
        while(this.getSize() > 0)
        {
            // remove elements randomly
            T el = this.remove();
            // if the element intersects with the intersectWith,
            // then add it to the intersections list
            if(intersectWith.contains(el))
            {
                intersections.add(el);
            }
        }

        // iterate through all intersecting points and add them to this
        for(T el : intersections)
        {
            this.add(el);
        }

    }


}
