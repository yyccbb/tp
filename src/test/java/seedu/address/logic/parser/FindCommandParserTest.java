package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.FieldContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_findName_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<String> keywords = Arrays.asList("Alice", "Bob");
        FieldContainsKeywordsPredicate[] predicateList = {new FieldContainsKeywordsPredicate(PREFIX_NAME, keywords)};
        FindCommand expectedFindCommand =
                new FindCommand(Arrays.asList(predicateList));
        assertParseSuccess(parser, " /n Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " /n \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_findType_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<String> keywords = Arrays.asList("stu");
        FieldContainsKeywordsPredicate[] predicateList = {new FieldContainsKeywordsPredicate(keywords)};
        FindCommand expectedFindCommand =
                new FindCommand(Arrays.asList(predicateList));
        assertParseSuccess(parser, " stu", expectedFindCommand);

        // multiple whitespaces around keywords
        assertParseSuccess(parser, "      stu   ", expectedFindCommand);
    }

}
