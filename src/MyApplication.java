import view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ProjectPlanningModelManager;
/**
 * Main application class responsible for launching the project planning application.
 * Extends JavaFX's Application class to initialize and start the  interface.
 *  @author Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 *  @version 3.0 - December 2023
 */
public class MyApplication extends Application
{

    ProjectPlanningModelManager model;
    /**
     * Entry point for launching the application.
     * Initializes the ProjectPlanningModelManager and sets up the ViewHandler to start the application.
     * @param primaryStage The primary stage for the JavaFX application.
     */
    @Override public void start(Stage primaryStage)
    {
        model=new ProjectPlanningModelManager();
        ViewHandler view = new ViewHandler(model);
        view.start(primaryStage);

    }

}


