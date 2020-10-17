package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParsePersonUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubHandle;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.project.Project;

/**
 * Creates a new person within
 */
public class NewTeammateCommand extends Command {

    public static final String COMMAND_WORD = "newteammate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Creates a new teammate as a part of this project"
        + "that participates in the current project with his/her name.\n"
        + "Parameters: GITHUB_HANDLE, PHONE, EMAIL, ADDRESS (must be present in the project)\n"
        + "Example: " + COMMAND_WORD + "1 Lucas 93824823 lucas@gmail.com 18 Evelyn Road";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "New Teammate: ";

    private Person teammate;

    /**
     * Creates an new teammate that is associated with the project
     */
    public NewTeammateCommand(String handle, String phone, String email, String address) throws ParseException {
        requireAllNonNull(handle, phone, email, address);

        GithubHandle personGithubHandle = ParsePersonUtil.parseGithubHandle(handle);
        Phone personPhone = ParsePersonUtil.parsePhone(phone);
        Email personEmail = ParsePersonUtil.parseEmail(email);
        Address personAddress = ParsePersonUtil.parseAddress(address);

        teammate = new Person(personGithubHandle, personPhone, personEmail, personAddress);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        teammate.addProject(project);
        project.addParticipation(teammate);


        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS,
            teammate.getGithubHandle().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof NewTeammateCommand // instanceof handles nulls
            && teammate.equals(((NewTeammateCommand) other).teammate));
    }
}
