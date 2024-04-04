package seedu.address.testutil;

import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TutorialTag;

/**
 * A utility class containing {@code TutorialTag} objects to be used in tests.
 */
public class TypicalTutorialTag {

    public static final TutorialTag TUES08 = new TutorialTag("TUES08", TagStatus.ASSIGNED);
    public static final TutorialTag WED10 = new TutorialTag("WED10", TagStatus.AVAILABLE);
    public static final TutorialTag THU10 = new TutorialTag("THU10", TagStatus.ASSIGNED);
}
