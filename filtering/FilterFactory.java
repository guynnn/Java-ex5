package filtering;

/**
 * Factory for the filter objects. Builds the desired filter object.
 */
public class FilterFactory {

    // Represents the GreaterThanFilter object
    private static final String GREATER_THAN = "greater_than";
    // Represents the BetweenFilter object
    private static final String BETWEEN = "between";
    // Represents the SmallerThanFilter object
    private static final String SMALLER_THAN = "smaller_than";
    // Represents the FileFilter object
    private static final String FILE = "file";
    // Represents the ContainsFilter object
    private static final String CONTAINS = "contains";
    // Represents the PrefixFilter object
    private static final String PREFIX = "prefix";
    // Represents the SuffixFilter object
    private static final String SUFFIX = "suffix";
    // Represents the WritableFilter object
    private static final String WRITABLE = "writable";
    // Represents the ExecutableFilter object
    private static final String EXECUTABLE = "executable";
    // Represents the HiddenFilter object
    private static final String HIDDEN = "hidden";
    // Represents the AllFilter object
    private static final String ALL = "all";
    // the commands are separated with this String
    private static final String SPLITTER = "#";
    // Indicates if the opposite filter is needed
    private static final String NOT = "NOT";
    // The value for Writable, Executable and hidden. must be YES or No
    private static final String YES_VALUE = "YES";
    private static final String NO_VALUE = "NO";

    /**
     * Builds the correct filter object.
     * @param str the command.
     * @param line the line number in the commands file.
     * @return the correct filter object.
     * @throws FilterException invalid command.
     */
    public static Filter build(String str, int line) throws FilterException{
        String[] data = str.split(SPLITTER);
        String type = data[0];
        boolean not = data[data.length - 1].equals(NOT);
        if (type.equals(GREATER_THAN)) {
            double start = Double.parseDouble(data[1]);
            if ((start < 0) || !(data.length == 2 || (data.length == 3 && not))){
                throw new FilterException(line);
            }
            return (not) ? new GreaterThanFilter(start, true) : new GreaterThanFilter(start, false);
        }
        else if (type.equals(BETWEEN)) {
            double start = Double.parseDouble(data[1]);
            double end = Double.parseDouble(data[2]);
            if ((start < 0 || end < 0 || start > end) || !(data.length == 3 ||(data.length == 4 && not))){
                throw new FilterException(line);
            }
            return (not) ? new BetweenFilter(start, end, true) : new BetweenFilter(start, end, false);
        }
        else if (type.equals(SMALLER_THAN)) {
            double start = Double.parseDouble(data[1]);
            if ((start < 0) || !(data.length == 2 || (data.length == 3 && not))){
                throw new FilterException(line);
            }
            return (not) ? new SmallerThanFilter(start, true) : new SmallerThanFilter(start, false);
        }
        else if (type.equals(FILE)) {
            String value = data[1];
            if (data.length == 3 && !not) {
                throw new FilterException(line);
            }
            return (not) ? new FileFilter(value, true) : new FileFilter(value, false);
        }
        else if (type.equals(CONTAINS)) {
            String value = data[1];
            if (data.length == 3 && !not) {
                throw new FilterException(line);
            }
            return (not) ? new ContainsFilter(value, true) : new ContainsFilter(value, false);
        }
        else if (type.equals(PREFIX)) {
            String value = data[1];
            if (data.length == 3 && !not) {
                throw new FilterException(line);
            }
            return (not) ? new PrefixFilter(value, true) : new PrefixFilter(value, false);
        }
        else if (type.equals(SUFFIX)) {
            String value = data[1];
            if (data.length == 3 && !not) {
                throw new FilterException(line);
            }
            return (not) ? new SuffixFilter(value, true) : new SuffixFilter(value, false);
        }
        else if (type.equals(WRITABLE)) {
            if (data.length == 1 || (!data[1].equals(YES_VALUE) && !data[1].equals(NO_VALUE)) ||
                    (data.length == 3 && !not)) {
                throw new FilterException(line);
            }
            String condition = data[1];
            boolean yesOrNo = condition.equals(YES_VALUE);
            return (not) ? new WritableFilter(yesOrNo, true) : new WritableFilter(yesOrNo, false);
        }
        else if (type.equals(EXECUTABLE)) {
            if (data.length == 1 || (!data[1].equals(YES_VALUE) && !data[1].equals(NO_VALUE)) ||
                    (data.length == 3 && !not)) {
                throw new FilterException(line);
            }
            String condition = data[1];
            boolean yesOrNo = condition.equals(YES_VALUE);
            return (not) ? new ExecutableFilter(yesOrNo, true) : new ExecutableFilter(yesOrNo, false);
        }
        else if (type.equals(HIDDEN)) {
            if (data.length == 1 || (!data[1].equals(YES_VALUE) && !data[1].equals(NO_VALUE)) ||
                    (data.length == 3 && !not)) {
                throw new FilterException(line);
            }
            String condition = data[1];
            boolean yesOrNo = condition.equals(YES_VALUE);
            return (not) ? new HiddenFilter(yesOrNo, true) : new HiddenFilter(yesOrNo, false);
        }
        else if (type.equals(ALL)) {
            if (data.length > 2 || (data.length == 2 && !not)){
                throw new FilterException(line);
            }
            return (not) ? new AllFilter(true) : new AllFilter(false);
        }
        else {
            throw new FilterException(line);
        }
    }
}
