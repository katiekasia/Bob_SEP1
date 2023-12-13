/**
 * Class representing a residential project, extending the Project class.
 *
 * Author: Katarzyna, Catalina, Sandut, Samo, Sebastian
 * Version: 2.0 – December 2023
 */
package model;
// Class representing a residential project, extending the Project class
public class Residential extends Project {
  // Private instance variables for residential projects
  private int numberOfKitchens; // Number of kitchens in the residential project
  private int numberOfBathrooms; // Number of bathrooms in the residential project
  private int numberOfOtherRooms; // Number of other rooms (excluding kitchens and bathrooms)
  private int timeline; // Project timeline in days
  private boolean isNewBuilding; // Flag indicating whether the residential project is a new building or not

  /**
   * Constructor for creating residential objects.
   * Parameters: ID - project ID, title - project title, budget - project budget, size - project size,
   * address - project address, type - project type, numberOfKitchens - number of kitchens in the residential project,
   * numberOfBathrooms - number of bathrooms in the residential project,
   * numberOfOtherRooms - number of other rooms (excluding kitchens and bathrooms),
   * isNewBuilding - flag indicating whether the residential project is a new building or not,
   * timeline - project timeline in days
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

  // Getter method for numberOfKitchens
  public int getNumberOfKitchens() {
    return numberOfKitchens;
  }

  // Setter method for numberOfKitchens
  public void setNumberOfKitchens(int numberOfKitchens) {
    this.numberOfKitchens = numberOfKitchens;
  }

  // Getter method for numberOfBathrooms
  public int getNumberOfBathrooms() {
    return numberOfBathrooms;
  }

  // Setter method for numberOfBathrooms
  public void setNumberOfBathrooms(int numberOfBathrooms) {
    this.numberOfBathrooms = numberOfBathrooms;
  }

  // Getter method for numberOfOtherRooms
  public int getNumberOfOtherRooms() {
    return numberOfOtherRooms;
  }

  // Setter method for numberOfOtherRooms
  public void setNumberOfOtherRooms(int numberOfOtherRooms) {
    this.numberOfOtherRooms = numberOfOtherRooms;
  }

  // Getter method for timeline
  public int getTimeline() {
    return timeline;
  }

  // Setter method for timeline
  public void setTimeline(int timeline) {
    this.timeline = timeline;
  }

  // Getter method for isNewBuilding
  public boolean getIsNewBuilding() {
    return isNewBuilding;
  }

  // Setter method for isNewBuilding
  public void setIsNewBuilding(boolean isNewBuilding) {
    this.isNewBuilding = isNewBuilding;
  }

  // Override toString method to provide a human-readable string representation of the residential object
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
