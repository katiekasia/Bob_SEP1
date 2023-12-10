package model;

import java.util.ArrayList;
<<<<<<< Updated upstream

public class ProjectPlanningModelManager implements ProjectPlanningModel
{
  public void ProjectPlanningModel()
  {
=======
>>>>>>> Stashed changes

public class ProjectPlanningModelManager implements ProjectPlanningModel {
  private ArrayList<Project> projectList;

  public ProjectPlanningModelManager() {
    this.projectList = new ArrayList<>();
  }

  @Override
  public ArrayList<Project> getAllProjects() {
    return projectList;
  }

<<<<<<< Updated upstream
  @Override public ArrayList<Project> getAllProjects()
  {
    return null;
  }

  // @Override public projectList<Project> getAllProjects()
  {
 //   return null;
=======
  @Override
  public void addProject(Project project) {
    projectList.add(project);
>>>>>>> Stashed changes
  }

  @Override
  public void removeProject(Project project) {
    projectList.remove(project);
  }

  @Override
  public Project getProjectByID(int ID) {
    for (Project project : projectList) {
      if (project.getID() == ID) {
        return project;
      }
    }
    return null;
  }

  @Override
  public ArrayList<Project> getProjectsByBudgetRange(double minBudget, double maxBudget) {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (Project project : projectList) {
      double projectBudget = project.getBudget();
      if (projectBudget >= minBudget && projectBudget <= maxBudget) {
        matchingProjects.add(project);
      }
    }
    return matchingProjects;
  }

  @Override
  public ArrayList<Project> getProjectsByType(ProjectType projectTypeGiven) {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (Project project : projectList) {
      if (project.getType() == projectTypeGiven) {
        matchingProjects.add(project);
      }
    }
    return matchingProjects;
  }

  @Override
  public ArrayList<Project> getProjectsByTitle(String title) {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (Project project : projectList) {
      if (project.getTitle().equalsIgnoreCase(title)) {
        matchingProjects.add(project);
      }
    }
    return matchingProjects;
  }
}