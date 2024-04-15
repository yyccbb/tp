package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FieldContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons whose contact details contain "
            + "the specified keywords (case-insensitive) under the specified flag and displays them as a list with "
            + "index numbers.\n"
            + "Parameters: [stu | ta] [/n NAME] [/i ID] [/p PHONE] [/e EMAIL]\n"
            + "Example: " + COMMAND_WORD + " stu /n grace /p 900";

    private final List<FieldContainsKeywordsPredicate> predicates;

    public FindCommand(List<FieldContainsKeywordsPredicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.persistentUpdateFilteredList(this.predicates);

        int filteredListSize = model.getFilteredPersonList().size();
        if (filteredListSize <= 1) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSON_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
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
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicates.equals(otherFindCommand.predicates);
    }
    // UPDATE THIS
    @Override
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this);
        for (FieldContainsKeywordsPredicate predicate : predicates) {
            sb.add("predicate", predicate);
        }
        return sb.toString();
    }
}
