package filesprocessing;

/**
 * An exception that represents a missing part in the section.
 */
public class MissingSectionException extends Exception{

    private static final long serialVersionUID = 1l;
    // represents what part the section is missing
    private String missing;

    /**
     * Builds a new exception.
     * @param missingSection what type the section is missing (FILTER or ORDER).
     */
    public MissingSectionException(String missingSection){
        missing = missingSection;
    }

    /**
     * @return informative message that represents the problem.
     */
    public String getMessage(){
        return "ERROR: " + missing + " sub-section missing";
    }
}
