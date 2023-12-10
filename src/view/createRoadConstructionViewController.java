package view;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;


public class createRoadConstructionViewController
{
  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  private ProjectList projects;  // Declare as a field


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
  private TextField bridgesTextField;
  @FXML
  private TextField tunnelsTextField;


  @FXML
  private TextField challengesTextField;
  @FXML
  private Button backButton;
  @FXML
  private Button saveButton;
  @FXML
  private Button cancelButton;

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
  private Label errorLabelWidth;
  @FXML
  private Label errorLabelLength;
  @FXML
  private Label errorLabelBridgetOrTunnels;
  @FXML
  private Label errorLabelChallenges;
  @FXML
  private Label errorLabelGeneralError;

  private void printProjects() {
    System.out.println("Projects List:");
    for (Project project : projects.getAllProjects()) {
      System.out.println(project);
    }
    System.out.println("End of Projects List");
  }
  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("projects");
  }

  @FXML
  private void saveButtonClicked() {
    try
    {
      // Retrieve the inserted data
      int id = Integer.parseInt(idTextField.getText());
      String title = titleTextField.getText();
      double budget = Double.parseDouble(budgetTextField.getText());
      String address = addressTextField.getText();
int timeline = Integer.parseInt(timelineTextField.getText());
      double width = Double.parseDouble(widthTextField.getText());
      double length = Double.parseDouble(lengthTextField.getText());
      boolean hasBridges = Boolean.parseBoolean(bridgesTextField.getText());
      boolean hasTunnels = Boolean.parseBoolean(tunnelsTextField.getText());
      boolean hasChallenges = Boolean.parseBoolean(challengesTextField.getText());



      RoadConstruction.defaultRoadConstruction[0] = timeline;
      RoadConstruction.defaultRoadConstruction[1] = hasBridges;
      RoadConstruction.defaultRoadConstruction[2] = hasTunnels;
      RoadConstruction.defaultRoadConstruction[3] = hasChallenges;



      // Every Time you press save it will reset the label basically !
      errorLabelTitle.setText("");
      errorLabelId.setText("");
      errorLabelBudget.setText("");
      errorLabelTimeline.setText("");
      errorLabelWidth.setText("");
      errorLabelLength.setText("");
      errorLabelAddress.setText("");
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

      if (width <0 )
      {
        errorLabelWidth.setText("Negative width");
        return;
      }

      if(String.valueOf(width).length() ==0)
      {
        errorLabelWidth.setText("Empty width");
        return;
      }

      if (length <0 )
      {
        errorLabelLength.setText("Negative length");
        return;
      }

      if(String.valueOf(length).length() ==0)
      {
        errorLabelWidth.setText("Empty length");
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

      // Create a Residential object if input is valid
      RoadConstruction newRoadConstruction = new RoadConstruction(
          id, title, budget, address,ProjectType.ROADCONSTRUCTION,length,width,hasBridges,hasTunnels,timeline,hasChallenges);

      ProjectStorage.addProject(newRoadConstruction);
      ProjectStorage.printProjects();

    }
    catch (NumberFormatException e) {
      errorLabelGeneralError.setText("Check inputs");
    }
  }
  @FXML
  private void backButtonClicked() {
    viewHandler.openView("selectType");
  }
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    projects = new ProjectList();
    // initiate the default settings
    int defaultTimeline = (int) RoadConstruction.defaultRoadConstruction[0];
    boolean defaultHasBridges = (boolean) RoadConstruction.defaultRoadConstruction[1];
    boolean defaultHasTunnels = (boolean) RoadConstruction.defaultRoadConstruction[2];
    boolean defaultHasChallenges = (boolean) RoadConstruction.defaultRoadConstruction[3];


    // Inject the textfields with initiated defaults settings
    timelineTextField.setText(String.valueOf(defaultTimeline));
    bridgesTextField.setText(String.valueOf(defaultHasBridges));
    tunnelsTextField.setText(String.valueOf(defaultHasTunnels));
    challengesTextField.setText(String.valueOf(defaultHasChallenges));
  }

  public void reset()
  {
    titleTextField.clear();
    idTextField.clear();
    budgetTextField.clear();
    widthTextField.clear();
    lengthTextField.clear();
    addressTextField.clear();
    errorLabelGeneralError.setText("");


    viewHandler.openView("selectType");
  }

  public Region getRoot()
  {
    return root;
  }
}






