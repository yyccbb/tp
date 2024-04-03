package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAGNAMES_SET_ASS1_ASS2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAGNAMES_SET_ASS3;
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
import seedu.address.model.tag.TagStatus;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        PersonBuilder updatedPersonBuilder = new PersonBuilder(personInFilteredList);
        VALID_TAGNAMES_SET_ASS1_ASS2.forEach(x -> updatedPersonBuilder.addTag(x, TagStatus.COMPLETE_GOOD));
        Person updatedPerson = updatedPersonBuilder.build();

        MarkCommand markCommand = new MarkCommand(INDEX_1_SET, VALID_TAGNAMES_SET_ASS1_ASS2,
                TagStatus.COMPLETE_GOOD);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PERSON_SUCCESS, Messages.format(updatedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), updatedPerson);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Set<Index> outOfBoundIndexSet = new HashSet<>(List.of(outOfBoundIndex));
        MarkCommand markCommand = new MarkCommand(outOfBoundIndexSet, VALID_TAGNAMES_SET_ASS1_ASS2,
                TagStatus.COMPLETE_GOOD);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        MarkCommand markCommand = new MarkCommand(outOfBoundIndexSet, VALID_TAGNAMES_SET_ASS1_ASS2,
                TagStatus.COMPLETE_GOOD);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final MarkCommand standardCommand = new MarkCommand(INDEX_1_SET, VALID_TAGNAMES_SET_ASS1_ASS2,
                TagStatus.DEFAULT_STATUS);

        // same values -> returns true
        MarkCommand commandWithSameValues = new MarkCommand(INDEX_1_SET, VALID_TAGNAMES_SET_ASS1_ASS2,
                TagStatus.DEFAULT_STATUS);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_2_SET, VALID_TAGNAMES_SET_ASS1_ASS2,
                TagStatus.DEFAULT_STATUS)));

        // different tagName -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_1_SET,
                VALID_TAGNAMES_SET_ASS3, TagStatus.DEFAULT_STATUS)));

        // different tagStatus -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_1_SET, VALID_TAGNAMES_SET_ASS1_ASS2,
                TagStatus.COMPLETE_GOOD)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Set<Index> indexSet = new HashSet<>(List.of(index));
        MarkCommand markCommand = new MarkCommand(indexSet, VALID_TAGNAMES_SET_ASS1_ASS2, TagStatus.COMPLETE_GOOD);
        String expected = MarkCommand.class.getCanonicalName() + "{index=" + indexSet + ", tagName(s)="
                + VALID_TAGNAMES_SET_ASS1_ASS2 + ", tagStatus=" + TagStatus.COMPLETE_GOOD + "}";
        assertEquals(expected, markCommand.toString());
    }

    @Test
    public void execute_nonExistingTutorialTagMarking_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, VALID_TAG_FRIENDS,
                TagStatus.AVAILABLE);

        String expectedMessage = Messages.MESSAGE_INVALID_TUTORIAL_TAG_VALUE + VALID_TAG_FRIENDS;
        assertCommandFailure(markCommand, model, expectedMessage);
    }

}
