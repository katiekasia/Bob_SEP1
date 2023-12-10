package view;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;

public class createRoadConstructionViewController
{
  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  private ProjectList projects;  // Declare as a field


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
  private TextField birdgesOrtunnelsTextField;

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

  private void printProjects() {
    System.out.println("Projects List:");
    for (Project project : projects.getAllProjects()) {
      System.out.println(project);
    }
    System.out.println("End of Projects List");
  }
  @FXML
  private void cancelButtonClicked() {
    viewHandler.openView("projects");
  }

  @FXML
  private void saveButtonClicked() {
    // Implement saving to XML functionality here

//    // If input is incorrect, display errorLabel
//    if (!validateInput()) {
//      errorLabel.setText("Incorrect input!");
//      errorLabel.setVisible(true);
//    } else {
//      errorLabel.setVisible(false);
//      viewHandler.openView("viewProject");
//      // Save details to XML
//    }
  }
  @FXML
  private void backButtonClicked() {
    viewHandler.openView("selectType");
  }
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
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
}






