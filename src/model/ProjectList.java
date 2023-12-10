package model;

//This is our Projecs list class which will hold all project and methods to add, edit , remove and get project

import java.util.ArrayList;

public class ProjectList
//    I make a class called ProjectList
{
  private ArrayList<Project> projects;

  //  I create an instance variable name projects of type ArrayList which takes PROJECTS OBJECTS AND ALSO its subclasses like  OUR RESIDENTIAL AND SO ON

  public ProjectList()
  {
    projects = new ArrayList<Project>();
  }

  public ArrayList<Project> getAllProjects()
  {
    return projects;
  }

  public void addProject(Project project)
  {
    projects.add(project);
  }

  public void removeProject(Project project)
  {
    projects.remove(project);
  }

  public Project getProjectByID(int ID)
  {
    for (Project project : projects)
    {
      if (project.getID() == ID)
      {
        return project;
      }
    }
    return null;
  }
  public ArrayList<Project> getProjectsBySize(String sizeRange) {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (Project project : projects) {
      double projectSize = project.getSize();

      if (sizeRange.equals("0-100m2") && projectSize >= 0 && projectSize <= 100) {
        matchingProjects.add(project);
      } else if (sizeRange.equals("101-500m2") && projectSize > 100 && projectSize <= 500) {
        matchingProjects.add(project);
      } else if (sizeRange.equals("500+m2") && projectSize > 500) {
        matchingProjects.add(project);
      }
    }

    return matchingProjects;
  }



  public ArrayList<Project> getProjectsByBudgetRange()
  {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (Project project : projects)
    {
      double projectBudget = project.getBudget();

      if (projectBudget >= 0 && projectBudget <= 500000 ) {
        matchingProjects.add(project);
      }
      else if (projectBudget > 500000 && projectBudget <= 2000000 )
      {
        matchingProjects.add(project);
      }
      else if (projectBudget > 2000000 && projectBudget <= 10000000 )
      {
        matchingProjects.add(project);
      }

    }

    return matchingProjects;
  }

  //HAS to add to the diagram in (type: ProjectType)
  public ArrayList<Project> getProjectsByType(ProjectType projectTypeGiven)
  {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (int i=0; i< getAllProjects().size(); i++)
    {
      if (projects.get(i).getType().equals(projectTypeGiven)) {
        matchingProjects.add(projects.get(i));
      }
    }
    return matchingProjects;
  }
  /*
  public ArrayList<Project> getProjectsByTimeline(int timeline)
  {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (int i=0; i< getAllProjects().size(); i++)
    {
      if()
        matchingProjects.add(projects.get(i));
      }
    }
    return matchingProjects;
  }
*/
  public ArrayList<Project> getProjectsByTitle(String title)
  {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (int i=0; i< getAllProjects().size(); i++)
    {
      if (projects.get(i).getTitle().equals(title)) {
        matchingProjects.add(projects.get(i));
      }
    }
    return matchingProjects;
  }




}





