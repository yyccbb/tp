package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.Assert;

class TagStatusTest {

    @Test
    void getTagStatus() {

        final String emptyTagStatus = "";

        assertTrue(TagStatus.COMPLETE_GOOD.equals(TagStatus.getTagStatus(
                TagStatus.COMPLETE_GOOD_KEYWORD)));
        assertTrue(TagStatus.COMPLETE_BAD.equals(TagStatus.getTagStatus(
                TagStatus.COMPLETE_BAD_KEYWORD)));
        assertTrue(TagStatus.INCOMPLETE_GOOD.equals(TagStatus.getTagStatus(
                TagStatus.INCOMPLETE_GOOD_KEYWORD)));
        assertTrue(TagStatus.INCOMPLETE_BAD.equals(TagStatus.getTagStatus(
                TagStatus.INCOMPLETE_BAD_KEYWORD)));

        assertTrue(TagStatus.PRESENT.equals(TagStatus.getTagStatus(
                TagStatus.PRESENT_KEYWORD)));
        assertTrue(TagStatus.ABSENT.equals(TagStatus.getTagStatus(
                TagStatus.ABSENT_KEYWORD)));
        assertTrue(TagStatus.ABSENT_WITH_REASON.equals(TagStatus.getTagStatus(
                TagStatus.ABSENT_WITH_REASON_KEYWORD)));

        assertTrue(TagStatus.ASSIGNED.equals(TagStatus.getTagStatus(
                TagStatus.ASSIGNED_KEYWORD)));
        assertTrue(TagStatus.AVAILABLE.equals(TagStatus.getTagStatus(
                TagStatus.AVAILABLE_KEYWORD)));

        Assert.assertThrows(IllegalArgumentException.class, () -> TagStatus.getTagStatus(emptyTagStatus));
    }
}
