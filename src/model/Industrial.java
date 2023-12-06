package model;

public class Industrial extends Project
{
  private String typeOfFacility;
  private int timeline;

  public static final Object[] defaultIndustrial = {30};

  public Industrial(int ID, String title, double budget, double size, String address, ProjectType type, String typeOfFacility,
      int timeline)
  {
    super(ID, title, budget, size, address, type);
    this.typeOfFacility = typeOfFacility;
    this.timeline = (int) defaultIndustrial[0];
  }

  public String getTypeOfFacility()
  {
    return typeOfFacility;
  }

  public void setTypeOfFacility(String typeOfFacility)
  {
    this.typeOfFacility = typeOfFacility;
  }

  public int getTimeline()
  {
    return timeline;
  }

  public void setTimeline(int timeline)
  {
    this.timeline = timeline;
  }

  public String toString()
  {
    return "Industrial{" + "ID=" + getID() + ", title='" + getTitle() + '\''
        + ", budget=" + getBudget() + ", size=" + getSize() + ", address='" + getAddress() + '\'' + ", type=" + getType() + ", typeOfFacility='"
        + typeOfFacility + '\'' + ", timeline=" + timeline + '}';
  }
}
