package model;

import java.util.ArrayList;

public class Test
{
  public static void main(String[] args)
  {
<<<<<<< Updated upstream
    //    Im checking the methods + creation of objects etc etc
    Commercial commercial1 = new Commercial(111, "Mall", 150000, 120,
        "Kamtjatka", ProjectType.COMMERCIAL, 1, 1, "Mall");
    Industrial industrial1 = new Industrial(123, "Factory", 100000, 50, "Here94",
        ProjectType.INDUSTRIAL, "Smth", 1);
    RoadConstruction roadConstruction1 = new RoadConstruction(125, "Highway",
        100000, 150, "SomeWhere1", ProjectType.ROADCONSTRUCTION, 41, 42, true,
        true, 2, true);
=======
//    Im checking the methods + creation of objects etc etc
    Commercial commercial1 = new Commercial(111,"Mall",150000,120,"Kamtjatka",ProjectType.COMMERCIAL,1,1,"Mall");
    Industrial industrial1 = new Industrial(123,"Factory",100000,50,"Here94",ProjectType.INDUSTRIAL,"Smth",1);
>>>>>>> Stashed changes

    ProjectList projects = new ProjectList();

    projects.addProject(commercial1);
    projects.addProject(industrial1);

    //    for (int i =0; i <projects.getAllProjects().size();i++)
    //    {
    //      System.out.println(projects.getAllProjects().get(i));
    //    }

   /* @Test
    public void testGetAllProjects() {
    ArrayList<Project> allProjects = ProjectList.getAllProjects();
    assertEquals(4, allProjects.size());
  }

    @Test
    public void testAddProject() {
    Project newProject = new Project(5, "New Project", ProjectType.COMMERCIAL, 1500000);
    ProjectList.addProject(newProject);
    ArrayList<Project> allProjects = ProjectList.getAllProjects();
    assertEquals(5, allProjects.size());
    assertTrue(allProjects.contains(newProject));
  }

    @Test
    public void testRemoveProject() {
    Project projectToRemove = ProjectList.getProjectByID(2);
    projectList.removeProject(projectToRemove);
    ArrayList<Project> allProjects = projectList.getAllProjects();
    assertEquals(3, allProjects.size());
    assertFalse(allProjects.contains(projectToRemove));
  }

    @Test
    public void testGetProjectByID() {
    Project project = projectList.getProjectByID(3);
    assertNotNull(project);
    assertEquals("Project 3", project.getTitle());
    assertEquals(ProjectType.INDUSTRIAL, project.getType());
  }

    @Test
    public void testGetProjectsByBudgetRange() {
    ArrayList<Project> budgetRange1 = projectList.getProjectsByBudgetRange();
    // Add assertions to check the correctness of budget ranges here
    // Example: assertEquals(expectedSize, budgetRange1.size());
  }

    @Test
    public void testGetProjectsByType() {
    ArrayList<Project> typeProjects = projectList.getProjectsByType(ProjectType.RESIDENTIAL);
    assertEquals(2, typeProjects.size());
    // Add more assertions to check the correctness of project types
  }

    @Test
    public void testGetProjectsByTitle() {
    ArrayList<Project> titleProjects = projectList.getProjectsByTitle("Project 2");
    assertEquals(1, titleProjects.size());
    // Add more assertions to check the correctness of project title
  }*/

  }
}
