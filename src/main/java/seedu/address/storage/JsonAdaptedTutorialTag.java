package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.TagStatus;
import seedu.address.model.tag.TutorialTag;

/**
 * Jackson-friendly version of {@link TutorialTag}.
 */
class JsonAdaptedTutorialTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTutorialTag(@JsonProperty("tagName") String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code TutorialTag} into this class for Jackson use.
     */
    public JsonAdaptedTutorialTag(TutorialTag source) {
        tagName = source.tagName;
    }

    @JsonProperty("tagName")
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tutorialTag object into the model's {@code TutorialTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutorialTag.
     */
    public TutorialTag toModelType() throws IllegalValueException {
        if (!TutorialTag.isValidTagName(tagName)) {
            throw new IllegalValueException(TutorialTag.MESSAGE_CONSTRAINTS);
        }
        return new TutorialTag(tagName, TagStatus.AVAILABLE);
    }

}
