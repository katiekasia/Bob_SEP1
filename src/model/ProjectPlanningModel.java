package model;

import java.util.ArrayList;

public interface ProjectPlanningModel
{
  ArrayList<Project> getAllProjects();

  void addProject(Project project);
  void removeProject(Project project);
  Project getProjectByID(int ID);

}
