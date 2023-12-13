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
 * that creates the Residential project
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu
 * @version 3.0- December 2023
 **/

public class createResidentialViewController {

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
  private Label errorLabelGeneralError;

  private int defaultNumberOfKitchens;
  private int defaultNumberOfBathrooms;
  private int defaultNumberOfOtherRooms;
  private int defaultTimeline;
  private boolean defaultIsNewBuilding;


  /**
   * Three-argument constructor.
   * Initializes the controller with necessary dependencies(viewhandler,root, project model)
   * Default settings specific to Residential Project are retrieved from "DefaultSettingHandler"
   * +  defaultNumberOfKitchens is set to 1, defaultNumberOfBathrooms to 1,  defaultNumberOfOtherRooms to 1, defaulTimeline is set to 9, defaultUseOfBuilding to true.
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

    defaultNumberOfKitchens = 1;
    defaultNumberOfBathrooms = 1;
    defaultNumberOfOtherRooms = 1;
    defaultTimeline = 9;
    defaultIsNewBuilding = true;


    numberOfKitchensTextField.setText(String.valueOf(defaultNumberOfKitchens));
    numberOfBathroomsTextField.setText(String.valueOf(defaultNumberOfBathrooms));
    numberOfOtherRoomsTextField.setText(String.valueOf(defaultNumberOfOtherRooms));
    isNewBuildingTextField.setText(String.valueOf(defaultIsNewBuilding));
    timelineTextField.setText(String.valueOf(defaultTimeline));
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
   * Gets the information from input fields, validates the data(throws errors if the input is incorrect).
   * Creates a new Residential object, adds it to the project storage.
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
      double size = Double.parseDouble(sizeTextField.getText());
      String address = addressTextField.getText();
      int numberOfKitchens = Integer.parseInt(numberOfKitchensTextField.getText());
      int numberOfBathrooms = Integer.parseInt(numberOfBathroomsTextField.getText());
      int numberOfOtherRooms = Integer.parseInt(numberOfOtherRoomsTextField.getText());
      boolean isNewBuilding = Boolean.parseBoolean(isNewBuildingTextField.getText());
      int timeline = Integer.parseInt(timelineTextField.getText());

      //updates default values
      defaultNumberOfKitchens = numberOfKitchens;
      defaultNumberOfBathrooms = numberOfBathrooms;
      defaultNumberOfOtherRooms = numberOfOtherRooms;
      defaultTimeline = timeline;
      defaultIsNewBuilding = isNewBuilding;


      //clearing the error labels
      errorLabelTitle.setText("");
      errorLabelId.setText("");
      errorLabelBudget.setText("");
      errorLabelTimeline.setText("");
      errorLabelSize.setText("");
      errorLabelAddress.setText("");
      errorLabelNrOfKitchens.setText("");
      errorLabelNrOfRooms.setText("");
      errorLabelNrOfBathrooms.setText("");
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

      if (numberOfKitchens <0 )
      {
        errorLabelNrOfKitchens.setText("Negative number of kitchens");
        return;
      }

      if(String.valueOf(numberOfKitchens).length() ==0)
      {
        errorLabelNrOfKitchens.setText("Empty number of kitchens");
        return;
      }

      if (numberOfBathrooms <0 )
      {
        errorLabelNrOfBathrooms.setText("Negative number of bathrooms");
        return;
      }

      if(String.valueOf(numberOfBathrooms).length() ==0)
      {
        errorLabelNrOfBathrooms.setText("Empty number of bathrooms");
        return;
      }

      if (numberOfOtherRooms <0 )
      {
        errorLabelNrOfRooms.setText("Negative number of rooms");
        return;
      }

      if(String.valueOf(numberOfOtherRooms).length() ==0)
      {
        errorLabelNrOfRooms.setText("Empty number of rooms");
        return;
      }


      // Creating a new residential object
      Residential newResidential = new Residential(
          id, title, budget, size, address,
          ProjectType.RESIDENTIAL,
          numberOfKitchens, numberOfBathrooms, numberOfOtherRooms,
          isNewBuilding, timeline);

      // Adding the new Industrial object to the project storage
      ProjectStorage.addProject(newResidential);
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
    sizeTextField.clear();
    addressTextField.clear();;
    numberOfKitchensTextField.clear();
    numberOfBathroomsTextField.clear();
    numberOfOtherRoomsTextField.clear();
    isNewBuildingTextField.clear();
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
    sizeTextField.clear();
    addressTextField.clear();;
    numberOfKitchensTextField.clear();
    numberOfBathroomsTextField.clear();
    numberOfOtherRoomsTextField.clear();
    isNewBuildingTextField.clear();
    errorLabelGeneralError.setText("");
    init(viewHandler, model, root);
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
