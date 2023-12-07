package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.ProjectPlanningModel;

import javax.swing.text.TableView;
import javax.swing.text.View;

public class ViewEditGeneralController
{
  @FXML
  private TextField IDField;
  @FXML
  private TextField TitleField;
  @FXML
  private TextField timelineField;
  @FXML
  private RadioButton AllButton;
  @FXML
  private ChoiceBox SizeChoice;
  @FXML
  private ChoiceBox BudgetChoice;
  @FXML
  private ChoiceBox timeLineChoice;
  @FXML
  private ChoiceBox TypeChoice;
  @FXML
  private TableView ProjectTable;
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
