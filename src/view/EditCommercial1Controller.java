package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.ProjectPlanningModel;
import model.ProjectType;
import model.Residential;

public class EditCommercial1Controller
{
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
  private TextField numberOfFloorsTextField;

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
    viewHandler.openView("projects");
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
      int timeline = Integer.parseInt(timelineTextField.getText());
      int numberOfFloors = Integer.parseInt(numberOfFloorsTextField.getText());
      String useOfBuilding = useOfBuildingField.getText();

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
      if(size <= 0)
      {
        errorLabelTitle.setText("Invalid size");
        return;
      }
      if(timeline <= 0)
      {
        errorLabelTitle.setText("Invalid timeline");
        return;
      }
      if(numberOfFloors <= 0)
      {
        errorLabelTitle.setText("Invalid number of floors");
        return;
      }

      if(address.isEmpty())
      {
        errorLabelTitle.setText("Empty address");
        return;
      }

      if(useOfBuilding.isEmpty())
      {
        errorLabelTitle.setText("Empty use of building");
        return;
      }

      //ERROR LABEL FOR TRUE FALSE ???????????????,,


      // SETTERS DOPLNIT
      Residential newResidential = new Residential(
          id, title, budget, size, address,
          ProjectType.RESIDENTIAL,
           timeline);

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
