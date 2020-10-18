package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.stream.Stream;

import seedu.address.logic.commands.project.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME, PREFIX_TASK_PROGRESS,
                        PREFIX_TASK_IS_DONE);

        if (!arePrefixesPresent(argMultimap, PREFIX_PROJECT_NAME,
                PREFIX_TASK_PROGRESS, PREFIX_TASK_IS_DONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        String taskName = ParserUtil.parseTaskBasicInformation(argMultimap.getValue(PREFIX_PROJECT_NAME).get());
        double taskProgress = Double.parseDouble(
                ParserUtil.parseTaskBasicInformation(argMultimap.getValue(PREFIX_TASK_PROGRESS).get()));
        boolean taskStatus = Boolean.parseBoolean(
                ParserUtil.parseTaskBasicInformation(argMultimap.getValue(PREFIX_TASK_IS_DONE).get()));

        Task task = new Task(taskName, "", null, taskProgress, taskStatus);

        return new AddTaskCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
