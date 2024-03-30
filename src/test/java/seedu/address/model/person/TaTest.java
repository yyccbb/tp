package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.TypicalPersons.FIONA;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class TaTest {

    @Test
    void testEquals() {
        // same value
        Ta fionaCopy = (Ta) new PersonBuilder(FIONA).build();
        assertEquals(fionaCopy, FIONA);

        // same object
        assertEquals(FIONA, FIONA);

        // different name
        Ta editedFiona = (Ta) new PersonBuilder(FIONA).withName(VALID_NAME_BOB).build();
        assertNotEquals(editedFiona, FIONA);
    }

    @Test
    void testToString() {
        String expected =
                Ta.class.getCanonicalName() + "{type=" + FIONA.getType() + ", name=" + FIONA.getName()
                        + ", id=" + FIONA.getId()
                        + ", phone=" + FIONA.getPhone()
                        + ", email=" + FIONA.getEmail()
                        + ", address=" + FIONA.getAddress()
                        + ", tags=" + FIONA.getTags() + "}";
        assertEquals(expected, FIONA.toString());
    }
}
