package filtering;

/**
 * An exception that represents a problem in the filter command.
 */
public class FilterException extends Exception {

    private static final long serialVersionUID = 1l;
    // The line we had problem in the commands file
    private int line;

    /**
     * Builds a new FilterException.
     * @param line The line we had problem in the commands file.
     */
    public FilterException(int line){
        this.line = line;
    }

    /**
     * @return a String represents the problem.
     */
    public String getMessage(){
        return "Warning in line " + line;
    }
}
