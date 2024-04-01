package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGSTATUS;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FieldContainsKeywordsPredicate;
import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TutorialTag;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class CreateTutTagCommand extends Command {

    public static final String COMMAND_WORD = "createtuttag";
    public static final String ADD_FLAG = "-a";
    public static final String DELETE_FLAG = "-d";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a TutorialTag to be used with the specified "
            + "TagName.\n"
            + "Parameters: MODE (must be either '-a' for adding tags and '-d' for deleting tags) "
            + PREFIX_TAG + " [TAGNAME]\n"
            + "Example: " + COMMAND_WORD + " " + ADD_FLAG + " " + PREFIX_TAG + " THU10\n";

    private final String tagName;
    private final boolean isAdding;

    public CreateTutTagCommand(String tagName, boolean isAdding) {
        this.tagName = tagName;
        this.isAdding = isAdding;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (isAdding) {
            model.addTutorialTag(new TutorialTag(tagName, TagStatus.AVAILABLE));
        } else {
            model.deleteTutorialTag(new TutorialTag(tagName, TagStatus.AVAILABLE));
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateTutTagCommand)) {
            return false;
        }

        CreateTutTagCommand otherFindCommand = (CreateTutTagCommand) other;
        return tagName.equals(otherFindCommand.tagName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tagName", tagName)
                .toString();
    }
}
