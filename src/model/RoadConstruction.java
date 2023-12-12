package model;

public class RoadConstruction extends Project
{
  private double length;
  private double width;

  private int timeline;
  private boolean hasBridges;

  private boolean hasTunnels;

  private boolean hasChallenges;

  public RoadConstruction(int ID, String title, double budget, String address, ProjectType type, double length, double width, boolean hasBridges, boolean hasTunnels, int timeline, boolean hasChallenges)
  {
    super(ID, title, budget, 0, address, type);

    Object[] defaultRoadConstruction = view.DefaultSettingsHandler.loadRoadConstructionDefaultSettings();
    this.length = (length != 0) ? length : (double) defaultRoadConstruction[0];
    this.width = (width != 0) ? width : (double) defaultRoadConstruction[1];
    this.timeline = (timeline != 0) ? timeline : (int) defaultRoadConstruction[2];
    this.hasBridges = hasBridges;
    this.hasTunnels = hasTunnels;
    this.hasChallenges = hasChallenges;
  }


  public double getLength()
  {
    return length;
  }

  public int getTimeline()
  {
    return timeline;
  }
  public void setTimeline(int timeline)
  {
    this.timeline=timeline;
  }

  public void setLength(double length)
  {
    this.length = length;
  }



  public double getWidth()
  {
    return width;
  }

  public void setWidth(double width)
  {
    this.width = width;
  }

  public boolean getHasBridges()
  {
    return hasBridges;
  }

  public void setHasBridges(boolean hasBridges)
  {
    this.hasBridges = hasBridges;
  }

  public boolean getHasChallenges()
  {
    return hasChallenges;
  }

  public void setHasChallenges(boolean hasChallenges)
  {
    this.hasChallenges = hasChallenges;
  }

  public boolean getHasTunnels()
  {
    return hasTunnels;
  }

  public void setHasTunnels(boolean hasTunnels)
  {
    this.hasTunnels = hasTunnels;
  }
  public String toString()
  {
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
