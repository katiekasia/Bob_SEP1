
package model;
/**
* Class representing a road construction project, extending the Project class.
*@author Katarzyna, Catalina, Sandut, Samo, Sebastian
 * @version: 2.0 – December 2023
*/
public class RoadConstruction extends Project {
  // Private instance variables for road construction projects
  private double length; // Length of the road construction project
  private double width; // Width of the road construction project
  private int timeline; // Project timeline in days
  private boolean hasBridges; // Flag indicating whether the road construction project has bridges
  private boolean hasTunnels; // Flag indicating whether the road construction project has tunnels
  private boolean hasChallenges; // Flag indicating whether the road construction project has challenges

  /**
   *  11 argument Constructor for creating road construction objects.
   * Parameters:
   * @param ID - project ID,
   * @param title - project title,
   * @param budget - project budget,
   * @param address - project address,
   * @param type - project type, length - length of the road construction project,
   *  @param width - width of the road construction project,
   *  @param hasBridges - flag indicating whether the road construction project has bridges,
   * @param hasTunnels - flag indicating whether the road construction project has tunnels,
   * @param timeline - project timeline in days,
   * @param hasChallenges - flag indicating whether the road construction project has challenges
   */
  public RoadConstruction(int ID, String title, double budget, String address, ProjectType type, double length, double width, boolean hasBridges, boolean hasTunnels, int timeline, boolean hasChallenges) {
    // Call the constructor of the superclass (Project) with specified parameters
    super(ID, title, budget, 0, address, type);

    // Load default road construction settings using DefaultSettingsHandler
    Object[] defaultRoadConstruction = DefaultSettingsHandler.loadRoadConstructionDefaultSettings();

    // Set length to the provided value if not 0, otherwise set it to the default value
    this.length = (length != 0) ? length : (double) defaultRoadConstruction[0];

    // Set width to the provided value if not 0, otherwise set it to the default value
    this.width = (width != 0) ? width : (double) defaultRoadConstruction[1];

    // Set timeline to the provided value if not 0, otherwise set it to the default value
    this.timeline = (timeline != 0) ? timeline : (int) defaultRoadConstruction[2];

    // Set hasBridges to the provided value
    this.hasBridges = hasBridges;

    // Set hasTunnels to the provided value
    this.hasTunnels = hasTunnels;

    // Set hasChallenges to the provided value
    this.hasChallenges = hasChallenges;
  }

  /** Getter method for length
   * @return The length in the road construction project.
   * */
  public double getLength() {
    return length;
  }

  /** Setter method for length
   *  @param length*/
  public void setLength(double length) {
    this.length = length;
  }

  /** Getter method for timeline
   * @return The timeline in the road construction project.
   * */
  public int getTimeline() {
    return timeline;
  }

  /** Setter method for timeline
   *  @param timeline*/
  public void setTimeline(int timeline) {
    this.timeline = timeline;
  }

  /** Getter method for width
   * @return The width in the road construction project.
   * */
  public double getWidth() {
    return width;
  }

  /** Setter method for width
   * @param width*/
  public void setWidth(double width) {
    this.width = width;
  }

  /** Getter method for hasBridges
   * @return The hasBridges in the road construction project.
   * */
  public boolean getHasBridges() {
    return hasBridges;
  }

  /** Setter method for hasBridges
   * @param hasBridges- true if it has,false if it doesn't*/
  public void setHasBridges(boolean hasBridges) {
    this.hasBridges = hasBridges;
  }

  /** Getter method for Challenges
   * @return The Challenges in the road construction project.
   * */
  public boolean getHasChallenges() {
    return hasChallenges;
  }

  /** Setter method for hasChallenges
   * @param  hasChallenges- true if it has,false if it doesn't*/
  public void setHasChallenges(boolean hasChallenges) {
    this.hasChallenges = hasChallenges;
  }

  /** Getter method for HasTunnels
   * @return The HasTunnels in the road construction project.
   * */
  public boolean getHasTunnels() {
    return hasTunnels;
  }

  /**Setter method for hasTunnels
   * @param  hasTunnels - true if it has,false if it doesn't */
  public void setHasTunnels(boolean hasTunnels) {
    this.hasTunnels = hasTunnels;
  }


  /**
   * Override toString method to provide a human-readable string representation of the road construction object
   *
   * @return A formatted string of the project information.
   */
  @Override
  public String toString() {
    // Return a formatted string with details about the road construction object
    return "RoadConstruction{" +
        "ID=" + getID() +
        ", title='" + getTitle() + '\'' +
        ", budget=" + getBudget() +
        ", size=" + getSize() +
        ", address='" + getAddress() + '\'' +
        ", type=" + getType() +
        ", length=" + length +
        ", width=" + width +
        ", hasBridges=" + hasBridges +
        ", hasTunnels=" + hasTunnels +
        ", timeline=" + timeline +
        ", hasChallenges=" + hasChallenges +
        '}';
  }
}
