package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class DeletetagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute() {
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Person updatedPerson = new PersonBuilder(personInFilteredList).deleteTag(VALID_TAG_FRIENDS).build();
        DeletetagCommand deletetagCommand = new DeletetagCommand(INDEX_FIRST_PERSON, VALID_TAG_FRIENDS);

        String expectedMessage = String.format(DeletetagCommand.MESSAG_DELETETAG_SUCCESS,
                Messages.format(updatedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), updatedPerson);

        assertCommandSuccess(deletetagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeletetagCommand deletetagCommand = new DeletetagCommand(outOfBoundIndex, VALID_TAG_FRIENDS);

        assertCommandFailure(deletetagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeletetagCommand deletetagCommand = new DeletetagCommand(outOfBoundIndex, VALID_TAG_FRIENDS);

        assertCommandFailure(deletetagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    public void equals() {
        final DeletetagCommand standardCommand = new DeletetagCommand(INDEX_FIRST_PERSON, VALID_TAG_FRIENDS);

        // same values -> returns true
        DeletetagCommand commandWithSameValues = new DeletetagCommand(INDEX_FIRST_PERSON, VALID_TAG_FRIENDS);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeletetagCommand(INDEX_SECOND_PERSON, VALID_TAG_FRIENDS)));

        // different tagName -> returns false
        assertFalse(standardCommand.equals(new DeletetagCommand(INDEX_FIRST_PERSON, VALID_TAG_HUSBAND)));

    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        DeletetagCommand deletetagCommand = new DeletetagCommand(index, VALID_TAG_FRIENDS);
        String expected = DeletetagCommand.class.getCanonicalName() + "{index=" + index + ", tagName="
                + VALID_TAG_FRIENDS + "}";
        assertEquals(expected, deletetagCommand.toString());
    }
}
