package model;

import java.util.ArrayList;

public class ProjectPlanningModelManager implements ProjectPlanningModel
{

  @Override public ArrayList<Project> getAllProjects()
  {
    return null;
  }

  @Override public void addProject(Project project)
  {

  }

  @Override public void removeProject(Project project)
  {

  }

  @Override public Project getProjectByID(int ID)
  {
    return null;
  }

  @Override public ArrayList<Project> getProjectsByBudgetRange()
  {
    return null;
  }

  @Override public ArrayList<Project> getProjectsByType(
      ProjectType projectTypeGiven)
  {
    return null;
  }

  @Override public ArrayList<Project> getProjectsByTitle(String title)
  {
    return null;
  }

  @Override public ArrayList<Project> getProjectsBySize()
  {
    return null;
  }
}
