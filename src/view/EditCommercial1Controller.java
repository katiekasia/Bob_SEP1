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

public class EditCommercial1Controller
{
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
  private TextField numberOfFloorsField;

  @FXML
  private TextField useOfBuildingField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button saveButton;

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
  private Label errorLabelNrOfFloors;
  @FXML
  private Label errorLabelGeneralError;

  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;
  private Object[] defaultSettings;


  public Region getRoot()
  {
    return root;
  }

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    defaultSettings = DefaultSettingsHandler.loadCommercialDefaultSettings();
  }

  public void setProjectDetailsCommercial(Project selectedProject) {
    // Populate the TextFields with selectedProject details
    idField.setText(String.valueOf(selectedProject.getID()));
    titleField.setText(selectedProject.getTitle());
    budgetField.setText(String.valueOf(selectedProject.getBudget()));
    sizeField.setText(String.valueOf(selectedProject.getSize()));
    addressField.setText(selectedProject.getAddress());
    Commercial projectCommercial = (Commercial)selectedProject;
    useOfBuildingField.setText(String.valueOf(projectCommercial.getUseOfBuilding()));
    timelineField.setText(String.valueOf(projectCommercial.getTimeline()));
    numberOfFloorsField.setText(String.valueOf(projectCommercial.getNumberOfFloors()));
  }

  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("viewProject", null);
  }

  @FXML
  private void saveButtonClicked() {
    try {
      int id = Integer.parseInt(idField.getText());
      String title = titleField.getText();
      double budget = Double.parseDouble(budgetField.getText());
      double size = Double.parseDouble(sizeField.getText());
      String address = addressField.getText();
      int numberOfFloors = Integer.parseInt(numberOfFloorsField.getText());
      int timeline = Integer.parseInt(timelineField.getText());
      String useOfBuilding = useOfBuildingField.getText();


      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");

      Commercial oldCommercial=null;

      for(int i=0; i<allProjects.size(); i++)
      {
        if(allProjects.get(i).getID()==id)
        {
          oldCommercial= (Commercial) allProjects.get(i);
        }
      }
      Commercial newCommercial= (Commercial) oldCommercial;

      // Remove the old project from XML
      XMLwriter.removeProjectFromXML(oldCommercial, "projects.xml");

      // Add the updated project to XML
      ProjectStorage.removeProject(oldCommercial); // Remove the old project

      if (newCommercial != null) {
        // Update the existing project object with new values
        newCommercial.setTitle(title);
        newCommercial.setBudget(budget);
        newCommercial.setSize(size);
        newCommercial.setAddress(address);
        newCommercial.setNumberOfFloors(numberOfFloors);
        newCommercial.setTimeline(timeline);
        newCommercial.setUseOfBuilding(useOfBuilding);

        ProjectStorage.addProject(newCommercial); // Add the updated project

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


/*
try{
    int id = Integer.parseInt(idField.getText());
    String title = titleField.getText();
    double budget = Double.parseDouble(budgetField.getText());
    double size = Double.parseDouble(sizeField.getText());
    String address = addressField.getText();
    int numberOfFloors = Integer.parseInt(numberOfFloorsField.getText());
    int timeline = Integer.parseInt(timelineField.getText());
    String useOfBuilding = useOfBuildingField.getText();

    Commercial oldCommercial = new Commercial(
        id, title, budget, size, address,
        ProjectType.COMMERCIAL,numberOfFloors,timeline, useOfBuilding);

    Commercial newCommercial = new Commercial(
        id, title, budget, size, address,
        ProjectType.COMMERCIAL,numberOfFloors,timeline, useOfBuilding);

    newCommercial.setID(id);
    newCommercial.setTitle(title);
    newCommercial.setBudget(budget);
    newCommercial.setNumberOfFloors(numberOfFloors);
    newCommercial.setAddress(address);
    newCommercial.setTimeline(timeline);
    newCommercial.setSize(size);
    newCommercial.setUseOfBuilding(useOfBuilding);

    errorLabelTitle.setText("");
    errorLabelId.setText("");
    errorLabelBudget.setText("");
    errorLabelTimeline.setText("");
    errorLabelSize.setText("");
    errorLabelAddress.setText("");
    errorLabelNrOfFloors.setText("");
    errorLabelTimeline.setText("");
    errorLabelGeneralError.setText("");

    XMLwriter.removeProjectFromXML(oldCommercial, "projects.xml");
    ProjectStorage.removeProject(oldCommercial);

    ProjectStorage.addProject(newCommercial);
    XMLreader.readProjectsFromXML("projects.xml");
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
    for(int i = 0; i< ProjectStorage.getAllProjects().size(); i++)
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

    if (numberOfFloors <0 )
    {
      errorLabelNrOfFloors.setText("Negative number of kitchens");
      return;
    }

    if(String.valueOf(numberOfFloors).length() ==0)
    {
      errorLabelNrOfFloors.setText("Empty number of kitchens");
      return;
    }


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
 */