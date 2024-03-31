package seedu.address.model.tag;

/**
 * Represents the different types of tags.
 */
public enum TagType {
    ASSIGNMENT,
    ATTENDANCE,
    TUTORIAL;
    public static final TagType DEFAULT_TYPE = ASSIGNMENT;
}
