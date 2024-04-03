package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.BatchDeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonType;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new BatchDeleteCommand object
 */
public class BatchDeleteCommandParser implements Parser<BatchDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BatchDeleteCommand
     * and returns a BatchDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BatchDeleteCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_TAG);
            PersonType personType = ParserUtil.parsePersonType(args);
            Tag tag = null;
            if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_TAG)) {
                tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
            }
            return new BatchDeleteCommand(personType, tag);
        } catch (ParseException | CommandException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
