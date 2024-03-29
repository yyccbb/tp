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

    W08, W09, W10, W11, W12, W13,
    T08, T09, T10, T11, T12, T13, T14, T15, T16, T17,
    F08, F09, F10, F11, F12, F13, F14, F15;

    public static final TagStatus DEFAULT_STATUS = INCOMPLETE_GOOD;
    public static final String COMPLETE_GOOD_KEYWORD = "cg";
    public static final String COMPLETE_BAD_KEYWORD = "cb";
    public static final String INCOMPLETE_GOOD_KEYWORD = "ig";
    public static final String INCOMPLETE_BAD_KEYWORD = "ib";
    public static final String PRESENT_KEYWORD = "p";
    public static final String ABSENT_KEYWORD = "a";
    public static final String ABSENT_WITH_REASON_KEYWORD = "awr";
    public static final String W08_KEYWORD = "w08";
    public static final String W09_KEYWORD = "w09";
    public static final String W10_KEYWORD = "w10";
    public static final String W11_KEYWORD = "w11";
    public static final String W12_KEYWORD = "w12";
    public static final String W13_KEYWORD = "w13";
    public static final String T08_KEYWORD = "t08";
    public static final String T09_KEYWORD = "t09";
    public static final String T10_KEYWORD = "t10";
    public static final String T11_KEYWORD = "t11";
    public static final String T12_KEYWORD = "t12";
    public static final String T13_KEYWORD = "t13";
    public static final String T14_KEYWORD = "t14";
    public static final String T15_KEYWORD = "t15";
    public static final String T16_KEYWORD = "t16";
    public static final String T17_KEYWORD = "t17";
    public static final String F08_KEYWORD = "f08";
    public static final String F09_KEYWORD = "f09";
    public static final String F10_KEYWORD = "f10";
    public static final String F11_KEYWORD = "f11";
    public static final String F12_KEYWORD = "f12";
    public static final String F13_KEYWORD = "f13";
    public static final String F14_KEYWORD = "f14";
    public static final String F15_KEYWORD = "f15";


    /**
     * @param status Keyword corresponding each of the TagStatus.
     * @return TagStatus matching the keyword.
     */
    public static TagStatus getTagStatus(String status) {
        switch (status) {
        case COMPLETE_GOOD_KEYWORD:
            return TagStatus.COMPLETE_GOOD;
        case COMPLETE_BAD_KEYWORD:
            return TagStatus.COMPLETE_BAD;
        case INCOMPLETE_GOOD_KEYWORD:
            return TagStatus.INCOMPLETE_GOOD;
        case INCOMPLETE_BAD_KEYWORD:
            return TagStatus.INCOMPLETE_BAD;
        case PRESENT_KEYWORD:
            return PRESENT;
        case ABSENT_KEYWORD:
            return ABSENT;
        case ABSENT_WITH_REASON_KEYWORD:
            return ABSENT_WITH_REASON;
        case W08_KEYWORD:
            return W08;
        case W09_KEYWORD:
            return W09;
        case W10_KEYWORD:
            return W10;
        case W11_KEYWORD:
            return W11;
        case W12_KEYWORD:
            return W12;
        case W13_KEYWORD:
            return W13;
        case T08_KEYWORD:
            return T08;
        case T09_KEYWORD:
            return T09;
        case T10_KEYWORD:
            return T10;
        case T11_KEYWORD:
            return T11;
        case T12_KEYWORD:
            return T12;
        case T13_KEYWORD:
            return T13;
        case T14_KEYWORD:
            return T14;
        case T15_KEYWORD:
            return T15;
        case T16_KEYWORD:
            return T16;
        case T17_KEYWORD:
            return T17;
        case F08_KEYWORD:
            return F08;
        case F09_KEYWORD:
            return F09;
        case F10_KEYWORD:
            return F10;
        case F11_KEYWORD:
            return F11;
        case F12_KEYWORD:
            return F12;
        case F13_KEYWORD:
            return F13;
        case F14_KEYWORD:
            return F14;
        case F15_KEYWORD:
            return F15;
        default:
            return TagStatus.DEFAULT_STATUS;
        }
    }

}
