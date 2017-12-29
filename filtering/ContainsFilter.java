package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * Represents a filter that return all the files that their name contained in a certain value.
 */
public class ContainsFilter extends Filter {

    // The value to check if it contains the file name
    private String value;

    /**
     * Builds a new ContainsFilter.
     * @param value the value to check if it contains the file name.
     * @param not true if we want the opposite filter.
     */
    public ContainsFilter(String value, boolean not){
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
                if (value.contains(files[i].getName()) ^ not){
                    outcome.add(files[i]);
                }
            }
        }
        return outcome;
    }
}
