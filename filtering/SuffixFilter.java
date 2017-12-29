package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * Represents a filter that return all the files that their name end with a certain value.
 */
public class SuffixFilter extends Filter {

    // The value to check if the file name ends with
    private String value;

    /**
     * Builds a new SuffixFilter.
     * @param value The value to check if the file name ends with.
     * @param not true if we want the opposite filter.
     */
    public SuffixFilter(String value, boolean not){
        this.value = value;
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
                if (files[i].getName().endsWith(value) ^ not){
                    outcome.add(files[i]);
                }
            }
        }
        return outcome;
    }
}
