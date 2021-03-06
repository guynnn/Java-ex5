package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * Represents a filter that return all the files that their size is smaller than a certain value.
 */
public class SmallerThanFilter extends Filter {

    // Casting bytes to KBytes
    private static final double KILO = 1024.0;
    // The lower bound of the size
    private double start;

    /**
     * Builds a new SmallerThanFilter.
     * @param start lower bound of the size allowed.
     * @param not true if the opposite filtered is wanted, false otherwise.
     */
    public SmallerThanFilter(double start, boolean not){
        this.start = start;
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
                if ((files[i].length() / KILO < start) ^ not){
                    outcome.add(files[i]);
                }
            }
        }
        return outcome;
    }
}
