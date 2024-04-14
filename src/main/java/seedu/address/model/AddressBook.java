package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.UniqueTutorialTagList;
import seedu.address.model.tag.TutorialTag;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueTutorialTagList tutorialTags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tutorialTags = new UniqueTutorialTagList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the tutorial tags list with {@code tutorialTags}.
     * {@code tutorialTags} must not contain duplicate tutorial tags.
     */
    public void setTutorialTags(List<TutorialTag> tutorialTags) {
        this.tutorialTags.setTutorialTags(tutorialTags);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTutorialTags(newData.getTutorialTagList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// tutorialTag-level operations

    /**
     */
    public boolean hasTutorialTag(TutorialTag tutorialTag) {
        requireNonNull(tutorialTag);
        return tutorialTags.contains(tutorialTag);
    }

    /**
     * Adds a tutorialTag to the address book.
     * The tutorialTag must not already exist in the address book.
     */
    public void addTutorialTag(TutorialTag t) {
        tutorialTags.add(t);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTutorialTag(TutorialTag key) {
        tutorialTags.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<TutorialTag> getTutorialTagList() {
        return tutorialTags.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons) && tutorialTags.equals(otherAddressBook.tutorialTags);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    public String getTutorialTagListString() {
        StringBuilder sb = new StringBuilder("Available Tutorial Tag(s): [");

        if (!tutorialTags.isEmpty()) {
            for (TutorialTag tag : tutorialTags) {
                sb.append(tag.getTagName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // Remove the last comma and space
        } else {
            return sb.append(" ]").toString();
        }

        sb.append("]");
        return sb.toString();
    }
}
