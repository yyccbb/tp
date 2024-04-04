package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorialTag.WED10;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;


public class StatefulStringUtilTest {
    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------
    protected StatefulStringUtil testStatefulStringUtil;


    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StatefulStringUtil.isNonZeroUnsignedInteger("")); // Boundary value
        assertFalse(StatefulStringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StatefulStringUtil.isNonZeroUnsignedInteger("a"));
        assertFalse(StatefulStringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StatefulStringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StatefulStringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StatefulStringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StatefulStringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StatefulStringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StatefulStringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StatefulStringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StatefulStringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
        assertTrue(StatefulStringUtil.isNonZeroUnsignedInteger("10"));
    }


    //---------------- Tests for containsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StatefulStringUtil
                .containsSubwordIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StatefulStringUtil.containsSubwordIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", ()
            -> StatefulStringUtil.containsSubwordIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StatefulStringUtil
                .containsSubwordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StatefulStringUtil.containsSubwordIgnoreCase("", "abc")); // Boundary case
        assertFalse(StatefulStringUtil.containsSubwordIgnoreCase("    ", "123"));

        // Matches a partial word only
        assertTrue(StatefulStringUtil
                .containsSubwordIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
        assertFalse(StatefulStringUtil
                .containsSubwordIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StatefulStringUtil
                .containsSubwordIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StatefulStringUtil
                .containsSubwordIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StatefulStringUtil
                .containsSubwordIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StatefulStringUtil
                .containsSubwordIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StatefulStringUtil
                .containsSubwordIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StatefulStringUtil.containsSubwordIgnoreCase("AAA bBb ccc  bbb", "bbB"));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StatefulStringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StatefulStringUtil.getDetails(null));
    }


    //---------------- Tests for tagContainsWordIgnoreCase --------------------------------------

    @Test
    public void testTagContainsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> testStatefulStringUtil.tagContainsWordIgnoreCase(WED10, null));
    }
}
