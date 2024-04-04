package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TutorialTag;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class EditTutTagListCommand extends Command {

    /**
     * Represents the subtype of the EditTutTagListCommand. ADD represents command to add an available
     * tutorial tag, DELETE represents command to delete an available tutorial tag and LIST represents
     * command to list all available tutorial tags.
     */
    public enum CommandSubtype { ADD, DELETE, LIST };

    public static final String COMMAND_WORD = "tuttag";
    public static final String ADD_FLAG = "add";
    public static final String DELETE_FLAG = "del";
    public static final String LIST_FLAG = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a TutorialTag to be used with the specified "
            + "TagName.\n"
            + "Parameters: MODE (must be either 'add' for adding tags or 'del' for deleting tags) "
            + PREFIX_TAG + " [TAGNAME]\n"
            + "Example: " + COMMAND_WORD + " " + ADD_FLAG + " " + PREFIX_TAG + " THU10\n";

    public static final String SAMPLE_COMMAND = COMMAND_WORD + " " + ADD_FLAG + " " + PREFIX_TAG + " WED10";
    public static final String MESSAGE_SUCCESS = "New tutorial tag added: %1$s";
    public static final String MESSAGE_DUPLICATE_TUTORIALTAG = "This tutorial tag already exists in the address book";
    public static final String EMPTY_TUTORIALTAGLIST_OUTPUT = "Available Tutorial Tag(s): [ ]";
    private final String tagName;
    private final CommandSubtype commandType;

    /**
     * Creates a new EditTutTagListCommand.
     *
     * @param tagName TagName of the tutorial tag to be added or deleted.
     * @param commandType indicates the command subtype.
     */
    public EditTutTagListCommand(String tagName, CommandSubtype commandType) {
        this.tagName = tagName;
        this.commandType = commandType;
    }

    /**
     * Creates a new EditTutTagListCommand.
     *
     * @param commandType indicates the command subtype.
     */
    public EditTutTagListCommand(CommandSubtype commandType) {
        this.commandType = commandType;
        this.tagName = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (commandType == CommandSubtype.ADD) {
            TutorialTag tag = new TutorialTag(tagName, TagStatus.AVAILABLE);
            if (model.hasTutorialTag(tag)) {
                throw new CommandException(MESSAGE_DUPLICATE_TUTORIALTAG);
            }
            model.addTutorialTag(tag);
        }
        if (commandType == CommandSubtype.DELETE) {
            model.deleteTutorialTag(new TutorialTag(tagName, TagStatus.AVAILABLE));
        }

        if (commandType == CommandSubtype.DELETE) {
            // nothing needs to be done
        }

        return new CommandResult(model.getTutorialTagListString());
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

    public static boolean isListCommand(CommandSubtype type) {
        return type == CommandSubtype.LIST;
    }
}
