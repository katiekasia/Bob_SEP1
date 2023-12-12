package model;

import java.io.*;
import java.util.Properties;

public class DefaultSettingsHandler {
  private static final String FILENAME = "projects.xml";

  public static void saveResidentialDefaultSettings(Object[] defaults) {
    Properties prop = new Properties();
    OutputStream output = null;

    try {
      output = new FileOutputStream(FILENAME);

      for (int i = 0; i < defaults.length; i++) {
        prop.setProperty("residential_default_" + i, String.valueOf(defaults[i]));
      }

      prop.store(output, null);
    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void saveCommercialDefaultSettings(Object[] defaults) {
    Properties prop = new Properties();
    OutputStream output = null;

    try {
      output = new FileOutputStream(FILENAME);

      for (int i = 0; i < defaults.length; i++) {
        prop.setProperty("commercial_default_" + i, String.valueOf(defaults[i]));
      }

      prop.store(output, null);
    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
  public static void saveIndustrialDefaultSettings(Object[] defaults) {
    Properties prop = new Properties();
    OutputStream output = null;

    try {
      output = new FileOutputStream(FILENAME);

      for (int i = 0; i < defaults.length; i++) {
        prop.setProperty("industrial_default_" + i, String.valueOf(defaults[i]));
      }

      prop.store(output, null);
    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


  public static void saveRoadConstructionDefaultSettings(Object[] defaults) {
    Properties prop = new Properties();
    OutputStream output = null;

    try {
      output = new FileOutputStream(FILENAME);

      for (int i = 0; i < defaults.length; i++) {
        prop.setProperty("roadConstruction_default_" + i, String.valueOf(defaults[i]));
      }

      prop.store(output, null);
    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
  public static Object[] loadResidentialDefaultSettings() {
    Properties prop = new Properties();
    InputStream input = null;
    Object[] defaults = new Object[5]; // Adjust the size based on the number of defaults

    try {
      input = new FileInputStream(FILENAME);
      prop.load(input);

      for (int i = 0; i < defaults.length; i++) {
        String value = prop.getProperty("residential_default_" + i);
        defaults[i] = value;
      }
    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return defaults;
  }

  public static Object[] loadCommercialDefaultSettings() {
    Properties prop = new Properties();
    InputStream input = null;
    Object[] defaults = new Object[3]; // Adjust the size based on the number of defaults

    try {
      input = new FileInputStream(FILENAME);
      prop.load(input);

      for (int i = 0; i < defaults.length; i++) {
        String value = prop.getProperty("commercial_default_" + i);
        defaults[i] = value;
      }
    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return defaults;
  }
  public static Object[] loadIndustrialDefaultSettings() {
    Properties prop = new Properties();
    InputStream input = null;
    Object[] defaults = new Object[2]; // Adjust the size based on the number of defaults

    try {
      input = new FileInputStream(FILENAME);
      prop.load(input);

      for (int i = 0; i < defaults.length; i++) {
        String value = prop.getProperty("industrial_default_" + i);
        defaults[i] = value;
      }
    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return defaults;
  }
  public static Object[] loadRoadConstructionDefaultSettings() {
    Properties prop = new Properties();
    InputStream input = null;
    Object[] defaults = new Object[2]; // Adjust the size based on the number of defaults

    try {
      input = new FileInputStream(FILENAME);
      prop.load(input);

      for (int i = 0; i < defaults.length; i++) {
        String value = prop.getProperty("roadConstruction_default_" + i);
        defaults[i] = value;
      }
    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return defaults;
  }


}