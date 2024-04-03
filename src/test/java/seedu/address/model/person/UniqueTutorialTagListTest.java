package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorialTag.WED10;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateTutorialTagException;
import seedu.address.model.person.exceptions.TutorialTagNotFoundException;
import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TutorialTag;

class UniqueTutorialTagListTest {
    private final UniqueTutorialTagList uniqueTutorialTagList = new UniqueTutorialTagList();

    @Test
    public void contains_nullTutorialTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialTagList.contains(null));
    }

    @Test
    public void contains_tutorialTagNotInList_returnsFalse() {
        assertFalse(uniqueTutorialTagList.contains(WED10));
    }

    @Test
    public void contains_tutorialTagInList_returnsTrue() {
        uniqueTutorialTagList.add(WED10);
        assertTrue(uniqueTutorialTagList.contains(WED10));
    }

    @Test
    public void contains_tutorialTagWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTutorialTagList.add(WED10);
        TutorialTag editedWed10 = new TutorialTag("WED10", TagStatus.AVAILABLE);
        assertTrue(uniqueTutorialTagList.contains(editedWed10));
    }

    @Test
    public void add_nullTutorialTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialTagList.add(null));
    }

    @Test
    public void add_duplicateTutorialTag_throwsDuplicatePersonException() {
        uniqueTutorialTagList.add(WED10);
        assertThrows(DuplicateTutorialTagException.class, () -> uniqueTutorialTagList.add(WED10));
    }

    @Test
    public void remove_nullTutorialTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialTagList.remove(null));
    }

    @Test
    public void remove_tutorialTagDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(TutorialTagNotFoundException.class, () -> uniqueTutorialTagList.remove(WED10));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueTutorialTagList.add(WED10);
        uniqueTutorialTagList.remove(WED10);
        UniqueTutorialTagList expectedUniqueTutorialTagList = new UniqueTutorialTagList();
        assertEquals(expectedUniqueTutorialTagList, uniqueTutorialTagList);
    }

    @Test
    public void setTutorialTags_nullUniqueTutorialTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialTagList
                .setTutorialTags((UniqueTutorialTagList) null));
    }

    @Test
    public void setTutorialTags_uniqueTutorialTagList_replacesOwnListWithProvidedUniqueTutorialTagList() {
        uniqueTutorialTagList.add(WED10);
        UniqueTutorialTagList expectedUniqueTutorialTagList = new UniqueTutorialTagList();
        expectedUniqueTutorialTagList.add(WED10);
        uniqueTutorialTagList.setTutorialTags(expectedUniqueTutorialTagList);
        assertEquals(expectedUniqueTutorialTagList, uniqueTutorialTagList);
    }

    @Test
    public void setTutorialTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialTagList
                .setTutorialTags((List<TutorialTag>) null));
    }

    @Test
    public void setTutorialTags_listWithDuplicateTutorialTags_throwsDuplicatePersonException() {
        List<TutorialTag> listWithDuplicateTutorialTags = Arrays.asList(WED10, WED10);
        assertThrows(DuplicateTutorialTagException.class, () -> uniqueTutorialTagList
                .setTutorialTags(listWithDuplicateTutorialTags));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTutorialTagList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueTutorialTagList.asUnmodifiableObservableList().toString(),
                uniqueTutorialTagList.toString());
    }

    @Test
    public void return_iterator_success() {
        assert (uniqueTutorialTagList.iterator() instanceof Iterator);
    }

    @Test
    public void equals_selfUniqueTutoiralTagList() {
        assert uniqueTutorialTagList.equals(uniqueTutorialTagList);
    }

    @Test
    public void equals_nullUniqueTutoiralTagList_fail() {
        assertFalse(uniqueTutorialTagList.equals(null));
    }

    @Test
    public void equals_nonUniqueTutoiralTagListObject_fail() {
        assertFalse(uniqueTutorialTagList.equals(new Object()));
    }

    @Test
    public void sameHashCode_forSameUniqueTutorialTagList() {
        UniqueTutorialTagList list1 = new UniqueTutorialTagList();
        UniqueTutorialTagList list2 = new UniqueTutorialTagList();
        list1.add(WED10);
        list2.add(WED10);
        assertEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    public void differentHashCode_forDifferentUniqueTutorialTagList() {
        UniqueTutorialTagList list1 = new UniqueTutorialTagList();
        UniqueTutorialTagList list2 = new UniqueTutorialTagList();
        list1.add(WED10);
        assertNotEquals(list1.hashCode(), list2.hashCode());
    }
}
