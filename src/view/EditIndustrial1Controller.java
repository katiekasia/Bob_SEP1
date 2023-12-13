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
 * for editing a selected project of Industrial type
 *
 * It has the next functions:initializes the view for editProject window, sets project details,
 * updates the project data.
 *  Works in coordination with the XML reader and writer.
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023
 **/
public class EditIndustrial1Controller
{

  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  @FXML private TextField idField;
  @FXML private TextField titleField;

  @FXML private TextField budgetField;

  @FXML private TextField sizeField;

  @FXML private TextField timelineField;

  @FXML private TextField addressField;

  @FXML private TextField typeOfFacilityField;

  @FXML
  private Label errorLabelGeneralError;

  private Object[] defaultSettings;
  /**
   * Three-argument constructor.
   * Initializes the controller with necessary dependencies(viewhandler,root, project model)
   * Default settings specific to Industrial Project are retrieved from "DefaultSettingHandler".
   *
   * @param viewHandler Manages view tranzitions
   * @param model       Contains the project planning model data
   * @param root        Represents the root node of the UI.
   */
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    defaultSettings = DefaultSettingsHandler.loadResidentialDefaultSettings();
  }
  /**
   * Resets the controller
   */
  public void reset()
  {

    init(viewHandler, model, root);
  }

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
   * Sets project details in the text fields.
   *
   * @param selectedProject Industrial project details will be displayed.
   */
  public void setProjectDetailsIndustrial(Project selectedProject)
  {
    // Populate the TextFields with selectedProject details
    idField.setText(String.valueOf(selectedProject.getID()));
    titleField.setText(selectedProject.getTitle());
    budgetField.setText(String.valueOf(selectedProject.getBudget()));
    sizeField.setText(String.valueOf(selectedProject.getSize()));
    addressField.setText(selectedProject.getAddress());
    Industrial projectIndustrial = (Industrial) selectedProject;
    //defaults
    timelineField.setText(String.valueOf(projectIndustrial.getTimeline()));
    typeOfFacilityField.setText(projectIndustrial.getTypeOfFacility());

  }
  /**
   * Handles the action when the cancel button is clicked,by going back to the viewProject
   */
  @FXML private void cancelButtonClicked()
  {
    viewHandler.openView("viewProject", null);
  }
  /**
   * Handles the action when the "Save" button is clicked.
   * Updates project information in ProjectStorage and XML file.
   */
  @FXML private void saveButtonClicked()
  {
    try {
      // Retrieving input data from text fields
      int id = Integer.parseInt(idField.getText());
      String title = titleField.getText();
      double budget = Double.parseDouble(budgetField.getText());
      double size = Double.parseDouble(sizeField.getText());
      String address = addressField.getText();
      int timeline = Integer.parseInt(timelineField.getText());
      String typeOfFacility = typeOfFacilityField.getText();

      //reads the projects details using the class XMLreader
      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
      Industrial oldIndustrial=null;

      // Find the existing project by ID
      for(int i=0; i<allProjects.size(); i++)
      {
        if(allProjects.get(i).getID()==id)
        {
          oldIndustrial= (Industrial) allProjects.get(i);
        }
      }
      Industrial newIndustrial= (Industrial) oldIndustrial;

      // Remove the old project from XML
      XMLwriter.removeProjectFromXML(oldIndustrial, "projects.xml");

      // remove old project from project storage
      ProjectStorage.removeProject(oldIndustrial);

      if (newIndustrial != null) {
        // Update the existing project object with new values
        newIndustrial.setTitle(title);
        newIndustrial.setBudget(budget);
        newIndustrial.setSize(size);
        newIndustrial.setAddress(address);
        newIndustrial.setTypeOfFacility(typeOfFacility);
        newIndustrial.setTimeline(timeline);

        // Add the updated project to storage
        ProjectStorage.addProject(newIndustrial);

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
