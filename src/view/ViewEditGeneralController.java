package view;

import dataPersistence.XMLreader;
import dataPersistence.XMLwriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.*;
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
  private ChoiceBox<String> timelineChoiceBox;



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

  public void initialize()
  {

    initializeTypeFilter(); //

    // Associate TableColumn with the respective property in the Project class
    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
    timeline.setCellValueFactory(new PropertyValueFactory<>("Timeline"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));
    Object[] defaultResidential = DefaultSettingsHandler.loadResidentialDefaultSettings();

    // Populate TableView with project details from ProjectStorage

    updateTable();
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML(
        "projects.xml");
    ObservableList<Project> projectData = FXCollections.observableArrayList(
        allProjects);
    ProjectTable.setItems(projectData);


    idTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      handleSearchByID();
    });

    titleTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      handleSearchByTitle();
    });

    allRadioButton.setOnAction(event -> clearFieldsAndShowAll());

    budgetChoiceBox.getItems().addAll("0-500000", "500001-2000000", "2000001-5000000","5000000 --> max");
    // Listen for changes in the budget choice box
    budgetChoiceBox.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          handleBudgetFilter(newValue);

        });
    // Timeline choice box options
    timelineChoiceBox.getItems().addAll("0-12 months", "12-24 months", "24-36 months", "36 --> max");

    // Listen for changes in the timeline choice box
    timelineChoiceBox.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          handleTimelineFilter(newValue);
        });




  }


  private void initializeTypeFilter() {
    typeChoiceBox.getItems().addAll("Commercial", "Residential", "Industrial", "RoadConstruction");

    // Listen for changes in the type choice box
    typeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          handleTypeFilter(newValue);
        });
  }

  private void handleTypeFilter(String selectedType) {
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
    ArrayList<Project> filteredProjects = new ArrayList<>();

    if (selectedType != null) {
      for (Project project : allProjects) {
        if (project.getType().toString().equalsIgnoreCase(selectedType)) {
          filteredProjects.add(project);
        }
      }
    } else {
      // Show all projects if type is null
      filteredProjects.addAll(allProjects);
    }

    updateTableWithFilteredProjects(filteredProjects);
  }


  private void handleTimelineFilter(String timelineRange) {
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
    ArrayList<Project> filteredProjects = new ArrayList<>();
    if (timelineRange != null) {
      switch (timelineRange) {
        case "0-12 months":
          filteredProjects = filterProjectsByTimeline(0, 12, allProjects);
          break;
        case "12-24 months":
          filteredProjects = filterProjectsByTimeline(12, 24, allProjects);
          break;
        case "24-36 months":
          filteredProjects = filterProjectsByTimeline(24, 36, allProjects);
          break;
        case "36 +":
          filteredProjects = filterProjectsByTimeline(36, Integer.MAX_VALUE, allProjects);
          break;
      }
    } else {
      // Show all projects if timeline range is null
      filteredProjects.addAll(allProjects);
    }

    updateTableWithFilteredProjects(filteredProjects);
  }
  private ArrayList<Project> filterProjectsByTimeline(int minMonths, int maxMonths, ArrayList<Project> allProjects) {
    ArrayList<Project> filteredProjects = new ArrayList<>();
    int projectTimeline=0;

    for (Project project : allProjects) {

      if (project instanceof Commercial)
      {
        projectTimeline = ((Commercial) project).getTimeline();
      }
      else if (project instanceof Residential)
      {
        projectTimeline = ((Residential) project).getTimeline();
      }
      else if (project instanceof Industrial)
      {
        projectTimeline = ((Industrial) project).getTimeline();
      }
        else if (project instanceof RoadConstruction)
      {
      projectTimeline = ((RoadConstruction) project).getTimeline();
      }
      if (projectTimeline >= minMonths && projectTimeline <= maxMonths) {
        filteredProjects.add(project);
      }
    }

    return filteredProjects;
  }
  private void handleBudgetFilter(String budgetRange) {
    if (budgetRange != null) {
      ArrayList<Project> filteredProjects = new ArrayList<>();
      switch (budgetRange) {
        case "0-500000":
          filteredProjects = filterProjectsByBudgetRange(0, 500000);
          break;
        case "500001-2000000":
          filteredProjects = filterProjectsByBudgetRange(500001, 2000000);
          break;
        case "2000000-10000000":
          filteredProjects = filterProjectsByBudgetRange(2000000, 10000000);
          break;
        case "5000000 --> max":
          filteredProjects = filterProjectsByBudgetRange(5000000, 2147483647);
          break;
      }
      updateTableWithFilteredProjects(filteredProjects);
    }
  }
  private ArrayList<Project> filterProjectsByBudgetRange(double minBudget, double maxBudget) {
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
    ArrayList<Project> filteredProjects = new ArrayList<>();

    for (Project project : allProjects) {
      double projectBudget = project.getBudget();
      if (projectBudget >= minBudget && projectBudget <= maxBudget) {
        filteredProjects.add(project);
      }
    }

    return filteredProjects;
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
    typeChoiceBox.getSelectionModel().clearSelection();
    typeChoiceBox.setValue(null);
    timelineChoiceBox.getSelectionModel().clearSelection();
    timelineChoiceBox.setValue(null);
    budgetChoiceBox.getSelectionModel().clearSelection();
    budgetChoiceBox.setValue(null);
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



  @FXML
  private void backButtonClicked() {
    typeChoiceBox.getSelectionModel().clearSelection();
    typeChoiceBox.setValue(null);
    budgetChoiceBox.getSelectionModel().clearSelection();
    budgetChoiceBox.setValue(null);
    timelineChoiceBox.getSelectionModel().clearSelection();
    timelineChoiceBox.setValue(null);
    viewHandler.openView("projects", null);


  }

  private void updateTableWithFilteredProjects(ArrayList<Project> projects) {
    ObservableList<Project> projectData = FXCollections.observableArrayList(projects);
    ProjectTable.setItems(projectData);
  }



  @FXML
  private void detailsButtonClicked() {

    Project selectedProject = (Project) ProjectTable.getSelectionModel().getSelectedItem();

    if (selectedProject != null && selectedProject.getType() == ProjectType.COMMERCIAL) {
      // Retrieve details for a Commercial project
      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
      for (Project project : allProjects) {
        if (project.getID() == selectedProject.getID()) {
          if (project.getType() == ProjectType.COMMERCIAL) {
            // Open the edit window for Commercial project and pass its details
            viewHandler.openView("editCommercial", selectedProject);
            break;
          }
        }
      }
    }
    if (selectedProject != null && selectedProject.getType() == ProjectType.RESIDENTIAL) {
      // Retrieve details for a Commercial project
      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
      for (Project project : allProjects) {
        if (project.getID() == selectedProject.getID()) {
          if (project.getType() == ProjectType.RESIDENTIAL) {
            // Open the edit window for Residential project and pass its details
            viewHandler.openView("editResidential", selectedProject);
            break;
          }
        }
      }
    }
    if (selectedProject != null && selectedProject.getType() == ProjectType.INDUSTRIAL) {
      // Retrieve details for a Commercial project
      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
      for (Project project : allProjects) {
        if (project.getID() == selectedProject.getID()) {
          if (project.getType() == ProjectType.INDUSTRIAL) {
            // Open the edit window for Residential project and pass its details
            viewHandler.openView("editIndustrial", selectedProject);
            break;
          }
        }
      }
    }
    if (selectedProject != null && selectedProject.getType() == ProjectType.ROADCONSTRUCTION) {
      // Retrieve details for a Commercial project
      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
      for (Project project : allProjects) {
        if (project.getID() == selectedProject.getID()) {
          if (project.getType() == ProjectType.ROADCONSTRUCTION) {
            // Open the edit window for Residential project and pass its details
            viewHandler.openView("editRoadConstruction", selectedProject);
            break;
          }
        }
      }
    }


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

  public void reset() {
    // Reset logic
    init(viewHandler, model, root);
  }


}
