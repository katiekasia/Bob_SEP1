package view;

import model.Residential.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.ProjectPlanningModel;
import model.ProjectType;
import model.Residential;

import javax.swing.text.View;
import java.io.IOException;


public class SelectProjectTypeViewController {

  private Stage primaryStage;
  private ViewHandler viewHandler;
  private Region root;
  private ProjectPlanningModel model;
  public Region getRoot()
  {
    return root;
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


  public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  @FXML
  private void handleResidentialButton() {
    Residential residential1 = new Residential(
        1,
        "",
        0,
        0,
        "",
        ProjectType.RESIDENTIAL,
        (Integer) Residential.defaultResidential.get(0), // numberOfKitchens
        (Integer) Residential.defaultResidential.get(1), // numberOfBathrooms
        (Integer) Residential.defaultResidential.get(2), // numberOfOtherRooms
        (Boolean) Residential.defaultResidential.get(4), // isNewBuilding
        (Integer) Residential.defaultResidential.get(3)  // timeline
    );
    viewHandler.openView("residentialProject"); // Call the method to open create Residential project
  }

  @FXML
  private void handleCommercialButton() {
    viewHandler.openView("commercialProject"); // Call the method to open create Commercial Project
  }
  @FXML
  private void handleIndustrialButton(ActionEvent event) {
    viewHandler.openView("industrialProject");
  }
  @FXML
  private void handleRoadConstructionButton(ActionEvent event) {
    viewHandler.openView("roadConstructionProject");
  }


  }



