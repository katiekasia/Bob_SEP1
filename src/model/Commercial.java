package model;

public class Commercial extends Project
{
  private int numberOfFloors;
  private int timeline;
  private String useOfBuilding;


//  the same thing for default settings as for Residential
  public Commercial(int ID, String title, double budget, double size, String address, ProjectType type,int numberOfFloors,int timeline, String useOfBuilding)
  {
    super(ID,title,budget,size,address,type);

    Object[] defaultCommercial = DefaultSettingsHandler.loadCommercialDefaultSettings();
    this.numberOfFloors = (numberOfFloors != 0) ? numberOfFloors : (int) defaultCommercial[0];
    this.timeline = (timeline != 0) ? timeline : (int) defaultCommercial[1];
    this.useOfBuilding = useOfBuilding;
  }

  public int getNumberOfFloors()
  {
    return numberOfFloors;
  }

  public void setNumberOfFloors(int numberOfFloors)
  {
    this.numberOfFloors = numberOfFloors;
  }

  public int getTimeline()
  {
    return timeline;
  }

  public void setTimeline(int timeline)
  {
    this.timeline = timeline;
  }

  public String getUseOfBuilding()
  {
    return useOfBuilding;
  }

  public void setUseOfBuilding(String useOfBuilding)
  {
    this.useOfBuilding = useOfBuilding;
  }

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
