package cpsc2150.MyQueue;

public abstract class AbsQueue<T> implements IQueue<T> {

    @Override
    public String toString() {
        String output = "\nQueue is: ";

        for (int n = 0; n < size(); n++) {
            T el = pop();
            output += el;

            if(n < size()){
                output += ", ";
            }

            add(el);
        }
        return output;
    }

}
