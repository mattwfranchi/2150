// Matt Franchi
package cpsc2150.listDec;

import java.util.List;
import java.util.Random;

public interface IShuffleList<T> extends List<T> {
    /**
     * @param swaps number of times to run shuffle
     * @pre swaps > 0
     * @post list is shuffled swaps times
     */
    default void shuffle(int swaps) {
        Random rand = new Random();
        for (int n = 0; n < swaps; n++) {
            int rand_pos1 = rand.nextInt(size());
            int rand_pos2 = rand.nextInt(size());

            swap(rand_pos1, rand_pos2);
        }
    }

    /**
     * @param i index of first position
     * @param j index of second position
     * @pre i >= 0 AND j >= 0 AND i < size() AND j < size()
     * @post contents of i = contents of #j AND contents of j = contents of #i
     */
    default void swap(int i, int j) {
        T temp = get(i);
        set(i, get(j));
        set(j, temp);
    }
}
