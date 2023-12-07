package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectProjectTypeViewController {

  private Stage primaryStage;

  public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  @FXML
  private void handleResidentialButton(ActionEvent event) {
    openResidentialView();
  }

  @FXML
  private void handleCommercialButton(ActionEvent event) {
    openCommercialView();
  }
  @FXML
  private void handleIndustrialButton(ActionEvent event) {
    openIndustrialView();
  }
  @FXML
  private void handleRoadConstructionlButton(ActionEvent event) {
    openRoadConstructionView();
  }

  private void openResidentialView() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("createResidential.fxml"));
      Region root = loader.load();
      Scene scene = new Scene(root);
      primaryStage.setTitle("Residential Project Details");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void openIndustrialView() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("createIndustrial.fxml"));
      Region root = loader.load();
      Scene scene = new Scene(root);
      primaryStage.setTitle("Industrial Project Details");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void openRoadConstructionView() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("createRoadConstruction.fxml"));
      Region root = loader.load();
      Scene scene = new Scene(root);
      primaryStage.setTitle("RoadConstruction Project Details");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void openCommercialView() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("createCommercial.fxml"));
      Region root = loader.load();
      Scene scene = new Scene(root);
      primaryStage.setTitle("Commercial Project Details");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


