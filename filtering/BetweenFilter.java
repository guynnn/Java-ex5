package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * Represents a filter that return all the files that their size is between a certain values.
 */
public class BetweenFilter extends Filter {

    // The lower bound of the size
    private double start;
    // The upper bound of the size
    private double end;
    // Casting bytes to KBytes
    private static final double KILO = 1024.0;

    /**
     * Builds a new BetweenFilter.
     * @param start lower bound of the size allowed.
     * @param end upper bound of the size allowed.
     * @param not true if the opposite filtered is wanted, false otherwise.
     */
    public BetweenFilter(double start, double end, boolean not){
        this.start = start;
        this.end = end;
        this.not = not;
    }

    @Override
    public LinkedList<File> filter(File file) {
        LinkedList<File> outcome = new LinkedList<>();
        File[] files = file.listFiles();
        if (files == null){
            return outcome;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                if ((files[i].length() / KILO >= start && files[i].length() / KILO <= end) ^ not){
                    outcome.add(files[i]);
                }
            }
        }
        return outcome;
    }
}
