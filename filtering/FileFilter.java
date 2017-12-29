package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * Represents a filter that return all the files that their name equal to a certain value.
 */
public class FileFilter extends Filter {

    // the value to check
    private String value;

    /**
     * Builds a new FileFilter.
     * @param value the value to check.
     * @param not true if we want the opposite filter.
     */
    public FileFilter(String value, boolean not){
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
                if (value.equals(files[i].getName()) ^ not){
                    outcome.add(files[i]);
                }
            }
        }
        return outcome;
    }
}
