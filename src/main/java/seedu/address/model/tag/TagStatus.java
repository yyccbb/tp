package seedu.address.model.tag;

/**
 * Represents submission / attendance status of a tag.
 */
public enum TagStatus {
    COMPLETE_GOOD, // complete before deadline
    COMPLETE_BAD, // complete after deadline
    INCOMPLETE_GOOD, // incomplete before deadline
    INCOMPLETE_BAD, // incomplete after deadline

    PRESENT,
    ABSENT,
    ABSENT_WITH_REASON,

    ASSIGNED, AVAILABLE;

    public static final TagStatus DEFAULT_STATUS = INCOMPLETE_GOOD;
    public static final String COMPLETE_GOOD_KEYWORD = "cg";
    public static final String COMPLETE_BAD_KEYWORD = "cb";
    public static final String INCOMPLETE_GOOD_KEYWORD = "ig";
    public static final String INCOMPLETE_BAD_KEYWORD = "ib";
    public static final String PRESENT_KEYWORD = "p";
    public static final String ABSENT_KEYWORD = "a";
    public static final String ABSENT_WITH_REASON_KEYWORD = "awr";
    public static final String ASSIGNED_KEYWORD = "as";
    public static final String AVAILABLE_KEYWORD = "av";

    public static final String INVALID_TAGSTATUS_ERROR_MSG = "Invalid TagStatus Flag, check out the help page for "
            + "valid TagStatus tags.";

    /**
     * @param status Keyword corresponding each of the TagStatus.
     * @return TagStatus matching the keyword.
     */
    public static TagStatus getTagStatus(String status) throws IllegalArgumentException {
        switch (status) {
        case COMPLETE_GOOD_KEYWORD:
            return COMPLETE_GOOD;
        case COMPLETE_BAD_KEYWORD:
            return COMPLETE_BAD;
        case INCOMPLETE_GOOD_KEYWORD:
            return INCOMPLETE_GOOD;
        case INCOMPLETE_BAD_KEYWORD:
            return INCOMPLETE_BAD;
        case PRESENT_KEYWORD:
            return PRESENT;
        case ABSENT_KEYWORD:
            return ABSENT;
        case ABSENT_WITH_REASON_KEYWORD:
            return ABSENT_WITH_REASON;
        case ASSIGNED_KEYWORD:
            return ASSIGNED;
        case AVAILABLE_KEYWORD:
            return AVAILABLE;
        default:
            throw new IllegalArgumentException(INVALID_TAGSTATUS_ERROR_MSG);
        }
    }

}
