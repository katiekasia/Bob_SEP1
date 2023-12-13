package dataPersistence;

import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XMLreader {

  // method used for displaying number of projects, if there are no projects to view it will return 0
  // otherwise , its gets all elements with the tag project, counts them and gets the length of it
  public static int getNumberOfProjects(String filePath) {
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        // If the file doesn't exist, return 0 projects
        return 0;
      }

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(file);

      Element root = doc.getDocumentElement();
      NodeList projectList = root.getElementsByTagName("Project");

      return projectList.getLength();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return 0; // Return 0 in case of an error
  }


  public static ArrayList<Project> readProjectsFromXML(String filePath) {
    ArrayList<Project> projects = new ArrayList<>();

    try {
      File file = new File("projects.xml");
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
        int id = Integer.parseInt(projectElement.getElementsByTagName("ID").item(0).getTextContent());
        String title = projectElement.getElementsByTagName("title").item(0).getTextContent();
        double size = Double.parseDouble(projectElement.getElementsByTagName("size")  .item(0).getTextContent());
        double budget = Double.parseDouble(projectElement.getElementsByTagName("budget").item(0).getTextContent());
        String address = projectElement.getElementsByTagName("address").item(0).getTextContent();

        String typeString = projectElement.getElementsByTagName("type").item(0).getTextContent();

        ProjectType type = ProjectType.valueOf(typeString);

        switch (type) {
          case RESIDENTIAL:
            int numberOfKitchens = Integer.parseInt(projectElement.getElementsByTagName("numberOfKitchens").item(0).getTextContent());;
            int numberOfBathrooms = Integer.parseInt(projectElement.getElementsByTagName("numberOfBathrooms").item(0).getTextContent());
            int numberOfOtherRooms = Integer.parseInt(projectElement.getElementsByTagName("numberOfOtherRooms").item(0).getTextContent());
            boolean isNewBuilding = Boolean.parseBoolean(projectElement.getElementsByTagName("isNewBuilding").item(0).getTextContent());
            int timeline = Integer.parseInt(projectElement.getElementsByTagName("timeline").item(0).getTextContent());
            projects.add(new Residential(id, title, budget, size, address, type, numberOfKitchens, numberOfBathrooms, numberOfOtherRooms, isNewBuilding, timeline));
            break;
          case COMMERCIAL:
            int numberOfFloors = Integer.parseInt(projectElement.getElementsByTagName("numberOfFloors").item(0).getTextContent());
            String useOfBuilding = projectElement.getElementsByTagName("useOfBuilding").item(0).getTextContent();
            int commercialTimeline = Integer.parseInt(projectElement.getElementsByTagName("timeline").item(0).getTextContent());
            projects.add(new Commercial(id, title, budget, size, address, type, numberOfFloors, commercialTimeline, useOfBuilding));
            break;
          case INDUSTRIAL:
            String typeOfFacility = projectElement.getAttribute("typeOfFacility");
            int industrialTimeline = Integer.parseInt(projectElement.getElementsByTagName("timeline").item(0).getTextContent());
            projects.add(new Industrial(id, title, budget, size, address, type, typeOfFacility, industrialTimeline));
            break;
          case ROADCONSTRUCTION:
            double length = Double.parseDouble(projectElement.getElementsByTagName("length").item(0).getTextContent());
            double width = Double.parseDouble(projectElement.getElementsByTagName("width").item(0).getTextContent());
            boolean hasBridges = Boolean.parseBoolean(projectElement.getElementsByTagName("hasBridges").item(0).getTextContent());
            boolean hasTunnels = Boolean.parseBoolean(projectElement.getElementsByTagName("hasTunnels").item(0).getTextContent());
            boolean hasChallenges = Boolean.parseBoolean(projectElement.getElementsByTagName("hasChallenges").item(0).getTextContent());
            int roadTimeline = Integer.parseInt(projectElement.getElementsByTagName("timeline").item(0).getTextContent());

            projects.add(new RoadConstruction(id, title, budget, address, type, length, width, hasBridges, hasTunnels, roadTimeline, hasChallenges));
            break;

          default:
            break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return projects;
  }

}