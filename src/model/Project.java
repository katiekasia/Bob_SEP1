/**
 * Abstract class representing a project.
 * Note: The project type is obtained from the ProjectType enum class.
 *
 * Author: Katarzyna, Catalina, Sandut, Samo, Sebastian
 * Version: 2.0 – December 2023
 */
package model;


/**
 * abstract class representing A project
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023
 **/
public abstract class Project {
  // instance variables for a project
  private int ID; // Project ID
  private String title; // Project title
  private double budget; // Project budget
  private double size; // Project size
  private String address; // Project address

  private ProjectType type; // Project type, obtained from the ProjectType enum class

  /**
   * Constructor for creating project objects.
   *
   * @param ID
   * Project ID.
   * @param title
   * Project title.
   * @param budget
   * Project budget.
   * @param size
   * Project size.
   * @param address
   * Project address.
   * @param type
   * Project type obtained from the ProjectType enum class.
   */
  public Project(int ID, String title, double budget, double size, String address, ProjectType type) {
    // initialize instance variables with provided values
    this.ID = ID;
    this.title = title;
    this.budget = budget;
    this.size = size;
    this.address = address;
    this.type = type;
  }

  /** Getter method for project ID
   * @return ID
   */
  public int getID() {
    return ID;
  }

  /** Setter method for project ID
   * @param ID */
  public void setID(int ID) {
    this.ID = ID;
  }

  /** Getter method for project title
   * @return title */
  public String getTitle() {
    return title;
  }

  /** Setter method for project title
   * @param title*/
  public void setTitle(String title) {
    this.title = title;
  }

  /** Getter method for project budget
   * @return budget */
  public double getBudget() {
    return budget;
  }

  /** Setter method for project budget
   * @param budget*/
  public void setBudget(double budget) {
    this.budget = budget;
  }

  /** Getter method for project size
   * @return size */
  public double getSize() {
    return size;
  }

  /** Setter method for project size
   * @param size */
  public void setSize(double size) {
    this.size = size;
  }

  /** Getter method for project address
   * @return address */
  public String getAddress() {
    return address;
  }

  /** Setter method for project address
   * @param address*/
  public void setAddress(String address) {
    this.address = address;
  }

  /** Getter method for project type
   * @return type */
  public ProjectType getType() {
    return type;
  }

  /** Setter method for project type
   * @param type*/
  public void setType(ProjectType type) {
    this.type = type;
  }

  /**
   * Equals method to compare projects for equality.
   *
   * @param obj Object to compare.
   * @return True if the projects are equal, false if they're not.
   */
  public boolean equals(Object obj) {
    // Check if the object is null or of a different class
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    // makes a copy
    Project other = (Project) obj;

    // compare instance variables for equality
    return ID == other.ID && title.equals(other.title) && budget == other.budget && size == other.size
        && address.equals(other.address);
  }

  /**
   * Abstract method to be implemented by subclasses, it gives a string representation of the project.
   *
   * @return A string representation of the project.
   */
  public abstract String toString();
}
