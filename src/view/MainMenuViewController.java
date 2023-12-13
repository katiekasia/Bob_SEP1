package view;

import dataPersistence.XMLreader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.ProjectPlanningModel;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controller class managing the main menu view of the application.
 * Handles actions related to the main menu's GUI elements, such as project creation,
 * viewing existing projects, and updating project counts.
 *
 * The view is controlled by this class through the associated FXML file.
 * @author Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023

 */

public class MainMenuViewController implements Initializable
{
  private ViewHandler viewHandler;

  @FXML private Label counterLabel;

  @FXML private ImageView imageView;

  private ProjectPlanningModel model;
  private Region root;
  /**
   * Initializes the controller with necessary dependencies(viewhandler,root, project model)
   *
   * @param viewHandler The ViewHandler instance managing view transitions
   * @param model       The ProjectPlanningModel instance handling project data
   * @param root        The root element of the main menu
   */

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    // every time the app ( main menu is lauched ) the method to update the label is launched
    updateCounterLabel();
  }
  /**
   * Get the root element of the main menu.
   *
   * @return The root Region of the main menu
   */
  public Region getRoot() {
    return root;
  }
  /**
   * Initializes the controller when the FXML "MainMenu"  is loaded.
   * Loads the application's logo image and sets it in the ImageView.
   *
   * @param url            The location of logo image
   * @param resourceBundle The resources used to localize the root object(logoImage)
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // Loads the logo  image
    String imagePath = "logo.png";
    Image image = new Image(getClass().getResourceAsStream(imagePath));

    // Set the image to the ImageView
    imageView.setImage(image);
  }
  /**
   * Opens the view window to create a new project.
   */
  @FXML public void createProject() {
    // Call the method to open SelectProjectType
    viewHandler.openView("selectType", null);
  }
  /**
   * Updates the counter label in the main menu with the number of projects.
   * Gets the number of projects from the 'projects.xml' file using XMLreader.
   */
  // updates the label in the main menu fxml with the number of projects from projects.fxml file ( see the method in the xml reader)
  public void updateCounterLabel()
  {
    int numberOfProjects = XMLreader.getNumberOfProjects("projects.xml");
    counterLabel.setText(String.valueOf(numberOfProjects));
  }
  /**
   * Opens the view window to display existing projects.
   */
  @FXML public void viewProject() {
      viewHandler.openView("viewProject", null); // Call the method to open View Projects
  }
  /**
   * Resets the controller logic.
   */
  public void reset() {
    init(viewHandler, model, root);
  }
}
