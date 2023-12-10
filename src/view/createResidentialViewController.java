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
<<<<<<< Updated upstream
  }
=======

// initiate the default settings
    int defaultNumberOfKitchens = (int) Residential.defaultResidential[0];
    int defaultNumberOfBathrooms = (int) Residential.defaultResidential[1];
    int defaultNumberOfOtherRooms = (int) Residential.defaultResidential[2];
    int defaultTimeline = (int) Residential.defaultResidential[3];
    boolean defaultIsNewBulding = (boolean) Residential.defaultResidential[4];

// Inject the textfields with initiated defaults settings
    numberOfKitchensTextField.setText(String.valueOf(defaultNumberOfKitchens));
    numberOfBathroomsTextField.setText(String.valueOf(defaultNumberOfBathrooms));
    numberOfOtherRoomsTextField.setText(String.valueOf(defaultNumberOfOtherRooms));
    isNewBuildingTextField.setText(String.valueOf(defaultIsNewBulding));
    timelineTextField.setText(String.valueOf(defaultTimeline));

  }

  @FXML
  private void saveButtonClicked() {
    try {
      // Retrieve the inserted data
      int id = Integer.parseInt(idTextField.getText());
      String title = titleTextField.getText();
      double budget = Double.parseDouble(budgetTextField.getText());
      double size = Double.parseDouble(sizeTextField.getText());
      String address = addressTextField.getText();
      int numberOfKitchens = Integer.parseInt(numberOfKitchensTextField.getText());
      int numberOfBathrooms = Integer.parseInt(numberOfBathroomsTextField.getText());
      int numberOfOtherRooms = Integer.parseInt(numberOfOtherRoomsTextField.getText());
      boolean isNewBuilding = Boolean.parseBoolean(isNewBuildingTextField.getText());
      int timeline = Integer.parseInt(timelineTextField.getText());

      // Perform additional input validation checks
      if (id <= 0 || String.valueOf(id).length()!= 6 || String.valueOf(id).isEmpty())
      {
        errorLabelId.setText("Invalid ID");
        return;
      }
      if(title.isEmpty() || !title.matches("[a-zA-Z]+"))
      {
        errorLabelTitle.setText("Invalid title");
        return;
      }
      if(budget <= 0 || String.valueOf(budget).isEmpty() || !String.valueOf(budget).matches("\\d+(\\.\\d+)?"))
      {
        errorLabelBudget.setText("Invalid budget");
        return;
      }
      if(timeline <= 0 )
      {
        errorLabelTimeline.setText("Invalid timeline");
        return;
      }
      if(size <= 0 || String.valueOf(size).isEmpty())
      {
        errorLabelSize.setText("Invalid size");
        return;
      }

      if (address.isEmpty() || !address.matches("[a-zA-Z]+")) {
        errorLabelAddress.setText("Invalid address");
        return;
      }
      if(numberOfKitchens <= 0 )
      {
        errorLabelNrOfKitchens.setText("Invalid numberOfKitchens");
        return;
      }
      if(numberOfBathrooms <= 0 || String.valueOf(numberOfBathrooms).isEmpty())
      {
        errorLabelNrOfBathrooms.setText("Invalid numberOfBathrooms");
        return;
      }
      if(numberOfOtherRooms <= 0 )
      {
        errorLabelNrOfRooms.setText("Invalid numberOfRooms");
        return;
      }
     /* if (!(isNewBuildingTextField.equals("true")) && !(isNewBuildingTextField.equals("false")) ){
        errorLabelIsNewBuilding.setText("Invalid value. Enter 'true' or 'false'");
        return;
      }*/

      // Create a Residential object if input is valid
      Residential newResidential = new Residential(
          id, title, budget, size, address,
          ProjectType.RESIDENTIAL,
          numberOfKitchens, numberOfBathrooms, numberOfOtherRooms,
          isNewBuilding, timeline);

      System.out.println(newResidential);

    }
    catch (NumberFormatException e) {
      errorLabelId.setText("Invalid ID.");
      errorLabelTitle.setText("Invalid title");
      errorLabelBudget.setText("Invalid budget");
      errorLabelTimeline.setText("Invalid timeline");
      errorLabelAddress.setText("Invalid address");
      errorLabelSize.setText("Invalid size");

      errorLabelNrOfKitchens.setText("Invalid numberOfKitchens");
      errorLabelNrOfBathrooms.setText("Invalid numberOfBathrooms");
      errorLabelNrOfRooms.setText("Invalid numberOfRooms");
      /*errorLabelIsNewBuilding.setText("Invalid value. Enter 'true' or 'false'");*/
    }
  }

  @FXML
  private void backButtonClicked() {
    viewHandler.openView("selectType");
  }
  private boolean validateInput()
  {
    // Implement input validation logic here
    // Return true if input is correct, false otherwise
    return true; // Placeholder - add validation checks
  }


>>>>>>> Stashed changes
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




