/**
 * Interface representing a project planning model.
 *
 * Author: Katarzyna, Catalina, Sandut, Samo, Sebastian
 * Version: 2.0 – December 2023
 */
package model;
import java.util.ArrayList;


/**
 *Interface representing a project planning model
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023
 **/
public interface ProjectPlanningModel {
  /**
   * Method to retrieve all projects in the model.
   *
   * @return An ArrayList containing all projects in the model.
   */
  ArrayList<Project> getAllProjects();

  /**
   * Method to add a project to the model.
   *
   * @param project Project to be added to the model.
   */
  void addProject(Project project);

  /**
   * Method to remove a project from the model.
   *
   * @param project Project to be removed from the model.
   */
  void removeProject(Project project);

  /**
   * Method to retrieve a project by its ID.
   *
   * @param ID ID of the project to retrieve.
   * @return The project with the specified ID, or null if not found.
   */
  Project getProjectByID(int ID);
}
