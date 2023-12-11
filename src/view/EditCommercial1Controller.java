package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Commercial;
import model.Project;
import model.ProjectPlanningModel;
import model.Residential;

public class EditCommercial1Controller
{
  @FXML
  private TextField idField;
  @FXML
  private TextField titleField;

  @FXML
  private TextField budgetField;

  @FXML
  private TextField sizeField;

  @FXML
  private TextField timelineField;

  @FXML
  private TextField addressField;

  @FXML
  private TextField numberOfFloorsField;

  @FXML
  private TextField useOfBuildingField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button saveButton;

  @FXML
  private Button editButton;

  @FXML
  private Label errorLabel;
  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  public Region getRoot()
  {
    return root;
  }
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  public void setProjectDetailsCommercial(Project selectedProject) {
    // Populate the TextFields with selectedProject details
    idField.setText(String.valueOf(selectedProject.getID()));
    titleField.setText(selectedProject.getTitle());
    budgetField.setText(String.valueOf(selectedProject.getBudget()));
    sizeField.setText(String.valueOf(selectedProject.getSize()));
    addressField.setText(selectedProject.getAddress());
    Commercial projectCommercial = (Commercial)selectedProject;
    useOfBuildingField.setText(String.valueOf(projectCommercial.getUseOfBuilding()));
    timelineField.setText(String.valueOf(projectCommercial.getTimeline()));
    numberOfFloorsField.setText(String.valueOf(projectCommercial.getNumberOfFloors()));
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
  @FXML
  private void backButtonClicked() {
    viewHandler.openView("selectType", null);
  }
  private boolean validateInput()
  {
    // Implement input validation logic here
    // Return true if input is correct, false otherwise
    return true; // Placeholder - add validation checks
  }

  public void reset()
  {
    // Reset logic
    init(viewHandler, model, root);
  }

}
