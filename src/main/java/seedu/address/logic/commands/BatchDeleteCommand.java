package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonType;
import seedu.address.model.tag.TutorialTag;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BatchDeleteCommand extends Command {

    public static final String COMMAND_WORD = "batchdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Batch deletes the contacts of the specified type and tutorial tag. \n"
            + "Parameters: TYPE /t TUTORIALTAG \n"
            + "Example: " + COMMAND_WORD + " stu /t TUE08";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s"; // to change

    private final PersonType personType;
    private final TutorialTag tutorialTag;

    /**
     * Constructor for the batch delete command.
     * @param personType Person type specified for the batch delete.
     * @param tutorialTag Tutorial tag specified for the batch delete.
     */
    public BatchDeleteCommand(PersonType personType, TutorialTag tutorialTag) {
        this.needsWarningPopup = true;
        this.personType = personType;
        this.tutorialTag = tutorialTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personType != PersonType.STU && personType != PersonType.TA) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_TYPE);
        } else if (personType == PersonType.TA && tutorialTag != null) {
            throw new CommandException(Messages.MESSAGE_BATCH_DELETE_TA_INVALID_TUTORIALTAG);
        } else if (personType == PersonType.STU && tutorialTag != null) {
            throw new CommandException(Messages.MESSAGE_BATCH_DELETE_TA_INVALID_TUTORIALTAG);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BatchDeleteCommand)) {
            return false;
        }

        BatchDeleteCommand otherBatchDeleteCommand = (BatchDeleteCommand) other;
        return personType.equals(otherBatchDeleteCommand.personType)
                && tutorialTag.equals(otherBatchDeleteCommand.tutorialTag);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("personType", personType)
                .add("tutorialTag", tutorialTag)
                .toString();
    }
}
