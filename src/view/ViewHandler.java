
  package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.ProjectPlanningModel;

  public class ViewHandler extends Application
  {

    private Scene currentScene;
    private Stage primaryStage;
    private MainMenuViewController mainMenuController;
    private SelectProjectTypeViewController selectProjectTypeViewController;
    private createCommercialViewController commercialViewController;
    private SelectProjectTypeViewController selectProjectController;
    private ViewEditGeneralController editGeneralController;
    private createResidentialViewController createResidentialController;
    private createCommercialViewController createCommercialController;
    private createIndustrialViewController createIndustrialController;

    private createRoadConstructionViewController createRoadContructionController;
    /*

          private EditCommercialController editCommercialController;
          private EditResidentialController editResidentialController;
          private EditIndustrialController editIndustrialController;
          private EditRoadContructionController editRoadContructionController;
    */
    private ProjectPlanningModel model;

    public ViewHandler(ProjectPlanningModel model)
    {
      this.model = model;
      this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage)
    {
      this.primaryStage = primaryStage;
      openView("projects");
    }

    private Region loadMainMenu(String fxmlFile)
    {
      Region root = null;
      if (mainMenuController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          mainMenuController = loader.getController();
          mainMenuController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        mainMenuController.reset();
      return mainMenuController.getRoot();
    }

    private Region loadSelectProjectTypeViewController(String fxmlFile)
    {
      Region root = null;
      if (selectProjectTypeViewController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          selectProjectTypeViewController = loader.getController();
          selectProjectTypeViewController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        selectProjectTypeViewController.reset();
      return selectProjectTypeViewController.getRoot();
    }

    private Region loadViewEditController(String fxmlFile)
    {
      Region root = null;
      if (editGeneralController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          editGeneralController = loader.getController();
          editGeneralController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        editGeneralController.reset();
      return editGeneralController.getRoot();
    }

    private Region loadCreateCommercialViewController(String fxmlFile)
    {
      Region root = null;
      if (createCommercialController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          createCommercialController = loader.getController();
          createCommercialController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        createCommercialController.reset();
      return createCommercialController.getRoot();
    }

    private Region loadCreateRoadConstructionViewController(String fxmlFile)
    {
      Region root = null;
      if (createRoadContructionController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          createRoadContructionController = loader.getController();
          createRoadContructionController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        createRoadContructionController.reset();
      return createRoadContructionController.getRoot();
    }
    private Region loadCreateIndustrialViewController(String fxmlFile)
    {
      Region root = null;
      if (createIndustrialController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          createIndustrialController = loader.getController();
          createIndustrialController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        createIndustrialController.reset();
      return createIndustrialController.getRoot();
    }
    private Region loadCreateResidentialViewController(String fxmlFile)
    {
      Region root = null;
      if (createResidentialController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          createResidentialController = loader.getController();
          createResidentialController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        createResidentialController.reset();
      return createResidentialController.getRoot();
    }

    public void closeView()
    {
      primaryStage.close();
    }

    public void openView(String id)
    {
      Region root = null;

      switch (id)
      {
        case "projects":
        {
          root = loadMainMenu("MainMenu.fxml");
          break;
        }

        case "selectType":
        {
          root = loadSelectProjectTypeViewController("SelectProjectType.fxml");
          break;
        }

        case "viewProject":
        {
          root = loadViewEditController("ViewEdit.fxml");
          break;
        }
        case "residentialProject":
        {
          root = loadCreateResidentialViewController("CreateResidential.fxml");
          break;
        }
        case "commercialProject":
        {
          root = loadCreateCommercialViewController("createCommercial.fxml");
          break;
        }
        case "industrialProject":
        {
          root = loadCreateIndustrialViewController("createIndustrial.fxml");
          break;
        }
        case "roadConstructionProject":
        {
          root = loadCreateRoadConstructionViewController("createRoadConstruction.fxml");
          break;
        }
      }
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

        // centering the window

        double x = (bounds.getWidth() - primaryStage.getWidth()) / 2;
        double y = (bounds.getHeight() - primaryStage.getHeight()) / 2;
        primaryStage.setX(x);
        primaryStage.setY(y);



    }
  }
        /*
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

 */

