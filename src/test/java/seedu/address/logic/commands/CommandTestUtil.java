package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAGSTATUS;
import static seedu.address.logic.parser.CliSyntax.TYPE_STU;
import static seedu.address.logic.parser.CliSyntax.TYPE_TA;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.FieldContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TYPE_AMY = "stu";
    public static final String VALID_TYPE_BOB = "ta";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";

    public static final String VALID_NAME_CARL = "Carl Kurz";
    public static final String VALID_ID_AMY = "A1234567Z";
    public static final String VALID_ID_BOB = "A0123456B";

    public static final String VALID_ID_CARL = "A2222222D";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";

    public static final String VALID_PHONE_CARL = "95352563";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";

    public static final String VALID_EMAIL_CARL = "heinz@example.com";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIENDS = "friends";
    public static final String VALID_TAG_ASSIGNMENT1 = "Assignment1";
    public static final String VALID_TAG_ASSIGNMENT2 = "Assignment2";
    public static final String VALID_TAG_ASSIGNMENT3 = "Assignment3";

    public static final String VALID_TAGSTATUS_COMPLETE_GOOD = "cg";
    public static final String VALID_TAGSTATUS_COMPLETE_BAD = "cb";
    public static final String INVALID_EDIT_TUTORIAL_TAG_FLAG = "out";
    public static final String VALID_EDIT_TUTORIAL_TAG_FLAG = "add";
    public static final String TYPE_DESC_AMY = " " + TYPE_STU + " ";
    public static final String TYPE_DESC_BOB = " " + TYPE_TA + " ";
    public static final String TYPE_DESC_CARL = " " + TYPE_STU + " ";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + " " + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + " " + VALID_NAME_BOB;
    public static final String NAME_DESC_CARL = " " + PREFIX_NAME + " " + VALID_NAME_CARL;
    public static final String ID_DESC_AMY = " " + PREFIX_ID + " " + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + " " + VALID_ID_BOB;
    public static final String ID_DESC_CARL = " " + PREFIX_ID + " " + VALID_ID_CARL;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + " " + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + " " + VALID_PHONE_BOB;
    public static final String PHONE_DESC_CARL = " " + PREFIX_PHONE + " " + VALID_PHONE_CARL;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + " " + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + " " + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_CARL = " " + PREFIX_EMAIL + " " + VALID_EMAIL_CARL;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + " " + VALID_TAG_FRIENDS;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + " " + VALID_TAG_HUSBAND;
    public static final String TAG_DESC_ASS1_ASS2 =
            " " + PREFIX_TAG + " " + VALID_TAG_ASSIGNMENT1 + " " + VALID_TAG_ASSIGNMENT2;
    public static final String TAG_DESC_ASS3 = " " + PREFIX_TAG + " " + VALID_TAG_ASSIGNMENT3;
    public static final String TAG_DESC_ASS1_MULTIPLE_WHITESPACES_ASS2 =
            " " + PREFIX_TAG + "   " + VALID_TAG_ASSIGNMENT1 + "     " + VALID_TAG_ASSIGNMENT2;
    public static final String TAGSTATUS_DESC_COMPLETE_GOOD =
            " " + PREFIX_TAGSTATUS + " " + VALID_TAGSTATUS_COMPLETE_GOOD;
    public static final String TAGSTATUS_DESC_COMPLETE_BAD =
            " " + PREFIX_TAGSTATUS + " " + VALID_TAGSTATUS_COMPLETE_BAD;
    public static final String TAG_FRIEND_TAGSTATUS_COMPLETE_GOOD = " " + PREFIX_TAG + " " + VALID_TAG_FRIENDS
            + TAGSTATUS_DESC_COMPLETE_GOOD;
    public static final String TAG_FRIEND_TAGSTATUS_COMPLETE_BAD = " " + PREFIX_TAG + " " + VALID_TAG_FRIENDS
            + TAGSTATUS_DESC_COMPLETE_BAD;
    public static final String TAG_ASS1_ASS2_TAGSTATUS_COMPLETE_GOOD =
            TAG_DESC_ASS1_ASS2 + TAGSTATUS_DESC_COMPLETE_GOOD;
    public static final String TAG_ASS3_TAGSTATUS_COMPLETE_BAD = TAG_DESC_ASS3 + TAGSTATUS_DESC_COMPLETE_BAD;
    public static final String TAG_ASS1_MULTIPLE_WHITESPACES_ASS2_TAGSTATUS_COMPLETE_GOOD =
            TAG_DESC_ASS1_MULTIPLE_WHITESPACES_ASS2 + TAGSTATUS_DESC_COMPLETE_GOOD;
    public static final Set<String> VALID_TAGNAMES_SET_ASS1_ASS2 = new HashSet<>(List.of(VALID_TAG_ASSIGNMENT1,
            VALID_TAG_ASSIGNMENT2));
    public static final Set<String> VALID_TAGNAMES_SET_ASS3 = new HashSet<>(List.of(
            VALID_TAG_ASSIGNMENT3));

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + " " + "James&"; // '&' not allowed in names
    public static final String INVALID_ID_DESC = " " + PREFIX_ID + " " + "123456789";
    // IDs must start and end with a letter
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + " " + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + " " + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + " " + "ass*"; // '*' not allowed in tags
    public static final String VALID_TAG_FRIEND_DESC = " " + PREFIX_TAG + " "
            + VALID_TAG_FRIENDS; // '*' not allowed in tags

    public static final String INVALID_TAG_TAGSTATUS_COMPLETE_GOOD = INVALID_TAG_DESC + " " + PREFIX_TAGSTATUS
            + " " + VALID_TAGSTATUS_COMPLETE_GOOD;
    public static final String VALID_TAG_FRIEND_TAGSTATUS_COMPLETE_GOOD = VALID_TAG_FRIEND_DESC + " "
            + PREFIX_TAGSTATUS + " " + VALID_TAGSTATUS_COMPLETE_GOOD;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new FieldContainsKeywordsPredicate(PREFIX_NAME, Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
