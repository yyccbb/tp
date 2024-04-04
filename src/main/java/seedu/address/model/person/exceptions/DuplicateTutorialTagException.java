package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate TutoiralTags (TutorialTags are considered duplicates
 * if they have the same TagName).
 */
public class DuplicateTutorialTagException extends RuntimeException {
    public DuplicateTutorialTagException() {
        super("Operation would result in duplicate TutorialTags");
    }
}
