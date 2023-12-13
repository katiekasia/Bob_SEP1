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
/**
 * This class represents the controller for managing the window
 * for editing a selected project of Residential type
 *
 * It has the next functions:initializes the view for editProject window, sets project details,
 * updates the project data.
 *  Works in coordination with the XML reader and writer.
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023
 **/
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
  private Label errorLabelGeneralError;

  private Object[] defaultSettings;


  /**
   * Gets the root node of the UI.
   *
   * @return The root node of the UI.
   */
  public Region getRoot()
  {
    return root;
  }
  /**
   * Initializes the controller with necessary dependencies(viewhandler,root, project model)
   * Default settings specific to Residential Project are retrieved from "DefaultSettingHandler".
   *
   * @param viewHandler Manages view tranzitions
   * @param model       Contains the project planning model data
   * @param root        Represents the root node of the UI.
   */
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    defaultSettings = DefaultSettingsHandler.loadResidentialDefaultSettings();
  }
  /**
   * Sets project details in the text fields.
   *
   * @param selectedProject Residential projects details will be displayed.
   */
    public void setProjectDetailsResidential(Project selectedProject) {
    // Populate the TextFields with selectedProject details
    idTextField.setText(String.valueOf(selectedProject.getID()));
    titleTextField.setText(selectedProject.getTitle());
    budgetTextField.setText(String.valueOf(selectedProject.getBudget()));
    addressTextField.setText(selectedProject.getAddress());
    sizeTextField.setText(String.valueOf(selectedProject.getSize()));
    Residential projectResidential = (Residential)selectedProject;

    //defaults for residential project
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
  /**
   * Resets the controller
   */
  public void reset()
  {
    // Reset logic
    init(viewHandler, model, root);
  }
  /**
   * Handles the action when the cancel button is clicked,by going back to the viewProject
   */
  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("viewProject", null);
  }
  /**
   * Handles the action when the "Save" button is clicked.
   * Updates project information in ProjectStorage and XML file.
   */
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

      //reads the projects details using the class XMLreader
      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
      Residential oldResidential=null;

      // Find the existing project by ID
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

      // remove old project from project storage
      ProjectStorage.removeProject(oldResidential);

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

        // Add the updated project
        ProjectStorage.addProject(newResidential);

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




}

