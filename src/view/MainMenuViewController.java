package view;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.ProjectPlanningModel;

public class MainMenuViewController
{
  private ViewHandler viewHandler;

  @FXML private Label title;
  @FXML private Button create;
  @FXML private Button view;
  @FXML private TextField nrOfProjects;

  private ProjectPlanningModel model;
  private Region root;

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  public Region getRoot() {
    return root;
  }

  @FXML public void createProject() {
      viewHandler.openView("selectType"); // Call the method to open SelectProjectType
  }

  @FXML public void viewProject() {
    try {
      viewHandler.openView("viewProject"); // Call the method to open View Projects
    } catch (IllegalArgumentException e) {

    }
  }

  public void reset() {
    // Reset logic
    init(viewHandler, model, root);
  }
}