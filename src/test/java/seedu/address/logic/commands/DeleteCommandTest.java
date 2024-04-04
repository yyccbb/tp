package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_getNeedsWarningPopup_returnsTrue() {

        Index[] indexArr = {INDEX_FIRST_PERSON};
        Set<Index> indexSet = new HashSet<>(Arrays.asList(indexArr));
        DeleteCommand deleteCommand = new DeleteCommand(indexSet);

        assertTrue(deleteCommand.getNeedsWarningPopup());
    }

    @Test
    public void execute_validSingleIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index[] indexArr = {INDEX_FIRST_PERSON};
        Set<Index> indexSet = new HashSet<>(Arrays.asList(indexArr));
        DeleteCommand deleteCommand = new DeleteCommand(indexSet);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndexUnfilteredList_success() {
        Person personToDelete1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personToDelete2 = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Index[] indexArr = {INDEX_FIRST_PERSON, INDEX_SECOND_PERSON};
        Set<Index> indexSet = new HashSet<>(Arrays.asList(indexArr));
        DeleteCommand deleteCommand = new DeleteCommand(indexSet);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete1)) + "\n" + String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete2));
        System.out.println(expectedMessage);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete1);
        expectedModel.deletePerson(personToDelete2);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSingleIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Index[] indexArr = {outOfBoundIndex};
        Set<Index> indexSet = new HashSet<>(Arrays.asList(indexArr));
        DeleteCommand deleteCommand = new DeleteCommand(indexSet);

        assertCommandFailure(deleteCommand, model,
                String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX,
                        "[" + outOfBoundIndex.getOneBased() + "]"));
    }

    @Test
    public void execute_validSingleIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Index[] indexArr = {INDEX_FIRST_PERSON};
        Set<Index> indexSet = new HashSet<>(Arrays.asList(indexArr));
        DeleteCommand deleteCommand = new DeleteCommand(indexSet);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSingleIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        Index[] indexArr = {outOfBoundIndex};
        Set<Index> indexSet = new HashSet<>(Arrays.asList(indexArr));
        // ensures that outOfBoundIndex is still in bounds of address book list
        DeleteCommand deleteCommand = new DeleteCommand(indexSet);

        assertCommandFailure(deleteCommand, model,
                String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX,
                        "[" + INDEX_SECOND_PERSON.getOneBased() + "]"));
    }

    @Test
    public void equals() {
        Index[] firstPersonIndexArr = {INDEX_FIRST_PERSON};
        Set<Index> firstPersonIndexSet = new HashSet<>(Arrays.asList(firstPersonIndexArr));
        Index[] secondPersonIndexArr = {INDEX_SECOND_PERSON};
        Set<Index> secondPersonIndexSet = new HashSet<>(Arrays.asList(secondPersonIndexArr));


        DeleteCommand deleteFirstCommand = new DeleteCommand(firstPersonIndexSet);
        DeleteCommand deleteSecondCommand = new DeleteCommand(secondPersonIndexSet);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(firstPersonIndexSet);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        Index[] indexArr = {targetIndex};
        Set<Index> indexSet = new HashSet<>(Arrays.asList(indexArr));
        DeleteCommand deleteCommand = new DeleteCommand(indexSet);
        String expected = DeleteCommand.class.getCanonicalName() + "{index=[" + targetIndex + "]}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
