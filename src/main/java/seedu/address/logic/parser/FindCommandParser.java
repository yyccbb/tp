package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.util.StringListUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FieldContainsKeywordsPredicate;
import seedu.address.model.person.PersonType;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_TAG);

        List<FieldContainsKeywordsPredicate> predicates = new ArrayList<>();

        PersonType type = ParserUtil.parseFindPersonType(argMultimap.getPreamble());
        if (type != null) {
            List<String> separated = Arrays.asList(type.toString().split("\\s+"));
            predicates.add(new FieldContainsKeywordsPredicate(separated));
        }

        List<Prefix> allPrefixes = Arrays.asList(PREFIX_NAME, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);
        for (Prefix prefix: allPrefixes) {
            if (ParserUtil.arePrefixesPresent(argMultimap, prefix)) {
                List<String> keywords = argMultimap.getAllValues(prefix);
                List<String> separated = StringListUtil.separateWithSpaces(keywords);
                predicates.add(new FieldContainsKeywordsPredicate(prefix, separated));
            }
        }

        return new FindCommand(predicates);
    }
}
