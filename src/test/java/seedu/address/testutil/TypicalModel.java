package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.JANE;
import static seedu.address.testutil.TypicalPersons.JOHN;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class TypicalModel {
    private static final AddressBook testAb = new AddressBookBuilder().withPerson(JOHN).withPerson(JANE).build();
    public static Model MODEL = new ModelManager(testAb, new UserPrefs());
}
