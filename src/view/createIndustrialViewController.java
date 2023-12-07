package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class createIndustrialViewController
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
  private TextField TypeOfFacilityField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button saveButton;

  @FXML
  private Label errorLabel;

  @FXML
  private void cancelButtonClicked(ActionEvent event) {
    // Close the window
    cancelButton.getScene().getWindow().hide();
  }

  @FXML
  private void saveButtonClicked(ActionEvent event) {
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
  }}



