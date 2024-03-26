package seedu.address.model.tag;

public class AttendanceTag extends Tag {
    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName   A valid tag name.
     * @param tagStatus A valid tag status.
     */
    public AttendanceTag(String tagName, TagStatus tagStatus) {
        super(tagName, tagStatus);
    }
}