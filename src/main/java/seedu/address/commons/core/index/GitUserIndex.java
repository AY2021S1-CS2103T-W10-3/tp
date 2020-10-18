package seedu.address.commons.core.index;


/**
 * Represents a unique Git username of a teammate
 *
 * {@code GitUserIndex} should be used right from the start (when parsing in a new user input), so that if the current
 * component wants to communicate with another component, it can send an {@code GitUserIndex} to avoid having to know
 * what base the other component is using for its gitUserIndex. However, after receiving the {@code Index}, that
 * component can convert it back to a string if the gitUserIndex will not be passed to a different component again.
 */
public class GitUserIndex {
    private String uniqueGitUserIndex;

    public GitUserIndex(String uniqueGitUserIndex) {
        this.uniqueGitUserIndex = uniqueGitUserIndex;
    }

    public String getGitUserName() {
        return uniqueGitUserIndex;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Index // instanceof handles nulls
            && uniqueGitUserIndex.equals(((GitUserIndex) other).uniqueGitUserIndex)); // state check
    }

}
