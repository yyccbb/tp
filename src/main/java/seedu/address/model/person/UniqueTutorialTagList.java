package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateTutorialTagException;
import seedu.address.model.person.exceptions.TutorialTagNotFoundException;
import seedu.address.model.tag.TutorialTag;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueTutorialTagList implements Iterable<TutorialTag> {
    private final ObservableList<TutorialTag> internalList = FXCollections.observableArrayList();
    private final ObservableList<TutorialTag> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent TutorialTag as the given argument.
     */
    public boolean contains(TutorialTag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTutorialTag);
    }

    /**
     * Adds a TutorialTag to the list.
     * The TutorialTag must not already exist in the list.
     */
    public void add(TutorialTag toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorialTagException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent TutorialTag from the list.
     * The TutorialTag must exist in the list.
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
     * {@code tutorialTags} must not contain duplicate tutorialTags.
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
     * Returns true if {@code tutorialTags} contains only unique tutorialTags.
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
