package model;

//this is our project class being abstract , and the project type gets the type from the enum class
public abstract class Project
{
  private int ID;
  private String title;
  private double budget;
  private double size;
  private String address;

  private ProjectType type;

public Project(int ID, String title,double budget, double size, String address, ProjectType type)
{
  this.ID= ID;
  this.title = title;
  this.budget = budget;
  this.size = size;
  this.address = address;
  this.type = type;
}

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
    this.ID = ID;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public double getBudget()
  {
    return budget;
  }

  public void setBudget(double budget)
  {
    this.budget = budget;
  }

  public double getSize()
  {
    return size;
  }

  public void setSize(double size)
  {
    this.size = size;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public ProjectType getType()
  {
    return type;
  }

  public void setType(ProjectType type)
  {
    this.type = type;
  }

  public boolean equals (Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Project other = (Project) obj;
     return ID == other.ID && title.equals(other.title) && budget==other.budget && size == other.size && address.equals(other.address);
  }

}
