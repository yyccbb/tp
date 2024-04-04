package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class ListTutTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_listIsFiltered_showsEverything() {
        ListTutTagCommand command = new ListTutTagCommand();
        CommandResult result = command.execute(model);
        CommandResult expectedResult = new CommandResult(ListTutTagCommand.EMPTY_TUTORIALTAGLIST_OUTPUT);
        assertEquals(expectedResult, result);
    }
}
