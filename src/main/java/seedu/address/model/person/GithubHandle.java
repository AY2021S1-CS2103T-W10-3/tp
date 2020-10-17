package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's GitHub handle.
 * Guarantees: immutable; is valid as declared in {@link #isValidGithubHandle(String)}; is unique for a person so can be
 *             the identifier for Person
 */
public class GithubHandle {

    public static final String MESSAGE_CONSTRAINTS =
            "GitHub handle should contain only alphabetical-numerical characters and '-', where '-' can only appear"
                    + " between some non-empty sequence of alphabetical-numerical strings, and it should not be blank";

    public static final String VALIDATION_REGEX = "^[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d])){0,38}$";

    public final String fullGithubHandle;

    /**
     * Constructs a {@code GithubHandle}.
     *
     * @param githubHandle A valid githubHandle.
     */
    public GithubHandle(String githubHandle) {
        requireNonNull(githubHandle);
        checkArgument(isValidGithubHandle(githubHandle), MESSAGE_CONSTRAINTS);
        fullGithubHandle = githubHandle;
    }

    /**
     * Returns true if a given string is a valid github handle.
     */
    public static boolean isValidGithubHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullGithubHandle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GithubHandle // instanceof handles nulls
                && fullGithubHandle.equals(((GithubHandle) other).fullGithubHandle)); // state check
    }

    @Override
    public int hashCode() {
        return fullGithubHandle.hashCode();
    }
}
