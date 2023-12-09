package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.ProjectPlanningModel;
import model.ProjectType;
import model.Residential;

public class editroadConstruction1Controller
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
  private TextField numberOfKitchensTextField;

  @FXML
  private TextField bridgesOrtunnelsTextField;

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
  private void cancelButtonClicked() {
    viewHandler.openView("projects");
  }

  @FXML
  private void saveButtonClicked() {
    try {
      // Retrieve the inserted data
      int id = Integer.parseInt(idTextField.getText());
      String title = titleTextField.getText();
      double budget = Double.parseDouble(budgetTextField.getText());
      String address = addressTextField.getText();
      int numberOfKitchens = Integer.parseInt(numberOfKitchensTextField.getText());
      int timeline = Integer.parseInt(timelineTextField.getText());

      int width = Integer.parseInt(widthTextField.getText());
      int length = Integer.parseInt(lengthTextField.getText());

      // Perform additional input validation checks
      if (id <= 0 || String.valueOf(id).length() != 6)
      {
        errorLabelId.setText("Invalid ID");
        return;
      }
      if(title.isEmpty())
      {
        errorLabelTitle.setText("Empty title");
        return;
      }
      if(budget <= 0)
      {
        errorLabelTitle.setText("Invalid budget");
        return;
      }

      if(timeline <= 0)
      {
        errorLabelTitle.setText("Invalid timeline");
        return;
      }
      if(address.isEmpty())
      {
        errorLabelTitle.setText("Empty address");
        return;
      }
      if(width <= 0)
      {
        errorLabelTitle.setText("Invalid width");
        return;
      }
      if(length <= 0)
      {
        errorLabelTitle.setText("Invalid length");
        return;
      }

      //ERROR LABEL FOR TRUE FALSE ???????????????,,


      // SETTERS DOPLNIT
      Residential newResidential = new Residential(
          id, title, budget, size, address,
          ProjectType.RESIDENTIAL,
          numberOfKitchens, numberOfBathrooms, numberOfOtherRooms,
          isNewBuilding, timeline);

      System.out.println(newResidential);

    }
    catch (NumberFormatException e) {
      errorLabelId.setText("Invalid numeric format.");
    }
  }
  @FXML
  private void backButtonClicked() {
    viewHandler.openView("selectType");
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
