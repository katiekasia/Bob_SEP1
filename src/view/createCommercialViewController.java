package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class createCommercialViewController
{

  @FXML
  private TextField idTextField;
  @FXML
  private TextField titleTextField;

  @FXML
  private TextField budgetTextField;

  @FXML
  private TextField sizeTextField;

  @FXML
  private TextField timelineTextField;

  @FXML
  private TextField addressTextField;
  @FXML
  private TextField numberOfFloorsField;

  @FXML
  private TextField useOfBuildingField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button saveButton;

  @FXML
<<<<<<< Updated upstream
  private Label errorLabel;
=======
  private Button backButton;

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
  private Label errorLabelUseOfBuilding;
  @FXML
  private Label errorLabelNrOfFloors;
>>>>>>> Stashed changes
  private ViewHandler viewHandler;
  private ProjectPlanningModel model;
  private Region root;

  public Region getRoot()
  {
    return root;
  }
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    // initiate the default settings
    int defaultNumberOfFloors = (int) Commercial.defaultCommercial[0];
    int defaultTimeline = (int) Commercial.defaultCommercial[1];
    // Inject the textfields with initiated defaults settings
    timelineTextField.setText(String.valueOf(defaultTimeline));
    numberOfFloorsField.setText(String.valueOf(defaultNumberOfFloors));
  }
  @FXML
  private void cancelButtonClicked(ActionEvent event) {
    // Close the window
    cancelButton.getScene().getWindow().hide();
  }

  @FXML
<<<<<<< Updated upstream
  private void saveButtonClicked(ActionEvent event) {
=======
  private void saveButtonClicked() {
    try {
      // Retrieve the inserted data
      int id = Integer.parseInt(idTextField.getText());
      String title = titleTextField.getText();
      double budget = Double.parseDouble(budgetTextField.getText());
      double size = Double.parseDouble(sizeTextField.getText());
      String address = addressTextField.getText();
      int numberOfFloors = Integer.parseInt(numberOfFloorsField.getText());
      String useOfBuilding= useOfBuildingField.getText();
      int timeline = Integer.parseInt(timelineTextField.getText());

      // Perform additional input validation checks
      if (id <= 0 || String.valueOf(id).length()!= 6 || String.valueOf(id).isEmpty())
      {
        errorLabelId.setText("Invalid ID");
        return;
      }
      if(title.isEmpty() || !title.matches("[a-zA-Z]+"))
      {
        errorLabelTitle.setText("Invalid title");
        return;
      }
      if(budget <= 0 || String.valueOf(budget).isEmpty() || !String.valueOf(budget).matches("\\d+(\\.\\d+)?"))
      {
        errorLabelBudget.setText("Invalid budget");
        return;
      }
      if(timeline <= 0 )
      {
        errorLabelTimeline.setText("Invalid timeline");
        return;
      }
      if(size <= 0 || String.valueOf(size).isEmpty())
      {
        errorLabelSize.setText("Invalid size");
        return;
      }

      if (address.isEmpty() || !address.matches("[a-zA-Z]+")) {
        errorLabelAddress.setText("Invalid address");
        return;
      }
      if(numberOfFloors<= 0 )
      {
        errorLabelNrOfFloors.setText("Invalid number");
        return;
      }
      if (useOfBuilding.isEmpty() || !useOfBuilding.matches("[a-zA-Z]+")) {
        errorLabelUseOfBuilding.setText("Invalid ");
        return;
      }


      // Create a Residential object if input is valid
      Commercial newCommercial = new Commercial(
          id, title, budget, size, address,
          ProjectType.COMMERCIAL, numberOfFloors, timeline, useOfBuilding);

      System.out.println(newCommercial);

    }
    catch (NumberFormatException e) {
      errorLabelId.setText("Invalid ID.");
      errorLabelTitle.setText("Invalid title");
      errorLabelBudget.setText("Invalid budget");
      errorLabelTimeline.setText("Invalid timeline");
      errorLabelAddress.setText("Invalid address");
      errorLabelSize.setText("Invalid size");
      errorLabelNrOfFloors.setText("Invalid number");
      errorLabelUseOfBuilding.setText("Invalid ");


    }
>>>>>>> Stashed changes
    // Implement saving to XML functionality here
    // If input is incorrect, display errorLabel
    if (!validateInput()) {
      errorLabel.setText("Incorrect input!");
      errorLabel.setVisible(true);
    } else {
      errorLabel.setVisible(false);
      // Save details to XML
    }
  }

  private boolean validateInput()
  {
    // Implement input validation logic here
    // Return true if input is correct, false otherwise
    return true; // Placeholder - add validation checks
  }

  public void reset()
  {
    // Reset logic
    init(viewHandler, model, root);
  }
  /*public void saveToXMLFile() throws Exception {
    // Define a DocumentBuilderFactory and DocumentBuilder
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    // Create a new document with the root element "Project"
    Document doc = docBuilder.newDocument();
    Element rootElement = doc.createElement("Project");
    doc.appendChild(rootElement);

    // Create elements for project details
    Element projectName = doc.createElement("ProjectName");
    projectName.appendChild(doc.createTextNode(this.projectName));
    rootElement.appendChild(projectName);

    // Similarly, create elements for other details like location, size, etc.

    // Write the XML document to a file
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(new File("Project.xml"));
    transformer.transform(source, result);
  }*/


}




