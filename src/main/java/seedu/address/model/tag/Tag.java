package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public abstract class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;
    private TagStatus tagStatus;
    private TagType tagType;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    protected Tag(String tagName, TagStatus tagStatus) {
        requireNonNull(tagName);
        // require the tagStatus not to be null for now
        // in the future, a null tagStatus input should be set to INCOMPLETE_GOOD
        // by default
        requireNonNull(tagStatus);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        this.tagStatus = tagStatus;
        this.tagType = getTagTypeWithTagStatus(tagStatus);
    }

    /**
     * Creates a new Tag with specified tagName and tagStatus.
     *
     * @param tagName Name of the tag to be created.
     * @param tagStatus Status of the tag.
     * @return A new tag of specific type corresponding to the TagStatus input.
     */
    public static Tag createTag(String tagName, TagStatus tagStatus) {
        requireNonNull(tagName);
        // require the tagStatus not to be null for now
        // in the future, a null tagStatus input should be set to INCOMPLETE_GOOD
        // by default
        requireNonNull(tagStatus);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        TagType tagType = getTagTypeWithTagStatus(tagStatus);

        switch (tagType) {
        case ASSIGNMENT:
            return new AssignmentTag(tagName, tagStatus);
        case TUTORIAL:
            return new TutorialTag(tagName, tagStatus);
        case ATTENDANCE:
            return new AttendanceTag(tagName, tagStatus);
        default:
            return null;
        }
    }

    public TagStatus getTagStatus() {
        return tagStatus;
    }

    public TagType getTagType() {
        return tagType;
    }

    public static TagType getTagTypeWithTagStatus(TagStatus ts) {
        switch (ts) {
        case COMPLETE_GOOD:
        case COMPLETE_BAD:
        case INCOMPLETE_GOOD:
        case INCOMPLETE_BAD:
            return TagType.ASSIGNMENT;
        case PRESENT:
        case ABSENT:
        case ABSENT_WITH_REASON:
            return TagType.ATTENDANCE;
        case ASSIGNED:
        case AVAILABLE:
            return TagType.TUTORIAL;
        default:
            return TagType.DEFAULT_TYPE;
        }
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return tagName.equals(otherTag.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[ " + tagName + " : " + tagStatus + " ]";
    }

    public static void isTagNameValid(String tagName) throws IllegalArgumentException {
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * @param currTags current tag set to be updated.
     * @param tagName name of the new tag.
     * @param tagStatus tagStatus of the new tag.
     * @return
     */
    public static Set<Tag> updateTagsWithNewTag(Set<Tag> currTags, String tagName, TagStatus tagStatus) {
        // Instead of retrieving the Tag sharing the same name and update it,
        // remove the potentially existing Tag of the same name from the hashset
        // and then add in a new Tag with the same tagName but updated tagStatus.
        // This is to avoid having linearly check through the hashset to retrieve
        // the existing Tag
        Tag newTag = Tag.createTag(tagName, tagStatus);
        currTags.remove(newTag);
        currTags.add(Tag.createTag(tagName, tagStatus));
        return currTags;
    }

    /**
     * @param currTags current tag set to be updated.
     * @param tagName name of the new tag.
     * @return
     */
    public static Set<Tag> deleteTagFromTagSet(Set<Tag> currTags, String tagName) {
        // remove the potentially existing Tag of the same name from the hashset.
        Tag newTag = Tag.createTag(tagName, TagStatus.DEFAULT_STATUS);
        currTags.remove(newTag);
        return currTags;
    }

    public boolean isAttendance() {
        return tagType == TagType.ATTENDANCE;
    }

    public boolean isAssignment() {
        return tagType == TagType.ASSIGNMENT;
    }

    public boolean isTutorial() {
        return tagType == TagType.TUTORIAL;
    }
}