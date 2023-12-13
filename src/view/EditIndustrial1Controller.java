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


  @FXML private Button saveButton;
  @FXML private Button cancelButton;

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

  private Object[] defaultSettings;

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    defaultSettings = DefaultSettingsHandler.loadResidentialDefaultSettings();

  }

  public void reset()
  {
    // Reset logic
    init(viewHandler, model, root);
  }

  public Region getRoot()
  {
    return root;
  }

  public void setProjectDetailsIndustrial(Project selectedProject)
  {
    // Populate the TextFields with selectedProject details
    idField.setText(String.valueOf(selectedProject.getID()));
    titleField.setText(selectedProject.getTitle());
    budgetField.setText(String.valueOf(selectedProject.getBudget()));
    sizeField.setText(String.valueOf(selectedProject.getSize()));
    addressField.setText(selectedProject.getAddress());
    Industrial projectIndustrial = (Industrial) selectedProject;
    timelineField.setText(String.valueOf(projectIndustrial.getTimeline()));
    typeOfFacilityField.setText(projectIndustrial.getTypeOfFacility());

  }

  @FXML private void cancelButtonClicked()
  {
    viewHandler.openView("viewProject", null);
  }

  @FXML private void saveButtonClicked()
  {
    try {
      int id = Integer.parseInt(idField.getText());
      String title = titleField.getText();
      double budget = Double.parseDouble(budgetField.getText());
      double size = Double.parseDouble(sizeField.getText());
      String address = addressField.getText();
      int timeline = Integer.parseInt(timelineField.getText());
      String typeOfFacility = typeOfFacilityField.getText();


      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");

      Industrial oldIndustrial=null;

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

      // Add the updated project to XML
      ProjectStorage.removeProject(oldIndustrial); // Remove the old project

      if (newIndustrial != null) {
        // Update the existing project object with new values
        newIndustrial.setTitle(title);
        newIndustrial.setBudget(budget);
        newIndustrial.setSize(size);
        newIndustrial.setAddress(address);
        newIndustrial.setTypeOfFacility(typeOfFacility);
        newIndustrial.setTimeline(timeline);


        ProjectStorage.addProject(newIndustrial); // Add the updated project

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
