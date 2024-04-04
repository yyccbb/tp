package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_TAGSTATUS_COMPLETE_GOOD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_ASS1_ASS2_TAGSTATUS_COMPLETE_GOOD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_ASS1_MULTIPLE_WHITESPACES_ASS2_TAGSTATUS_COMPLETE_GOOD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_ASS3_TAGSTATUS_COMPLETE_BAD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAGNAMES_SET_ASS1_ASS2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAGNAMES_SET_ASS3;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_1_2_3_SET;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagStatus;

class MarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, TAG_ASS1_ASS2_TAGSTATUS_COMPLETE_GOOD, MESSAGE_INVALID_FORMAT);

        // no tag specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 2", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TAG_ASS1_ASS2_TAGSTATUS_COMPLETE_GOOD,
                MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TAG_ASS1_ASS2_TAGSTATUS_COMPLETE_GOOD,
                MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 /i string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TAG_TAGSTATUS_COMPLETE_GOOD,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_oneIndex_success() throws ParseException {
        final Index targetIndex = INDEX_SECOND_PERSON;
        Set<Index> targetIndexSet = new HashSet<>(Arrays.asList(targetIndex));

        // one tag name
        String userInput = targetIndex.getOneBased() + TAG_ASS3_TAGSTATUS_COMPLETE_BAD;
        MarkCommand expectedCommand =
                new MarkCommand(targetIndexSet, VALID_TAGNAMES_SET_ASS3, TagStatus.COMPLETE_BAD);
        System.out.println(expectedCommand.equals(parser.parse(userInput)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple tag names
        userInput = targetIndex.getOneBased() + TAG_ASS1_ASS2_TAGSTATUS_COMPLETE_GOOD;
        expectedCommand =
                new MarkCommand(targetIndexSet, VALID_TAGNAMES_SET_ASS1_ASS2, TagStatus.COMPLETE_GOOD);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple taq names separated by multiple whitespaces
        userInput = targetIndex.getOneBased() + TAG_ASS1_MULTIPLE_WHITESPACES_ASS2_TAGSTATUS_COMPLETE_GOOD;
        expectedCommand =
                new MarkCommand(targetIndexSet, VALID_TAGNAMES_SET_ASS1_ASS2, TagStatus.COMPLETE_GOOD);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleIndices_success() throws ParseException {
        String targetIndexString = "1 2 3";
        Set<Index> targetIndexSet = INDEX_1_2_3_SET;

        // one tag name
        String userInput = targetIndexString + TAG_ASS3_TAGSTATUS_COMPLETE_BAD;
        MarkCommand expectedCommand =
                new MarkCommand(targetIndexSet, VALID_TAGNAMES_SET_ASS3, TagStatus.COMPLETE_BAD);
        System.out.println(expectedCommand.equals(parser.parse(userInput)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple tag names
        userInput = targetIndexString + TAG_ASS1_ASS2_TAGSTATUS_COMPLETE_GOOD;
        expectedCommand =
                new MarkCommand(targetIndexSet, VALID_TAGNAMES_SET_ASS1_ASS2, TagStatus.COMPLETE_GOOD);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple taq names separated by multiple whitespaces
        userInput = targetIndexString + TAG_ASS1_MULTIPLE_WHITESPACES_ASS2_TAGSTATUS_COMPLETE_GOOD;
        expectedCommand =
                new MarkCommand(targetIndexSet, VALID_TAGNAMES_SET_ASS1_ASS2, TagStatus.COMPLETE_GOOD);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
