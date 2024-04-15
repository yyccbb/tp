package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
public class SampleDataUtilTest {

    @Test
    public void testGetSamplePersons() {
        // Get the sample persons array
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        // Check if the array is not null
        assertNotNull(samplePersons);

        // Check if the length of the array is correct
        assertEquals(5, samplePersons.length);

        // Test individual properties of each person object in the array
        assertPersonEquals(new PersonBuilder().withName("Bernice Yu")
                .withId("A9128392K")
                .withPhone("99272758")
                .withEmail("berniceyu@example.com")
                .withTags()
                .build(), samplePersons[0]);

        assertPersonEquals(new PersonBuilder().withName("Charlotte Oliveiro")
                .withId("A2222222P")
                .withPhone("93210283")
                .withEmail("charlotte@example.com")
                .withTags()
                .build(), samplePersons[1]);

        assertPersonEquals(new PersonBuilder().withName("David Li")
                .withId("A9128392Z")
                .withPhone("91031282")
                .withEmail("lidavid@example.com")
                .withTags()
                .build(), samplePersons[2]);

        assertPersonEquals(new PersonBuilder().withName("Irfan Ibrahim")
                .withId("B0198266Z")
                .withPhone("92492021")
                .withEmail("irfan@example.com")
                .withTags()
                .build(), samplePersons[3]);

        assertPersonEquals(new PersonBuilder().withName("Roy Balakrishnan")
                .withId("B0000666C")
                .withPhone("92624417")
                .withEmail("royb@example.com")
                .withTags()
                .build(), samplePersons[4]);
    }

    // Helper method to assert properties of a person
    private void assertPersonEquals(Person expectedPerson, Person actualPerson) {
        // Check individual properties of the person objects
        assertEquals(expectedPerson.getName().toString(), actualPerson.getName().toString());
        assertEquals(expectedPerson.getId().toString(), actualPerson.getId().toString());
        assertEquals(expectedPerson.getPhone().toString(), actualPerson.getPhone().toString());
        assertEquals(expectedPerson.getEmail().toString(), actualPerson.getEmail().toString());
        assertTrue(actualPerson.getTags().containsAll(expectedPerson.getTags()));
    }
}
