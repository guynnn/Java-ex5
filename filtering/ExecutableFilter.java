package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * Represents a filter that return all the files that are executable.
 */
public class ExecutableFilter extends Filter {

    // true for executable files, false for un-executable.
    private boolean condition;

    /**
     * Builds a new ExecutableFilter.
     * @param condition true to filter all the executable files, false for un-executable.
     * @param not true if we want the opposite filter.
     */
    public ExecutableFilter(boolean condition, boolean not){
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
                if ((!condition ^ files[i].canExecute()) ^ not){
                    outcome.add(files[i]);
                }
            }
        }
        return outcome;
    }
}
