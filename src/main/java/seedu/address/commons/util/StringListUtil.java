package seedu.address.commons.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This ListUtil class contains additional methods for list manipulation.
 */
public class StringListUtil {

    /**
     * Given a list of strings, returns a new list of strings separated by white spaces.
     * @param originalList list of strings
     * @return separatedList a new list of strings separated by white spaces
     */
    public static List<String> separateWithSpaces(List<String> originalList) {
        List<String> separatedList = new ArrayList<>();
        for (String str : originalList) {
            if (str.contains(" ")) {
                String[] parts = str.split("\\s+");
                for (String part : parts) {
                    separatedList.add(part);
                }
            } else {
                separatedList.add(str);
            }
        }
        return separatedList;
    }
}
