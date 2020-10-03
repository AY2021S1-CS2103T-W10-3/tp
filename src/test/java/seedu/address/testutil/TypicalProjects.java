package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_MODEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MainCatalogue;
import seedu.address.model.project.Project;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {

    public static final Project ALICE = new ProjectBuilder().withProjectName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").withTasks("Write DG", "Write user stories").build();
    public static final Project BENSON = new ProjectBuilder().withProjectName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withTasks("Practice presentation").build();
    public static final Project CARL = new ProjectBuilder().withProjectName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withTasks("Read info pack").build();
    public static final Project DANIEL = new ProjectBuilder().withProjectName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Project ELLE = new ProjectBuilder().withProjectName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Project FIONA = new ProjectBuilder().withProjectName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Project GEORGE = new ProjectBuilder().withProjectName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Project HOON = new ProjectBuilder().withProjectName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Project IDA = new ProjectBuilder().withProjectName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Project's details found in {@code CommandTestUtil}
    public static final Project AMY = new ProjectBuilder().withProjectName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withTasks(VALID_TASK_DG, VALID_TASK_MODEL).build();
    public static final Project BOB = new ProjectBuilder().withProjectName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withTasks(VALID_TASK_DG).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns an {@code MainCatalogue} with all the typical projects.
     */
    public static MainCatalogue getTypicalMainCatalogue() {
        MainCatalogue ab = new MainCatalogue();
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
