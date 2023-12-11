package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Project;
import model.ProjectPlanningModel;
import model.RoadConstruction;

public class EditRoadConstructionController
{
  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  @FXML
  private TextField titleTextField;

  @FXML
  private TextField idTextField;

  @FXML
  private TextField budgetTextField;

  @FXML
  private TextField widthTextField;
  @FXML
  private TextField lengthTextField;

  @FXML
  private TextField timelineTextField;

  @FXML
  private TextField addressTextField;

  @FXML
  private TextField bridgesTextField;
  @FXML
  private TextField tunnelsTextField;

  @FXML
  private TextField challengesTextField;
  @FXML
  private Button backButton;
  @FXML
  private Button saveButton;
  @FXML
  private Button cancelButton;

  @FXML
  private Label errorLabel;

  public void setProjectDetailsRoadConstruction(Project selectedProject)
  {
    // Populate the TextFields with selectedProject details
    idTextField.setText(String.valueOf(selectedProject.getID()));
    titleTextField.setText(selectedProject.getTitle());
    budgetTextField.setText(String.valueOf(selectedProject.getBudget()));
    addressTextField.setText(selectedProject.getAddress());
    RoadConstruction projectRoad = (RoadConstruction) selectedProject;
    tunnelsTextField.setText(String.valueOf(projectRoad.getHasTunnels()));
    timelineTextField.setText(String.valueOf(projectRoad.getTimeline()));
    bridgesTextField.setText(String.valueOf(projectRoad.getHasBridges()));
    challengesTextField.setText(String.valueOf(projectRoad.getHasChallenges()));
    widthTextField.setText(String.valueOf(projectRoad.getWidth()));
    lengthTextField.setText(String.valueOf(projectRoad.getLength()));

  }
  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("viewProject", null);
  }

  @FXML
  private void saveButtonClicked() {
    // Implement saving to XML functionality here

    //    // If input is incorrect, display errorLabel
    //    if (!validateInput()) {
    //      errorLabel.setText("Incorrect input!");
    //      errorLabel.setVisible(true);
    //    } else {
    //      errorLabel.setVisible(false);
    //      viewHandler.openView("viewProject");
    //      // Save details to XML
    //    }
  }

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  public void reset()
  {
    // Reset logic
    init(viewHandler, model, root);
  }

  public Region getRoot()
  {
    return root;
  }
}
