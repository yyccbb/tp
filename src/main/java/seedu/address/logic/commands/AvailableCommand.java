package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.TutorialTagContainsGroupPredicate;
import seedu.address.model.tag.TutorialTag;

/**
 * Finds and lists all available TAs for the tutorial group.
 */
public class AvailableCommand extends Command {
    public static final String COMMAND_WORD = "available";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all available TAs for the tutorial slot.\n"
            + "Parameters: [FLAG] [TUTORIAL] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + " TUES08 ";
    public static final String SAMPLE_COMMAND = COMMAND_WORD + " " + PREFIX_GROUP + " WED10 ";

    private final TutorialTagContainsGroupPredicate predicate;
    private final TutorialTag tutorialGroup;

    /**
     * Creates an AvailableCommand to find all available TAs for the tutorial group.
     */
    public AvailableCommand(TutorialTagContainsGroupPredicate predicate, TutorialTag tutorialGroup) {
        this.predicate = predicate;
        this.tutorialGroup = tutorialGroup;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTutorialTag(tutorialGroup)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_TAG_VALUE + tutorialGroup.getTagName());
        }

        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_AVAILABLE_TAS_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AvailableCommand)) {
            return false;
        }

        AvailableCommand otherAvailableCommand = (AvailableCommand) other;
        return predicate.equals(otherAvailableCommand.predicate);
    }
}
