
import javafx.application.Application;
import model.DefaultSettingsHandler;

/**
 * The entry point of the application.
 * Launches the application by calling the launch method with the main application class.
 * Loads default settings for all project types using the DefaultSettingsHandler upon application start.
 * Default settings for residential, commercial, industrial, and road construction projects are retrieved.
  * @author Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
  * @version 3.0 - December 2023
 */
public class Main
{
    /**
 * The main method of the application.
 * @param args Command-line arguments .
 */
    public static void main(String[] args)
    {
        Application.launch(MyApplication.class);
        Object[] residentialDefaults = DefaultSettingsHandler.loadResidentialDefaultSettings();
        Object[] commercialDefaults = DefaultSettingsHandler.loadCommercialDefaultSettings();
        Object[] industrialDefaults = DefaultSettingsHandler.loadIndustrialDefaultSettings();
        Object[] roadConstructionDefaults = DefaultSettingsHandler.loadRoadConstructionDefaultSettings();
    }
}