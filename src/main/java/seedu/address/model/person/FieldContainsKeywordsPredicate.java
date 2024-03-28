package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FieldContainsKeywordsPredicate implements Predicate<Person> {
    private Prefix prefix;
    private final List<String> keywords;

    public FieldContainsKeywordsPredicate(Prefix prefix, List<String> keywords) {
        this.prefix = prefix;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (this.prefix.equals(PREFIX_NAME)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        } else if (this.prefix.equals(PREFIX_ID)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getId().value, keyword));
        } else if (this.prefix.equals(PREFIX_PHONE)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
        } else if (this.prefix.equals(PREFIX_EMAIL)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().value, keyword));
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FieldContainsKeywordsPredicate)) {
            return false;
        }

        FieldContainsKeywordsPredicate otherFieldContainsKeywordsPredicate = (FieldContainsKeywordsPredicate) other;
        return keywords.equals(otherFieldContainsKeywordsPredicate.keywords);
    }
    //TO UPDATE
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
