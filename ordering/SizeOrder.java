package ordering;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Represents an order by size.
 */
public class SizeOrder extends Order {

    // Casting bytes to KBytes
    private static final double KILO = 1024.0;

    /**
     * Builds a new SizeOrder.
     */
    public SizeOrder(){

    }

    /**
     * Builds a new SizeOrder.
     * @param reverse true if we want to reverse the order of the files.
     */
    public SizeOrder(boolean reverse){
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
        Arrays.sort(outcome, (o1, o2) -> reverseOrder * compare(o1, o2));
        return outcome;
    }

    // returns the size of the file
    private Double getSize(File file){
        double size = file.length() / KILO;
        return size;
    }

    // comparing two files by their size. If its the same, compare them by their absolute path
    private int compare(File file1, File file2){
        if (getSize(file1).compareTo(getSize(file2)) == 0){
            return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
        }
        return getSize(file1).compareTo(getSize(file2));
    }
}
