package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.RemovetagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class RemovetagCommandParser implements Parser<RemovetagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemovetagCommand}
     * and returns a {@code RemovetagCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public RemovetagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Set<Index> indices;
        try {
            indices = StatefulParserUtil.parseIndices(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemovetagCommand.MESSAGE_USAGE), ive);
        }

        if (!StatefulParserUtil.arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemovetagCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TAG);
        String tagNamesString = argMultimap.getValue(PREFIX_TAG).get();
        Set<String> tagNames = StatefulParserUtil.parseTagNamesString(tagNamesString);

        try {
            tagNames.forEach(Tag::isTagNameValid);
            return new RemovetagCommand(indices, tagNames);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }

}
