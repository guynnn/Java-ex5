package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * Represents a filter that return all the files that are writable.
 */
public class WritableFilter extends Filter {

    // true for writable files, false for un-writable.
    private boolean condition;

    /**
     * Builds a new WritableFilter.
     * @param condition true to filter all the writable files, false for un-writable.
     * @param not true if we want the opposite filter.
     */
    public WritableFilter(boolean condition, boolean not){
        this.condition = condition;
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
                if ((!condition ^ files[i].canWrite()) ^ not){
                    outcome.add(files[i]);
                }
            }
        }
        return outcome;
    }
}
