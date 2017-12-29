package ordering;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents the order command.
 */
public abstract class Order {

    /** true if we want to reverse the order of the files */
    protected boolean reverse;

    /**
     * Ordering the filtered files in the correct order.
     * @param files the filtered files.
     * @return sorted files array.
     */
    public abstract File[] order(LinkedList<File> files);
}
