package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Project;
import model.ProjectPlanningModel;
import model.ProjectStorage;
import model.ProjectType;
import javafx.scene.control.TableView;
import java.util.ArrayList;

import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.ProjectStorage;
import model.ProjectPlanningModel;

import javax.swing.text.View;


public class ViewEditGeneralController
{

  @FXML
  private RadioButton allRadioButton;

  @FXML
  private TextField idTextField;

  @FXML
  private TextField titleTextField;

  @FXML
  private ChoiceBox<String> budgetChoiceBox;

  @FXML
  private ChoiceBox<String> typeChoiceBox;

  @FXML
  private ChoiceBox<String> sizeChoiceBox;

  @FXML
  private ChoiceBox<String> timelineChoiceBox;

  @FXML
  private TableView tableView;

  @FXML
  private TableView ProjectTable;
  @FXML
  private TableColumn<Project, String> title;
  @FXML
  private TableColumn<Project, Integer> ID;
  @FXML
  private TableColumn<Project, Double> budget;
  @FXML
  private TableColumn<Project, String> timeline;
  @FXML
  private TableColumn<Project, ProjectType> type;
  @FXML
  private Button DetailsEdit;

  private Button detailsEditButton;

  @FXML
  private Button deleteButton;

  @FXML
  private Button backButton;
  private ProjectPlanningModel model;
  private Region root;
  private ViewHandler viewHandler;

  public Region getRoot() {
    return root;
  }
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  public void initialize() {
    // Associate TableColumn with the respective property in the Project class
    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
    timeline.setCellValueFactory(new PropertyValueFactory<>("Timeline"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));

    // Populate TableView with project details from ProjectStorage

    updateTable();
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
    ObservableList<Project> projectData = FXCollections.observableArrayList(allProjects);
    ProjectTable.setItems(projectData);
  }

  public void updateTable() {
    ProjectTable.getItems().clear();
    // Populate TableView with project details from ProjectStorage
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
    ObservableList<Project> projectData = FXCollections.observableArrayList(allProjects);
    ProjectTable.setItems(projectData);
  }



  /*public void setProjectList(ProjectList projectList) {
    this.projectList = projectList;
  }


  @FXML
  private void handleAllRadioButtonAction() {
    if (allRadioButton.isSelected()) {
      // Show all projects in the table view
      // Example:
      tableView.setItems(projectList.getAllProjects());
    }
  }
  @FXML
  private void handleIDTextFieldAction() {
    String enteredID = idTextField.getText();
    if (enteredID.length() == 6 && enteredID.matches("\\d+")) {
      // Show project with entered ID in the table view
      // Example:
      Project project = projectList.getProjectByID(Integer.parseInt(enteredID));
      if (project != null) {
        tableView.setItems(projectList.getProjectAsList(project));
      }
    }
  }*/

  @FXML
  private void backButtonClicked() {

    viewHandler.openView("projects");


  }

  @FXML
  private void detailsButtonClicked() {
    // Implement saving to XML functionality here

    // If input is incorrect, display errorLabel
    //    if (!validateInput()) {
    //      errorLabel.setText("Incorrect input!");
    //      errorLabel.setVisible(true);
    //    } else {
    //      // Example: Get data from text fields
    //      String title = titleTextField.getText();
    //      int id = Integer.parseInt(idTextField.getText());
    //      double budget = Double.parseDouble(budgetTextField.getText());
    //      // ...other fields
    //
    //      errorLabel.setVisible(false);
    //      viewHandler.openView("viewProject");
    //      // Save details to XML
    // }
  }
  @FXML
  private void deleteButtonClicked() {
    Project selectedProject = (Project) ProjectTable.getSelectionModel().getSelectedItem();

    if (selectedProject != null) {
      // Remove from TableView
      ProjectTable.getItems().remove(selectedProject);

      // Remove from XML file
      boolean removed = XMLwriter.removeProjectFromXML(selectedProject, "projects.xml");
      if (removed) {
        System.out.println("Project removed from XML");
      } else {
        System.out.println("Error removing project from XML");
        // Add logic to handle the error
      }
    } else {
      // Handle case when no item is selected
      System.out.println("No project selected");
    }
  }


  private boolean validateInput()
  {
    // Implement input validation logic here
    // Return true if input is correct, false otherwise
    return true; // Placeholder - add validation checks
  }

  public void reset() {
    // Reset logic
    init(viewHandler, model, root);
  }

}