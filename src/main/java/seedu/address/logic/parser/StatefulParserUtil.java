package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StatefulStringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditTutTagListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagStatus;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class StatefulParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is out of bounds or not an unsigned positive integer.";
    private static StatefulParserUtil instance = null;
    private Model model;
    private StatefulParserUtil(Model model) {
        this.model = model;
    }

    /**
     * Initializes the {@code StatefulParserUtil} singleton with a model.
     */
    public static void initialize(Model model) {
        if (instance == null) {
            instance = new StatefulParserUtil(model);
        }
    }

    /**
     * Get the instance of the {@code StatefulParserUtil} singleton.
     */
    public static StatefulParserUtil getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance not initialized yet.");
        }
        return instance;
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StatefulStringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        int intIndex = Integer.parseInt(trimmedIndex);
        Model model = StatefulParserUtil.getInstance().model;
        if (intIndex > model.getFilteredPersonList().size()) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(intIndex);
    }

    /**
     * Parses a {@code String} consisting of indices into a {@code Set<Index>}.
     *
     * @throws ParseException if any substring is not a valid index.
     */
    public static Set<Index> parseIndices(String oneBasedIndices) throws ParseException {
        requireNonNull(oneBasedIndices);
        String trimmedOneBasedIndices = oneBasedIndices.trim();

        Set<Index> parsedIndices = new HashSet<>();
        List<String> indicesList;
        if (trimmedOneBasedIndices.equals("all")) {
            Model model = StatefulParserUtil.getInstance().model;
            int size = model.getFilteredPersonList().size();
            indicesList = Stream.iterate(1, x -> x + 1)
                    .limit(size)
                    .map(Objects::toString)
                    .collect(Collectors.toList());
        } else {
            indicesList = Arrays.asList(trimmedOneBasedIndices.split("\\s+"));
        }

        // Java Lambda expressions do not allow propagating of checked exceptions.
        // To circumvent this, wrap the checked exception in an unchecked exception.
        try {
            parsedIndices = indicesList.stream().map(x -> {
                try {
                    return parseIndex(x);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toSet());
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ParseException) {
                throw (ParseException) cause;
            } else {
                throw e;
            }
        }

        return parsedIndices;
    }

    /**
     * Parses a {@code String type} into a {@code PersonType}.
     * Leading and trailing whitespaces will be trimmed.
     * An unspecified person type will default to student.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static PersonType parsePersonType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        if (!PersonType.isValidPersonType(trimmedType) && !trimmedType.isEmpty()) {
            throw new ParseException(PersonType.MESSAGE_CONSTRAINTS);
        }
        return PersonType.getPersonType(trimmedType);
    }

    /**
     * Parses a {@code String type} into a {@code PersonType} for the find command.
     * Leading and trailing whitespaces will be trimmed.
     * An unspecified person type will return null.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static PersonType parseFindPersonType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        if (!PersonType.isValidPersonType(trimmedType) && !trimmedType.isEmpty()) {
            throw new ParseException(PersonType.MESSAGE_CONSTRAINTS);
        } else if (trimmedType.isEmpty()) {
            return null;
        } else {
            return PersonType.getPersonType(trimmedType);
        }
    }


    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String id} into a {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static Id parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!Id.isValidId(trimmedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(trimmedId);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return Tag.createTag(trimmedTag, TagStatus.DEFAULT_STATUS);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String} of tag names separated by whitespaces
     * into a {@code Set<String>}, where each element corresponds to a
     * trimmed tag name.
     */
    public static Set<String> parseTagNamesString(String tagNames) {
        requireNonNull(tagNames);
        final Set<String> tagNamesSet = new HashSet<>();
        tagNamesSet.addAll(Arrays.asList(tagNames.split("\\s+")));
        return tagNamesSet;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * @param flag Command flag on whether the EditTutTagListCommand
     * @throws ParseException
     */
    public static EditTutTagListCommand.CommandSubtype isCreatingNewTag(String flag) throws ParseException {
        if (flag.equals(EditTutTagListCommand.ADD_FLAG)) {
            return EditTutTagListCommand.CommandSubtype.ADD;
        }

        if (flag.equals(EditTutTagListCommand.DELETE_FLAG)) {
            return EditTutTagListCommand.CommandSubtype.DELETE;
        }

        if (flag.equals(EditTutTagListCommand.LIST_FLAG)) {
            return EditTutTagListCommand.CommandSubtype.LIST;
        }

        throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }

}
