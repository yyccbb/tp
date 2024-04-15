package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person(s) identified by the index number used in the displayed person list.\n"
            + "Parameters: (all | INDEX (must be a positive integer within the size of the displayed list) " +
            "[OTHER_INDICES]) \n"
            + "Example 1: " + COMMAND_WORD + " 1\n"
            + "Example 2: " + COMMAND_WORD + " 1 5 3";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Set<Index> indices;

    /**
     * Constructor for the delete command.
     * @param indices set of indices whose contact entry are to be deleted.
     */
    public DeleteCommand(Set<Index> indices) {
        requireNonNull(indices);
        this.needsWarningPopup = true;
        this.indices = indices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // need not check for duplicate indices because a Set is used
        List<Person> lastShownList = new ArrayList<>(model.getFilteredPersonList());

        StringBuilder resultStringBuilder;

        checksInvalidIndices(lastShownList, indices);
        try {
            resultStringBuilder = indices.stream().map(x -> {
                try {
                    return executeHelper(model, lastShownList, x);
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

        resultStringBuilder.deleteCharAt(resultStringBuilder.length() - 1); // remove the last '\n'
        return new CommandResult(resultStringBuilder.toString());
    }
    private void checksInvalidIndices(List<Person> lastShownList, Set<Index> indices) throws CommandException {
        // check whether index specified is within valid range
        List<Integer> invalidIndices = new ArrayList<>();
        for (Index index: indices) {
            if (index.getZeroBased() >= lastShownList.size()) {
                invalidIndices.add(index.getOneBased());
            }
        }
        if (invalidIndices.size() > 0) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, invalidIndices));
        }
    }

    /**
     * Deletes a single {@code Person}. Index is guaranteed to be a valid index.
     */
    private StringBuilder executeHelper(Model model, List<Person> lastShownList, Index index) throws CommandException {
        requireAllNonNull(model, lastShownList, index);

        Person personToDelete = lastShownList.get(index.getZeroBased());
        model.deletePerson(personToDelete);

        return new StringBuilder()
                .append(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)))
                .append("\n");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return indices.equals(otherDeleteCommand.indices);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", indices.toString())
                .toString();
    }
}
