package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGSTATUS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.StringListUtil;
import seedu.address.logic.commands.CreateTutTagCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FieldContainsKeywordsPredicate;
import seedu.address.model.person.PersonType;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagStatus;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class CreateTutTagCommandParser implements Parser<CreateTutTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateTutTagCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTutTagCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CreateTutTagCommand.MESSAGE_USAGE));
        }

        String commandFlag = argMultimap.getPreamble();
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TAG);
        String tagName = argMultimap.getValue(PREFIX_TAG).get();

        try {
            boolean isCreatingNewFlag = ParserUtil.isCreatingNewTag(commandFlag);
            Tag.isTagNameValid(tagName);
            return new CreateTutTagCommand(tagName, isCreatingNewFlag);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }
}
