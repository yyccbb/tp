package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
        assertTrue(TagStatus.DEFAULT_STATUS.equals(TagStatus.getTagStatus(
                emptyTagStatus)));

        assertTrue(TagStatus.W08.equals(TagStatus.getTagStatus(
                TagStatus.W08_KEYWORD)));
        assertTrue(TagStatus.W09.equals(TagStatus.getTagStatus(
                TagStatus.W09_KEYWORD)));
        assertTrue(TagStatus.W10.equals(TagStatus.getTagStatus(
                TagStatus.W10_KEYWORD)));
        assertTrue(TagStatus.W11.equals(TagStatus.getTagStatus(
                TagStatus.W11_KEYWORD)));
        assertTrue(TagStatus.W12.equals(TagStatus.getTagStatus(
                TagStatus.W12_KEYWORD)));
        assertTrue(TagStatus.W13.equals(TagStatus.getTagStatus(
                TagStatus.W13_KEYWORD)));

        assertTrue(TagStatus.T08.equals(TagStatus.getTagStatus(
                TagStatus.T08_KEYWORD)));
        assertTrue(TagStatus.T09.equals(TagStatus.getTagStatus(
                TagStatus.T09_KEYWORD)));
        assertTrue(TagStatus.T10.equals(TagStatus.getTagStatus(
                TagStatus.T10_KEYWORD)));
        assertTrue(TagStatus.T11.equals(TagStatus.getTagStatus(
                TagStatus.T11_KEYWORD)));
        assertTrue(TagStatus.T12.equals(TagStatus.getTagStatus(
                TagStatus.T12_KEYWORD)));
        assertTrue(TagStatus.T13.equals(TagStatus.getTagStatus(
                TagStatus.T13_KEYWORD)));
        assertTrue(TagStatus.T14.equals(TagStatus.getTagStatus(
                TagStatus.T14_KEYWORD)));
        assertTrue(TagStatus.T15.equals(TagStatus.getTagStatus(
                TagStatus.T15_KEYWORD)));
        assertTrue(TagStatus.T16.equals(TagStatus.getTagStatus(
                TagStatus.T16_KEYWORD)));
        assertTrue(TagStatus.T17.equals(TagStatus.getTagStatus(
                TagStatus.T17_KEYWORD)));

        assertTrue(TagStatus.F08.equals(TagStatus.getTagStatus(
                TagStatus.F08_KEYWORD)));
        assertTrue(TagStatus.F09.equals(TagStatus.getTagStatus(
                TagStatus.F09_KEYWORD)));
        assertTrue(TagStatus.F10.equals(TagStatus.getTagStatus(
                TagStatus.F10_KEYWORD)));
        assertTrue(TagStatus.F11.equals(TagStatus.getTagStatus(
                TagStatus.F11_KEYWORD)));
        assertTrue(TagStatus.F12.equals(TagStatus.getTagStatus(
                TagStatus.F12_KEYWORD)));
        assertTrue(TagStatus.F13.equals(TagStatus.getTagStatus(
                TagStatus.F13_KEYWORD)));
        assertTrue(TagStatus.F14.equals(TagStatus.getTagStatus(
                TagStatus.F14_KEYWORD)));
        assertTrue(TagStatus.F15.equals(TagStatus.getTagStatus(
                TagStatus.F15_KEYWORD)));
    }
}
