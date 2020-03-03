package cpsc2150.MyQueue;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by mwfranc on 3/3/20.
 */
public class TestArrayQueue
{
    /**
     * @post MakeAQueue() = new IQueue
     * @return IQueue initialized with a constructor for an implementation that implements IQueue
     *
     */
    private IQueue<Integer> MakeAQueue()
    {
        IQueue<Integer> Q;
        Q = new ArrayQueue<>();
        return Q;
    }

    // QUEUE ADD TESTS

    @Test
    public void testAdd_emptyQueue_add0()
    {
        IQueue Q = MakeAQueue();
        int valToAdd = 0;
        Q.add(0);
        assertTrue(Q.size() == 1);
        assertTrue(Q.get(1)== valToAdd);
    }

    @Test
    public void testAdd_routineQueue_add5()
    {
        IQueue<Integer> Q = MakeAQueue();
        int valToAdd = 5;
        Q.add(0);
        Q.add(5);
        assertTrue(Q.size() == 2);
        assertTrue(Q.get(2) == valToAdd);
    }

    @Test public void testAdd_QueueFull_add7()
    {
        IQueue<Integer> Q = MakeAQueue();
        int valToAdd = 7;
        for(int n = 0; n < Q.MAX_DEPTH; n++)
        {
            Q.add(0);
        }
        assertFalse(Q.get(Q.MAX_DEPTH) == valToAdd);
        assertTrue(Q.size() == Q.MAX_DEPTH);

    }

    // QUEUE PEEK TESTS
    @Test

    @Test

    @Test

    // QUEUE ENDOFQUEUE TESTS
    @Test

    @Test

    @Test

    // QUEUE INSERT TESTS
    @Test

    @Test

    @Test

    // QUEUE REMOVE TESTS
    @Test

    @Test

    @Test

    // QUEUE GET TESTS
    @Test

    @Test

    @Test

    //


}
