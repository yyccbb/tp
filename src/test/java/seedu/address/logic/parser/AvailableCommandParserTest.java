package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTutorialTag.TUES08;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AvailableCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TutorialTagContainsGroupPredicate;

public class AvailableCommandParserTest {

    private final AvailableCommandParser parser = new AvailableCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AvailableCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_validArgs_returnsAvailableCommand() throws ParseException {
        String args = " available " + PREFIX_GROUP + " TUES08 ";
        AvailableCommand expectedAvailableCommand = new AvailableCommand(
                new TutorialTagContainsGroupPredicate("TUES08"), TUES08);

        assertParseSuccess(parser, args, expectedAvailableCommand);
    }
}
