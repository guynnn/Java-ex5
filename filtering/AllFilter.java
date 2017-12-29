package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * Represents a filter that return all the files.
 */
public class AllFilter extends Filter {

    /**
     * Builds a new AllFilter.
     * @param not true if we wanted the opposite filter, false otherwise.
     */
    public AllFilter(boolean not){
        this.not = not;
    }

    /**
     * Builds a new AllFilter.
     */
    public AllFilter(){

    }

    @Override
    public LinkedList<File> filter(File file) {
        File[] files = file.listFiles();
        LinkedList<File> outcome = new LinkedList<>();
        if (files == null){
            return outcome;
        }
        for (int i = 0; i < files.length; i++){
            if (files[i].isFile()) {
                if (!not) {
                    outcome.add(files[i]);
                }
            }
        }
        return outcome;
    }
}
