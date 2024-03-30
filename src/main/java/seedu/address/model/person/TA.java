package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TA extends Person{
    private final Set<Tag> tags = new HashSet<>();
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param id
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public TA(Name name, Id id, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(PersonType.TA, name, id, phone, email, address);
        this.tags.addAll(tags);
    }

    @Override
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TA)) {
            return false;
        }

        TA otherTA = (TA) other;
        return name.equals(otherTA.name)
                && type.equals(otherTA.type)
                && id.equals(otherTA.id)
                && phone.equals(otherTA.phone)
                && email.equals(otherTA.email)
                && address.equals(otherTA.address)
                && tags.equals(otherTA.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(type, name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("type", type)
                .add("name", name)
                .add("id", id)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }
}
