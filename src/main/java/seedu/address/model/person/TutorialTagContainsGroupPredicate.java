package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;


/**
 * Tests that a {@code TA}'s {@code Tag} matches any of the tutorial groups given.
 */
public class TutorialTagContainsGroupPredicate implements Predicate<Person> {
    private final String tutorialGroup;

    public TutorialTagContainsGroupPredicate(String tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream()
                .anyMatch(tag -> StringUtil.containsTutorialGroup(tag, tutorialGroup));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorialTagContainsGroupPredicate)) {
            return false;
        }

        TutorialTagContainsGroupPredicate otherNameContainsKeywordsPredicate
                = (TutorialTagContainsGroupPredicate) other;
        return tutorialGroup.equals(otherNameContainsKeywordsPredicate.tutorialGroup);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tutorialGroup", tutorialGroup).toString();
    }
}