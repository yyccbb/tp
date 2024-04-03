package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FieldContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonType;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TutorialTag;

/**
 * Batch deletes contacts from address book.
 */
public class BatchDeleteCommand extends Command {

    public static final String COMMAND_WORD = "batchdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Batch deletes the contacts of the specified type and tutorial tag. \n"
            + "Parameters: TYPE /t TUTORIALTAG \n"
            + "Example: " + COMMAND_WORD + " stu /t TUE08";

    public static final String MESSAGE_BATCH_DELETE_PERSON_SUCCESS = "Batch delete %1$s"; // to change
    public static final String MESSAGE_BATCH_DELETE_SUFFIX = "from %1$s";

    private final PersonType personType;
    private final TutorialTag tutorialTag;

    /**
     * Constructor for the batch delete command.
     * @param personType Person type specified for the batch delete.
     * @param tag Tag specified for the batch delete.
     */
    public BatchDeleteCommand(PersonType personType, Tag tag) throws CommandException {
        this.needsWarningPopup = true;
        this.personType = personType;
        if (tag != null) {
            System.out.println(personType);
            System.out.println(tag);
            if (!tag.isTutorial()) {
                throw new CommandException(Messages.MESSAGE_BATCH_DELETE_INVALID_TAG);
            } else {
                this.tutorialTag = (TutorialTag) tag;
            }
        } else {
            this.tutorialTag = null;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (personType != PersonType.STU && personType != PersonType.TA) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_TYPE);
        } else if (personType == PersonType.TA && tutorialTag != null) {
            throw new CommandException(Messages.MESSAGE_BATCH_DELETE_TA_TUTORIALTAG);
        } else if (personType == PersonType.STU && !model.hasTutorialTag(tutorialTag)) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIALTAG);
        }

        if (personType == PersonType.STU) {
            String[] tutorialTagKeywordArr = {tutorialTag.getTagName()};
            List<String> tutorialTagKeywordList = Arrays.asList(tutorialTagKeywordArr);

            String[] personTypeArr = {personType.toString()};
            List<String> personTypeList = Arrays.asList(personTypeArr);

            FieldContainsKeywordsPredicate[] predicateArr = {new FieldContainsKeywordsPredicate(personTypeList),
                new FieldContainsKeywordsPredicate(PREFIX_TAG, tutorialTagKeywordList)};
            List<FieldContainsKeywordsPredicate> predicateList = Arrays.asList(predicateArr);

            FindCommand findCommand = new FindCommand(predicateList);
            findCommand.execute(model);
            for (Person person : model.getFilteredPersonList()) {
                model.deletePerson(person);
            }
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            return new CommandResult(String.format(MESSAGE_BATCH_DELETE_PERSON_SUCCESS + MESSAGE_BATCH_DELETE_SUFFIX,
                    "students", tutorialTag));
        } else {
            String[] personTypeArr = {personType.toString()};
            List<String> personTypeList = Arrays.asList(personTypeArr);

            FieldContainsKeywordsPredicate[] predicateArr = {new FieldContainsKeywordsPredicate(personTypeList)};
            List<FieldContainsKeywordsPredicate> predicateList = Arrays.asList(predicateArr);

            FindCommand findCommand = new FindCommand(predicateList);
            findCommand.execute(model);
            ObservableList<Person> matchingList = FXCollections.observableArrayList(model.getFilteredPersonList());
            System.out.println(matchingList);
            for (Person person : matchingList) {
                model.deletePerson(person);
                System.out.println("Deleted");
                System.out.println(person);
            }
            System.out.println("HII");
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            return new CommandResult(String.format(MESSAGE_BATCH_DELETE_PERSON_SUCCESS, "TAs"));
        }
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
