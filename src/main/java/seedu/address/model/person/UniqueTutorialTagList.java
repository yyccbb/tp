package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateTutorialTagException;
import seedu.address.model.person.exceptions.TutorialTagNotFoundException;
import seedu.address.model.tag.TutorialTag;


/**
 * A list of tutorial tags that enforces uniqueness between its elements and does not allow nulls.
 * A tutorial tag is considered unique by comparing using {@code TutorialTag#isSameTutorialTag(TutorialTag)}.
 * As such, adding and updating of tutorial tags uses TutorialTag#isSameTutorialTag(TutorialTag) for equality so as to
 * ensure that the tutorial tag being added or updated is unique in terms of identity in the UniqueTutorialTagList.
 * However, the removal of a tutorial tag uses TutorialTag#equals(Object) so as to ensure that the tutorial tag
 * with exactly the same TagName will be removed.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueTutorialTagList implements Iterable<TutorialTag> {
    private final ObservableList<TutorialTag> internalList = FXCollections.observableArrayList();
    private final ObservableList<TutorialTag> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutorial tag as the given argument.
     */
    public boolean contains(TutorialTag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTutorialTag);
    }

    /**
     * Adds a tutorial tag to the list.
     * The tutorial tag must not already exist in the list.
     */
    public void add(TutorialTag toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorialTagException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent tutorial tag from the list.
     * The tutorial tag must exist in the list.
     */
    public void remove(TutorialTag toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TutorialTagNotFoundException();
        }
    }

    public void setTutorialTags(UniqueTutorialTagList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tutorialTags}.
     * {@code tutorialTags} must not contain duplicate tutorial tags.
     */
    public void setTutorialTags(List<TutorialTag> tutorialTags) {
        requireAllNonNull(tutorialTags);
        if (!tutorialTagsAreUnique(tutorialTags)) {
            throw new DuplicateTutorialTagException();
        }

        internalList.setAll(tutorialTags);
    }
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TutorialTag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TutorialTag> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTutorialTagList)) {
            return false;
        }

        UniqueTutorialTagList otherUniqueTutorialTagsList = (UniqueTutorialTagList) other;
        return internalList.equals(otherUniqueTutorialTagsList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code tutorialTags} contains only unique tutorial tags.
     */
    private boolean tutorialTagsAreUnique(List<TutorialTag> tutorialTags) {
        for (int i = 0; i < tutorialTags.size() - 1; i++) {
            for (int j = i + 1; j < tutorialTags.size(); j++) {
                if (tutorialTags.get(i).isSameTutorialTag(tutorialTags.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
