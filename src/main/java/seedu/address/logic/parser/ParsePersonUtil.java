package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubHandle;
import seedu.address.model.person.Phone;

/**
 * Contains utility methods used for parsing strings in NewTeammate Parser class.
 */
public class ParsePersonUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses a {@code String githubHandle} into a {@code GithubHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code githubHandle} is invalid.
     */
    public static GithubHandle parseGithubHandle(String githubHandle) throws ParseException {
        requireNonNull(githubHandle);
        String trimmedGithubHandle = githubHandle.trim();
        if (!GithubHandle.isValidGithubHandle(githubHandle)) {
            throw new ParseException(GithubHandle.MESSAGE_CONSTRAINTS);
        }
        return new GithubHandle(trimmedGithubHandle);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedphone = phone.trim();
        if (!Phone.isValidPhone(phone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedphone);
    }

    /**
     * Parses a {@code String email} into a {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(email)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String address} into a {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedaddress = address.trim();
        if (!Address.isValidAddress(address)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedaddress);
    }


}
