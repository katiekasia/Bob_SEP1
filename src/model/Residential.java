/**
 * Class representing a residential project, extending the Project class.
 *
 * Author: Katarzyna, Catalina, Sandut, Samo, Sebastian
 * Version: 2.0 – December 2023
 */
package model;

/**
 * Class representing a residential project, extending the Project class
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023
 **/
public class Residential extends Project {
  // Private instance variables for residential projects
  private int numberOfKitchens; // Number of kitchens in the residential project
  private int numberOfBathrooms; // Number of bathrooms in the residential project
  private int numberOfOtherRooms; // Number of other rooms (excluding kitchens and bathrooms)
  private int timeline; // Project timeline in days
  private boolean isNewBuilding; // Flag indicating whether the residential project is a new building or not

  /**
   * 11 argument Constructor for creating residential objects.
   * Parameters:
   * @param ID - project ID
   *  @param title - project title
   *  @param budget - project budget
   *  @param size - project size,
   * @param address - project address,
   *  @param type - project type,
   *   @param numberOfKitchens - number of kitchens in the residential project,
   * @param numberOfBathrooms - number of bathrooms in the residential project,
   * @param numberOfOtherRooms - number of other rooms (excluding kitchens and bathrooms),
   * @param isNewBuilding - flag indicating whether the residential project is a new building or not,
   * @param timeline - project timeline in days
   */
  public Residential(int ID, String title, double budget, double size, String address, ProjectType type,
      int numberOfKitchens, int numberOfBathrooms, int numberOfOtherRooms, boolean isNewBuilding, int timeline) {
    // Call the constructor of the superclass (Project) with specified parameters
    super(ID, title, budget, size, address, type);

    // Load default residential settings using DefaultSettingsHandler
    Object[] defaultResidential = DefaultSettingsHandler.loadResidentialDefaultSettings();

    // Set numberOfKitchens to the provided value if not 0, otherwise set it to the default value
    this.numberOfKitchens = (numberOfKitchens != 0) ? numberOfKitchens : (int) defaultResidential[0];

    // Set numberOfBathrooms to the provided value if not 0, otherwise set it to the default value
    this.numberOfBathrooms = (numberOfBathrooms != 0) ? numberOfBathrooms : (int) defaultResidential[1];

    // Set numberOfOtherRooms to the provided value if not 0, otherwise set it to the default value
    this.numberOfOtherRooms = (numberOfOtherRooms != 0) ? numberOfOtherRooms : (int) defaultResidential[2];

    // Set timeline to the provided value if not 0, otherwise set it to the default value
    this.timeline = (timeline != 0) ? timeline : (int) defaultResidential[3];

    // Set isNewBuilding to the provided value
    this.isNewBuilding = isNewBuilding;
  }

  /** Getter method for numberOfKitchens
   * @return The numberOfKitchens in the residential project.
   * */
  public int getNumberOfKitchens() {
    return numberOfKitchens;
  }

  /**Setter method for numberOfKitchens
   *  @param numberOfKitchens*/
  public void setNumberOfKitchens(int numberOfKitchens) {
    this.numberOfKitchens = numberOfKitchens;
  }


  /** Getter method for numberOfBathrooms
   * @return The numberOfBathrooms in the residential project.
   * */
  public int getNumberOfBathrooms() {
    return numberOfBathrooms;
  }

  /**Setter method for numberOfBathrooms
   *  @param numberOfBathrooms*/
  public void setNumberOfBathrooms(int numberOfBathrooms) {
    this.numberOfBathrooms = numberOfBathrooms;
  }

  /** Getter method for NumberOfOtherRooms
   * @return The NumberOfOtherRooms in the residential project.
   * */
  public int getNumberOfOtherRooms() {
    return numberOfOtherRooms;
  }

  /** Setter method for numberOfOtherRooms
   *  @param numberOfOtherRooms*/
  public void setNumberOfOtherRooms(int numberOfOtherRooms) {
    this.numberOfOtherRooms = numberOfOtherRooms;
  }

  /** Getter method for timeline
   * @return The timeline  in the residential project.
   * */
  public int getTimeline() {
    return timeline;
  }

  /**Setter method for timeline
   *  @param timeline*/
  public void setTimeline(int timeline) {
    this.timeline = timeline;
  }

  /** Getter method for isNewBuilding
   * @return The isNewBuilding in the residential project.
   * */
  public boolean getIsNewBuilding() {
    return isNewBuilding;
  }

  /** Setter method for isNewBuilding
   *  @param isNewBuilding- true if it is,false if it isn't*/
  public void setIsNewBuilding(boolean isNewBuilding) {
    this.isNewBuilding = isNewBuilding;
  }

  /**
   * Override toString method to provide a human-readable string representation of the residential object
   *
   * @return A formatted string of the project information.
   */
  @Override
  public String toString() {
    // Return a formatted string with details about the residential object
    return "Residential{" +
        "ID=" + getID() +
        ", title='" + getTitle() + '\'' +
        ", budget=" + getBudget() +
        ", size=" + getSize() +
        ", address='" + getAddress() + '\'' +
        ", type=" + getType() +
        ", numberOfKitchens=" + numberOfKitchens +
        ", numberOfBathrooms=" + numberOfBathrooms +
        ", numberOfOtherRooms=" + numberOfOtherRooms +
        ", isNewBuilding=" + isNewBuilding +
        ", timeline=" + timeline +
        '}';
  }
}
