package view;

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

public class MainMenuViewController implements Initializable
{
  private ViewHandler viewHandler;

  @FXML private Label title;
  @FXML private Button create;
  @FXML private Button view;
  @FXML private Label counterLabel;
  @FXML private TextField nrOfProjects;
  @FXML private ImageView imageView;

  private ProjectPlanningModel model;
  private Region root;

  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    updateCounterLabel(); // every time the app ( main menu is lauched ) the method to update the label is launched
  }

  public Region getRoot() {
    return root;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // Load the image
    String imagePath = "logo.png"; // Provide the correct path
    Image image = new Image(getClass().getResourceAsStream(imagePath));

    // Set the image to the ImageView
    imageView.setImage(image);
  }
  @FXML public void createProject() {
    viewHandler.openView("selectType", null); // Call the method to open SelectProjectType
  }

  // updates the label in the main menu fxml with the number of projects from projects.fxml file ( see the method in the xml reader)
  public void updateCounterLabel()
  {
    int numberOfProjects = XMLreader.getNumberOfProjects("projects.xml");
    counterLabel.setText(String.valueOf(numberOfProjects));
  }
  @FXML public void viewProject() {
      viewHandler.openView("viewProject", null); // Call the method to open View Projects
  }

  public void reset() {
    // Reset logic
    init(viewHandler, model, root);
  }
}
