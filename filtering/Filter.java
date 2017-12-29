package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * Represents the filter command.
 */
public abstract class Filter {

    // True if the opposite filter operation is wanted, false otherwise
    protected boolean not;

    /**
     * @param files the path for the file we need to filter.
     * @return List of all the filtered files.
     */
    public abstract LinkedList<File> filter(File files);
}
