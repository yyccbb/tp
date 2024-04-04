package seedu.address.commons.util;

import javafx.collections.ObservableList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TutorialTag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {


    private static ObservableList<TutorialTag> validTutorials;

    public StringUtil (ObservableList<TutorialTag> validTutorials) {
        this.validTutorials = validTutorials;
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, and performs subword matching.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == true
     *       containsWordIgnoreCase("ABc def", "Ac") == false //not a match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty
     */
    public static boolean containsSubwordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim().toLowerCase();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(wordInPreppedSentence -> wordInPreppedSentence.toLowerCase().contains(preppedWord));
    }

    public static boolean containsTutorialGroup(Tag tag, String tutorialGroup) {
        requireNonNull(tag);
        requireNonNull(tutorialGroup);

        String tagName = tag.tagName;
        checkArgument(!tutorialGroup.isEmpty(), "Tutorial group parameter cannot be empty");
        checkArgument(!tutorialGroup.contains(" "), "Only use one word for tutorial group parameter");

        for (TutorialTag tutorial : validTutorials) {
            String validTutorialGroupTag = tutorial.tagName;
            if (validTutorialGroupTag.equalsIgnoreCase(tutorialGroup)) {
                return tagName.equalsIgnoreCase(tutorialGroup) && tag.getTagStatus() == TagStatus.AVAILABLE;
            }
        }
        return false;
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
