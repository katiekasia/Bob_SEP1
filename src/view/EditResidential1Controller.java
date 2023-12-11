package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Project;
import model.ProjectPlanningModel;
import model.ProjectType;
import model.Residential;

public class EditResidential1Controller
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
  private TextField sizeTextField;

  @FXML
  private TextField timelineTextField;

  @FXML
  private TextField addressTextField;

  @FXML
  private TextField numberOfKitchensTextField;

  @FXML
  private TextField numberOfBathroomsTextField;

  @FXML
  private TextField numberOfOtherRoomsTextField;

  @FXML
 private TextField isNewBuildingTextField;

  @FXML
  private Button backButton;
  @FXML
  private Button saveButton;
  @FXML
  private Button cancelButton;
  @FXML
  private Label errorLabel;


  public Region getRoot()
  {
    return root;
  }

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }
  public void setProjectDetailsResidential(Project selectedProject) {
    // Populate the TextFields with selectedProject details
    idTextField.setText(String.valueOf(selectedProject.getID()));
    titleTextField.setText(selectedProject.getTitle());
    budgetTextField.setText(String.valueOf(selectedProject.getBudget()));
    addressTextField.setText(selectedProject.getAddress());
    sizeTextField.setText(selectedProject.getAddress());
    Residential projectResidential = (Residential)selectedProject;
    timelineTextField.setText(String.valueOf(projectResidential.getTimeline()));
    numberOfBathroomsTextField.setText(String.valueOf(projectResidential.getNumberOfBathrooms()));
    numberOfKitchensTextField.setText(String.valueOf(projectResidential.getNumberOfKitchens()));
    numberOfOtherRoomsTextField.setText(String.valueOf(projectResidential.getNumberOfOtherRooms()));
    isNewBuildingTextField.setText(String.valueOf(projectResidential.getIsNewBuilding()));
  }
  public void reset()
  {
    // Reset logic
    init(viewHandler, model, root);
  }
  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("viewProject", null);
  }

  @FXML
  private void saveButtonClicked() {
    // Implement saving to XML functionality here

    // If input is incorrect, display errorLabel
    //    if (!validateInput()) {
    //      errorLabel.setText("Incorrect input!");
    //      errorLabel.setVisible(true);
    //    } else {
    //      // Example: Get data from text fields
    //      String title = titleTextField.getText();
    //      int id = Integer.parseInt(idTextField.getText());
    //      double budget = Double.parseDouble(budgetTextField.getText());
    //      // ...other fields
    //
    //      errorLabel.setVisible(false);
    //      viewHandler.openView("viewProject");
    //      // Save details to XML
    // }
  }

  private boolean validateInput()
  {
    // Implement input validation logic here
    // Return true if input is correct, false otherwise
    return true; // Placeholder - add validation checks
  }



}

