package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_HANG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOURL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_MODEL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.ALICE;
import static seedu.address.testutil.TypicalProjects.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ProjectBuilder;

public class ProjectTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Project project = new ProjectBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> project.getProjectTags().remove(0));
    }

    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(ALICE.isSameProject(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameProject(null));

        // different deadline and repoUrl -> returns false
        Project editedAlice = new ProjectBuilder(ALICE)
                .withDeadline(VALID_DEADLINE_BOT).withRepoUrl(VALID_REPOURL_B).build();
        assertFalse(ALICE.isSameProject(editedAlice));

        // different name -> returns false
        editedAlice = new ProjectBuilder(ALICE).withProjectName(VALID_PROJECT_NAME_BOT).build();
        assertFalse(ALICE.isSameProject(editedAlice));

        // same name, same deadline, different attributes -> returns true
        editedAlice = new ProjectBuilder(ALICE).withRepoUrl(VALID_REPOURL_B).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_BOT)
                .withTags(VALID_PROJECT_TAG_HANG).withTasks(VALID_PROJECT_TAG_DG).build();
        assertTrue(ALICE.isSameProject(editedAlice));

        // same name, same repoUrl, different attributes -> returns true
        editedAlice = new ProjectBuilder(ALICE).withDeadline(VALID_DEADLINE_BOT)
                .withProjectDescription(VALID_PROJECT_DESCRIPTION_BOT)
                .withTags(VALID_PROJECT_TAG_HANG).withTasks(VALID_PROJECT_TAG_DG).build();
        assertTrue(ALICE.isSameProject(editedAlice));

        // same name, same deadline, same repoUrl, different attributes -> returns true
        editedAlice = new ProjectBuilder(ALICE).withProjectDescription(VALID_PROJECT_DESCRIPTION_BOT).withTags(
            VALID_PROJECT_TAG_HANG)
                .withTasks(VALID_PROJECT_TAG_DG).build();
        assertTrue(ALICE.isSameProject(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Project aliceCopy = new ProjectBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different project -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Project editedAlice = new ProjectBuilder(ALICE).withProjectName(VALID_PROJECT_NAME_BOT).build();
        assertFalse(ALICE.equals(editedAlice));

        // different deadline -> returns false
        editedAlice = new ProjectBuilder(ALICE).withDeadline(VALID_DEADLINE_BOT).build();
        assertFalse(ALICE.equals(editedAlice));

        // different repoUrl -> returns false
        editedAlice = new ProjectBuilder(ALICE).withRepoUrl(VALID_REPOURL_B).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ProjectBuilder(ALICE).withProjectDescription(VALID_PROJECT_DESCRIPTION_BOT).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ProjectBuilder(ALICE).withTags(VALID_PROJECT_TAG_HANG).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tasks -> returns false
        editedAlice = new ProjectBuilder(ALICE).withTasks(VALID_TASK_MODEL).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
