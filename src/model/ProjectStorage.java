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

  // Implement other methods as needed...
}