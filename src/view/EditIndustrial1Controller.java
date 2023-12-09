package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.ProjectPlanningModel;
import model.ProjectType;
import model.Residential;

public class EditIndustrial1Controller
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
  private TextField sizeTextField;

  @FXML
  private TextField timelineTextField;

  @FXML
  private TextField addressTextField;

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
  private Label errorLabelNrOfKitchens;
  @FXML
  private Label errorLabelNrOfBathrooms;
  @FXML
  private Label errorLabelNrOfRooms;
  @FXML
  private Label errorLabelIsNewBuilding;

    @FXML
    private TextField TypeOfFacilityField;

    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    @FXML
    private Label errorLabel;


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
      String typeOfFacility = TypeOfFacilityField.getText();

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
      if(address.isEmpty())
      {
        errorLabelTitle.setText("Empty address");
        return;
      }
      if(typeOfFacility.isEmpty())
      {
        errorLabelTitle.setText("Empty type of facility");
        return;
      }

      //ERROR LABEL FOR TRUE FALSE ???????????????,,


      // SETTERS DOPLNIT
      Residential newResidential = new Residential(
          id, title, budget, size, address,
          ProjectType.INDUSTRIAL);

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


