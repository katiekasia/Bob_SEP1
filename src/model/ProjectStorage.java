package model;

import java.util.ArrayList;

public class ProjectStorage {
  private static ArrayList<Project> allProjects = new ArrayList<>();

  public static ArrayList<Project> getAllProjects() {
    return allProjects;
  }

  public static void addProject(Project project) {
    allProjects.add(project);
  }

  public static void removeProject(Project project) {
    allProjects.remove(project);
  }

  public static Project getProjectByID(int ID) {
    for (Project project : allProjects) {
      if (project.getID() == ID) {
        return project;
      }
    }
    return null;
  }


  public ArrayList<Project> getProjectsByBudgetRange(double minBudget, double maxBudget) {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (Project project : allProjects) {
      double projectBudget = project.getBudget();
      if (projectBudget >= minBudget && projectBudget <= maxBudget) {
        matchingProjects.add(project);
      }
    }
    return matchingProjects;
  }


  public ArrayList<Project> getProjectsByType(ProjectType projectTypeGiven) {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (Project project : allProjects) {
      if (project.getType() == projectTypeGiven) {
        matchingProjects.add(project);
      }
    }
    return matchingProjects;
  }


  public ArrayList<Project> getProjectsByTitle(String title)
  {
    ArrayList<Project> matchingProjects = new ArrayList<>();

    for (Project project : allProjects)
    {
      if (project.getTitle().equalsIgnoreCase(title))
      {
        matchingProjects.add(project);
      }
    }
    return matchingProjects;
  }
    public static void printProjects() {
      System.out.println("Projects List:");
      for (Project project : allProjects) {
        System.out.println(project);
      }
      System.out.println("End of Projects List");
    }
  }

