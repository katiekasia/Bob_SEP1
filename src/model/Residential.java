package model;
import java.util.ArrayList;

public class Residential extends Project
{
  private int numberOfKitchens;
  private int numberOfBathrooms;
  private int numberOfOtherRooms;
  private int timeline;
  private boolean isNewBuilding;


  public static final Object[] defaultResidential = {1,1,1,9,true};
  //  I made an object arraylist which will store the defaults ( as it was done in Steffen video with Car )

  public Residential(int ID, String title, double budget, double size, String address, ProjectType type, int numberOfKitchens, int numberOfBathrooms, int numberOfOtherRooms, boolean isNewBuilding, int timeline)
  {
    super(ID,title,budget,size,address,type);
    this.numberOfKitchens = (int) defaultResidential[0];
    this.numberOfBathrooms = (int) defaultResidential[1];
    this.numberOfOtherRooms = (int) defaultResidential[2];
    this.timeline = (int) defaultResidential[3];
    this.isNewBuilding = (boolean) defaultResidential[4];
  }
  public int getNumberOfKitchens()
  {
    return numberOfKitchens;
  }

  public void setNumberOfKitchens(int numberOfKitchens)
  {
    this.numberOfKitchens = numberOfKitchens;
  }

  public int getNumberOfBathrooms()
  {
    return numberOfBathrooms;
  }
  public void setNumberOfBathrooms(int numberOfBathrooms)
  {
    this.numberOfBathrooms = numberOfBathrooms;
  }

  public int getNumberOfOtherRooms()
  {
    return numberOfOtherRooms;
  }

  public void setNumberOfOtherRooms(int numberOfOtherRooms)
  {
    this.numberOfOtherRooms = numberOfOtherRooms;
  }

  public int getTimeline()
  {
    return timeline;
  }

  public void setTimeline(int timeline)
  {
    this.timeline = timeline;
  }

  public boolean getIsNewBuilding()
  {
    return isNewBuilding;
  }

  public void setIsNewBuilding(boolean isNewBuilding)
  {
    this.isNewBuilding = isNewBuilding;
  }

  public String toString() {
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
