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
public class DeletetagCommand extends Command {

    public static final String COMMAND_WORD = "deletetag";
    public static final String SAMPLE_COMMAND = COMMAND_WORD + " 1 " + PREFIX_TAG + " assignment1 ";
    // to be updated
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the specified tag from the specified"
            + " contact entry.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + " [TAG]\n"
            + "Example: " + SAMPLE_COMMAND;

    public static final String MESSAG_DELETETAG_SUCCESS = "Updated Person: %1$s";
    private final Index index;
    private final String tagName;

    /**
     * @param index of the person in the filtered person list to have the tag deleted.
     * @param tagName name of the tag to be deleted.
     */
    public DeletetagCommand(Index index, String tagName) {
        requireAllNonNull(index, tagName);
        this.index = index;
        this.tagName = tagName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // check whether index specified is within valid range
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> currTags = new HashSet<>(personToEdit.getTags());

        // create a new person with the new tag, necessary as the person fields are currently final
        Person editedPerson = createEditedPerson(personToEdit, Tag.deleteTagFromTagSet(currTags, this.tagName));

        // update the person list and make GUI show all existing person
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAG_DELETETAG_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletetagCommand)) {
            return false;
        }

        // state check
        DeletetagCommand e = (DeletetagCommand) other;
        return index.equals(e.index)
                && tagName.equals(e.tagName);
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
                .add("index", index.toString())
                .add("tagName", tagName).toString();
    }
}
