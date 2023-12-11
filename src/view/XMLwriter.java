package view;

import model.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;

public class XMLwriter {

  private static Document loadOrCreateDocument(String filePath) {
    try {
      File file = new File("projects.xml");
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();

      if (file.exists()) {
        return builder.parse(file);
      } else {
        Document doc = builder.newDocument();
        Element rootElement = doc.createElement("Projects");
        doc.appendChild(rootElement);
        return doc;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  public static void appendProjectsToXML(ArrayList<Project> projects, String filePath) {
    try {
      Document doc;
      File file = new File(filePath);
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();

      if (file.exists()) {
        doc = builder.parse(file);
      } else {
        doc = builder.newDocument();
        Element rootElement = doc.createElement("Projects");
        doc.appendChild(rootElement);
      }
      Element rootElement = doc.getDocumentElement();

      ArrayList<String> existingProjectIDs = new ArrayList<>();
      NodeList existingProjects = rootElement.getElementsByTagName("Project");
      for (int i = 0; i < existingProjects.getLength(); i++) {
        Element existingProject = (Element) existingProjects.item(i);
        String existingID = existingProject.getElementsByTagName("ID").item(0).getTextContent();
        existingProjectIDs.add(existingID);
      }

      for (Project project : projects)
      {

        String currentID = String.valueOf(project.getID());
        if (!existingProjectIDs.contains(currentID))
        {
          Element projectElement = doc.createElement("Project");

          Element idElement = doc.createElement("ID");
          idElement.appendChild(doc.createTextNode(String.valueOf(project.getID())));
          projectElement.appendChild(idElement);

          Element titleElement = doc.createElement("title");
          titleElement.appendChild(doc.createTextNode(project.getTitle()));
          projectElement.appendChild(titleElement);

          Element budgetElement = doc.createElement("budget");
          budgetElement.appendChild(doc.createTextNode(String.valueOf(project.getBudget())));
          projectElement.appendChild(budgetElement);

          Element sizeElement = doc.createElement("size");
          sizeElement.appendChild(doc.createTextNode(String.valueOf(project.getSize())));
          projectElement.appendChild(sizeElement);

          Element addressElement = doc.createElement("address");
          addressElement.appendChild(doc.createTextNode(project.getAddress()));
          projectElement.appendChild(addressElement);

          // Check if the project is of type Residential and include its specific properties
          if (project instanceof Residential)
          {
            Residential residentialProject = (Residential) project;

            // Add elements specific to Residential
            Element kitchensElement = doc.createElement("numberOfKitchens");
            kitchensElement.appendChild(doc.createTextNode(String.valueOf(residentialProject.getNumberOfKitchens())));
            projectElement.appendChild(kitchensElement);

            Element bathroomsElement = doc.createElement("numberOfBathrooms");
            bathroomsElement.appendChild(doc.createTextNode(String.valueOf(residentialProject.getNumberOfBathrooms())));
            projectElement.appendChild(bathroomsElement);

            Element roomsElement = doc.createElement("numberOfOtherRooms");
            roomsElement.appendChild(doc.createTextNode(String.valueOf(residentialProject.getNumberOfOtherRooms())));
            projectElement.appendChild(roomsElement);

            Element isNewBuildingElement = doc.createElement("isNewBuilding");
            isNewBuildingElement.appendChild(doc.createTextNode(String.valueOf(residentialProject.getIsNewBuilding())));
            projectElement.appendChild(isNewBuildingElement);

            Element timelineElement = doc.createElement("timeline");
            timelineElement.appendChild(doc.createTextNode(String.valueOf(residentialProject.getTimeline())));
            projectElement.appendChild(timelineElement);

            // Add project type
            Element typeElement = doc.createElement("type");
            typeElement.appendChild(doc.createTextNode(residentialProject.getType().toString()));
            projectElement.appendChild(typeElement);
          }
          else if (project instanceof Commercial)
          {
            Commercial commercialProject = (Commercial) project;

            // Add elements specific to Residential
            Element numberOfFloorsElement = doc.createElement("numberOfFloors");
            numberOfFloorsElement.appendChild(doc.createTextNode(String.valueOf(commercialProject.getNumberOfFloors())));
            projectElement.appendChild(numberOfFloorsElement);

            Element useOfBuildingElement = doc.createElement("useOfBuilding");
            useOfBuildingElement.appendChild(doc.createTextNode(String.valueOf(commercialProject.getUseOfBuilding())));
            projectElement.appendChild(useOfBuildingElement);

            Element timelineElement = doc.createElement("timeline");
            timelineElement.appendChild(doc.createTextNode(String.valueOf(commercialProject.getTimeline())));
            projectElement.appendChild(timelineElement);

            // Add project type
            Element typeElement = doc.createElement("type");
            typeElement.appendChild(doc.createTextNode(commercialProject.getType().toString()));
            projectElement.appendChild(typeElement);
          }
          else if (project instanceof Industrial)
          {
            Industrial industrialProject = (Industrial) project;

            // Add elements specific to Residential
            Element typeOfFacilityElement = doc.createElement("typeOfFacility");
            typeOfFacilityElement.appendChild(doc.createTextNode(String.valueOf(industrialProject.getTypeOfFacility())));
            projectElement.appendChild(typeOfFacilityElement);

            Element timelineElement = doc.createElement("timeline");
            timelineElement.appendChild(doc.createTextNode(String.valueOf(industrialProject.getTimeline())));
            projectElement.appendChild(timelineElement);

            // Add project type
            Element typeElement = doc.createElement("type");
            typeElement.appendChild(doc.createTextNode(industrialProject.getType().toString()));
            projectElement.appendChild(typeElement);
          }


        else if (project instanceof RoadConstruction)
        {
          RoadConstruction roadConstructionProject = (RoadConstruction) project;

          // Add elements specific to Road Construction
          Element hasBridgesElement = doc.createElement("hasBridges");
          hasBridgesElement.appendChild(doc.createTextNode(String.valueOf(roadConstructionProject.getHasBridges())));
          projectElement.appendChild(hasBridgesElement);

          Element timelineElement = doc.createElement("timeline");
          timelineElement.appendChild(doc.createTextNode(String.valueOf(roadConstructionProject.getTimeline())));
          projectElement.appendChild(timelineElement);

          // Add project type
          Element hasTunnelsElement = doc.createElement("hasTunnels");
          hasTunnelsElement.appendChild(doc.createTextNode(String.valueOf(roadConstructionProject.getHasTunnels())));
          projectElement.appendChild(hasTunnelsElement);

          Element hasChallengesElement = doc.createElement("hasChallenges");
          hasChallengesElement.appendChild(doc.createTextNode(String.valueOf(roadConstructionProject.getHasChallenges())));
          projectElement.appendChild(hasChallengesElement);

          Element lengthElement = doc.createElement("length");
          lengthElement.appendChild(doc.createTextNode(String.valueOf(roadConstructionProject.getLength())));
          projectElement.appendChild(lengthElement);

          Element widthElement = doc.createElement("width");
          widthElement.appendChild(doc.createTextNode(String.valueOf(roadConstructionProject.getWidth())));
          projectElement.appendChild(widthElement);

          // Add project type
          Element typeElement = doc.createElement("type");
          typeElement.appendChild(doc.createTextNode(roadConstructionProject.getType().toString()));
          projectElement.appendChild(typeElement);
        }

          rootElement.appendChild(projectElement);
        }
      }

      // Write the content into an XML file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(filePath));
      transformer.transform(source, result);

      System.out.println("Projects appended to " + filePath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static boolean removeProjectFromXML(Project projectToRemove, String filePath) {
    try {
      Document doc = loadOrCreateDocument(filePath);
      if (doc == null) {
        return false;
      }

      NodeList projects = doc.getElementsByTagName("Project");
      for (int i = 0; i < projects.getLength(); i++) {
        Element projectElement = (Element) projects.item(i);

        String projectId = projectElement.getElementsByTagName("ID").item(0).getTextContent();
        if (projectId.equals(String.valueOf(projectToRemove.getID()))) {
          projectElement.getParentNode().removeChild(projectElement);
          saveDocumentToFile(doc, filePath);
          return true;
        }
      }
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private static void saveDocumentToFile(Document doc, String filePath) throws TransformerException {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(new File(filePath));
    transformer.transform(source, result);
  }
}

/*
 public static void appendProjectsToXML(ArrayList<Project> projects, String filePath) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.newDocument();

      Element rootElement = doc.createElement("Projects");
      doc.appendChild(rootElement);

      for (Project project : projects) {
        Element projectElement = doc.createElement("Project");

        Element idElement = doc.createElement("ID");
        idElement.appendChild(doc.createTextNode(String.valueOf(project.getID())));
        projectElement.appendChild(idElement);

        Element titleElement = doc.createElement("title");
        titleElement.appendChild(doc.createTextNode(project.getTitle()));
        projectElement.appendChild(titleElement);

        Element budgetElement = doc.createElement("budget");
        budgetElement.appendChild(doc.createTextNode(String.valueOf(project.getBudget())));
        projectElement.appendChild(budgetElement);

        Element sizeElement = doc.createElement("size");
        sizeElement.appendChild(doc.createTextNode(String.valueOf(project.getSize())));
        projectElement.appendChild(sizeElement);

        Element addressElement = doc.createElement("address");
        addressElement.appendChild(doc.createTextNode(project.getAddress()));
        projectElement.appendChild(addressElement);


        // Check if the project is of type Residential and include its specific properties
        if (project instanceof Residential) {
          Residential residentialProject = (Residential) project;

          // Add elements specific to Residential
          Element kitchensElement = doc.createElement("numberOfKitchens");
          kitchensElement.appendChild(doc.createTextNode(String.valueOf(residentialProject.getNumberOfKitchens())));
          projectElement.appendChild(kitchensElement);

          Element bathroomsElement = doc.createElement("numberOfBathrooms");
          bathroomsElement.appendChild(doc.createTextNode(String.valueOf(residentialProject.getNumberOfBathrooms())));
          projectElement.appendChild(bathroomsElement);

          Element roomsElement = doc.createElement("numberOfOtherRooms");
          roomsElement.appendChild(doc.createTextNode(String.valueOf(residentialProject.getNumberOfOtherRooms())));
          projectElement.appendChild(roomsElement);

          Element isNewBuildingElement = doc.createElement("isNewBuilding");
          isNewBuildingElement.appendChild(doc.createTextNode(String.valueOf(residentialProject.getIsNewBuilding())));
          projectElement.appendChild(isNewBuildingElement);

          Element timelineElement = doc.createElement("timeline");
          timelineElement.appendChild(doc.createTextNode(String.valueOf(residentialProject.getTimeline())));
          projectElement.appendChild(timelineElement);

          // Add project type
          Element typeElement = doc.createElement("type");
          typeElement.appendChild(doc.createTextNode(residentialProject.getType().toString()));
          projectElement.appendChild(typeElement);
        }
        else if (project instanceof Commercial) {
          Commercial commercialProject = (Commercial) project;

          // Add elements specific to Residential
          Element numberOfFloorsElement = doc.createElement("numberOfFloors");
          numberOfFloorsElement.appendChild(doc.createTextNode(String.valueOf(commercialProject.getNumberOfFloors())));
          projectElement.appendChild(numberOfFloorsElement);


          Element useOfBuildingElement = doc.createElement("useOfBuilding");
          useOfBuildingElement.appendChild(doc.createTextNode(String.valueOf(commercialProject.getUseOfBuilding())));
          projectElement.appendChild(useOfBuildingElement);

          Element timelineElement = doc.createElement("timeline");
          timelineElement.appendChild(doc.createTextNode(String.valueOf(commercialProject.getTimeline())));
          projectElement.appendChild(timelineElement);

          // Add project type
          Element typeElement = doc.createElement("type");
          typeElement.appendChild(doc.createTextNode(commercialProject.getType().toString()));
          projectElement.appendChild(typeElement);
        }
        else if (project instanceof Industrial)
        {
          Industrial industrialProject = (Industrial) project;

          // Add elements specific to Residential
          Element typeOfFacilityElement = doc.createElement("typeOfFacility");
          typeOfFacilityElement.appendChild(doc.createTextNode(String.valueOf(industrialProject.getTypeOfFacility())));
          projectElement.appendChild(typeOfFacilityElement);

          Element timelineElement = doc.createElement("timeline");
          timelineElement.appendChild(doc.createTextNode(String.valueOf(industrialProject.getTimeline())));
          projectElement.appendChild(timelineElement);

          // Add project type
          Element typeElement = doc.createElement("type");
          typeElement.appendChild(doc.createTextNode(industrialProject.getType().toString()));
          projectElement.appendChild(typeElement);
        }

        // Add other project details similarly...
        // Modify this section to include all relevant project details

        rootElement.appendChild(projectElement);
      }

      // Write the content into an XML file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(filePath));
      transformer.transform(source, result);

      System.out.println("Projects appended to " + filePath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

 */