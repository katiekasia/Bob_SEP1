
  package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.ProjectPlanningModel;

  public class ViewHandler extends Application {

    private Scene currentScene;
    private Stage primaryStage;
    private MainMenuViewController mainMenuController;
    private SelectProjectTypeViewController selectProjectController;

      private ViewEditGeneralController editGeneralController;
      private EditCommercialController editCommercialController;
      private EditResidentialController editResidentialController;
      private EditIndustrialController editIndustrialController;
      private EditRoadContructionController editRoadContructionController;
      private CreateCommercialController createCommercialController;
      private CreateResidentialController createResidentialController;
      private CreateIndustrialController createIndustrialController;
      private CreateRoadContructionController createRoadContructionController;

    private ProjectPlanningModel model;

    public ViewHandler(ProjectPlanningModel model) {
      this.model = model;
      this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      openView("projects");
    }
    public void openView(String id) {
      Region root = null;

      switch (id)
      {
        case "projects":
        {
          root = loadMainMenu("MainMenuViewController.fxml");
          break;
        }

        case "projectsComplete":
          root = loadProjectsListView("ProjectsListView.fxml");
          break;
        //                      _____ FOR RESIDENTIAL ______
        //  loading scene graph with specified function for determined window use
        case "addResidential":
        {
          root = loadAddResidentialProjectView("AddResidentialProjectView.fxml",
              null, Function.add);
          break;
        }
        case "editResidential":
        {
          root = loadAddResidentialProjectView("AddResidentialProjectView.fxml",
              state, Function.edit);
          break;
        }
        case "displayResidential":
        {
          root = loadAddResidentialProjectView("AddResidentialProjectView.fxml",
              state, Function.display);
          break;
        }
        //                      _____ FOR COMMERCIAL ______
        case "addCommercial":
        {
          root = loadAddCommercialProjectView("AddCommercialProjectView.fxml",
              null, Function.add);
          break;
        }
        case "editCommercial":
        {
          root = loadAddCommercialProjectView("AddCommercialProjectView.fxml",
              state, Function.edit);
          break;
        }
        case "displayCommercial":
        {

          root = loadAddCommercialProjectView("AddCommercialProjectView.fxml",
              state, Function.display);
          break;
        }

        //                      _____ FOR INDUSTRIAL ______
        //  loading scene graph with specified function for determined window use
        case "addIndustrial":
        {
          root = loadAddIndustrialProjectView("AddIndustrialProjectView.fxml",
              null, Function.add);
          break;
        }
        case "editIndustrial":
        {
          root = loadAddIndustrialProjectView("AddIndustrialProjectView.fxml",
              state, Function.edit);
          break;
        }
        case "displayIndustrial":
        {
          root = loadAddIndustrialProjectView("AddIndustrialProjectView.fxml",
              state, Function.display);
          break;
        }

        case "addRoadConstruction":
        {
          root = loadAddRoadConstructionProjectView(
              "AddRoadConstructionProjectView.fxml", state, Function.add);
          break;
        }
        case "editRoadConstruction":
        {
          root = loadAddRoadConstructionProjectView(
              "AddRoadConstructionProjectView.fxml", state, Function.edit);
          break;
        }
        case "displayRoadConstruction":
        {
          root = loadAddRoadConstructionProjectView(
              "AddRoadConstructionProjectView.fxml", state, Function.display);
          break;
        }

        case ("reports"):
        {
          root = loadReportsView("ReportsView.fxml");
          break;
        }
        case ("update"):
        {
          root = loadUpdateView("UpdateProjectView.fxml", state);
          break;
        }
      }

 */
      currentScene.setRoot(root);
      String title = "";
      if (root.getUserData() != null)

      {
        title += root.getUserData();
      }

      Screen screen = Screen.getPrimary();
      Rectangle2D bounds = screen.getVisualBounds();

      primaryStage.setTitle(title);
      primaryStage.setScene(currentScene);
      primaryStage.setWidth(root.getPrefWidth());
      primaryStage.setHeight(root.getPrefHeight());
      primaryStage.show();

      /*
      centering the window
       */
      double x = (bounds.getWidth() - primaryStage.getWidth()) / 2;
      double y = (bounds.getHeight() - primaryStage.getHeight()) / 2;
      primaryStage.setX(x);
      primaryStage.setY(y);
    }
/*
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
*/
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