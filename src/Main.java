
import javafx.application.Application;
import model.DefaultSettingsHandler;


public class Main {
    public static void main(String[] args) {
        Application.launch(MyApplication.class);
        Object[] residentialDefaults = DefaultSettingsHandler.loadResidentialDefaultSettings();
        Object[] commercialDefaults = DefaultSettingsHandler.loadCommercialDefaultSettings();
        Object[] industrialDefaults = DefaultSettingsHandler.loadIndustrialDefaultSettings();
        Object[] roadConstructionDefaults = DefaultSettingsHandler.loadRoadConstructionDefaultSettings();
    }
}