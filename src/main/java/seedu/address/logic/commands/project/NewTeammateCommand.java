package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import seedu.address.logic.commands.exceptions.CommandException;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMMATE_PHONE;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * Creates a new person within a project
 */
public class NewTeammateCommand extends Command {

    public static final String COMMAND_WORD = "newteammate";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new teammate as a part of this project"
        + "\nParameters: "
        + PREFIX_TEAMMATE_NAME + "TEAMMATE NAME "
        + PREFIX_TEAMMATE_GIT_USERNAME + "GIT USERNAME "
        + PREFIX_TEAMMATE_PHONE + "PHONE "
        + PREFIX_TEAMMATE_EMAIL + "EMAIL "
        + PREFIX_TEAMMATE_ADDRESS + "ADDRESS\n"
        + "Example: " + COMMAND_WORD + " mn/Lucas mg/LucasTai98 mp/93824823 me/lucas@gmail.com ma/18 Evelyn Road";

    public static final String MESSAGE_NEW_TEAMMATE_SUCCESS = "New Teammate added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the main catalogue";

    private static final Logger logger = Logger.getLogger("NewTeammateCommandLogger");
    private final Person toAdd;

    /**
     * Creates an new teammate that is associated with the project
     */
    public NewTeammateCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        model.addPerson(toAdd);
        toAdd.addProject(project);
        project.addParticipation(toAdd);
        model.addParticipation(project.getParticipation(toAdd.getGitUserNameString()));
        logger.log(Level.INFO, "New Teammate added");

        return new CommandResult(String.format(MESSAGE_NEW_TEAMMATE_SUCCESS, toAdd.getGitUserNameString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof NewTeammateCommand // instanceof handles nulls
            && toAdd.equals(((NewTeammateCommand) other).toAdd));
    }
}
