package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Project;

public class createRoadConstructionViewController
{
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
  private TextField numberOfKitchensTextField;

  @FXML
  private TextField birdgesOrtunnelsTextField;

  @FXML
  private TextField challengesTextField;

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



    // Save project data or perform other necessary actions
  }

  @FXML
  private void handleCancelButton(ActionEvent event) {
    // Close the window or perform cancel action
    // For example, close the stage
    errorLabel.setText(""); // Clear any error messages
    titleTextField.getScene().getWindow().hide(); // Hide the window
  }
}




