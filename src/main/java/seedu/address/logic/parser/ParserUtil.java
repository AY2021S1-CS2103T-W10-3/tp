package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Email;
import seedu.address.model.project.Phone;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String projectName} into a {@code ProjectName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code projectName} is invalid.
     */
    public static ProjectName parseProjectName(String projectName) throws ParseException {
        requireNonNull(projectName);
        String trimmedProjectName = projectName.trim();
        if (!ProjectName.isValidProjectName(trimmedProjectName)) {
            throw new ParseException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        return new ProjectName(trimmedProjectName);
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
     * Parses a {@code String projectDescription} into an {@code ProjectDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code projectDescription} is invalid.
     */
    public static ProjectDescription projectDescription(String projectDescription) throws ParseException {
        requireNonNull(projectDescription);
        String trimmedProjectDescription = projectDescription.trim();
        if (!ProjectDescription.isValidProjectDescription(trimmedProjectDescription)) {
            throw new ParseException(ProjectDescription.MESSAGE_CONSTRAINTS);
        }
        return new ProjectDescription(trimmedProjectDescription);
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
     * Parses a {@code String projectTag} into a {@code ProjectTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code projectTag} is invalid.
     */
    public static ProjectTag parseProjectTag(String projectTag) throws ParseException {
        requireNonNull(projectTag);
        String trimmedTag = projectTag.trim();
        if (!ProjectTag.isValidProjectTagName(trimmedTag)) {
            throw new ParseException(ProjectTag.MESSAGE_CONSTRAINTS);
        }
        return new ProjectTag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> projectTags} into a {@code Set<ProjectTag>}.
     */
    public static Set<ProjectTag> parseTags(Collection<String> projectTags) throws ParseException {
        requireNonNull(projectTags);
        final Set<ProjectTag> projectTagSet = new HashSet<>();
        for (String tagName : projectTags) {
            projectTagSet.add(parseProjectTag(tagName));
        }
        return projectTagSet;
    }

    /**
     * Parses a {@code String task} into a {@code Task}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Task parseTask(String task) {
        requireNonNull(task);
        String trimmedTask = task.trim();
        return new Task(trimmedTask, null, null, 0, false);
    }


    /**
     * Parses {@code Collection<String> tasks} into a {@code Set<Task>}.
     */
    public static Set<Task> parseTasks(Collection<String> tasks) {
        requireNonNull(tasks);
        final Set<Task> taskSet = new HashSet<>();
        for (String taskName : tasks) {
            taskSet.add(parseTask(taskName));
        }
        return taskSet; //TODO: parse tasks in a more meaningful way
    }
}
