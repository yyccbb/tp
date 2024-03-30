package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // Identity fields
    final PersonType type;
    final Name name;
    final Id id;
    final Phone phone;
    final Email email;

    // Data fields
    final Address address;

    /**
     * Every field must be present and not null.
     */
    Person(PersonType type, Name name, Id id, Phone phone, Email email, Address address) {
        requireAllNonNull(type, name, id, phone, email, address);
        this.type = type;
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Creates either a Student or a TA.
     * All fields are compulsory.
     *
     * @param type
     * @param name
     * @param id
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public static Person of(PersonType type, Name name, Id id, Phone phone, Email email, Address address,
                     Set<Tag> tags) {
        requireAllNonNull(type, name, id, phone, email, address, tags);
        if (type == PersonType.STU) {
            return new Student(name, id, phone, email, address, tags);
        } else {
            return new Ta(name, id, phone, email, address, tags);
        }
    }

    public PersonType getType() {
        return type;
    }
    public Name getName() {
        return name;
    }
    public Id getId() {
        return id;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public abstract Set<Tag> getTags();

    /**
     * Returns true if both persons have the same id.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getId().equals(getId());
    }
}
