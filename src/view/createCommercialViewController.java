package view;

import java.util.ArrayList;

import dataPersistence.XMLwriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;
/**
 * A class representing the controller for managing the window
 * that creates the Commercial project
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu
 * @version 3.0- December 2023
 **/
public class createCommercialViewController
{
  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  private ProjectStorage projects;

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

  private int defaultNumberOfFloors;
  private int defaultTimeline;

  /**
        * Initializes the controller with necessary dependencies(viewhandler,root, project model)
        * Default settings specific to CommercialProject are retrieved from "DefaultSettingHandler"
        * + defaultNumbersOfFloors is set to 1 and defaulTimeline is set to 9
        * sets the default values for numberOfFloorsField and timelineField
        * by converting the default values to strings and assigning them as the text content of the fields
        *  Initializes "projects" as an instance of projectStorage class(handles the storage of the projects)
   * @param viewHandler Manages view tranzitions
   * @param model       Contains the project planning model data
   * @param root        Represents the root node of the UI.
   */
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    projects = new ProjectStorage();

    Object[] defaultSettings = DefaultSettingsHandler.loadCommercialDefaultSettings();
    defaultNumberOfFloors = 1;
    defaultTimeline = 9;

    numberOfFloorsField.setText(String.valueOf(defaultNumberOfFloors));
    timelineField.setText(String.valueOf(defaultTimeline));
  }
  /**
   * Handles the action when the cancel button is clicked,by going back to the projects view when pressed.
   */
  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("projects", null);
  }
  /**
   * Handles the save button when clicked action .
   * Gets the information from input fields, validates the data(throws errors if the input is incorrect),
   * creates a new Commercial object, adds it to the project storage,
   * writes the updated list of projects to an XML file, and updates the view accordingly.
   *@throws  NumberFormatException
   */
  @FXML
  private void saveButtonClicked() {
    try{
      //gets the information inserted in the fields
    int id = Integer.parseInt(idField.getText());
    String title = titleField.getText();
    double budget = Double.parseDouble(budgetField.getText());
    double size = Double.parseDouble(sizeField.getText());
    String address = addressField.getText();
    int numberOfFloors = Integer.parseInt(numberOfFloorsField.getText());
    int timeline = Integer.parseInt(timelineField.getText());
    String useOfBuilding = useOfBuildingField.getText();

    //updates default values for nroffloors and timeline
    defaultNumberOfFloors = numberOfFloors;
    defaultTimeline= timeline;

    //clearing the error labels
    errorLabelTitle.setText("");
    errorLabelId.setText("");
    errorLabelBudget.setText("");
    errorLabelTimeline.setText("");
    errorLabelSize.setText("");
    errorLabelAddress.setText("");
    errorLabelNrOfFloors.setText("");
    errorLabelTimeline.setText("");
    errorLabelGeneralError.setText("");

   //validating input
      //ensure title doesn't have numbers
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
   //ensured id has 6 digits
    if (String.valueOf(id).length() != 6)
    {
      errorLabelId.setText("ID should be 6 digits");
      return;
    }
  //ensured id is not negative
    if (id <= 0)
    {
      errorLabelId.setText("Negative ID");
      return;
    }
    //checks whether the ID entered for the new project matches the ID of any existing project
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
   // excludes negative values for budget and empty field for budget
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
      // excludes negative values for size and empty field for size
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
   // // excludes negative values for timeline
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
   //checks for illegal characters
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
      // excludes negative values
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
      // Creating a new Commercial object
    Commercial newCommercial = new Commercial(
        id, title, budget, size, address,
        ProjectType.COMMERCIAL,numberOfFloors,timeline, useOfBuilding);

    // // Adding the new Commercial object to the project storage
    ProjectStorage.addProject(newCommercial);

    ProjectStorage.printProjects();
      // Writing the updated project list to an XML file
      ArrayList<Project> allProjects = ProjectStorage.getAllProjects();
      String filePath = "projects.xml";
      XMLwriter.appendProjectsToXML(allProjects, filePath);

      // Updating the view after changes
      viewHandler.updateViewEditGeneralTable();
      viewHandler.openView("viewProject", null);
  }

  catch (NumberFormatException e) {
    // Handling input conversion errors
  errorLabelGeneralError.setText("Check inputs");
}
}
  /**
   * Handles the action when the back button is clicked, clearing the
   * fields and going  back to the select type view.
   */
  @FXML
  private void backButtonClicked() {
    titleField.clear();
    idField.clear();
    budgetField.clear();
    sizeField.clear();
    addressField.clear();
    useOfBuildingField.clear();
    errorLabelGeneralError.setText("");
    viewHandler.openView("selectType", null);
  }

  /**
   * Clears input fields and navigates back to the select type view.
   */
  public void reset()
  {
    titleField.clear();
    idField.clear();
    budgetField.clear();
    sizeField.clear();
    addressField.clear();;
    useOfBuildingField.clear();
    errorLabelGeneralError.setText("");
    viewHandler.openView("selectType", null);
  }
  /**
   * Provides access to the root node of the UI.
   *
   * @return The root node of the UI.
   */
  public Region getRoot()
  {
    return root;
  }

}




