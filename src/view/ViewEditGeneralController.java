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

/**
 * This Controller class handles the viewing and filtered search of projects stored in XML files.
 * Manages the display of projects in a TableView, filtering by ID,Timeline,Budget,Title
 * , and handles the deleting of a project and editing of its details.
 *
 * @author Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko, Samo Susa
 * @version 3.0 - December 2023
 */

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

  /**
   * Gets the root element.
   *
   * @return The root element of the view.
   */
  public Region getRoot() {
    return root;
  }
  /**
   * Initializes the controller.
   *
   * @param viewHandler The handler for view-related actions.
   * @param model       The project planning model.
   * @param root        The root element of the view.
   */
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }
  /**
   * Initializes the view window and completes the next functions:
   * Populates the TableView with the projects form XML file.
   * Handle search by ID and title.
   * Adds options to choice boxes for budget,timeline and type filters,easing the search.
   */
  public void initialize()
  {
    // Initializes the type filter for project types(4 types)
    initializeTypeFilter();

    // Associate TableColumn with the respective property in the Project class
    title.setCellValueFactory(new PropertyValueFactory<>("title"));
    ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
    timeline.setCellValueFactory(new PropertyValueFactory<>("Timeline"));
    type.setCellValueFactory(new PropertyValueFactory<>("type"));

    // Load default settings for Residential projects
    Object[] defaultResidential = DefaultSettingsHandler.loadResidentialDefaultSettings();

    // Populate TableView with project details from ProjectStorage
    updateTable();
    // Read all projects from XML and populate the TableView
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML(
        "projects.xml");
    ObservableList<Project> projectData = FXCollections.observableArrayList(
        allProjects);
    ProjectTable.setItems(projectData);

    // allowing user to make text field changes to handle search by ID and title
    idTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      handleSearchByID();
    });

    titleTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      handleSearchByTitle();
    });
    // Set action for allRadioButton to clear filter fields and show all projects everytime when pressed
    allRadioButton.setOnAction(event -> clearFieldsAndShowAll());

    // Adding options to the budget choice box94 ranges) from which user can choose to search

    budgetChoiceBox.getItems().addAll("0-500000", "500001-2000000", "2000001-5000000","5000000 --> max");
    budgetChoiceBox.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          handleBudgetFilter(newValue);

        });
    // Add options to the timeline choice box94 options)
    timelineChoiceBox.getItems().addAll("0-12 months", "12-24 months", "24-36 months", "36 --> max");
    timelineChoiceBox.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          handleTimelineFilter(newValue);
        });
  }

  /**
   * Initializes the type choice box with options for 4 project types (Commercial, Residential, Industrial, RoadConstruction).
   * Based on the selected project type,the choice box
   * triggers the handling of filtering.
   */
  private void initializeTypeFilter() {
    // Adds project type options to the type choice box
    typeChoiceBox.getItems().addAll("Commercial", "Residential", "Industrial", "RoadConstruction");

    // Listen for changes in the type choice box
    //when a different project type is selected, it triggers the handleTypeFilter()
    //method to handle the filtering of projects based on the selected project type.
    typeChoiceBox.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          handleTypeFilter(newValue);
        });
  }

  /**
   * Filters the projects based on the selected project type.
   *
   * @param selectedType The selected project type to filter by.
   *                     If null, shows all projects without filtering by type.
   */
  private void handleTypeFilter(String selectedType) {
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
    ArrayList<Project> filteredProjects = new ArrayList<>();

    if (selectedType != null) {
      // Filters projects by the selected project type
      for (Project project : allProjects) {
        if (project.getType().toString().equalsIgnoreCase(selectedType)) {
          filteredProjects.add(project);
        }
      }
    } else {
      // Show all projects if type is null
      filteredProjects.addAll(allProjects);
    }
    // Updates the tableView with the filtered projects of the selected type
    updateTableWithFilteredProjects(filteredProjects);
  }

  /**
   * Filters the projects based on the selected timeline range.
   *
   * @param timelineRange The selected timeline range to filter by. Can be:
   *                      - "0-12 months": Filters projects with a timeline between 0 and 12 months (inclusive).
   *                      - "12-24 months": Filters projects with a timeline between 12 and 24 months (inclusive).
   *                      - "24-36 months": Filters projects with a timeline between 24 and 36 months (inclusive).
   *                      - "36 +": Filters projects with a timeline greater than 36 months.
   *                      If null, shows all projects without filtering by timeline.
   */
  private void handleTimelineFilter(String timelineRange) {
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
    ArrayList<Project> filteredProjects = new ArrayList<>();
    if (timelineRange != null) {
      // Filters projects based on the selected timeline range
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
    // Update the table with the filtered projects
    updateTableWithFilteredProjects(filteredProjects);
  }

  /**
   * Filters the projects by timeline range.
   *
   * @param minMonths    The minimum value of the timeline range.
   * @param maxMonths    The maximum value of the timeline range.
   * @param allProjects  The list of all projects to filter.
   * @return             The ArrayList of projects filtered by the specified timeline range.
   */
  private ArrayList<Project> filterProjectsByTimeline(int minMonths, int maxMonths, ArrayList<Project> allProjects) {
    ArrayList<Project> filteredProjects = new ArrayList<>();
    int projectTimeline=0;
    // Iterates through all projects  in the arraylist to filter by timeline range
    for (Project project : allProjects) {

      // Checking the timeline of each project types and filtering depending on the type
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
        // Filters and displays projects falling within the chosen timeline range
      }
      if (projectTimeline >= minMonths && projectTimeline <= maxMonths) {
        filteredProjects.add(project);
      }
    }

    return filteredProjects;
  }
  /**
   * Filters projects based on the chosen budget range(from 4 options).
   *
   * @param budgetRange The selected budget range to filter by.
   *                    If null, no filtering by budget range is applied.
   */
  private void handleBudgetFilter(String budgetRange) {
    if (budgetRange != null) {
      ArrayList<Project> filteredProjects = new ArrayList<>();
      // Switch statement to handle different budget ranges
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
      // Update the tableView  with the filtered projects based on the budget range chosen
      updateTableWithFilteredProjects(filteredProjects);
    }
  }
  /**
   * Filters projects based on the specified budget range.
   *
   * @param minBudget The minimum budget value to filter projects by.
   * @param maxBudget The maximum budget value to filter projects by.
   * @return An ArrayList of Project objects within the selected budget range.
   */
  private ArrayList<Project> filterProjectsByBudgetRange(double minBudget, double maxBudget)
  {
    // Get all projects from XML
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
    ArrayList<Project> filteredProjects = new ArrayList<>();

    // Iterate through each project in the list to filter by budget range
    for (Project project : allProjects) {
      double projectBudget = project.getBudget();
      if (projectBudget >= minBudget && projectBudget <= maxBudget)
      {
        // Add projects with budgets within the selected range to the filtered list
        filteredProjects.add(project);
      }
    }
    // Return the ArrayList containing projects that fall within the selected budget range
    return filteredProjects;
  }
  /**
   * Handles the action event triggered when searching for projects by ID.
   * Calls the 'handleSearchByID()' method to perform the project search based on the entered ID.
   *
   * @param event The ActionEvent triggered when searching for projects by ID in the user interface.
   */
  @FXML
  private void handleSearchByIDTextFieldAction(ActionEvent event) {
    handleSearchByID();
  }
  /**
   * Handles the search function based on the entered project ID.
   * Gets the ID from the text field, checks its validity, and filters the projects accordingly.
   * If a valid ID is entered, filters the projects to display only the project with the matching ID.
   * If the entered ID is invalid or the text field is empty, displays all projects.
   */
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
      // If the ID is not valid or the TextField is empty, shows all projects
      updateTable();
      allRadioButton.setSelected(false);
    }
  }
  /**
   * Handles the action triggered when text is entered in the title search field.
   * Calls the method "handleSearchByTitle()" to filter projects based on the entered title.
   *
   * @param event The ActionEvent triggered by text entry in the title search field.
   */
  @FXML
  private void handleSearchByTitleTextFieldAction(ActionEvent event) {
    handleSearchByTitle();
  }
  /**
   * Filters projects based on the entered title and updates the TableView in the necessary way.
   * If the title search field is empty, displays all projects from the list.
   * Updates the TableView with projects whose titles contain the entered text .
   * If no matching titles are found, displays all projects in the TableView.
   */
  @FXML
  private void handleSearchByTitle() {
    String enteredTitle = titleTextField.getText();

    if (!enteredTitle.isEmpty()) {
      // Get all projects from XML
      ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");

      // Filter projects based on the entered title
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
  /**
   * Method clears the selection and content of ChoiceBoxes and TextFields,
   * setting them to a default state.
   * Clears the selections and values in 'Type', 'Timeline', 'Budget' ChoiceBoxes,
   * as well as clears the 'ID' and 'Title' TextFields.
   * Resets the UI to display all projects in the TableView by calling the 'updateTable' method.
   */
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

    // Clear other input fields and display all projects in the table view
    updateTable();
  }


  /**
   * Updates the TableView with project details from the XML file.
   * Clears the existing content in the TableView, reads all projects from the "projects.xml" file
   * using XMLreader, converts the projects into an ObservableList,
   * and sets this updated project data to be displayed in the TableView.
   */
  public void updateTable() {
    ProjectTable.getItems().clear();
    // Populate TableView with project details from ProjectStorage
    ArrayList<Project> allProjects = XMLreader.readProjectsFromXML("projects.xml");
    ObservableList<Project> projectData = FXCollections.observableArrayList(allProjects);
    ProjectTable.setItems(projectData);
  }

  /**
   * Handles the action when the back button is clicked.
   * Clears the selections in the type, budget, and timeline choice boxes, sets their values to null,
   * and navigates the user back to the 'projects' view using the ViewHandler.
   */

  @FXML
  private void backButtonClicked() {
    typeChoiceBox.getSelectionModel().clearSelection();
    typeChoiceBox.setValue(null);
    budgetChoiceBox.getSelectionModel().clearSelection();
    budgetChoiceBox.setValue(null);
    timelineChoiceBox.getSelectionModel().clearSelection();
    timelineChoiceBox.setValue(null);

    // Navigate back to the 'projects' view
    viewHandler.openView("projects", null);


  }
  /**
   * Updates the TableView with a filtered list of projects.
   *
   * @param projects The list of projects to be displayed in the TableView.
   *                 The TableView is updated with these filtered projects.
   */
  private void updateTableWithFilteredProjects(ArrayList<Project> projects)
  {
    // Creation of an  ObservableList(for sychronization function) from the provided projects list
    ObservableList<Project> projectData = FXCollections.observableArrayList(projects);
    ProjectTable.setItems(projectData);
  }


  /**
   * Handles the action performed when the "Details" button is clicked.
   * Retrieves the selected project from the TableView and opens the edit window for the corresponding project type.
   * Uses the selected project's type to determine which edit view  window to open.
   */
  @FXML
  private void detailsButtonClicked() {
    // Get the selected project from the TableView
    Project selectedProject = (Project) ProjectTable.getSelectionModel().getSelectedItem();

    // Find the selected project by ID and its corresponding project type
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
  /**
   * Handles the action performed when the "Delete" button is clicked.
   * Deletes the selected project from both the TableView and the XML file.
   * If no project is selected, it logs a message indicating that no project is selected.
   */
  @FXML
  private void deleteButtonClicked()
  {
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
  /**
   * Resets the controller logic.
   */
  public void reset() {
    init(viewHandler, model, root);
  }


}
