package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.Status;
import seedu.address.model.project.Project;

/**
 * An UI component that displays information of a {@code Project}.
 */
public class ProjectCard extends UiPart<Region> {

    private static final String FXML = "ProjectListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on MainCatalogue level 4</a>
     */

    public final Project project;
    private Status status;

    @FXML
    private HBox cardPane;
    @FXML
    private Label projectName;
    @FXML
    private Label id;
    @FXML
    private Label deadline;
    @FXML
    private Label projectDescription;
    @FXML
    private Label repoUrl;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane tasks;

    /**
     * Creates a {@code ProjectCode} with the given {@code Project} and index to display.
     */
    public ProjectCard(Project project, int displayedIndex, Status status) {
        super(FXML);
        this.project = project;
        this.status = status;
        id.setText(displayedIndex + ". ");
        projectName.setText(project.getProjectName().fullProjectName);
        deadline.setText(project.getDeadline().value);
        project.getProjectTags().stream()
                .sorted(Comparator.comparing(projectTag -> projectTag.projectTagName))
                .forEach(projectTag -> this.tags.getChildren()
                .add(new Label(projectTag.projectTagName)));

        projectDescription.setText(project.getProjectDescription().value);
        repoUrl.setText(project.getRepoUrl().value);
        project.getTasks().stream()
                .sorted(Comparator.comparing(task -> task.taskName))
                .forEach(task -> tasks.getChildren().add(new Label(task.taskName)));
        //System.out.println(status + name.getText());
        if (status == Status.CATALOGUE) {
            projectDescription.setVisible(false);
            repoUrl.setVisible(false);
            tasks.setVisible(false);
            projectDescription.setManaged(false);
            repoUrl.setManaged(false);
            tasks.setManaged(false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProjectCard)) {
            return false;
        }

        // state check
        ProjectCard card = (ProjectCard) other;
        return id.getText().equals(card.id.getText())
                && project.equals(card.project)
                && status.equals(card.status);
    }
}
