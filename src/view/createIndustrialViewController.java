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
 * that creates the Industrial project
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu
 * @version 3.0- December 2023
 **/
public class createIndustrialViewController
{
  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  private ProjectStorage projects;
  private int defaultTimeline;

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

  /**
   * Three-argument constructor.
   * Initializes the controller with necessary dependencies(viewhandler,root, project model)
   * Default settings specific to Industrial Project are retrieved from "DefaultSettingHandler"
   * + defaulTimeline is set to 30
   * sets the default value for timelineField by converting the default value to string and assigning it as the text content of the fields
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

    Object[] defaultSettings = DefaultSettingsHandler.loadIndustrialDefaultSettings();

    defaultTimeline = 30;

    timelineField.setText(String.valueOf(defaultTimeline));
  }
  /**
   * Handles the save button when clicked action .
   * Gets the information from input fields, validates the data(throws errors if the input is incorrect).
   * Creates a new Industrial object, adds it to the project storage.
   * Writes the updated list of projects to an XML file, and updates the view accordingly.
   *@throws  NumberFormatException
   */
  @FXML
  private void saveButtonClicked() {
    try
    {
      //gets the information inserted in the fields
      int id = Integer.parseInt(idField.getText());
      String title = titleField.getText();
      double budget = Double.parseDouble(budgetField.getText());
      double size = Double.parseDouble(sizeField.getText());
      String address = addressField.getText();
      int timeline = Integer.parseInt(timelineField.getText());
      String typeOfFacility = typeOfFacilityField.getText();

      //updates default value timeline
      defaultTimeline= timeline;

      //clearing the error labels
      errorLabelTitle.setText("");
      errorLabelId.setText("");
      errorLabelBudget.setText("");
      errorLabelTimeline.setText("");
      errorLabelSize.setText("");
      errorLabelAddress.setText("");
      errorLabelTypeOfFacility.setText("");
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
      //ensured id input  is not negative
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
      //  excludes negative values for timeline
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
      //checks for illegal characters
      if (!typeOfFacility.matches("^[a-zA-Z0-9_ ]*$"))
      {
        errorLabelTypeOfFacility.setText("Invalid elements ");
        return;
      }
      //Ensure type of facility field is not empty
      if (typeOfFacility.isEmpty())
      {
        errorLabelTypeOfFacility.setText("Type of facility is empty");
        return;
      }

      // Creating a new Industrial object
      Industrial newIndustrial = new Industrial(
          id, title, budget, size, address,
          ProjectType.INDUSTRIAL,
          typeOfFacility, timeline);

      // Adding the new Industrial object to the project storage
      ProjectStorage.addProject(newIndustrial);
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
   * Handles the action when the cancel button is clicked,by going back to the projects view when pressed.
   */
  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("projects", null);
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
    addressField.clear();;
    typeOfFacilityField.clear();
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
    typeOfFacilityField.clear();
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





