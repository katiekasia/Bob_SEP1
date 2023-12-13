/**
 * Class representing an industrial project, extending the Project class.
 * Industrial projects have specific attributes such as the type of facility and timeline.
 *
 * @author Katarzyna, Catalina, Sandut, Samo, Sebastian
 * @version 2.0 – December 2023
 */
package model;
/**
 * Class representing an industrial project, extending the Project class
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023
 **/
public class Industrial extends Project {
  private String typeOfFacility;
  private int timeline;

  /**
   * Eight-argument constructor for Industrial projects.
   * There are four different types of projects, each with its own special attributes. However, six of them are shared between all of them (superclass).
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
   *The special ones for industrial project type are the following ones.
   * @param typeOfFacility
   * The kind of (industrial) facility this project will create.
   * @param timeline
   * How long the project is planned to take during its realization phase.
   */
  public Industrial(int ID, String title, double budget, double size, String address, ProjectType type, String typeOfFacility,
      int timeline) {
    // call the constructor of the superclass (Project) with specified parameters
    super(ID, title, budget, size, address, type);

    // load default industrial settings using DefaultSettingsHandler
    Object[] defaultIndustrial = DefaultSettingsHandler.loadIndustrialDefaultSettings();

    // set typeOfFacility to the provided value
    this.typeOfFacility = typeOfFacility;

    // set timeline to the provided value if not 0, otherwise set it to the default value
    this.timeline = (timeline != 0) ? timeline : (int) defaultIndustrial[0];
  }

  /** Getter method for typeOfFacility
   * @return The typeOfFacility in the industrial project.
   * */
  public String getTypeOfFacility() {
    return typeOfFacility;
  }

  /** Setter method for typeOfFacility
   *  @param typeOfFacility */
  public void setTypeOfFacility(String typeOfFacility) {
    this.typeOfFacility = typeOfFacility;
  }

  /** Getter method for timeline
   * @return The timeline in the industrial project.
   */
  public int getTimeline() {
    return timeline;
  }

  /** Setter method for timeline
   *  @param timeline */
  public void setTimeline(int timeline) {
    this.timeline = timeline;
  }

  /**
   * ToString method to provide a readable string of the project information.
   *
   * @return A formatted string of the project information.
   */
  public String toString() {
    return "Industrial{" + "ID=" + getID() + ", title='" + getTitle() + '\'' +
        ", budget=" + getBudget() + ", size=" + getSize() + ", address='" + getAddress() + '\'' + ", type=" + getType() +
        ", typeOfFacility='" + typeOfFacility + '\'' + ", timeline=" + timeline + '}';
  }
}
