package seedu.address.ui;

import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.project.Project;

/**
 * An UI component that displays information of a {@code Project}.
 */
public class ProjectDashboard extends UiPart<Region> {

    private static final String FXML = "ProjectDashboard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on MainCatalogue level 4</a>
     */

    public final Project project;

    @FXML
    private HBox projectDashboardPane;
    @FXML
    private Label projectName;
    @FXML
    private Label deadline;
    @FXML
    private Label projectDescription;
    @FXML
    private Label repoUrl;
    @FXML
    private Label header1;
    @FXML
    private Label header2;
    @FXML
    private Label header3;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane teammates;
    @FXML
    private FlowPane tasks;

    /**
     * Creates a {@code ProjectDashboardCode} with the given {@code Project} to display.
     */
    public ProjectDashboard(Optional<Project> project) {
        super(FXML);
        this.project = project.get();
        projectName.setText(this.project.getProjectName().fullProjectName);
        deadline.setText("Project deadline: " + this.project.getDeadline().toString());
        header1.setText("Project tags: ");
        this.project.getProjectTags().stream()
                .sorted(Comparator.comparing(projectTag -> projectTag.projectTagName))
                .forEach(projectTag -> this.tags.getChildren()
                        .add(new Label(projectTag.projectTagName)));

        projectDescription.setText("Project description: " + this.project.getProjectDescription().value);
        repoUrl.setText("Project repourl: " + this.project.getRepoUrl().value);
        header2.setText("Tasks: ");
        this.project.getFilteredTaskList()
                .forEach(task -> tasks.getChildren().add(new Label(task.taskName)));
        header3.setText("Teammates: ");
        this.project.getTeammates().stream()
                .forEach(person -> teammates.getChildren().add(new Label((person.getGitUserNameString()))));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProjectDashboard)) {
            return false;
        }

        // state check
        ProjectDashboard card = (ProjectDashboard) other;
        return project.equals(card.project);
    }
}
