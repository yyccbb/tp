package seedu.address.model.tag;

/**
 * Represents an assignment tag.
 */
public class AssignmentTag extends Tag {
    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName   A valid tag name.
     * @param tagStatus A valid tag status.
     */
    public AssignmentTag(String tagName, TagStatus tagStatus) {
        super(tagName, tagStatus);
    }
}
