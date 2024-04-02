package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EDIT_TUTORIAL_TAG_FLAG;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EDIT_TUTORIAL_TAG_FLAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTutTagListCommand;
import seedu.address.model.tag.Tag;

class EditTutTagListCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTutTagListCommand.MESSAGE_USAGE);

    private EditTutTagListCommandParser parser = new EditTutTagListCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no tagFlag specified
        assertParseFailure(parser, VALID_TAG_FRIEND_DESC, MESSAGE_INVALID_FORMAT);

        // no tagName specified
        assertParseFailure(parser, VALID_EDIT_TUTORIAL_TAG_FLAG, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidFlag_failure() {
        assertParseFailure(parser, INVALID_EDIT_TUTORIAL_TAG_FLAG + " "
                        + TAG_DESC_FRIEND,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_EDIT_TUTORIAL_TAG_FLAG
                        + " " + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validCommand_success() {
        String userInput = VALID_EDIT_TUTORIAL_TAG_FLAG
                + " " + VALID_TAG_FRIEND_DESC;

        EditTutTagListCommand expectedCommand = new EditTutTagListCommand(VALID_TAG_FRIENDS, true);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
