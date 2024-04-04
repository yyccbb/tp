package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TutorialTag;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class EditTutTagListCommand extends Command {

    public static final String COMMAND_WORD = "tuttag";
    public static final String ADD_FLAG = "add";
    public static final String DELETE_FLAG = "del";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a TutorialTag to be used with the specified "
            + "TagName.\n"
            + "Parameters: MODE (must be either 'add' for adding tags or 'del' for deleting tags) "
            + PREFIX_TAG + " [TAGNAME]\n"
            + "Example: " + COMMAND_WORD + " " + ADD_FLAG + " " + PREFIX_TAG + " THU10\n";

    public static final String SAMPLE_COMMAND = COMMAND_WORD + " " + ADD_FLAG + " " + PREFIX_TAG + " WED10";
    public static final String MESSAGE_SUCCESS = "New tutorial tag added: %1$s";
    public static final String MESSAGE_DUPLICATE_TUTORIALTAG = "This tutorialTag already exists in the address book";
    private final String tagName;
    private final boolean isAdding;

    /**
     * Creates a new EditTutTagListCommand.
     *
     * @param tagName TagName of the tutorial tag to be added or deleted.
     * @param isAdding whether the command is to add tutorial tag.
     */
    public EditTutTagListCommand(String tagName, boolean isAdding) {
        this.tagName = tagName;
        this.isAdding = isAdding;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (isAdding) {
            model.addTutorialTag(new TutorialTag(tagName, TagStatus.AVAILABLE));
        }
        if (!isAdding) {
            model.deleteTutorialTag(new TutorialTag(tagName, TagStatus.AVAILABLE));
        }
        return new CommandResult(model.getTutorialTagList().toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTutTagListCommand)) {
            return false;
        }

        EditTutTagListCommand otherFindCommand = (EditTutTagListCommand) other;
        return tagName.equals(otherFindCommand.tagName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tagName", tagName)
                .toString();
    }
}
