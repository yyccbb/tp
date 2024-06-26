package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTutorialTag.WED10;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TutorialTag;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    ///////
    @Test
    public void hasTutorialTag_nullTutorialTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTutorialTag(null));
    }

    @Test
    public void hasTutorialTag_tutorialTagNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTutorialTag(WED10));
    }

    @Test
    public void hasTutorialTag_tutorialTagInAddressBook_returnsTrue() {
        addressBook.addTutorialTag(WED10);
        assertTrue(addressBook.hasTutorialTag(WED10));
    }

    @Test
    public void hasTutorialTag_tutorialTagWithSameTagNamesInAddressBook_returnsTrue() {
        addressBook.addTutorialTag(WED10);
        TutorialTag editedWed10 = new TutorialTag("WED10", TagStatus.AVAILABLE);
        assertTrue(addressBook.hasTutorialTag(editedWed10));
    }

    @Test
    public void deleteTutorialTag_success() {
        addressBook.addTutorialTag(WED10);
        addressBook.removeTutorialTag(WED10);
        assertFalse(addressBook.hasTutorialTag(WED10));
    }

    @Test
    public void getTutoiralList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTutorialTagList().remove(0));
    }
    ///////

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    void equals_selfAddressbook_true() {
        assert(addressBook.equals(addressBook));
    }

    @Test
    void equals_otherTypeObject_false() {
        assertFalse(addressBook.equals(new Object()));
    }

    @Test
    void hashcode_staysTheSameForSameAddressBook() {
        assertEquals(addressBook.hashCode(), addressBook.hashCode());
    }

    @Test
    void getTutorialTagListString_forEmptyAddressBook() {
        assertEquals(addressBook.getTutorialTagListString(), "Available Tutorial Tag(s): [ ]");
    }

    @Test
    void getTutorialTagListString_forNonEmptyAddressBook() {
        addressBook.addTutorialTag(WED10);
        assertEquals(addressBook.getTutorialTagListString(), "Available Tutorial Tag(s): [WED10]");
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
        @Override
        public ObservableList<TutorialTag> getTutorialTagList() {
            return getTutorialTagList();
        };
    }
}
