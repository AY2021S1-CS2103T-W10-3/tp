package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Represents a Project in the main catalogue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final ProjectName projectName;
    private final Phone phone;
    private final RepoUrl repoUrl;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final HashMap<PersonName, Participation> listOfParticipations = new HashMap<>();
    private final Set<Task> tasks = new HashSet<>();
    private final Set<Meeting> meetings = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Project(ProjectName projectName, Phone phone, RepoUrl repoUrl, Address address, Set<Tag> tags,
                   HashMap<PersonName, Participation> listOfParticipations, Set<Task> tasks) {
        requireAllNonNull(projectName, phone, repoUrl, address, tags, listOfParticipations, tasks);
        this.projectName = projectName;
        this.phone = phone;
        this.repoUrl = repoUrl;
        this.address = address;
        this.tags.addAll(tags);
        this.listOfParticipations.putAll(listOfParticipations);
        this.tasks.addAll(tasks);
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    public Phone getPhone() {
        return phone;
    }

    public RepoUrl getRepoUrl() {
        return repoUrl;
    }

    public Address getAddress() {
        return address;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable task set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    /**
     * Adds a participation instance of a Person to a project
     */
    public void addParticipation(Person p) {
        listOfParticipations.put(
            p.getPersonName(), new Participation(p, this));
    }

    /**
     * Returns true if both projects of the same projectName have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getProjectName().equals(getProjectName())
                && (otherProject.getPhone().equals(getPhone()) || otherProject.getRepoUrl().equals(getRepoUrl()));
    }

    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;
        return otherProject.getProjectName().equals(getProjectName())
                && otherProject.getPhone().equals(getPhone())
                && otherProject.getRepoUrl().equals(getRepoUrl())
                && otherProject.getAddress().equals(getAddress())
                && otherProject.getTags().equals(getTags())
                && otherProject.getTasks().equals(getTasks());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(projectName, phone, repoUrl, address, tags, tasks);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Project Name: ")
                .append(getProjectName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getRepoUrl())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Tasks: ");
        getTasks().forEach(builder::append);
        return builder.toString();
    }

}
