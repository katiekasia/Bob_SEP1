
  package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Project;
import model.ProjectPlanningModel;
  /**
   * The ViewHandler class manages the switching and loading of the view windows in the project planning management application..
   * It controls the primary stage and scene, initializes all the controllers and loads associated FXML files for each view.
   * @author Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
   * @version 3.0 - December 2023
   */
  public class ViewHandler extends Application
  {

    private Scene currentScene;
    private Stage primaryStage;
    private MainMenuViewController mainMenuController;
    private SelectProjectTypeViewController selectProjectTypeViewController;

    private ViewEditGeneralController editGeneralController;
    private createResidentialViewController createResidentialController;
    private createCommercialViewController createCommercialController;
    private createIndustrialViewController createIndustrialController;

    private createRoadConstructionViewController createRoadContructionController;

          private EditCommercial1Controller editCommercialController;
          private EditResidential1Controller editResidentialController;
          private EditIndustrial1Controller editIndustrialController;
          private EditRoadConstructionController editRoadContructionController;

    private ProjectPlanningModel model;
    /**
     * One-argument Constructor for ViewHandler class.
     * Initializes the ViewHandler with the provided ProjectPlanningModel(in the model package).
     *
     * @param model The ProjectPlanningModel used by the ViewHandler.
     */
    public ViewHandler(ProjectPlanningModel model)
    {
      this.model = model;
      this.currentScene = new Scene(new Region());
    }
    /**
     * Initiates the primary stage of the application and opens the 'projects' view by default.
     *
     * @param primaryStage The main stage of the application.
     */
    public void start(Stage primaryStage)
    {
      this.primaryStage = primaryStage;
      openView("projects", null);
    }
    /**
     * Updates the view of the projectTable (ViewEdit) if the editGeneralController is instantiated.
     */
    public void updateViewEditGeneralTable() {
      if (editGeneralController != null) {
        editGeneralController.updateTable();
      }
    }
    /**
     * Loads the Main Menu view window by initializing the FXMLLoader to load the required FXML file.
     * Initializes the mainMenuController with ViewHandler, ProjectPlanningModel, and root node.
     * Returns the root node of the Main Menu view.
     * If the mainMenuController is already instantiated, resets and returns the root node.
     *
     * @param fxmlFile The file path to the FXML file of the Main Menu view.
     * @return The root node of the Main Menu view.
     */
    private Region loadMainMenu(String fxmlFile)
    {
      Region root = null;
      if (mainMenuController == null)
      {
        try
        {
          //loading the FXML file for main page
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();
          // Initializing the mainMenuController with references to ViewHandler, ProjectPlanningModel, and root node
          mainMenuController = loader.getController();
          mainMenuController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        // If mainMenuController is already instantiated, reset the controller
        mainMenuController.reset();
      // Returns the root node of the Main Menu view
      return mainMenuController.getRoot();
    }

    /**
     * Loads the Select Project Type view by loading the associated FXML file.
     * Initializes the selectProjectTypeViewController with ViewHandler, ProjectPlanningModel, and root node.
     * Returns the root node of the Select Project Type view.
     * If the selectProjectTypeViewController is not instantiated, it initializes the controller and returns the root node.
     * If the selectProjectTypeViewController is already instantiated, it resets the controller and returns the root node.
     *
     * @param fxmlFile The file path to the FXML file of the Select Project Type view.
     * @return The root node of the Select Project Type view.
     */
    private Region loadSelectProjectTypeViewController(String fxmlFile)
    {
      Region root = null;
      // If selectProjectTypeViewController is not instantiated or initialized for the first time
      if (selectProjectTypeViewController == null)
      {
        try
        {
          // Loads the FXML file and initializes the selectProjectTypeViewController
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
        // If selectProjectTypeViewController is already instantiated, reset the controller
        selectProjectTypeViewController.reset();
      // Returns the root node of the Select Project Type view
      return selectProjectTypeViewController.getRoot();
    }

    /**
     * Loads the View Edit General view(fxml file), initializes its controller, and returns its root node.
     * Checks if the editGeneralController is instantiated; if not, initializes and returns the root node.
     * If the controller exists, resets it and returns the root node.
     *
     * @param fxmlFile The path to the FXML file for the View Edit General view.
     * @return The root node of the View Edit General view.
     */
    private Region loadViewEditGeneralController(String fxmlFile)
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
    /**
     * Loads the Create Commercial View Controller(fxml file), initializes its controller, and returns its root node.
     * Checks if the createCommercialController is instantiated; if not, initializes and returns the root node.
     * If the controller exists, resets it and returns the root node.
     *
     * @param fxmlFile The path to the FXML file for the View Edit General view.
     * @return The root node of the CreateCommercialViewController view.
     */
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
    /**
     * Loads the CreateRoadContructionViewController view(fxml file), initializes its controller, and returns its root node.
     * Checks if the CreateRoadContructionViewController is instantiated; if not, initializes and returns the root node.
     * If the controller exists, resets it and returns the root node.
     *
     * @param fxmlFile The path to the FXML file for the View Edit General view.
     * @return The root node of the Create RoadContruction ViewController view.
     */
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
    /**
     * Loads the CreateIndustrialViewController view(fxml file), initializes its controller, and returns its root node.
     * Checks if the CreateIndustrialViewController is instantiated; if not, initializes and returns the root node.
     * If the controller exists, resets it and returns the root node.
     *
     * @param fxmlFile The path to the FXML file for the View Edit General view.
     * @return The root node of the Create Industrial ViewController view.
     */
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
    /**
     * Loads the CreateResidentialViewController view(fxml file), initializes its controller, and returns its root node.
     * Checks if the CreateResidentialViewController is instantiated; if not, initializes and returns the root node.
     * If the controller exists, resets it and returns the root node.
     *
     * @param fxmlFile The path to the FXML file for the View Edit General view.
     * @return The root node of the Create Residential ViewController view.
     */
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
    /*
    --------------------- LOADING PAGES FOR ALL THE EDIT PAGES -----------------------------------
     */
    /**
     * Loads the CreateEditCommercialViewController view(fxml file), initializes its controller, and returns its root node.
     * Checks if the CreateEditCommercialViewController is instantiated; if not, initializes and returns the root node.
     * If the controller exists, resets it and returns the root node.
     *
     * @param fxmlFile The path to the FXML file for the View Edit General view.
     * @return The root node of the Create EditCommercial ViewController view.
     */
    private Region loadEditCommercialViewController(String fxmlFile)
    {
      Region root = null;
      if (editCommercialController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          editCommercialController = loader.getController();
          editCommercialController.init(this, model, root);

        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        editCommercialController.reset();
      return editCommercialController.getRoot();
    }

    /**
     * Loads the CreateEditResidentialViewController view(fxml file), initializes its controller, and returns its root node.
     * Checks if the CreateEditResidentialViewController is instantiated; if not, initializes and returns the root node.
     * If the controller exists, resets it and returns the root node.
     *
     * @param fxmlFile The path to the FXML file for the View Edit General view.
     * @return The root node of the Create EditResidential ViewController view.
     */
    private Region loadEditResidentialViewController(String fxmlFile)
    {
      Region root = null;
      if (editResidentialController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          editResidentialController = loader.getController();
          editResidentialController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        editResidentialController.reset();
      return editResidentialController.getRoot();
    }
    /**
     * Loads the CreateEditIndustrialViewController view(fxml file), initializes its controller, and returns its root node.
     * Checks if the CreateEditIndustrialViewController is instantiated; if not, initializes and returns the root node.
     * If the controller exists, resets it and returns the root node.
     *
     * @param fxmlFile The path to the FXML file for the View Edit General view.
     * @return The root node of the Create EditIndustrial ViewController view.
     */
    private Region loadEditIndustrialViewController(String fxmlFile)
    {
      Region root = null;
      if (editIndustrialController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          editIndustrialController = loader.getController();
          editIndustrialController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        editIndustrialController.reset();
      return editIndustrialController.getRoot();
    }
    /**
     * Loads the CreateEditRoadConstructionViewController view(fxml file), initializes its controller, and returns its root node.
     * Checks if the CreateEditRoadConstructionViewController is instantiated; if not, initializes and returns the root node.
     * If the controller exists, resets it and returns the root node.
     *
     * @param fxmlFile The path to the FXML file for the View Edit General view.
     * @return The root node of the Create EditRoadConstruction ViewController view.
     */
    private Region loadEditRoadConstructionViewController(String fxmlFile)
    {
      Region root = null;
      if (editRoadContructionController == null)
      {
        try
        {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource(fxmlFile));
          root = loader.load();

          editRoadContructionController = loader.getController();
          editRoadContructionController.init(this, model, root);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
      else
        editRoadContructionController.reset();
      return editRoadContructionController.getRoot();
    }

    /**
     * Switches and loads different views based on the provided ID and selectedProject.
     * Loads FXML files for different views, sets the root node, title, scene, dimensions,
     * and shows the primary stage.
     *
     * @param id              The identifier used to determine which view to open.
     * @param selectedProject The project selected for editing, if applicable (can be null).
     */
    public void openView(String id, Project selectedProject)
    {
      Region root = null;
      // Switch case to load different views based on ID
      // Each case loads the corresponding FXML file and sets necessary details for the view
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
          root = loadViewEditGeneralController("ViewEdit.fxml");
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
        case "editCommercial":
        {
          root = loadEditCommercialViewController("editCommercial.fxml");
          if (editCommercialController != null) {
            editCommercialController.setProjectDetailsCommercial(selectedProject);
          }
          break;
        }
        case "editResidential":
        {
          root = loadEditResidentialViewController("editResidential.fxml");
          if (editResidentialController != null) {
            editResidentialController.setProjectDetailsResidential(selectedProject);
          }
          break;
        }
        case "editIndustrial":
        {
          root = loadEditIndustrialViewController("editIndustrial.fxml");
          if (editIndustrialController != null) {
            editIndustrialController.setProjectDetailsIndustrial(selectedProject);
          }
          break;
        }
        case "editRoadConstruction":
        {
          root = loadEditRoadConstructionViewController("editRoadConstruction.fxml");
          if (editRoadContructionController != null) {
            editRoadContructionController.setProjectDetailsRoadConstruction(selectedProject);
          }
          break;
        }
      }
      // Sets the loaded view as the root for the current scene
        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData() != null)
        {
          title += root.getUserData();
        }
      // Sets title, scene, dimensions, and shows the primary stage
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


