package seedu.address.logic.parser;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.Optional;

import seedu.address.logic.commands.AvailableCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TutorialTagContainsGroupPredicate;
import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TutorialTag;

/**
 * Parses input arguments and creates a new AvailableCommand object
 */
public class AvailableCommandParser implements Parser<AvailableCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AvailableCommand
     * and returns an AvailableCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AvailableCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AvailableCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        Optional<String> group = argMultimap.getValue(PREFIX_GROUP);

        try {
            checkArgument(!group.isEmpty(), String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AvailableCommand.MESSAGE_USAGE));
            checkArgument(!group.get().equals(""), AvailableCommand.MESSAGE_NON_EMPTY_GROUP_NAME);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }

        String tutorialGroup = argMultimap.getValue(PREFIX_GROUP).get();

        TutorialTag tutorialTag = new TutorialTag(tutorialGroup, TagStatus.AVAILABLE);

        return new AvailableCommand(new TutorialTagContainsGroupPredicate(tutorialGroup), tutorialTag);
    }
}
