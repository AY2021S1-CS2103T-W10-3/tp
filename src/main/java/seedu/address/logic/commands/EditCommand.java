package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Email;
import seedu.address.model.project.Phone;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;

/**
 * Edits the details of an existing project in the main catalogue.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the project identified "
            + "by the index number used in the displayed project list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PROJECT_NAME + "PROJECTNAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_PROJECT_DESCRIPTION + "PROJECTDESCRIPTION] "
            + "[" + PREFIX_PROJECT_TAG + "TAG]...\n"
            + "[" + PREFIX_TASK + "TASK]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PROJECT_SUCCESS = "Edited Project: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in the main catalogue.";

    private final Index index;
    private final EditProjectDescriptor editProjectDescriptor;

    /**
     * @param index of the project in the filtered project list to edit
     * @param editProjectDescriptor details to edit the project with
     */
    public EditCommand(Index index, EditProjectDescriptor editProjectDescriptor) {
        requireNonNull(index);
        requireNonNull(editProjectDescriptor);

        this.index = index;
        this.editProjectDescriptor = new EditProjectDescriptor(editProjectDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        Project editedProject = createEditedProject(projectToEdit, editProjectDescriptor);

        if (!projectToEdit.isSameProject(editedProject) && model.hasProject(editedProject)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        model.setProject(projectToEdit, editedProject);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PROJECT_SUCCESS, editedProject));
    }

    /**
     * Creates and returns a {@code Project} with the details of {@code projectToEdit}
     * edited with {@code editProjectDescriptor}.
     */
    private static Project createEditedProject(Project projectToEdit, EditProjectDescriptor editProjectDescriptor) {
        assert projectToEdit != null;

        ProjectName updatedProjectName = editProjectDescriptor.getProjectName().orElse(projectToEdit.getProjectName());
        Phone updatedPhone = editProjectDescriptor.getPhone().orElse(projectToEdit.getPhone());
        Email updatedEmail = editProjectDescriptor.getEmail().orElse(projectToEdit.getEmail());
        ProjectDescription updatedProjectDescription = editProjectDescriptor.getProjectDescription()
            .orElse(projectToEdit.getProjectDescription());
        Set<ProjectTag> updatedProjectTags = editProjectDescriptor.getTags().orElse(projectToEdit.getProjectTags());
        Set<Task> updatedTasks = editProjectDescriptor.getTasks().orElse(projectToEdit.getTasks());

        return new Project(updatedProjectName, updatedPhone, updatedEmail, updatedProjectDescription,
            updatedProjectTags, new HashMap<>(), updatedTasks);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editProjectDescriptor.equals(e.editProjectDescriptor);
    }

    /**
     * Stores the details to edit the project with. Each non-empty field value will replace the
     * corresponding field value of the project.
     */
    public static class EditProjectDescriptor {
        private ProjectName projectName;
        private Phone phone;
        private Email email;
        private ProjectDescription projectDescription;
        private Set<ProjectTag> projectTags;
        private Set<Task> tasks;

        public EditProjectDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code projectTags} is used internally.
         */
        public EditProjectDescriptor(EditProjectDescriptor toCopy) {
            setProjectName(toCopy.projectName);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setProjectDescription(toCopy.projectDescription);
            setTags(toCopy.projectTags);
            setTasks(toCopy.tasks);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(projectName, phone, email, projectDescription, projectTags, tasks);
        }

        public void setProjectName(ProjectName projectName) {
            this.projectName = projectName;
        }

        public Optional<ProjectName> getProjectName() {
            return Optional.ofNullable(projectName);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setProjectDescription(ProjectDescription projectDescription) {
            this.projectDescription = projectDescription;
        }

        public Optional<ProjectDescription> getProjectDescription() {
            return Optional.ofNullable(projectDescription);
        }

        /**
         * Sets {@code projectTags} to this object's {@code projectTags}.
         * A defensive copy of {@code projectTags} is used internally.
         */
        public void setTags(Set<ProjectTag> projectTags) {
            this.projectTags = (projectTags != null) ? new HashSet<>(projectTags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code projectTags} is null.
         */
        public Optional<Set<ProjectTag>> getTags() {
            return (projectTags != null) ? Optional.of(Collections.unmodifiableSet(projectTags)) : Optional.empty();
        }

        /**
         * Sets {@code tasks} to this object's {@code tasks}.
         * A defensive copy of {@code tasks} is used internally.
         */
        public void setTasks(Set<Task> tasks) {
            this.tasks = (tasks != null) ? new HashSet<>(tasks) : null;
        }

        /**
         * Returns an unmodifiable task set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tasks} is null.
         */
        public Optional<Set<Task>> getTasks() {
            return (tasks != null) ? Optional.of(Collections.unmodifiableSet(tasks)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProjectDescriptor)) {
                return false;
            }

            // state check
            EditProjectDescriptor e = (EditProjectDescriptor) other;

            return getProjectName().equals(e.getProjectName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getProjectDescription().equals(e.getProjectDescription())
                    && getTags().equals(e.getTags())
                    && getTasks().equals(e.getTasks());
        }
    }
}
