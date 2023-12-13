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

/**
 * Controller class that handles the selection for the type of the project(4 types )
 *
 * @author Kasia Olejarczyk, Sandut Chilat, Catalina Tonu
 * @version 3.0- December 2023
 */

public class SelectProjectTypeViewController {

  private ViewHandler viewHandler;
  private Region root;
  private ProjectPlanningModel model;
  /**
   * Gets the root element.
   *
   * @return The root element of the view.
   */
  public Region getRoot()
  {
    return root;
  }

  /**
   * Initializes the controller.
   *
   * @param viewHandler The handler for view-related actions.
   * @param model       The project planning model.
   * @param root        The root element of the view.
   */
  public void init(ViewHandler viewHandler, ProjectPlanningModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }
  /**
   * Resets the controller logic.
   */
  public void reset()
  {
    init(viewHandler, model, root);
  }


  /**
   * Opens the view window to create a Residential project.
   */
  @FXML
  private void handleResidentialButton()
  {
    viewHandler.openView("residentialProject", null); // Call the method to open create Residential project
  }
  /**
   * Opens the view to create a Commercial project.
   */
  @FXML
  private void handleCommercialButton() {
    viewHandler.openView("commercialProject", null); // Call the method to open create Commercial Project
  }
  /**
   * Opens the view to create an Industrial project.
   */
  @FXML
  private void handleIndustrialButton() {
    viewHandler.openView("industrialProject", null);
  }
  /**
   * Opens the view to create a Road Construction project.
   */
  @FXML
  private void handleRoadConstructionButton() {
    viewHandler.openView("roadConstructionProject", null);
  }
  /**
   * Returns to the projects main Menu view.
   */
  @FXML
  private void handleBackButton() {
    viewHandler.openView("projects", null);
  }

  }



