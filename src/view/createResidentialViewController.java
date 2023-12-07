package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Project;
import model.ProjectPlanningModel;

public class createResidentialViewController {

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
  private TextField buildingUseTextField;

  @FXML
  private Label errorLabel;

  @FXML
  private void handleSaveButton(ActionEvent event) {
    // Implement saving functionality to XML or database here
    // Validate input and save the residential project details
    // Example: Get data from text fields
    String title = titleTextField.getText();
    int id = Integer.parseInt(idTextField.getText());
    double budget = Double.parseDouble(budgetTextField.getText());
    // ...other fields
  }

  @FXML
  private void handleCancelButton(ActionEvent event) {
    // Close the window or perform cancel action
    // For example, close the stage
    errorLabel.setText(""); // Clear any error messages
    titleTextField.getScene().getWindow().hide(); // Hide the window
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




