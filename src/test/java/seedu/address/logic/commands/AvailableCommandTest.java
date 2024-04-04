package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalModel.MODEL;
import static seedu.address.testutil.TypicalTutorialTag.THU10;
import static seedu.address.testutil.TypicalTutorialTag.WED10;
import static seedu.address.testutil.TypicalValidTutorialTagList.VALID_TUTORIAL_TAG_LIST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.TutorialTagContainsGroupPredicate;
import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TutorialTag;

public class AvailableCommandTest {

    @Test
    public void equals() {
        TutorialTagContainsGroupPredicate predicate1 = new TutorialTagContainsGroupPredicate("WED10");
        TutorialTagContainsGroupPredicate predicate2 = new TutorialTagContainsGroupPredicate("THU10");
        AvailableCommand command1 = new AvailableCommand(predicate1, WED10);
        AvailableCommand command2 = new AvailableCommand(predicate2, THU10);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        AvailableCommand command1Copy = new AvailableCommand(predicate1, WED10);
        assertTrue(command1.equals(command1Copy));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different command -> returns false
        assertFalse(command1.equals(command2));
    }

    @Test
    public void execute_validTutorialTag_success() throws CommandException {
        Model model = MODEL;
        StringUtil testStringUtil = new StringUtil(VALID_TUTORIAL_TAG_LIST);
        TutorialTagContainsGroupPredicate predicate = new TutorialTagContainsGroupPredicate("WED10");
        AvailableCommand command = new AvailableCommand(predicate, WED10);

        CommandResult commandResult = command.execute(model);

        assertEquals("0 TAs available for the tutorial group.", commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidTutorialTag_throwsCommandException() {
        Model model = new ModelManager();
        TutorialTagContainsGroupPredicate predicate = new TutorialTagContainsGroupPredicate("TUES08");
        AvailableCommand command = new AvailableCommand(predicate, new TutorialTag("TUES08", TagStatus.ASSIGNED));

        try {
            command.execute(model);
        } catch (CommandException e) {
            assertEquals("Specified tutorial tag name is not allowed: TUES08", e.getMessage());
        }
    }
}
