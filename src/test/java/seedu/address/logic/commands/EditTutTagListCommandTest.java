package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalTutorialTag.WED10;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicateTutorialTagException;
import seedu.address.model.tag.TutorialTag;

class EditTutTagListCommandTest {
    static final String TAGNAME_WED10 = "WED10";
    static final String TAGNAME_THU10 = "THU10";

    @Test
    public void execute_tutorialTagAcceptedByModel_addSuccessful() throws Exception {
        EditTutTagListCommandTest.ModelStubAcceptingTutorialTagAdded modelStub =
                new EditTutTagListCommandTest.ModelStubAcceptingTutorialTagAdded();

        new EditTutTagListCommand(TAGNAME_WED10, true)
                .execute(modelStub);

        assertEquals(Arrays.asList(WED10), modelStub.tutorialTagAdded);
    }

    @Test
    public void execute_tutorialTagDeletedByModel_addSuccessful() throws Exception {
        EditTutTagListCommandTest.ModelStubAcceptingTutorialTagAdded modelStub =
                new EditTutTagListCommandTest.ModelStubAcceptingTutorialTagAdded();

        modelStub.addTutorialTag(WED10);

        new EditTutTagListCommand(TAGNAME_WED10, false)
                .execute(modelStub);

        assertFalse(modelStub.hasTutorialTag(WED10));
    }

    @Test
    public void execute_tutorialAddDuplicateTutorialTag_addFailed() throws Exception {
        EditTutTagListCommandTest.ModelStubAcceptingTutorialTagAdded modelStub =
                new EditTutTagListCommandTest.ModelStubAcceptingTutorialTagAdded();

        modelStub.addTutorialTag(WED10);

        EditTutTagListCommand command = new EditTutTagListCommand(TAGNAME_WED10, true);

        assertThrows(CommandException.class, () -> command.execute(modelStub));
    }


    @Test
    public void equals() {
        EditTutTagListCommand addWed10Command = new EditTutTagListCommand(TAGNAME_WED10, true);
        EditTutTagListCommand addThu10Command = new EditTutTagListCommand(TAGNAME_THU10, true);

        // same object -> returns true
        assertTrue(addWed10Command.equals(addWed10Command));

        // same values -> returns true
        EditTutTagListCommand addWed10CommandCopy = new EditTutTagListCommand(TAGNAME_WED10, true);
        assertTrue(addWed10Command.equals(addWed10CommandCopy));

        // different types -> returns false
        assertFalse(addWed10Command.equals(1));

        // null -> returns false
        assertFalse(addWed10Command.equals(null));

        // different tutorialTag -> returns false
        assertFalse(addWed10Command.equals(addThu10Command));
    }

    @Test
    public void toStringMethod() {
        EditTutTagListCommand editTutTagListCommand = new EditTutTagListCommand(TAGNAME_WED10, true);
        String expected = EditTutTagListCommand.class.getCanonicalName() + "{tagName=" + TAGNAME_WED10 + "}";
        assertEquals(expected, editTutTagListCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void persistentUpdateFilteredList(List<? extends Predicate<Person>> predicates) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutorialTag(TutorialTag target) {
            throw new AssertionError("This method should not be called.");
        };
        @Override
        public void addTutorialTag(TutorialTag tutorialTag) {
            throw new AssertionError("This method should not be called.");
        };
        @Override
        public boolean hasTutorialTag(TutorialTag tutorialTag) {
            throw new AssertionError("This method should not be called.");
        };
        @Override
        public ObservableList<TutorialTag> getTutorialTagList() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public String getTutorialTagListString() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingTutorialTagAdded extends ModelStub {
        final ArrayList<TutorialTag> tutorialTagAdded = new ArrayList<>();

        @Override
        public boolean hasTutorialTag(TutorialTag tutorialTag) {
            requireNonNull(tutorialTag);
            return tutorialTagAdded.stream().anyMatch(tutorialTag::isSameTutorialTag);
        }

        @Override
        public void addTutorialTag(TutorialTag tutorialTag) {
            requireNonNull(tutorialTag);
            tutorialTagAdded.add(tutorialTag);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ObservableList<TutorialTag> getTutorialTagList() {
            return FXCollections.observableArrayList(tutorialTagAdded);
        };

        @Override
        public void deleteTutorialTag(TutorialTag target) {
            tutorialTagAdded.remove(target);
        };

        @Override
        public String getTutorialTagListString() {
            return tutorialTagAdded.toString();
        }
    }
}
