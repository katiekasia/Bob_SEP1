package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Project;
import model.ProjectPlanningModel;
import model.ProjectType;
import javafx.scene.control.TableView;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


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

    idTextField.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        // Handle the filtering logic here
        handleSearchByID();
      }
    });
    titleTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      handleSearchByTitle();
    });
    allRadioButton.setOnAction(event -> clearFieldsAndShowAll());


  }
  @FXML
  private void handleSearchByIDTextFieldAction(ActionEvent event) {
    handleSearchByID();
  }
  @FXML
  private void handleSearchByID() {
    String enteredID = idTextField.getText();

    if (enteredID.length() == 6 && enteredID.matches("\\d+")) {
      int projectID = Integer.parseInt(enteredID);

      // Get all projects from XML
      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");

      // Filter projects based on the entered ID
      ObservableList<Project> filteredProjects = FXCollections.observableArrayList();
      for (Project project : allProjects) {
        if (project.getID() == projectID) {
          filteredProjects.add(project);
        }
      }
      allRadioButton.setSelected(false);
      // Update the TableView with the filtered projects
      ProjectTable.setItems(filteredProjects);
    } else {
      // If the ID is not valid or the TextField is empty, show all projects
      updateTable();
      allRadioButton.setSelected(false);
    }
  }

  @FXML
  private void handleSearchByTitleTextFieldAction(ActionEvent event) {
    handleSearchByTitle();
  }

  @FXML
  private void handleSearchByTitle() {
    String enteredTitle = titleTextField.getText();

    if (!enteredTitle.isEmpty()) {
      // Get all projects from XML
      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");

      // Filter projects based on the entered title (case-insensitive)
      ObservableList<Project> filteredProjects = FXCollections.observableArrayList();
      for (Project project : allProjects) {
        if (project.getTitle().toLowerCase().contains(enteredTitle.toLowerCase())) {
          filteredProjects.add(project);
        }
      }


      // Update the TableView with the filtered projects
      ProjectTable.setItems(filteredProjects);
      allRadioButton.setSelected(false);

    } else {
      // If the title TextField is empty, show all projects
      updateTable();
      allRadioButton.setSelected(false);

    }
  }

  @FXML
  private void clearFieldsAndShowAll() {
    idTextField.clear();
    titleTextField.clear();
    // Clear other input fields as needed

    // Show all projects in the table view
    updateTable();
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
  private void handleIDTextFieldAction() {
    String enteredID = idTextField.getText();
    if (enteredID.length() == 6 && enteredID.matches("\\d+")) {
      int idToFilter = Integer.parseInt(enteredID);
      ArrayList<Project> filteredProjects = filterProjectsByID(idToFilter);
      updateTableWithFilteredProjects(filteredProjects);
    } else {
      // Handle invalid input (e.g., show an error message)
    }
  }

  private ArrayList<Project> filterProjectsByID(int id) {
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
    ArrayList<Project> filteredProjects = new ArrayList<>();

    for (Project project : allProjects) {
      if (project.getID() == id) {
        filteredProjects.add(project);
      }
    }

    return filteredProjects;
  }

  private void updateTableWithFilteredProjects(ArrayList<Project> projects) {
    ObservableList<Project> projectData = FXCollections.observableArrayList(projects);
    ProjectTable.setItems(projectData);
  }

  // ... (other existing code)


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