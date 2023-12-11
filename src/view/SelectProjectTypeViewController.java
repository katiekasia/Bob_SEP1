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



  @FXML
  private void handleResidentialButton()
  {
    viewHandler.openView("residentialProject", null); // Call the method to open create Residential project
  }

  @FXML
  private void handleCommercialButton() {
    viewHandler.openView("commercialProject", null); // Call the method to open create Commercial Project
  }
  @FXML
  private void handleIndustrialButton() {
    viewHandler.openView("industrialProject", null);
  }
  @FXML
  private void handleRoadConstructionButton() {
    viewHandler.openView("roadConstructionProject", null);
  }
  @FXML
  private void handleBackButton() {
    viewHandler.openView("projects", null);
  }


  }



