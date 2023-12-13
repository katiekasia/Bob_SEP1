/**
 * Class representing a commercial project, extending the Project class.
 * Commercial projects have their own unique specific attributes: number of floors, timeline, and use of the building.
 *
 * @author Katarzyna, Catalina, Sandut, Samo, Sebastian
 * @version 2.0 – December 2023
 */
package model;
// Class representing a commercial project, extending the Project class
public class Commercial extends Project {
  private int numberOfFloors;
  private int timeline;
  private String useOfBuilding;

  /**
   * Nine-argument constructor for Commercial projects.
   * There are four different types of projects, each with its own special attributes. However, six of them are shared between all project types (superclass).
   *
   * @param ID
   * The unique identification number of the project (6 digits).
   * @param title
   * The name of the project.
   * @param budget
   * The monetary allowances towards the project.
   * @param size
   * The scale of the project.
   * @param address
   * The physical place where the project resides.
   * @param type
   * One of four different options, ProjectType.
   *
   * The special ones for commercial project type are the following ones.
   * @param numberOfFloors
   * The amount of floors of the building.
   * @param timeline
   * How long the project is planned to take during its realization phase.
   * @param useOfBuilding
   * The purpose of the building.
   */
  public Commercial(int ID, String title, double budget, double size, String address, ProjectType type,
      int numberOfFloors, int timeline, String useOfBuilding) {
    // call the constructor of the superclass (Project) with specified parameters
    super(ID, title, budget, size, address, type);

    // load default commercial settings using DefaultSettingsHandler
    Object[] defaultCommercial = DefaultSettingsHandler.loadCommercialDefaultSettings();

    // set numberOfFloors to the provided value if not 0, otherwise set it to the default value
    this.numberOfFloors = (numberOfFloors != 0) ? numberOfFloors : (int) defaultCommercial[0];

    // set timeline to the provided value if not 0, otherwise set it to the default value
    this.timeline = (timeline != 0) ? timeline : (int) defaultCommercial[1];

    // set useOfBuilding to the provided value
    this.useOfBuilding = useOfBuilding;
  }

  /** Getter method for numberOfFloors */
  public int getNumberOfFloors() {
    return numberOfFloors;
  }

  /** Setter method for numberOfFloors */
  public void setNumberOfFloors(int numberOfFloors) {
    this.numberOfFloors = numberOfFloors;
  }

  /** Getter method for timeline */
  public int getTimeline() {
    return timeline;
  }

  /** Setter method for timeline */
  public void setTimeline(int timeline) {
    this.timeline = timeline;
  }

  /** Getter method for useOfBuilding */
  public String getUseOfBuilding() {
    return useOfBuilding;
  }

  /** Setter method for useOfBuilding */
  public void setUseOfBuilding(String useOfBuilding) {
    this.useOfBuilding = useOfBuilding;
  }

  /**
   * ToString method to provide a readable string of the project information.
   *
   * @return A formatted string of the project information.
   */
  public String toString() {
    return "Commercial{" +
        "ID=" + getID() +
        ", title='" + getTitle() + '\'' +
        ", budget=" + getBudget() +
        ", size=" + getSize() +
        ", address='" + getAddress() + '\'' +
        ", type=" + getType() +
        ", numberOfFloors=" + numberOfFloors +
        ", timeline=" + timeline +
        ", useOfBuilding='" + useOfBuilding + '\'' +
        '}';
  }
}
