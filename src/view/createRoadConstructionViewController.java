package view;

import java.util.ArrayList;

import dataPersistence.XMLwriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;
import model.RoadConstruction;

/**
 * A class representing the controller for managing the window
 * that creates the Road Construction project
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu
 * @version 3.0- December 2023
 **/
public class createRoadConstructionViewController
{
  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  private ProjectStorage projects;

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
  private Label errorLabelTitle;
  @FXML
  private Label errorLabelId;
  @FXML
  private Label errorLabelTimeline;

  @FXML
  private Label errorLabelAddress;
  @FXML
  private Label errorLabelBudget;
  @FXML
  private Label errorLabelWidth;
  @FXML
  private Label errorLabelLength;
  @FXML
  private Label errorLabelGeneralError;

  private boolean defaultHasBridges;
  private boolean defaultHasChallenges;
  private boolean defaultHasTunnels;
  private int defaultTimeline;

  /**
   * Handles the action when the cancel button is clicked,by going back to the projects view when pressed.
   */
  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("projects", null);
  }

  /**
   * Three-argument constructor.
   * Initializes the controller with necessary dependencies(viewhandler,root, project model)
   * Default settings specific to Residential Project are retrieved from "DefaultSettingHandler"
   * +  defaultHasTunnels is set to "false",  defaultHasChallenges is set to "false",   defaultHasBridges is set to "false", defaulTimeline is set to 18.
   * Sets the default value for timelineField(and all other fields for default values) by converting the default values to strings and assigning them as the text content of the fields.
   *  Initializes "projects" as an instance of projectStorage class(handles the storage of the projects)
   * @param viewHandler Manages view tranzitions
   * @param model       Contains the project planning model data
   * @param root        Represents the root node of the UI.
   */
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    projects = new ProjectStorage();

    Object[] defaultSettings = DefaultSettingsHandler.loadResidentialDefaultSettings();

    // initiate the default settings
    defaultTimeline = 18;
    defaultHasBridges = false;
    defaultHasTunnels = false;
    defaultHasChallenges = false;

    timelineTextField.setText(String.valueOf(defaultTimeline));
    bridgesTextField.setText(String.valueOf(defaultHasBridges));
    tunnelsTextField.setText(String.valueOf(defaultHasTunnels));
    challengesTextField.setText(String.valueOf(defaultHasChallenges));
  }

  /**
   * Handles the save button when clicked action .
   * Gets the information from input fields, validates the data(throws errors if the input is incorrect).
   * Creates a new Road Construction object, adds it to the project storage.
   * Writes the updated list of projects to an XML file, and updates the view accordingly.
   *@throws  NumberFormatException
   */
  @FXML
  private void saveButtonClicked() {
    try
    {
      //gets the information inserted in the fields
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
      //updates default values
      defaultTimeline = timeline;
      defaultHasBridges = hasBridges;
      defaultHasTunnels = hasTunnels;
      defaultHasChallenges = hasChallenges;

      // Every Time you press save it will reset the label basically !
      errorLabelTitle.setText("");
      errorLabelId.setText("");
      errorLabelBudget.setText("");
      errorLabelTimeline.setText("");
      errorLabelWidth.setText("");
      errorLabelLength.setText("");
      errorLabelAddress.setText("");
      errorLabelGeneralError.setText("");

      //validating input,checking for errors
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

      // Creating a new Road Construction  object
      RoadConstruction newRoadConstruction = new RoadConstruction(
          id, title, budget, address,ProjectType.ROADCONSTRUCTION,length,width,
          hasBridges,hasTunnels,timeline,hasChallenges);

      // Adding the new Industrial object to the project storage
      ProjectStorage.addProject(newRoadConstruction);
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
    titleTextField.clear();
    idTextField.clear();
    budgetTextField.clear();
    widthTextField.clear();
    lengthTextField.clear();
    addressTextField.clear();
    errorLabelGeneralError.setText("");

    viewHandler.openView("selectType", null);
  }

  /**
   * Clears input fields and navigates back to the select type view.
   */
  public void reset()
  {
    titleTextField.clear();
    idTextField.clear();
    budgetTextField.clear();
    widthTextField.clear();
    lengthTextField.clear();
    addressTextField.clear();
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






