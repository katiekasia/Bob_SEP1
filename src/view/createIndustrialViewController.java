package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Industrial;
import model.ProjectPlanningModel;
import model.ProjectType;


public class createIndustrialViewController
{
  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  @FXML
  private TextField idTextField;
  @FXML
  private TextField titleTextField;

  @FXML
  private TextField budgetTextField;

  @FXML
  private TextField sizeTextField;

  @FXML
  private TextField timelineTextField;

  @FXML
  private TextField addressTextField;



  @FXML
  private TextField TypeOfFacilityTextField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button saveButton;


  @FXML
  private Label errorLabelTitle;
  @FXML
  private Label errorLabelId;
  @FXML
  private Label errorLabelTimeline;
  @FXML
  private Label errorLabelSize;
  @FXML
  private Label errorLabelAddress;
  @FXML
  private Label errorLabelBudget;
  @FXML
  private Label errorLabelTypeOfFacility;

<<<<<<< Updated upstream
=======
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    // initiate the default settings

    int defaultTimeline = (int) Industrial.defaultIndustrial[0];

    // Inject the textfields with initiated defaults settings

    timelineTextField.setText(String.valueOf(defaultTimeline));
  }
>>>>>>> Stashed changes
  @FXML
  private void cancelButtonClicked(ActionEvent event) {
    // Close the window
    cancelButton.getScene().getWindow().hide();
  }

  @FXML
<<<<<<< Updated upstream
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
=======
  private void saveButtonClicked() {
    try {
      // Retrieve the inserted data
      int id = Integer.parseInt(idTextField.getText());
      String title = titleTextField.getText();
      double budget = Double.parseDouble(budgetTextField.getText());
      double size = Double.parseDouble(sizeTextField.getText());
      String address = addressTextField.getText();
      String typeOfFacility = TypeOfFacilityTextField.getText();
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
      if(typeOfFacility.isEmpty())
      {
        errorLabelTypeOfFacility.setText("Invalid ");
        return;
      }

     /* if (!(isNewBuildingTextField.equals("true")) && !(isNewBuildingTextField.equals("false")) ){
        errorLabelIsNewBuilding.setText("Invalid value. Enter 'true' or 'false'");
        return;
      }*/

      // Create a Industrial object if input is valid
      Industrial newIndustrial = new Industrial(
          id, title, budget, size, address,
          ProjectType.INDUSTRIAL, typeOfFacility, timeline);

      System.out.println(newIndustrial);

    }
    catch (NumberFormatException e) {
      errorLabelId.setText("Invalid numeric format.");
      errorLabelTypeOfFacility.setText("Invalid ");
      errorLabelTitle.setText("Invalid title");
      errorLabelBudget.setText("Invalid budget");
      errorLabelTimeline.setText("Invalid timeline");
      errorLabelAddress.setText("Invalid address");
      errorLabelSize.setText("Invalid size");
    }
  }

  @FXML
  private void backButtonClicked() {
    viewHandler.openView("selectType");
>>>>>>> Stashed changes
  }

  private boolean validateInput()
  {
    // Implement input validation logic here
    // Return true if input is correct, false otherwise
    return true; // Placeholder - add validation checks
  }
<<<<<<< Updated upstream
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }
=======


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





