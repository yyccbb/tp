package seedu.address.commons.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    public static List<String> separateWithSpaces(List<String> originalList) {
        List<String> separatedList = new ArrayList<>();
        for (String str : originalList) {
            if (str.contains(" ")) {
                String[] parts = str.split(" ");
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
