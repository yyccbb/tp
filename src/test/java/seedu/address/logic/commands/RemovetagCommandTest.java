package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAGNAMES_SET_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_1_SET;
import static seedu.address.testutil.TypicalIndexes.INDEX_2_SET;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class RemovetagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute() {
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Person updatedPerson = new PersonBuilder(personInFilteredList).removeTag(VALID_TAG_FRIENDS).build();
        RemovetagCommand removetagCommand = new RemovetagCommand(INDEX_1_SET, VALID_TAGNAMES_SET_FRIENDS);

        String expectedMessage = String.format(RemovetagCommand.MESSAG_REMOVETAG_SUCCESS,
                Messages.format(updatedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), updatedPerson);

        assertCommandSuccess(removetagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Index> outOfBoundIndexSet = new HashSet<>(List.of(outOfBoundIndex));
        RemovetagCommand removetagCommand = new RemovetagCommand(outOfBoundIndexSet, VALID_TAGNAMES_SET_FRIENDS);

        assertCommandFailure(removetagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        Set<Index> outOfBoundIndexSet = new HashSet<>(List.of(outOfBoundIndex));
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        RemovetagCommand removetagCommand = new RemovetagCommand(outOfBoundIndexSet, VALID_TAGNAMES_SET_FRIENDS);

        assertCommandFailure(removetagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    public void equals() {
        final RemovetagCommand standardCommand = new RemovetagCommand(INDEX_1_SET, VALID_TAGNAMES_SET_FRIENDS);

        // same values -> returns true
        RemovetagCommand commandWithSameValues = new RemovetagCommand(INDEX_1_SET, VALID_TAGNAMES_SET_FRIENDS);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemovetagCommand(INDEX_2_SET, VALID_TAGNAMES_SET_FRIENDS)));

        // different tagName -> returns false
        assertFalse(standardCommand.equals(new RemovetagCommand(INDEX_2_SET, VALID_TAGNAMES_SET_FRIENDS)));

    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Set<Index> indexSet = new HashSet<>(List.of(index));
        RemovetagCommand removetagCommand = new RemovetagCommand(indexSet, VALID_TAGNAMES_SET_FRIENDS);
        String expected = RemovetagCommand.class.getCanonicalName() + "{index=" + indexSet + ", tagName(s)="
                + VALID_TAGNAMES_SET_FRIENDS + "}";
        assertEquals(expected, removetagCommand.toString());
    }
}
