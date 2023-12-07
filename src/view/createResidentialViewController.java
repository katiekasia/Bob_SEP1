package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Project;
import model.ProjectPlanningModel;
import model.Residential;
import model.ProjectType;

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
  private TextField isNewBuildingTextField;

  @FXML
  private Button backButton;
  @FXML
  private Button saveButton;
  @FXML
  private Button cancelButton;
  @FXML
  private Label errorLabel;



  @FXML
  private void validID() {
    if(idTextField.getText().isEmpty())
    {
      errorLabel.setText("ID cannot be empty");
    }
  }

  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("projects");
  }

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

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
   {
     // retrive the inserted data + the defaults
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

     Residential newResidential = new Residential(
         id, title, budget, size, address,
         ProjectType.RESIDENTIAL,  // always residential , depending on the createTYPEviewController window
         numberOfKitchens, numberOfBathrooms, numberOfOtherRooms,
         isNewBuilding, timeline);

     System.out.println(newResidential);

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


  public void reset()
  {
    init(viewHandler, model, root);
  }

  public Region getRoot()
  {
    return root;
  }
}




