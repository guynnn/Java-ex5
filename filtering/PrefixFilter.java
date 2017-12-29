package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * Represents a filter that return all the files that their name start with a certain value.
 */
public class PrefixFilter extends Filter {

    // The value to check if the file name starts with
    private String value;

    /**
     * Builds a new PrefixFilter.
     * @param value The value to check if the file name starts with.
     * @param not true if we want the opposite filter.
     */
    public PrefixFilter(String value, boolean not){
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
                if (files[i].getName().startsWith(value) ^ not){
                    outcome.add(files[i]);
                }
            }
        }
        return outcome;
    }
}
