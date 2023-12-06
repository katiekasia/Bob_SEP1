import view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ProjectPlanningModelManager;

public class MyApplication extends Application
{
    ProjectPlanningModelManager model;
    @Override public void start(Stage primaryStage)
    {
        model=new ProjectPlanningModelManager();
        ViewHandler view = new ViewHandler(model);
        view.start(primaryStage);
    }
}
