package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class TutorialTagContainsGroupPredicateTest {

    @Test
    public void equals() {
        TutorialTagContainsGroupPredicate predicate1 = new TutorialTagContainsGroupPredicate("TUES08");
        TutorialTagContainsGroupPredicate predicate2 = new TutorialTagContainsGroupPredicate("TUES09");

        // same object -> returns true
        assertTrue(predicate1.equals(predicate1));

        // same values -> returns true
        TutorialTagContainsGroupPredicate predicate1Copy = new TutorialTagContainsGroupPredicate("TUES08");
        assertTrue(predicate1.equals(predicate1Copy));

        // different types -> returns false
        assertFalse(predicate1.equals(1));

        // null -> returns false
        assertFalse(predicate1.equals(null));

        // different tutorial group -> returns false
        assertFalse(predicate1.equals(predicate2));
    }
}
