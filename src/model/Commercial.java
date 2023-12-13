package model; // a comment describing the package or its purpose, currently commented out.

// class representing a commercial project, extending the Project class
public class Commercial extends Project
{
  // private instance variables for commercial projects
  private int numberOfFloors; // number of floors in the building
  private int timeline; // project timeline in days
  private String useOfBuilding; // purpose or use of the building, e.g., office, retail, etc.

  // constructor for creating commercial objects
  // parameters: ID - project ID, title - project title, budget - project budget, size - project size,
  // address - project address, type - project type, numberOfFloors - number of floors in the building,
  // timeline - project timeline, useOfBuilding - purpose or use of the building
  public Commercial(int ID, String title, double budget, double size, String address, ProjectType type,
      int numberOfFloors, int timeline, String useOfBuilding)
  {
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

  // getter method for numberOfFloors
  public int getNumberOfFloors()
  {
    return numberOfFloors;
  }

  // setter method for numberOfFloors
  public void setNumberOfFloors(int numberOfFloors)
  {
    this.numberOfFloors = numberOfFloors;
  }

  // getter method for timeline
  public int getTimeline()
  {
    return timeline;
  }

  // setter method for timeline
  public void setTimeline(int timeline)
  {
    this.timeline = timeline;
  }

  // getter method for useOfBuilding
  public String getUseOfBuilding()
  {
    return useOfBuilding;
  }

  // setter method for useOfBuilding
  public void setUseOfBuilding(String useOfBuilding)
  {
    this.useOfBuilding = useOfBuilding;
  }

  // override toString method to provide a human-readable string representation of the commercial object
  public String toString() {
    // return a formatted string with details about the commercial object
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
