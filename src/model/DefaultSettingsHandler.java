/**
 * Class DefaultSettingsHandler, which allows all the default settings to function in all the different project types.
 * Handles loading default settings for residential, commercial, industrial, and road construction projects from a configuration file.
 * The configuration file is specified by the static variable FILENAME.
 *
 * @author Katarzyna, Catalina, Sandut, Samo, Sebastian
 * @version 2.0 – December 2023
 */
package model;
import java.io.*;
import java.util.Properties;

/**
 * Class handling of the default settings for all project types
 *
 * @author  Kasia Olejarczyk, Sandut Chilat, Catalina Tonu, Sebastian Bartko
 * @version 3.0- December 2023
 **/
public class DefaultSettingsHandler {
  // static variable of the filename for configuration
  private static String FILENAME = "projects.xml";

  /**
   * Loads the default settings for residential projects.
   *
   * @return an array containing default settings for residential projects.
   */
  public static Object[] loadResidentialDefaultSettings() {
    // initialize properties and input stream
    Properties prop = new Properties();
    InputStream input = null;

    // array to store default settings, adjust the size based on the number of defaults
    Object[] defaults = new Object[5];

    try {
      // try to open the input stream for the specified filename
      input = new FileInputStream(FILENAME);

      // load properties from the input stream
      prop.load(input);

      // run through defaults one by one, retrieve values from properties, and store them in the array
      for (int i = 0; i < defaults.length; i++) {
        String value = prop.getProperty("residential_default_" + i);
        defaults[i] = value;
      }
    } catch (IOException io) {
      io.printStackTrace(); // print a stack trace in case of an IO exception
    } finally {
      // close the input stream in the ""finally" block to ensure it's closed even if an exception occurs
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    // return the array containing default settings
    return defaults;
  }

  /**
   * Loads the default settings for commercial projects.
   *
   * @return an array containing default settings for commercial projects.
   */
  //the defaults are implemented pretty much the same way for all the projects, with differences due to different info types
  public static Object[] loadCommercialDefaultSettings() {
    // initialize properties and input stream
    Properties prop = new Properties();
    InputStream input = null;

    // array to store default settings, adjust the size based on the number of defaults
    Object[] defaults = new Object[3];

    try {
      // try to open the input stream for the specified filename
      input = new FileInputStream(FILENAME);

      // load properties from the input stream
      prop.load(input);

      // ITERATE through defaults, retrieve values from properties, and store them in the array
      for (int i = 0; i < defaults.length; i++) {
        String value = prop.getProperty("commercial_default_" + i);
        defaults[i] = value;
      }
    } catch (IOException io) {
      io.printStackTrace(); // print a stack trace in case of an IO exception
    } finally {
      // close the input stream in the "finally" block to ensure it's closed even if an exception occurs
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    // return the array containing default settings
    return defaults;
  }

  /**
   * Loads the default settings for industrial projects.
   *
   * @return an array containing default settings for industrial projects.
   */
  public static Object[] loadIndustrialDefaultSettings() {
    // initialize properties and input stream
    Properties prop = new Properties();
    InputStream input = null;

    // array to store default settings, adjust the size based on the number of defaults
    Object[] defaults = new Object[2];

    try {
      // try to open the input stream for the specified filename
      input = new FileInputStream(FILENAME);

      // load properties from the input stream
      prop.load(input);

      // iterate through defaults, retrieve values from properties, and store them in the array
      for (int i = 0; i < defaults.length; i++) {
        String value = prop.getProperty("industrial_default_" + i);
        defaults[i] = value;
      }
    } catch (IOException io) {
      io.printStackTrace(); // print a stack trace in case of an IO exception
    } finally {
      // close the input stream in the finally block to ensure it's closed even if an exception occurs
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    // return the array containing default settings
    return defaults;
  }

  /**
   * Loads the default settings for road construction projects.
   *
   * @return an array containing default settings for road construction projects.
   */
  public static Object[] loadRoadConstructionDefaultSettings() {
    // initialize properties and input stream
    Properties prop = new Properties();
    InputStream input = null;

    // array to store default settings, adjust the size based on the number of defaults
    Object[] defaults = new Object[2];

    try {
      // try to open the input stream for the specified filename
      input = new FileInputStream(FILENAME);

      // load properties from the input stream
      prop.load(input);

      // iterate through defaults, retrieve values from properties, and store them in the array
      for (int i = 0; i < defaults.length; i++) {
        String value = prop.getProperty("roadConstruction_default_" + i);
        defaults[i] = value;
      }
    } catch (IOException io) {
      io.printStackTrace(); // print a stack trace in case of an IO exception
    } finally {
      // close the input stream in the finally block to ensure it's closed even if an exception occurs
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    // return the array containing default settings
    return defaults;
  }
}
