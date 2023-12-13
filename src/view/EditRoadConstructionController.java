package view;

import dataPersistence.XMLreader;
import dataPersistence.XMLwriter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

import java.util.ArrayList;
/**
 * This class represents the controller for managing the window
 * for editing a selected project of Road-Construction type
 *
 * It has the next functions:initializes the view for editProject window, sets project details,
 * updates the project data.
 *  Works in coordination with the XML reader and writer.
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023
 **/
public class EditRoadConstructionController
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
   * Default settings specific to Road Construction Project are retrieved from "DefaultSettingHandler".
   *
   * @param viewHandler Manages view tranzitions
   * @param model       Contains the project planning model data
   * @param root        Represents the root node of the UI.
   */
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    defaultSettings = DefaultSettingsHandler.loadRoadConstructionDefaultSettings();
  }
  /**
   * Sets project details in the text fields.
   *
   * @param selectedProject Road-Construction details will be displayed.
   */
  public void setProjectDetailsRoadConstruction(Project selectedProject)
  {
    // Populate the TextFields with selectedProject details
    idTextField.setText(String.valueOf(selectedProject.getID()));
    titleTextField.setText(selectedProject.getTitle());
    budgetTextField.setText(String.valueOf(selectedProject.getBudget()));
    addressTextField.setText(selectedProject.getAddress());

    //defaults
    RoadConstruction projectRoad = (RoadConstruction) selectedProject;
    tunnelsTextField.setText(String.valueOf(projectRoad.getHasTunnels()));
    timelineTextField.setText(String.valueOf(projectRoad.getTimeline()));
    bridgesTextField.setText(String.valueOf(projectRoad.getHasBridges()));
    challengesTextField.setText(String.valueOf(projectRoad.getHasChallenges()));
    widthTextField.setText(String.valueOf(projectRoad.getWidth()));
    lengthTextField.setText(String.valueOf(projectRoad.getLength()));

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
      String address = addressTextField.getText();
      int timeline = Integer.parseInt(timelineTextField.getText());

      double length = Double.parseDouble(lengthTextField.getText());
      double width = Double.parseDouble(widthTextField.getText());
      boolean hasBridges = Boolean.parseBoolean(bridgesTextField.getText());
      boolean hasChallenges = Boolean.parseBoolean(challengesTextField.getText());
      boolean hasTunnels = Boolean.parseBoolean(tunnelsTextField.getText());

      //reads the old projects details using the class XMLreader
      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
      RoadConstruction oldRoadConstruction=null;

      // Find the existing project by ID
      for(int i=0; i<allProjects.size(); i++)
      {
        if(allProjects.get(i).getID()==id)
        {
          oldRoadConstruction= (RoadConstruction) allProjects.get(i);
        }
      }
      RoadConstruction newRoadConstruction= (RoadConstruction) oldRoadConstruction;

      // Remove the old project from XML
      XMLwriter.removeProjectFromXML(oldRoadConstruction, "projects.xml");

      // remove old project from project storage
      ProjectStorage.removeProject(oldRoadConstruction);

      if (newRoadConstruction != null) {
        // Update the existing project object with new values
        newRoadConstruction.setTitle(title);
        newRoadConstruction.setBudget(budget);
        newRoadConstruction.setHasBridges(hasBridges);
        newRoadConstruction.setAddress(address);
        newRoadConstruction.setHasChallenges(hasChallenges);
        newRoadConstruction.setTimeline(timeline);
        newRoadConstruction.setWidth(width);
        newRoadConstruction.setLength(length);
        newRoadConstruction.setHasTunnels(hasTunnels);

        // Add the updated project
        ProjectStorage.addProject(newRoadConstruction);

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
  /**
   * Resets the controller
   */
  public void reset()
  {
    init(viewHandler, model, root);
  }


}
