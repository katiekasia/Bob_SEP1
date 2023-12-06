package model;

public class RoadConstruction extends Project
{
  private double length;
  private double width;

  private int timeline;
  private boolean hasBridges;

  private boolean hasTunnels;

  private boolean hasChallenges;

  public static final Object[] defaultRoadConstruction = {18,false,false,false};

  public RoadConstruction(int ID, String title,double budget, double size, String address, ProjectType type,double length, double width,boolean hasBridges, boolean hasTunnels, int timeline, boolean hasChallenges)
  {
    super(ID,title,budget,size,address,type);
    this.length = length;
    this.width = width;
    this.timeline = (int) defaultRoadConstruction[0];
    this.hasBridges = (boolean) defaultRoadConstruction[1];
    this.hasTunnels = (boolean) defaultRoadConstruction[2];
    this.hasChallenges = (boolean) defaultRoadConstruction[3];

  }

  public double getLength()
  {
    return length;
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
