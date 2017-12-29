package filesprocessing;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class. Runs the entire program.
 */
public class DirectoryProcessor {

    // message to print in case the program gets bad parameters
    private static String invalidUsage = "ERROR: the correct usage is 2 arguments: [sourceDirectory] " +
                                                                                  "[commandsFile]";
    // message to print in case of an error while reading the file
    private static String invalidFile = "ERROR: could not access the file";

    /**
     * The main method. gets the paths of the folder and the commands file, and sorting the files inside
     * the folder according to the commands file.
     * @param args array of length 2. The first parameter is the sourceDir and the second is commands file.
     */
    public static void main(String[] args){
        if (args.length != 2){
            System.err.println(invalidUsage);
            return;
        }
        try {
            // Updating the directory
            Section.setDir(args[0]);
            // Building the section in the commands file
            ArrayList<Section> sections = Parsing.buildSections(args[1]);
            for (int i = 0; i < sections.size(); i++){
                // Printing every section's output
                sections.get(i).print();
            }
        } catch (MissingSectionException m){
            System.err.println(m.getMessage());
        } catch (IOException i){
            System.err.println(invalidFile);
        }
    }
}
