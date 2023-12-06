
  package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.ProjectPlanningModel;

  public class ViewHandler extends Application {

    private Scene currentScene;
    private Stage primaryStage;
    private MainMenuViewController mainMenuController;
    private SelectProjectTypeViewController selectProjectController;
    /*
      private ViewEditGeneralController editGeneralController;
      private EditCommercialController editCommercialController;
      private EditResidentialController editResidentialController;
      private EditIndustrialController editIndustrialController;
      private EditRoadContructionController editRoadContructionController;
      private CreateCommercialController createCommercialController;
      private CreateResidentialController createResidentialController;
      private CreateIndustrialController createIndustrialController;
      private CreateRoadContructionController createRoadContructionController;
    */
    private ProjectPlanningModel model;

    public ViewHandler(ProjectPlanningModel model) {
      this.model = model;
      this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      openView();
    }

    public void openView() {
      Region root = null;
      root = loadMainMenu("MainMenu.fxml");
      currentScene.setRoot(root);
        String title = "Main Menu";
        if (root.getUserData() != null) {
          title += root.getUserData();
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
      }

    private Region loadMainMenu(String fxmlFile){
      Region root = null;
      if (mainMenuController == null){
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          mainMenuController = loader.getController();
          mainMenuController.init(this, model, root);
        }
        catch (Exception e){
          e.printStackTrace();
        }
      }
      else
        mainMenuController.reset();
      return mainMenuController.getRoot();
    }

    public void closeView() {
      primaryStage.close();
    }

  }