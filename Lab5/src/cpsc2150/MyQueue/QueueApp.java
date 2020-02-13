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
        int choice;

        System.out.println("Array (1) or List (2) Implementation: ");
        choice = in.nextInt();

        while(choice != 1 && choice != 2){
            System.out.println("Invalid choice. Try again. ");
            System.out.println("Array (1) or List (2) Implementation: ");
            choice = in.nextInt();
        }

        // 0 is array implementation, 1 is list implementation
        if (choice == 1) {
            q = new ArrayQueue();
        } else {
            q = new ListQueue();
        }

        do {
            System.out.print("\nSelect an option: \n" +
                    "1. Add to the Queue\n" +
                    "2. Get next number from the Queue\n" +
                    "3. Peek at the front of the Queue\n" +
                    "4. Peek at the end of the Queue\n" +
                    "5. Insert in the Queue\n" +
                    "6. Get a position in the Queue\n" +
                    "7. Remove from a position in the Queue\n" +
                    "8. Quit\n");

            choice = in.nextInt();
            while (choice < 0 || choice > 8) {
                System.out.print("Choice is not valid. Try again: ");
                choice = in.nextInt();
            }

            int idx; int el;
            if(q.size() == 0 && choice !=1 && choice != 5 && choice != 8){
                choice = -1; // Triggers default switch case: empty
            }
            switch (choice) {

                case 1:
                    if(q.size() >= q.MAX_DEPTH){
                        System.out.println("Queue is full! Returning to main menu...");
                        break;
                    }
                    System.out.print("What number to add to the Queue? ");
                    q.add(in.nextInt());
                    break;

                case 2:
                    System.out.printf("Next number is %d", q.pop());
                    break;

                case 3:
                    System.out.printf("Peek: %d", q.peek());
                    break;

                case 4:
                    System.out.printf("Peek at end: %d", q.endOfQueue());
                    break;

                case 5:
                    int num;
                    if(q.size() >= q.MAX_DEPTH){
                        System.out.println("Queue is full! Returning to main menu...");
                        break;
                    }
                    System.out.print("What number to add to the queue? ");
                    num = in.nextInt();
                    System.out.print("What position to insert in? ");
                    idx = in.nextInt();
                    while (idx < 1 || idx > q.size()+1) {
                        System.out.print("Invalid position. Try again: ");
                        idx = in.nextInt();
                    }

                    q.insert(num, idx);
                    break;

                case 6:

                    System.out.print("What position to get from the Queue? ");
                    idx = in.nextInt();
                    while (idx < 1 || idx > q.size()) {
                        System.out.print("Invalid position. Try again: ");
                        idx = in.nextInt();
                    }
                    el = q.get(idx);
                    System.out.printf("%d is at position %d in the Queue.",el,idx);
                    break;

                case 7:

                    System.out.print("What position to remove from the Queue? ");
                    idx = in.nextInt();
                    while (idx < 1 || idx > q.size()) {
                        System.out.print("Invalid position. Try again: ");
                        idx = in.nextInt();
                    }
                    el = q.remove(idx);
                    System.out.printf("%d was at position %d in the Queue.",el,idx);
                    break;

                case 8:
                    System.out.println("Program exiting...");
                    break;

                default:
                    System.out.print("Queue is empty!");
                    break;



            }
            System.out.print(q);
        } while(choice != 8);
    }
}
