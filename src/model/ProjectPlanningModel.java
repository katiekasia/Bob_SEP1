package model;

import java.util.ArrayList;

public interface ProjectPlanningModel
{
<<<<<<< Updated upstream
  public void createProject(Project project);
  ArrayList<Project> getAllProjects();
=======
>>>>>>> Stashed changes

  ArrayList<Project> getAllProjects();
  void addProject(Project project);
  void removeProject(Project project);
  Project getProjectByID(int ID);
  ArrayList<Project> getProjectsByBudgetRange(double minBudget, double maxBudget);
  ArrayList<Project> getProjectsByType(ProjectType projectTypeGiven);
  ArrayList<Project> getProjectsByTitle(String title);

}
