package ordering;

/**
 * Factory for the order objects. Builds the desired order object.
 */
public class OrderFactory {

    // Represents the AbsOrder object
    private static final String ABS = "abs";
    // Represents the TypeOrder object
    private static final String TYPE = "type";
    // Represents the SizeOrder object
    private static final String SIZE = "size";
    // Indicates if we need the reverse order
    private static final String REVERSE = "REVERSE";
    // the commands are separated with this String
    private static final String SPLITTER = "#";

    /**
     * Builds the correct order object.
     * @param info the command.
     * @param line the line number in the commands file.
     * @return the correct order object.
     * @throws OrderException invalid command.
     */
    public static Order build(String info, int line) throws OrderException{
        String[] data = info.split(SPLITTER);
        if (data.length > 2 || (data.length == 2 && !data[1].equals(REVERSE))){
            throw new OrderException(line);
        }
        boolean reverse = data.length == 2 && data[1].equals(REVERSE);
        String type = data[0];
        if (type.equals(ABS)) {
            return (reverse) ? new AbsOrder(true): new AbsOrder();
        }
        else if (type.equals(TYPE)){
            return (reverse) ? new TypeOrder(true) : new TypeOrder();
        }
        else if (type.equals(SIZE)){
            return (reverse) ? new SizeOrder(true) :new SizeOrder();
        }
        else {
            throw new OrderException(line);
        }
    }
}
