/**
 * Class representing a storage mechanism for projects.
 *
 * Author: Katarzyna, Catalina, Sandut, Samo, Sebastian
 * Version: 2.0 – December 2023
 */
package model;
import java.util.ArrayList;


/**
 * Class representing a storage list for projects
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023
 **/
public class ProjectStorage {
  // Static ArrayList to store all projects
  private static ArrayList<Project> allProjects = new ArrayList<>();

  /**
   * Method to retrieve all projects stored in the storage.
   *
   * @return An ArrayList containing all projects stored in the storage.
   */
  public static ArrayList<Project> getAllProjects() {
    return allProjects;
  }

  /**
   * Method to add a project to the storage.
   *
   * @param project Project to be added to the storage.
   */
  public static void addProject(Project project) {
    allProjects.add(project);
  }

  /**
   * Method to remove a project from the storage.
   *
   * @param project Project to be removed from the storage.
   */
  public static void removeProject(Project project) {
    allProjects.remove(project);
  }

  /**
   * Method to retrieve a project by its ID from the storage.
   *
   * @param id ID of the project to retrieve.
   * @return The project with the specified ID, or null if not found.
   */
  public static Project getProjectByID(int id) {
    // Iterate through projects to find the one with the specified ID
    for (Project project : allProjects) {
      if (project.getID() == id) {
        return project;
      }
    }
    return null; // Return null if the project with the specified ID is not found
  }

  /**
   * Method to print all projects in the storage.

   */
  public static void printProjects() {
    System.out.println("Projects List:");
    for (Project project : allProjects) {
      System.out.println(project);
    }
    System.out.println("End of Projects List");
  }
}
