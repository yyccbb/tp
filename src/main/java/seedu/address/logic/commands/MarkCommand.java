package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGSTATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TagType;
import seedu.address.model.tag.TutorialTag;

/**
 * Changes the remark of an existing person in the address book.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String SAMPLE_COMMAND = COMMAND_WORD + " 1 " + PREFIX_TAG
            + " assignment1 " + PREFIX_TAGSTATUS + " cg";
    public static final String SAMPLE_COMMAND_2 = COMMAND_WORD + " 1 2 " + PREFIX_TAG
            + " assignment2 assignment3 " + PREFIX_TAGSTATUS + " ig";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the status of the specified tag(s) "
            + "of specified contact entry(s) with the specified status.\n"
            + "If the tag(s) specified does not exist, a new tag with the tag name"
            + " and tag status would be created.\n"
            + "Parameters: INDEX (must be a positive integer within the size of the displayed list) "
            + "[OTHER_INDICES} "
            + PREFIX_TAG + " TAG_NAME [OTHER_TAG_NAMES] " + PREFIX_TAGSTATUS + " TAG_STATUS\n"
            + "Example 1: " + SAMPLE_COMMAND + "\n"
            + "Example 2: " + SAMPLE_COMMAND_2;

    public static final String MESSAGE_MARK_PERSON_SUCCESS = "Updated Person: %1$s";
    private final Set<Index> indices;
    private final Set<String> tagNames;
    private final TagStatus tagStatus;

    /**
     * @param indices of the person in the filtered person list to update tag status
     * @param tagNames name of the tag whose status is to be updated
     * @param tagStatus the status to update the specified tag with
     */
    public MarkCommand(Set<Index> indices, Set<String> tagNames, TagStatus tagStatus) {
        requireAllNonNull(indices, tagNames, tagStatus);
        this.indices = indices;
        this.tagNames = tagNames;
        this.tagStatus = tagStatus;
    }

    /**
     * Edit the tags of a single {@code Person}.
     */
    private StringBuilder executeHelper(Model model, Index index) throws CommandException {
        requireAllNonNull(model, index);
        List<Person> lastShownList = model.getFilteredPersonList();

        // check whether index specified is within valid range
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> currTags = new HashSet<>(personToEdit.getTags());

        // create a new person with the new tag(s), necessary as the person fields are final
        Person editedPerson = createEditedPerson(personToEdit, Tag.updateTagsWithNewTags(currTags,
                this.tagNames, this.tagStatus));

        model.setPerson(personToEdit, editedPerson);

        return new StringBuilder()
                .append(String.format(MESSAGE_MARK_PERSON_SUCCESS, Messages.format(editedPerson)))
                .append("\n");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // need not check for duplicate indices because a Set is used

        // only tutorial tags with predefined tag names are allowed
        if (Tag.getTagTypeWithTagStatus(tagStatus) == TagType.TUTORIAL) {
            for (String tagName: tagNames) {
                TutorialTag tag = new TutorialTag(tagName, TagStatus.AVAILABLE);
                if (!model.hasTutorialTag(tag)) {
                    throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_TAG_VALUE + tagName);
                }
            }
        }

        StringBuilder resultStringBuilder;
        try {
            resultStringBuilder = indices.stream().map(x -> {
                try {
                    return executeHelper(model, x);
                } catch (CommandException e) {
                    throw new RuntimeException(e);
                }
            }).reduce(StringBuilder::append).orElse(new StringBuilder());
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            if (cause instanceof CommandException) {
                throw (CommandException) cause;
            } else {
                throw e;
            }
        }
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        resultStringBuilder.deleteCharAt(resultStringBuilder.length() - 1); // remove the last '\n'
        return new CommandResult(resultStringBuilder.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return indices.equals(e.indices)
                && tagNames.equals(e.tagNames)
                && tagStatus.equals(e.tagStatus);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     */
    private static Person createEditedPerson(Person personToEdit, Set<Tag> newTags) {
        assert personToEdit != null;
        return Person.of(personToEdit.getType(), personToEdit.getName(), personToEdit.getId(),
                personToEdit.getPhone(), personToEdit.getEmail(), newTags);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", indices.toString())
                .add("tagName(s)", tagNames)
                .add("tagStatus", tagStatus)
                .toString();
    }
}
