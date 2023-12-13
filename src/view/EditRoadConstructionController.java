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
  private Label errorLabelWidth;
  @FXML
  private Label errorLabelLength;
  @FXML
  private Label errorLabelBridgetOrTunnels;
  @FXML
  private Label errorLabelChallenges;
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

    defaultSettings = DefaultSettingsHandler.loadRoadConstructionDefaultSettings();
  }

  public void setProjectDetailsRoadConstruction(Project selectedProject)
  {
    // Populate the TextFields with selectedProject details
    idTextField.setText(String.valueOf(selectedProject.getID()));
    titleTextField.setText(selectedProject.getTitle());
    budgetTextField.setText(String.valueOf(selectedProject.getBudget()));
    addressTextField.setText(selectedProject.getAddress());
    RoadConstruction projectRoad = (RoadConstruction) selectedProject;
    tunnelsTextField.setText(String.valueOf(projectRoad.getHasTunnels()));
    timelineTextField.setText(String.valueOf(projectRoad.getTimeline()));
    bridgesTextField.setText(String.valueOf(projectRoad.getHasBridges()));
    challengesTextField.setText(String.valueOf(projectRoad.getHasChallenges()));
    widthTextField.setText(String.valueOf(projectRoad.getWidth()));
    lengthTextField.setText(String.valueOf(projectRoad.getLength()));

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
      String address = addressTextField.getText();
      int timeline = Integer.parseInt(timelineTextField.getText());

      double length = Double.parseDouble(lengthTextField.getText());
      double width = Double.parseDouble(widthTextField.getText());
      boolean hasBridges = Boolean.parseBoolean(bridgesTextField.getText());
      boolean hasChallenges = Boolean.parseBoolean(challengesTextField.getText());
      boolean hasTunnels = Boolean.parseBoolean(tunnelsTextField.getText());

      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");

      RoadConstruction oldRoadConstruction=null;

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

      // Add the updated project to XML
      ProjectStorage.removeProject(oldRoadConstruction); // Remove the old project

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

        ProjectStorage.addProject(newRoadConstruction); // Add the updated project

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

  public void reset()
  {
    // Reset logic
    init(viewHandler, model, root);
  }


}
