package model;

import java.util.ArrayList;

public class ProjectStorage
{
  private static ArrayList<Project> allProjects = new ArrayList<>();

  public static ArrayList<Project> getAllProjects()
  {
    return allProjects;
  }

  public static void addProject(Project project)
  {
    allProjects.add(project);
  }

  public static void removeProject(Project project)
  {
    allProjects.remove(project);
  }

  public static Project getProjectByID(int id)
  {
    for (Project project : allProjects)
    {
      if (project.getID() == id)
      {
        return project;
      }
    }
    return null;
  }

    public static void printProjects () {
    System.out.println("Projects List:");
    for (Project project : allProjects)
    {
      System.out.println(project);
    }
    System.out.println("End of Projects List");
  }
  }

