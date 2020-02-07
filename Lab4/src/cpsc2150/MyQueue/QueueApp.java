package cpsc2150.MyQueue;

import java.util.Scanner;

public class QueueApp {
    public static void main(String[] args) {
        IQueue q;
    /*
    You will add in code here to ask the user whether they want an
    array implementation or a list implementation. Then use their
    answer to initialize q appropriately
    */
        // Read in choice using a Scanner instance
        Scanner in = new Scanner(System.in);
        int choice = -1;

        do{
            System.out.println("Array (0) or List (1) Implementation: ");
            choice = in.nextInt();
        } while(choice != 0 && choice != 1);

        // 0 is array implementation, 1 is list implementation
        if (choice == 0) {
            q = new ArrayQueue();
        } else {
           q = new ListQueue();
        }

        int x = 42;
        q.add(x);
        x = 17;
        q.add(x);
        x = 37;
        q.add(x);
        x = 36;
        q.add(x);
        x = 12;
        q.add(x);
        //Add the code to print the queue. After the code is finished,
        //the queue should still contain all its values in order

        /*
         * Pop each element, print it, then push back onto the end
         */
        int s = q.size();
        Integer el = 0;
        for(int n = 0; n < s; n ++){
            el = q.pop();
            System.out.printf("%d\n",el);
            q.add(el);
        }
    }
}
