package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalTutorialTag.WED10;

import org.junit.jupiter.api.Test;


class JsonAdaptedTagTest {

    @Test
    public void getTagName() {
        JsonAdaptedTutorialTag wed10 = new JsonAdaptedTutorialTag(WED10.getTagName());
        assertEquals(wed10.getTagName(), WED10.getTagName());
    }

    @Test
    public void createJsonAdaptedTutorialTagFromTutorialTag() {
        JsonAdaptedTutorialTag wed10 = new JsonAdaptedTutorialTag(WED10);
        assertEquals(wed10.getTagName(), WED10.getTagName());
    }
}
