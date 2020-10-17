package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Objects;

import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * Represents a Teammate in the team.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private GithubHandle githubHandle;
    private PersonName personName;
    private Phone phone;
    private Email email;

    // Data fields
    private Address address;
    private HashMap<ProjectName, Participation> listOfParticipations = new HashMap<>();
    // TODO: may use project id instead

    /**
     * Every field must be present and not null.
     */
    public Person(GithubHandle githubHandle, PersonName personName, Email email, Address address, Phone phone) {
        requireAllNonNull(githubHandle, phone, email, address);
        this.githubHandle = githubHandle;
        this.personName = personName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public GithubHandle getGithubHandle() {
        return githubHandle;
    }

    public PersonName getPersonName() {
        return personName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public void updateGithubHandle(String newGithubHandleStr) {
        githubHandle = new GithubHandle(newGithubHandleStr);
    }

    public void updatePersonName(String newPersonNameStr) {
        personName = new PersonName(newPersonNameStr);
    }

    public void updateAddress(String newAddressStr) {
        address = new Address(newAddressStr);
    }

    public void updatePhone(String newPhonestr) {
        phone = new Phone(newPhonestr);
    }

    public void updateEmail(String newEmailStr) {
        email = new Email(newEmailStr);
    }

    public void addProject(Project p) {
        listOfParticipations.put(p.getProjectName(), new Participation(this, p));
    }

    /**
     * Returns true if both teammates of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameTeammate(Person otherTeammate) {
        if (otherTeammate == this) {
            return true;
        }

        return otherTeammate != null
                && otherTeammate.getGithubHandle().equals(getGithubHandle());
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

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getGithubHandle().equals(getGithubHandle())
                && otherPerson.getPersonName().equals(getPersonName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(githubHandle, personName, phone, email, address);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Github username: ")
                .append(getGithubHandle())
                .append(" Name: ")
                .append(getPersonName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress());
        return builder.toString();
    }
}
