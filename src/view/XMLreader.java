package view;

import model.Project;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XMLreader {
  public static ArrayList<Project> readProjectsFromXML(String filePath) {
    ArrayList<Project> projects = new ArrayList<>();

    try {
      File file = new File(filePath);
      if (!file.exists()) {
        // If the file doesn't exist, return an empty list of projects
        return projects;
      }

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(file);

      Element root = doc.getDocumentElement();
      NodeList projectList = root.getElementsByTagName("Project");

      for (int i = 0; i < projectList.getLength(); i++) {
        Element projectElement = (Element) projectList.item(i);
        // Extract project details similarly to the XMLwriter class
        // Create Project objects and add them to the 'projects' ArrayList
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return projects;
  }
}
