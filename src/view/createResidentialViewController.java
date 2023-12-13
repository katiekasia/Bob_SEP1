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

public class createResidentialViewController {

  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  private ProjectStorage projects;  // Declare as a field




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



  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("projects", null);
  }

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

    // Inject the textfields with initiated defaults settings
    numberOfKitchensTextField.setText(String.valueOf(defaultNumberOfKitchens));
    numberOfBathroomsTextField.setText(String.valueOf(defaultNumberOfBathrooms));
    numberOfOtherRoomsTextField.setText(String.valueOf(defaultNumberOfOtherRooms));
    isNewBuildingTextField.setText(String.valueOf(defaultIsNewBuilding));
    timelineTextField.setText(String.valueOf(defaultTimeline));
  }

  @FXML
  private void saveButtonClicked() {
    try
    {
      // Retrieve the inserted data
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

      defaultNumberOfKitchens = numberOfKitchens;
      defaultNumberOfBathrooms = numberOfBathrooms;
      defaultNumberOfOtherRooms = numberOfOtherRooms;
      defaultTimeline = timeline;
      defaultIsNewBuilding = isNewBuilding;


      // Every Time you press save it will reset the label basically !
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

      // Perform additional input validation checks for the following ones :
      // title cannot be empty and have elemtnts !@#$%^&*()_+ ( DONE )
      // ID cannot be negative and more then 6 digits and empty ( DONE )
      // Budget cannot be empty and negative ( DONE )
      //Size cannot be empty and negative ( DONE )
      // timeline cannot be empty and negative (DONE)
      //Address cannot contain elemtns !@#$%^&*()_+ and be empty ( DONE )
      //Nr of kitchen cannot be negative and empty ( DONE )
      //Nr of bathrooms cannot be negative and empty (DONE)
      //Nr of  other rooms cannot be negative and empty ( DONE )

      //Title doesn't contain special characters
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


      // Create a Residential object if input is valid
      Residential newResidential = new Residential(
          id, title, budget, size, address,
          ProjectType.RESIDENTIAL,
          numberOfKitchens, numberOfBathrooms, numberOfOtherRooms,
          isNewBuilding, timeline);

      ProjectStorage.addProject(newResidential);

      ProjectStorage.printProjects();
      // Write projects to XML
      ArrayList<Project> allProjects = ProjectStorage.getAllProjects();
      String filePath = "projects.xml"; // Set your desired file path
      XMLwriter.appendProjectsToXML(allProjects, filePath); // Call the XMLwriter method

      viewHandler.updateViewEditGeneralTable();
      viewHandler.openView("viewProject", null);


    }
    catch (NumberFormatException e) {
      errorLabelGeneralError.setText("Check inputs");
    }
  }

  @FXML
  private void backButtonClicked() {
    //    WHEN YOU PRESS BACK , EVERYTHING BASICALLY SETS BACK lol
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


  public void reset()
  {
    //    same as for back, by pressing it everything resets
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

  public Region getRoot()
  {
    return root;
  }
}
