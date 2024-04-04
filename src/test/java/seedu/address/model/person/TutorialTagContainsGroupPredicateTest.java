package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.JOHN;
import static seedu.address.testutil.TypicalValidTutorialTagList.VALID_TUTORIAL_TAG_LIST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.StringUtil;

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

    @Test
    public void test_test() {
        StringUtil testStringUtil = new StringUtil(VALID_TUTORIAL_TAG_LIST);
        TutorialTagContainsGroupPredicate predicate = new TutorialTagContainsGroupPredicate("TUES08");

        // Person with matching tutorial group
        assertTrue(predicate.test(JOHN));

        // Person without matching tutorial group
        assertFalse(predicate.test(ALICE));
    }
}

