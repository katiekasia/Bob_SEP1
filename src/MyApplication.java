import model.Project;
import view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ProjectPlanningModelManager;
import view.XMLreader;

import java.util.ArrayList;

public class MyApplication extends Application
{
    ProjectPlanningModelManager model;
    @Override public void start(Stage primaryStage)
    {
        model=new ProjectPlanningModelManager();
        ViewHandler view = new ViewHandler(model);
        view.start(primaryStage);
       // initializeApp();//
    }
   /* public void initializeApp() {
        ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
        // Do something with existingProjects, like storing them for further use in the application
        model.addProject(Pro);
    }*/
}
