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



  public ArrayList<Project> getProjectsByBudgetRange(double minBudget, double maxBudget)
  {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (Project project : projects)
    {
      double projectBudget = project.getBudget();

      if (projectBudget >= minBudget && projectBudget <= maxBudget) {
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





