package seedu.address.model.tag;

import org.junit.jupiter.api.Test;

class TutorialTagTest {

    @Test
    public void equals_theSameTag_success() {
        String validTagName = "test";
        TutorialTag tag1 = new TutorialTag(validTagName, TagStatus.AVAILABLE);
        assert(tag1.isSameTutorialTag(tag1));
    }

    @Test
    public void equals_tagWithTheSameTagName_success() {
        String validTagName = "test";
        TutorialTag tag1 = new TutorialTag(validTagName, TagStatus.AVAILABLE);
        TutorialTag tag2 = new TutorialTag(validTagName, TagStatus.AVAILABLE);
        assert(tag1.isSameTutorialTag(tag2));
    }
}
