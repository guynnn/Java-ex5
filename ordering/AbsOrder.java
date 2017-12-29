package ordering;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Represents an order by name.
 */
public class AbsOrder extends Order{

    /**
     * Builds a new AbsOrder.
     */
    public AbsOrder(){

    }

    /**
     * Builds a new AbsOrder.
     * @param reverse true if we want to reverse the order of the files.
     */
    public AbsOrder(boolean reverse){
        this.reverse = reverse;
    }

    @Override
    public File[] order(LinkedList<File> files) {
        File[] outcome = new File[files.size()];
        for (int i = 0; i < outcome.length; i++){
            outcome[i] = files.get(i);
        }
        int reverseOrder;
        if (reverse){
            reverseOrder = -1;
        }
        else {
            reverseOrder = 1;
        }
        Arrays.sort(outcome, ((o1, o2) -> reverseOrder * o1.getAbsolutePath().compareTo(o2.getAbsolutePath()
                                                                                                        )));
        return outcome;
    }
}
