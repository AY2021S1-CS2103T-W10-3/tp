package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.project.Project;

/**
 * Unmodifiable view of an main catalogue
 */
public interface ReadOnlyMainCatalogue {

    /**
     * Returns an unmodifiable view of the projects list.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Project> getProjectList();

    /**
     * Gets the current status for valid scope.
     */
    Status getStatus();

    /**
     * Enters a designated project.
     */
    void enter(Project project);

    /**
     * Quits the current project view.
     */
    void quit();
}
