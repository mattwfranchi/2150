package cpsc2150.MyQueue;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by mwfranc on 3/3/20.
 */
public class TestArrayQueue
{
    /**
     * @post MakeAQueue() = new IQueue<Integer></Integer>
     * @return IQueue<Integer> initialized with a constructor for an implementation that implements IQueue<Integer>
     *
     */
    private IQueue<Integer>MakeAQueue()
    {
        IQueue<Integer> Q;
        Q = new ArrayQueue();
        return Q;
    }

    // QUEUE ADD TESTS

    @Test
    public void testAdd_Boundary_emptyQueue_size0_add0()
    {
        IQueue<Integer> Q =  MakeAQueue();
        Integer valToAdd = 0;
        Q.add(0);
        assertEquals(1, Q.size());
        assertEquals(Q.endOfQueue(),valToAdd);
    }

    @Test
    public void testAdd_Routine_size2_add5()
    {
        IQueue<Integer> Q = MakeAQueue();
        Integer valToAdd = 5;
        Q.add(0);
        Q.add(1);
        Q.add(5);
        assertEquals(3, Q.size());
        assertEquals(Q.get(3),valToAdd);
    }

    @Test
    public void testAdd_Boundary_QueueAlmostFull_sizeMAXDEPTHminus1_add7()
    {
        IQueue<Integer> Q = MakeAQueue();
        Integer valToAdd = 7;
        for(int n = 0; n < Q.MAX_DEPTH-1; n++)
        {
            Q.add(0);
        }
        Q.add(valToAdd);
        assertEquals(Q.get(Q.MAX_DEPTH),valToAdd);
        assertEquals(Q.size(), Q.MAX_DEPTH);

    }

    // QUEUE PEEK TESTS

    @Test
    public void testPeek_Boundary_size1_QueueSmall()
    {
        IQueue<Integer> Q = MakeAQueue();
        Integer valToAdd = 5;
        Q.add(5);
        Integer peekValue = Q.peek();
        assertEquals(peekValue,valToAdd);
        assertEquals(Q.size(), 1);

    }

    @Test
    public void testPeek_Boundary_sizeMAXDEPTH_QueueFull()
    {
        IQueue<Integer> Q = MakeAQueue();
        Integer initialValToAdd = 2;
        Integer valToAdd = 5;
        Q.add(initialValToAdd);
        for(int n = 0; n < Q.MAX_DEPTH-1; n++)
        {
            Q.add(valToAdd);
        }
        Integer peekValue = Q.peek();
        assertEquals(peekValue,initialValToAdd);
        assertEquals(Q.size(), Q.MAX_DEPTH);
    }

    @Test
    public void testPeek_Routine_size3()
    {
        IQueue<Integer> Q = MakeAQueue();
        Integer initialValToAdd = 3;
        Q.add(initialValToAdd);
        Q.add(4);
        Q.add(5);
        assertEquals(Q.peek(),initialValToAdd);
        assertEquals(Q.size(),3);
    }

    // QUEUE ENDOFQUEUE TESTS

    @Test
    public void testEndOfQueue_Boundary_size1_QueueSmall()
    {
        IQueue<Integer> Q = MakeAQueue();
        Integer valToAdd = 1;
        Q.add(valToAdd);
        Integer endOfQueueVal = Q.endOfQueue();

        assertEquals(endOfQueueVal,valToAdd);
        assertEquals(Q.size(), 1);

    }

    @Test
    public void testEndOfQueue_Boundary_sizeMAXDEPTH_QueueFull()
    {
        IQueue<Integer> Q = MakeAQueue();
        Integer valToAdd = 1;
        for(int n = 0; n < Q.MAX_DEPTH-1; n++)
        {
            Q.add(valToAdd);
        }
        Integer lastValToAdd = 2;

        Q.add(lastValToAdd);

        Integer endOfQueueVal = Q.endOfQueue();

        assertEquals(endOfQueueVal,lastValToAdd);
        assertEquals(Q.size(),Q.MAX_DEPTH);


    }

    @Test
    public void testEndOfQueue_Routine_size3()
    {
        IQueue<Integer> Q = MakeAQueue();
        Q.add(1);
        Q.add(2);
        Integer valToAdd = 3;
        Q.add(valToAdd);
        Integer endOfQueueVal = Q.endOfQueue();

        assertEquals(valToAdd,endOfQueueVal);
        assertEquals(Q.size(),3);


    }

    // QUEUE INSERT TESTS
    // CHECK INSERT FUNCTION CONSTRAINTS
    @Test
    public void testInsert_Boundary_size5_insertAtPos1()
    {
        IQueue<Integer> Q = MakeAQueue();
        for(int n = 0; n < 5; n ++)
        {
            Q.add(1);
        }
        Integer valToInsert = 2;
        Q.insert(valToInsert,1);
        Integer firstVal = Q.peek();

        assertEquals(firstVal,valToInsert);
        assertEquals(Q.size(),6);
    }

    @Test
    public void testInsert_Boundary_size5_insertAtPos5()
    {
        IQueue<Integer> Q = MakeAQueue();
        for(int n = 0; n < 5; n++)
        {
            Q.add(1);
        }
        Integer valToInsert = 2;
        Q.insert(valToInsert,Q.size()+1);
        Integer lastVal = Q.endOfQueue();

        assertEquals(lastVal,valToInsert);
        assertEquals(Q.size(),6);
    }

    @Test
    public void testInsert_Routine_val2_size5_insertAtPos2()
    {

        IQueue<Integer> Q = MakeAQueue();
        for(int n = 0; n < 5; n ++)
        {
            Q.add(1);
        }
        Integer valToInsert = 2;
        Q.insert(valToInsert,2);
        Integer valAtPos2 = Q.get(2);

        assertEquals(valToInsert,valAtPos2);
        assertEquals(Q.size(),6);
    }

    // QUEUE REMOVE TESTS

    @Test
    public void testRemove_Boundary_size5_removePos1()
    {
        IQueue<Integer> Q = MakeAQueue();
        for(int n = 1; n <= 5; n ++)
        {
            Q.add(n);
        }

        Integer valRemoved = Q.remove(1);

        assertEquals((int)valRemoved,1);
        assertEquals(Q.size(),4);

    }

    @Test
    public void testRemove_Boundary_size5_removePos5()
    {
        IQueue<Integer> Q = MakeAQueue();

        for(int n = 1; n <= 5; n ++)
        {
            Q.add(n);
        }
        Integer lastVal = Q.get(Q.size());
        Integer valRemoved = Q.remove(Q.size());

        assertEquals(lastVal,valRemoved);
        assertEquals(Q.size(),4);
    }

    @Test
    public void testRemove_Routine_size5_removePos2()
    {
        IQueue<Integer> Q = MakeAQueue();
        for(int n = 1; n <= 5; n ++)
        {
            Q.add(n);
        }
        Integer secondVal = Q.get(2);
        Integer valRemoved = Q.remove(2);

        assertEquals(secondVal,valRemoved);
        assertEquals(Q.size(),4);

    }

    // QUEUE GET TESTS

    @Test
    public void testGet_Boundary_size5_getPos1()
    {
        IQueue<Integer> Q = MakeAQueue();
        Integer valToAdd = 0;
        Q.add(valToAdd);
        for(int n = 1; n < 5; n++)
        {
            Q.add(n);
        }

        Integer valAtFirstPos = Q.get(1);

        assertEquals(valToAdd,valAtFirstPos);
        assertEquals(Q.size(),5);

    }

    @Test
    public void testGet_Boundary_size5_getPos5()
    {
        IQueue<Integer> Q = MakeAQueue();

        for(int n = 1; n <= 5; n ++)
        {
            Q.add(n);
        }

        Integer valAtLastPos = Q.get(Q.size());

        assertEquals(5,(int) valAtLastPos);
        assertEquals(Q.size(),5);

    }

    @Test
    public void testGet_Routine_size5_getPos2()
    {
        IQueue<Integer> Q = MakeAQueue();

        for(int n = 1; n <= 5; n ++)
        {
            Q.add(n);
        }

        Integer valAtPos2 = Q.get(2);

        assertEquals(2, (int) valAtPos2);
        assertEquals(Q.size(),5);
    }

}
