package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.global.AddCommand;
import seedu.address.logic.commands.global.ClearCommand;
import seedu.address.logic.commands.global.DeleteCommand;
import seedu.address.logic.commands.global.EditCommand;
import seedu.address.logic.commands.global.ExitCommand;
import seedu.address.logic.commands.global.FindCommand;
import seedu.address.logic.commands.global.HelpCommand;
import seedu.address.logic.commands.global.ListCommand;
import seedu.address.logic.commands.global.StartCommand;
import seedu.address.logic.commands.project.AddTaskCommand;
import seedu.address.logic.commands.project.AssignCommand;
import seedu.address.logic.commands.project.EditTaskCommand;
import seedu.address.logic.commands.project.EditTeammateCommand;
import seedu.address.logic.commands.project.FilterCommand;
import seedu.address.logic.commands.project.LeaveProjectViewCommand;
import seedu.address.logic.commands.project.NewTeammateCommand;
import seedu.address.logic.commands.project.ViewTaskCommand;
import seedu.address.logic.commands.project.ViewTeammateCommand;
import seedu.address.logic.commands.task.LeaveTaskViewCommand;
import seedu.address.logic.commands.teammate.LeaveTeammateViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Status;
import seedu.address.model.exceptions.InvalidScopeException;

/**
 * Parses user input.
 */
public class MainCatalogueParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @param status    the status of the current scoping
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, Status status) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case StartCommand.COMMAND_WORD:
            return new StartCommandParser().parse(arguments);

        case LeaveProjectViewCommand.COMMAND_WORD:
            return new LeaveProjectViewCommand();

        case LeaveTaskViewCommand.COMMAND_WORD:
            return new LeaveTaskViewCommand();

        case LeaveTeammateViewCommand.COMMAND_WORD:
            return new LeaveTeammateViewCommand();

        case AssignCommand.COMMAND_WORD:
            if (status != Status.CATALOGUE) {
                return new AssignCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case FilterCommand.COMMAND_WORD:
            if (status != Status.CATALOGUE) {
                return new FilterCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case NewTeammateCommand.COMMAND_WORD:
            if (status != Status.CATALOGUE) {
                return new NewTeammateCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case AddTaskCommand.COMMAND_WORD:
            if (status != Status.CATALOGUE) {
                return new AddTaskCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case EditTaskCommand.COMMAND_WORD:
            if (status != Status.CATALOGUE) {
                return new EditTaskCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        case EditTeammateCommand.COMMAND_WORD:
            if (status != Status.CATALOGUE) {
                return new EditTeammateCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }
        case ViewTaskCommand.COMMAND_WORD:
            if (status != Status.CATALOGUE) {
                return new ViewTaskCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }
        case ViewTeammateCommand.COMMAND_WORD:
            if (status != Status.CATALOGUE) {
                return new ViewTeammateCommandParser().parse(arguments);
            } else {
                throw new InvalidScopeException(Status.PROJECT, status);
            }

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
