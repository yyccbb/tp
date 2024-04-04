package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all available tutorial tags in the address book to the user.
 */
public class ListTutTagCommand extends Command {

    public static final String COMMAND_WORD = "listtuttag";
    public static final String EMPTY_TUTORIALTAGLIST_OUTPUT = "Available Tutorial Tags: [ ]";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(model.getTutorialTagListString());
    }
}
