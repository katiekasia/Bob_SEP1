/**
 * Class implementing the ProjectPlanningModel interface, managing projects.
 *
 * Author: Katarzyna, Catalina, Sandut, Samo, Sebastian
 * Version: 2.0 – December 2023
 */
package model;
import java.util.ArrayList;


/**
 * Class implementing the ProjectPlanningModel interface, managing projects
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023
 **/
public class ProjectPlanningModelManager implements ProjectPlanningModel {
  /**
   * Method to retrieve all projects in the model.
   *
   * @return An ArrayList containing all projects in the model.
   */
  @Override
  public ArrayList<Project> getAllProjects() {
    return null; // Placeholder implementation, returns null
  }

  /**
   * Method to add a project to the model.
   *
   * @param project Project to be added to the model.
   */
  @Override
  public void addProject(Project project) {
    // Placeholder implementation, no specific behavior specified
  }

  /**
   * Method to remove a project from the model.
   *
   * @param project Project to be removed from the model.
   */
  @Override
  public void removeProject(Project project) {
    // Placeholder implementation, no specific behavior specified
  }

  /**
   * Method to retrieve a project by its ID.
   *
   * @param ID ID of the project to retrieve.
   * @return The project with the specified ID, or null if not found.
   */
  @Override
  public Project getProjectByID(int ID) {
    return null; // Placeholder implementation, returns null
  }
}
