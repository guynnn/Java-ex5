package ordering;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Represents an order by type.
 */
public class TypeOrder extends Order{
    /**
     * Builds a new TypeOrder.
     */
    public TypeOrder(){

    }

    /**
     * Builds a new TypeOrder.
     * @param reverse true if we want to reverse the order of the files.
     */
    public TypeOrder(boolean reverse){
        this.reverse = reverse;
    }

    @Override
    public File[] order(LinkedList<File> files) {
        File[] outcome = new File[files.size()];
        for (int i = 0; i < outcome.length; i++){
            outcome[i] = files.get(i);
        }
        int reverseOrder;
        if (reverse){
            reverseOrder = -1;
        }
        else {
            reverseOrder = 1;
        }
        Arrays.sort(outcome, (o1, o2) -> reverseOrder * compare(o1, o2));
        return outcome;
    }

    // return the type of the file
    private String getType(File file){
        String name = file.getName();
        return name.contains(".") ? name.substring(name.lastIndexOf(".")+  1) : "";
    }

    // comparing two files by their type. If its the same, compare them by their absolute path
    private int compare(File file1, File file2){
        if (getType(file1).compareTo(getType(file2)) == 0){
            return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
        }
        return getType(file1).compareTo(getType(file2));
    }
}
