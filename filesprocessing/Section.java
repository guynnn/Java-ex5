package filesprocessing;

import filtering.*;
import ordering.*;
import java.io.File;
import java.util.LinkedList;

/**
 * This class represents an entire sub-section of the command file.
 */
public class Section {

    // Used to filter the wanted files
    private Filter filter;
    // Holds the warning of the filter command, if it has one
    private String filterWarning;
    // Used to order the filtered files in the desired order
    private Order order;
    // Holds the warning of the order command, if it has one
    private String orderWarning;
    // The file all the section are working on
    private static File dir;

    /**
     * Builds a new sub-section.
     * @param filter used to filter the wanted files.
     * @param filterWarning the warning of the filter command.
     * @param order used to order the filtered files in the desired order.
     * @param orderWarning the warning of the order command.
     */
    public Section(Filter filter, String filterWarning, Order order, String orderWarning){
        this.filter = filter;
        this.filterWarning = filterWarning;
        this.order = order;
        this.orderWarning = orderWarning;
    }

    // Filter the wanted files
    private LinkedList<File> filter(){
         return filter.filter(dir);
    }

    // Order the filtered files in the desired order
    private File[] order(){
        return order.order(filter());
    }

    /**
     * Printing the names of the filtered files, in the desired order.
     */
    public void print(){
        if (!filterWarning.equals("")){
            // If it has warnings
            System.err.println(filterWarning);
        }
        // If it has warnings
        if (!orderWarning.equals("")){
            System.err.println(orderWarning);
        }
        File[] files = order();
        for (int i = 0; i < files.length; i++){
            System.out.println(files[i].getName());
        }
    }

    /**
     * Setting the directory that the sections work on.
     * @param directory the path for the directory.
     */
    public static void setDir(String directory){
        dir = new File(directory);
    }
}
