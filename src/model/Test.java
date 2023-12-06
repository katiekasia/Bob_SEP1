package model;

public class Test
{
  public static void main(String[] args)
  {
//    Im checking the methods + creation of objects etc etc
    Commercial commercial1 = new Commercial(111,"Mall",150000,120,"Kamtjatka",ProjectType.COMMERCIAL,1,1,"Mall");
    Industrial industrial1 = new Industrial(123,"Factory",100000,50,"Here94",ProjectType.INDUSTRIAL,"Smth",1);
    Residential residential1 = new Residential(124,"Campus",100000,60,"There95",ProjectType.RESIDENTIAL,1,2,3,true,2);
    RoadConstruction roadConstruction1 = new RoadConstruction(125,"Highway",100000,150,"SomeWhere1",ProjectType.ROADCONSTRUCTION,41,42,true,true,2,true);

    ProjectList projects = new ProjectList();

    projects.addProject(commercial1);
    projects.addProject(industrial1);
    projects.addProject(residential1);
    projects.addProject(roadConstruction1);

//    for (int i =0; i <projects.getAllProjects().size();i++)
//    {
//      System.out.println(projects.getAllProjects().get(i));
//    }

    System.out.println(projects.getProjectsByBudgetRange(0,500000));











  }

}
