package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.TagStatus;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withType("stu").withName("Alice Pauline").withId("A0111111A")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withType("stu").withName("Benson Meier").withId("A0111111C")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withType("stu").withName("Carl Kurz").withId("A2222222D")
            .withPhone("95352563").withEmail("heinz@example.com").build();
    public static final Person DANIEL = new PersonBuilder().withType("stu").withName("Daniel Meier")
            .withId("B3334444E").withPhone("87652533").withEmail("cornelia@example.com")
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withType("stu").withName("Elle Meyer")
            .withId("B3334444F").withPhone("9482224").withEmail("werner@example.com")
            .build();
    public static final Person FIONA = new PersonBuilder().withType("ta").withName("Fiona Kunz")
            .withId("B5678012F").withPhone("9482427").withEmail("lydia@example.com")
            .build();
    public static final Person GEORGE = new PersonBuilder().withType("ta").withName("George Best")
            .withId("A0000000X").withPhone("9482442")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withType("stu").withName("Hoon Meier")
            .withId("A0123456W").withPhone("8482424").withEmail("stefan@example.com")
            .build();
    public static final Person IDA = new PersonBuilder().withType("ta").withName("Ida Mueller")
            .withId("P9876543N").withPhone("8482131").withEmail("hans@example.com")
            .build();

    public static final Person JANE = new PersonBuilder().withType("stu").withName("Jane Doe").withId("A0111111B")
            .withPhone("98765432").withEmail("jane@example.com").addTag("W10", TagStatus.getTagStatus("as"))
            .addTag("Assignment1", TagStatus.getTagStatus("cg")).build();

    public static final Person JOHN = new PersonBuilder().withType("ta").withName("John Doe").withId("A0111111D")
            .withPhone("98765432").withEmail("john@example.com").addTag("TUES08", TagStatus.getTagStatus("av"))
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY =

            new PersonBuilder().withType(VALID_TYPE_AMY).withName(VALID_NAME_AMY).withId(VALID_ID_AMY)
                    .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                    .withTags(VALID_TAG_FRIENDS).build();
    public static final Person BOB =
            new PersonBuilder().withType(VALID_TYPE_BOB).withName(VALID_NAME_BOB).withId(VALID_ID_BOB)
                    .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                    .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIENDS).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
