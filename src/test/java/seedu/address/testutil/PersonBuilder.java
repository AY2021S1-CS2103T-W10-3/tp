package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubHandle;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building Project objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_GITHUB_HANDLE = "alice-pauline";
    public static final String DEFAULT_PERSON_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "88888888";
    public static final String DEFAULT_EMAIL = "alicepauline@sample.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private GithubHandle githubHandle;
    private Phone phone;
    private Email email;
    private Address address;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        githubHandle = new GithubHandle(DEFAULT_GITHUB_HANDLE);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        githubHandle = personToCopy.getGithubHandle();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
    }

    /**
     * Sets the {@code GithubHandle} of the {@code Person} that we are building.
     */
    public PersonBuilder withGithubHandle(String githubHandle) {
        this.githubHandle = new GithubHandle(githubHandle);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    public Person build() {
        return new Person(githubHandle, new PersonName(""), email, address, phone);
    }

}
