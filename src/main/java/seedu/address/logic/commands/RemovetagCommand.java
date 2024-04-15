package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
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

/**
 * Changes the remark of an existing person in the address book.
 */
public class RemovetagCommand extends Command {

    public static final String COMMAND_WORD = "removetag";
    public static final String SAMPLE_COMMAND = COMMAND_WORD + " 1 " + PREFIX_TAG + " assignment1 ";
    // to be updated
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the specified tag from the specified"
            + " contact entry.\n"
            + "Parameters: (all | INDEX (must be a positive integer within the size of the displayed list) "
            + "[OTHER_INDICES]) "
            + PREFIX_TAG + " TAG\n"
            + "Example: " + SAMPLE_COMMAND;

    public static final String MESSAG_REMOVETAG_SUCCESS = "Updated Person: %1$s";
    private final Set<Index> indices;
    private final Set<String> tagNames;

    /**
     * @param indices of the person in the filtered person list to have the tag deleted.
     * @param tagNames name of the tag to be deleted.
     */
    public RemovetagCommand(Set<Index> indices, Set<String> tagNames) {
        requireAllNonNull(indices, tagNames);
        this.indices = indices;
        this.tagNames = tagNames;
    }

    /**
     * Removes the specified tags of a single {@code Person}.
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
        Person editedPerson = createEditedPerson(personToEdit, Tag.removeTagsFromTagSet(currTags,
                this.tagNames));

        model.setPerson(personToEdit, editedPerson);

        return new StringBuilder()
                .append(String.format(MESSAG_REMOVETAG_SUCCESS, Messages.format(editedPerson)))
                .append("\n");
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // need not check for duplicate indices because a Set is used

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
        if (!(other instanceof RemovetagCommand)) {
            return false;
        }

        // state check
        RemovetagCommand e = (RemovetagCommand) other;
        return indices.equals(e.indices)
                && tagNames.equals(e.tagNames);
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
                .add("tagName(s)", tagNames).toString();
    }
}
