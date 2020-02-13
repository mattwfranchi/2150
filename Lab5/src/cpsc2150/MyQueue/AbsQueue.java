package cpsc2150.MyQueue;

public abstract class AbsQueue implements IQueue {

    @Override
    public String toString() {
        String output = "\nQueue is: ";

        for (int n = 0; n < size(); n++) {
            Integer el = pop();
            output += Integer.toString(el);

            if(n < size()){
                output += ", ";
            }

            add(el);
        }
        return output;
    }

}
