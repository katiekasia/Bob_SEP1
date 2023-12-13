package view;

import dataPersistence.XMLreader;
import dataPersistence.XMLwriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

import java.util.ArrayList;

public class EditResidential1Controller
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

  private Object[] defaultSettings;


  public Region getRoot()
  {
    return root;
  }

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    defaultSettings = DefaultSettingsHandler.loadResidentialDefaultSettings();
  }
    public void setProjectDetailsResidential(Project selectedProject) {
    // Populate the TextFields with selectedProject details
    idTextField.setText(String.valueOf(selectedProject.getID()));
    titleTextField.setText(selectedProject.getTitle());
    budgetTextField.setText(String.valueOf(selectedProject.getBudget()));
    addressTextField.setText(selectedProject.getAddress());
    sizeTextField.setText(String.valueOf(selectedProject.getSize()));
    Residential projectResidential = (Residential)selectedProject;
    timelineTextField.setText(String.valueOf(projectResidential.getTimeline()));
    numberOfBathroomsTextField.setText(String.valueOf(projectResidential.getNumberOfBathrooms()));
    numberOfKitchensTextField.setText(String.valueOf(projectResidential.getNumberOfKitchens()));
    numberOfOtherRoomsTextField.setText(String.valueOf(projectResidential.getNumberOfOtherRooms()));
    isNewBuildingTextField.setText(String.valueOf(projectResidential.getIsNewBuilding()));

      if (selectedProject == null) {
        numberOfKitchensTextField.setText(String.valueOf(defaultSettings[0]));
        numberOfBathroomsTextField.setText(String.valueOf(defaultSettings[1]));
        numberOfOtherRoomsTextField.setText(String.valueOf(defaultSettings[2]));
        isNewBuildingTextField.setText(String.valueOf(defaultSettings[4]));
        // Assuming the defaultSettings array has the correct order
      }
  }
  public void reset()
  {
    // Reset logic
    init(viewHandler, model, root);
  }
  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("viewProject", null);
  }

  @FXML
  private void saveButtonClicked() {
    try {
      int id = Integer.parseInt(idTextField.getText());
      String title = titleTextField.getText();
      double budget = Double.parseDouble(budgetTextField.getText());
      double size = Double.parseDouble(sizeTextField.getText());
      String address = addressTextField.getText();
      int numberOfKitchens = Integer.parseInt(numberOfKitchensTextField.getText());
      int numberOfBathrooms = Integer.parseInt(numberOfBathroomsTextField.getText());
      int numberOfOtherRooms = Integer.parseInt(numberOfOtherRoomsTextField.getText());
      int timeline = Integer.parseInt(timelineTextField.getText());
      boolean isNewBuilding = Boolean.parseBoolean(isNewBuildingTextField.getText());


      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");

      Residential oldResidential=null;

      for(int i=0; i<allProjects.size(); i++)
      {
        if(allProjects.get(i).getID()==id)
        {
          oldResidential= (Residential) allProjects.get(i);
        }
      }
      Residential newResidential= (Residential) oldResidential;

      // Remove the old project from XML
      XMLwriter.removeProjectFromXML(oldResidential, "projects.xml");

      // Add the updated project to XML
      ProjectStorage.removeProject(oldResidential); // Remove the old project

      if (newResidential != null) {
        // Update the existing project object with new values
        newResidential.setTitle(title);
        newResidential.setBudget(budget);
        newResidential.setSize(size);
        newResidential.setAddress(address);
        newResidential.setNumberOfBathrooms(numberOfBathrooms);
        newResidential.setTimeline(timeline);
        newResidential.setIsNewBuilding(isNewBuilding);
        newResidential.setNumberOfKitchens(numberOfKitchens);
        newResidential.setNumberOfOtherRooms(numberOfOtherRooms);


        ProjectStorage.addProject(newResidential); // Add the updated project

        // Write the updated projects to XML
        allProjects = ProjectStorage.getAllProjects();
        XMLwriter.appendProjectsToXML(allProjects, "projects.xml");

        // Update the ViewTable
        viewHandler.updateViewEditGeneralTable();
        viewHandler.openView("viewProject", null);
      }
      else {
        // Handle case where the project with the given ID doesn't exist
        errorLabelGeneralError.setText("Project not found");
      }
    } catch (NumberFormatException e) {
      errorLabelGeneralError.setText("Check inputs");
    }
  }
  private boolean validateInput()
  {
    // Implement input validation logic here
    // Return true if input is correct, false otherwise
    return true; // Placeholder - add validation checks
  }



}

