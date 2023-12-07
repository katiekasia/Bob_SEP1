package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.ProjectPlanningModel;

public class createCommercialViewController
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
  @FXML
  private void cancelButtonClicked() {
    cancelButton.getScene().getWindow().hide();
  }

  @FXML
  private void saveButtonClicked() {
    // Implement saving to XML functionality here
    // If input is incorrect, display errorLabel
    if (!validateInput()) {
      errorLabel.setText("Incorrect input!");
      errorLabel.setVisible(true);
    } else {
      errorLabel.setVisible(false);
      // Save details to XML
    }
  }
  @FXML
  private void backButtonClicked() {
    // Implement saving to XML functionality here
    // If input is incorrect, display errorLabel
    if (!validateInput()) {
      errorLabel.setText("Incorrect input!");
      errorLabel.setVisible(true);
    } else {
      errorLabel.setVisible(false);
      // Save details to XML
    }
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




