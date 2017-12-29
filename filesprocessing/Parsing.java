package filesprocessing;

import filtering.*;
import ordering.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is responsible for reading the commands file
 */
public class Parsing {

    // The title of the filter section
    private static final String FILTER = "FILTER";
    // The title of the filter section
    private static final String ORDER = "ORDER";
    // we make this kind of Order in default and in case of problem in the specific command
    private static final String ORDER_DEFAULT = "abs";

    // Reading the commands file and makes an array out of it
    private static String[] readCommand(String command) throws IOException{
        LinkedList<String> data = new LinkedList<String>();
        BufferedReader br = new BufferedReader(new FileReader(command));
        String line = br.readLine();
        // reading the file
        while (line != null) {
            data.add(line);
            line = br.readLine();
        }
        // Casting the data from LinkedList to array
        String[] arrayData = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            arrayData[i] = data.get(i);
        }
        return arrayData;
    }

    /**
     * Makes a Section array based on the command file, which we use to print the output in the main class.
     * @param commandDir The path of the commands file.
     * @return array of Section.
     * @throws MissingSectionException in case the seb-section has no FILTER or ORDER.
     * @throws IOException in case the file's reading operation has failed.
     */
    public static ArrayList<Section> buildSections(String commandDir)
                                                                throws MissingSectionException, IOException {
        // Getting array of commands from the path
        String[] commands = readCommand(commandDir);
        ArrayList<Section> sections = new ArrayList<>();
        // Now we run through the array of commands
        for (int line = 0; line < commands.length;){
            Filter filter;
            // If there is a warning in the filter command, we save it here
            String filterWarning = "";
            if (!commands[line].equals(FILTER)){
                // No FILTER title
                throw new MissingSectionException(FILTER);
            }
            try {
                filter = FilterFactory.build(commands[line + 1], line + 2);
            } catch (FilterException f){
                // Problem in the filter command
                filterWarning = f.getMessage();
                filter = new AllFilter();
            } catch (ArrayIndexOutOfBoundsException a){
                // missing the filter command
                throw new MissingSectionException(FILTER);
            }
            // moving now to the Order part
            line += 2;
            if (commands.length <= line){
                //No ORDER title
                throw new MissingSectionException(ORDER);
            }
            Order order;
            String orderWarning = "";
            if (!commands[line].equals(ORDER)){
                //No ORDER title
                throw new MissingSectionException(ORDER);
            }
            if (commands.length - 1 == line || commands[line + 1].equals(FILTER)){
                // If this is the last line, or if there's no explicit command for order
                try {
                    order = OrderFactory.build(ORDER_DEFAULT, line + 1);
                } catch (OrderException o){
                    orderWarning = o.getMessage();
                    order = new AbsOrder();
                }
                line++;
            }
            else {
                try {
                    order = OrderFactory.build(commands[line + 1], line + 2);
                } catch (OrderException o){
                    orderWarning = o.getMessage();
                    order = new AbsOrder();
                }
                line += 2;
            }
            Section section = new Section(filter, filterWarning, order, orderWarning);
            sections.add(section);
        }
        return sections;
    }
}
