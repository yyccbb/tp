package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class StringListUtilTest {
    @Test
    public void separateWithSpaces_stringListWithSpaces_stringListSeparatedBySpace() {
        String[] strArr = {"alice Bob", "terry", "Gavin"};
        String[] expectedStrArr = {"alice", "Bob", "terry", "Gavin"};
        assertArrayEquals(StringListUtil.separateWithSpaces(Arrays.asList(strArr)).toArray(), expectedStrArr);
    }
}
