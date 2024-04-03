package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.JANE;
import static seedu.address.testutil.TypicalPersons.JOHN;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * A utility class containing a {@code Model} object to be used in tests.
 */
public class TypicalModel {
    public static final AddressBook AB = new AddressBookBuilder().withPerson(JOHN).withPerson(JANE).build();
    public static final Model MODEL = new ModelManager(AB, new UserPrefs());
}
