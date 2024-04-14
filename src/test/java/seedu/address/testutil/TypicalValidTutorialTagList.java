package seedu.address.testutil;

import static seedu.address.testutil.TypicalTutorialTag.THU10;
import static seedu.address.testutil.TypicalTutorialTag.TUES08;
import static seedu.address.testutil.TypicalTutorialTag.WED10;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tag.TutorialTag;

/**
 * A utility class containing a list of {@code TutorialTag} objects to be used in tests.
 */
public class TypicalValidTutorialTagList {

    public static final ObservableList<TutorialTag> VALID_TUTORIAL_TAG_LIST = initaliseValidTutorialTagList();
    /**
     * Returns a list of valid {@code TutorialTag} objects.
     */
    public static ObservableList<TutorialTag> initaliseValidTutorialTagList() {
        ObservableList<TutorialTag> validTutorialTagList = FXCollections.observableArrayList();
        validTutorialTagList.add(WED10);
        validTutorialTagList.add(THU10);
        validTutorialTagList.add(TUES08);
        return validTutorialTagList;
    }

}
