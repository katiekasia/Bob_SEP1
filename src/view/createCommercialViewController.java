package view;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

public class createCommercialViewController
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
  private TextField numberOfFloorsField;

  @FXML
  private TextField useOfBuildingField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button saveButton;

  @FXML
  private Button backButton;

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
  private Label errorLabelNrOfFloors;
  @FXML
  private Label errorLabelGeneralError;

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    projects = new ProjectList();

    // initiate the default settings
    int defaultNumberOfFloors = (int) Commercial.defaultCommercial[0];
    int defaultTimeline = (int) Commercial.defaultCommercial[1];
    // Inject the textfields with initiated defaults settings
    numberOfFloorsField.setText(String.valueOf(defaultNumberOfFloors));
    timelineField.setText(String.valueOf(defaultTimeline));
  }
  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("projects");
  }

  @FXML
  private void saveButtonClicked() {
    try{
    int id = Integer.parseInt(idField.getText());
    String title = titleField.getText();
    double budget = Double.parseDouble(budgetField.getText());
    double size = Double.parseDouble(sizeField.getText());
    String address = addressField.getText();
    int numberOfFloors = Integer.parseInt(numberOfFloorsField.getText());
    int timeline = Integer.parseInt(timelineField.getText());
    String useOfBuilding = useOfBuildingField.getText();



    Commercial.defaultCommercial[0] = numberOfFloors;
    Commercial.defaultCommercial[1] = timeline;

    errorLabelTitle.setText("");
    errorLabelId.setText("");
    errorLabelBudget.setText("");
    errorLabelTimeline.setText("");
    errorLabelSize.setText("");
    errorLabelAddress.setText("");
    errorLabelNrOfFloors.setText("");
    errorLabelTimeline.setText("");
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

    if (numberOfFloors <0 )
    {
      errorLabelNrOfFloors.setText("Negative number of kitchens");
      return;
    }

    if(String.valueOf(numberOfFloors).length() ==0)
    {
      errorLabelNrOfFloors.setText("Empty number of kitchens");
      return;
    }

    Commercial newCommercial = new Commercial(
        id, title, budget, size, address,
        ProjectType.COMMERCIAL,numberOfFloors,timeline, useOfBuilding);

    ProjectStorage.addProject(newCommercial);


      viewHandler.updateViewEditGeneralTable();
      viewHandler.openView("viewProject");
    ProjectStorage.printProjects();
      // Write projects to XML
      ArrayList<Project> allProjects = ProjectStorage.getAllProjects();
      String filePath = "projects.xml"; // Set your desired file path
      XMLwriter.appendProjectsToXML(allProjects, filePath); // Call the XMLwriter method
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
    addressField.clear();
    useOfBuildingField.clear();
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
    useOfBuildingField.clear();
    errorLabelGeneralError.setText("");
    viewHandler.openView("selectType");
  }

  public Region getRoot()
  {
    return root;
  }

}




