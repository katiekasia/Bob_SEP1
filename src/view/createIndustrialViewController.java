package view;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

public class createIndustrialViewController
{
  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  private ProjectList projects;  // Declare as a field


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
  private TextField typeOfFacilityField;
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
  private Label errorLabelGeneralError;
  @FXML
  private Label errorLabelTypeOfFacility;
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


  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    projects = new ProjectList();

    int defaultTimeline = (int) Industrial.defaultIndustrial[0];
    // Inject the textfields with initiated defaults settings
    timelineField.setText(String.valueOf(defaultTimeline));
  }

  @FXML
  private void saveButtonClicked() {
    try
    {
      // Retrieve the inserted data
      int id = Integer.parseInt(idField.getText());
      String title = titleField.getText();
      double budget = Double.parseDouble(budgetField.getText());
      double size = Double.parseDouble(sizeField.getText());
      String address = addressField.getText();
      int timeline = Integer.parseInt(timelineField.getText());
      String typeOfFacility = typeOfFacilityField.getText();


      Industrial.defaultIndustrial[0] = timeline;

      errorLabelTitle.setText("");
      errorLabelId.setText("");
      errorLabelBudget.setText("");
      errorLabelTimeline.setText("");
      errorLabelSize.setText("");
      errorLabelAddress.setText("");
      errorLabelTypeOfFacility.setText("");
      errorLabelGeneralError.setText("");

      if (!title.matches("^[a-zA-Z0-9_ ]*$"))
      {
        errorLabelTitle.setText("Invalid elements ");
        return;
      }
      //Ensure title is not empty
      if (title.isEmpty())
      {
        errorLabelTitle.setText("Title empty");
        return;
      }

      if (String.valueOf(id).length() != 6)
      {
        errorLabelId.setText("ID should be 6 digits");
        return;
      }

      if (id <= 0)
      {
        errorLabelId.setText("Negative ID");
        return;
      }
      for(int i=0; i<ProjectStorage.getAllProjects().size();i++)
      {
        if(ProjectStorage.getAllProjects().get(i).getID()==id)
        {
          errorLabelId.setText("ID already exist");
          return;
        }
      }

      if (String.valueOf(id).length() == 0)
      {
        errorLabelId.setText("Id cannot be empty");
        return;
      }

      if (budget <0 )
      {
        errorLabelBudget.setText("Negative budget");
        return;
      }

      if(String.valueOf(budget).length() ==0)
      {
        errorLabelBudget.setText("Empty Budget");
        return;
      }

      if (size <0 )
      {
        errorLabelSize.setText("Negative size");
        return;
      }

      if(String.valueOf(size).length() ==0)
      {
        errorLabelSize.setText("Empty size");
        return;
      }

      if (timeline <0 )
      {
        errorLabelTimeline.setText("Negative timeline");
        return;
      }

      if(String.valueOf(timeline).length() ==0)
      {
        errorLabelTimeline.setText("Empty timeline");
        return;
      }

      if (!address.matches("^[a-zA-Z0-9_ ]*$"))
      {
        errorLabelAddress.setText("Invalid elements ");
        return;
      }
      //Ensure addreses is not empty
      if (address.isEmpty())
      {
        errorLabelAddress.setText("Address is empty");
        return;
      }

      if (!typeOfFacility.matches("^[a-zA-Z0-9_ ]*$"))
      {
        errorLabelTypeOfFacility.setText("Invalid elements ");
        return;
      }

      if (typeOfFacility.isEmpty())
      {
        errorLabelAddress.setText("Type of facility is empty");
        return;
      }

      // Create a Residential object if input is valid
      Industrial newIndustrial = new Industrial(
          id, title, budget, size, address,
          ProjectType.INDUSTRIAL,
          typeOfFacility, timeline);

      ProjectStorage.addProject(newIndustrial);
      ProjectStorage.printProjects();

    }
    catch (NumberFormatException e) {
      errorLabelGeneralError.setText("Check inputs");
    }
  }


  @FXML
  private void backButtonClicked() {
    titleField.clear();
    idField.clear();
    budgetField.clear();
    sizeField.clear();
    addressField.clear();;
    typeOfFacilityField.clear();
    timelineField.clear();
    errorLabelGeneralError.setText("");
    viewHandler.openView("selectType");
  }


  public void reset()
  {
    titleField.clear();
    idField.clear();
    budgetField.clear();
    sizeField.clear();
    addressField.clear();;
    typeOfFacilityField.clear();
    timelineField.clear();
    errorLabelGeneralError.setText("");
    viewHandler.openView("selectType");
  }


  public Region getRoot()
  {
    return root;
  }
}





