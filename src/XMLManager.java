//import model.*;
//import org.w3c.dom.*;
//import javax.xml.parsers.*;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.*;
//
//public class XMLManager
//{
//  public XMLManager()
//  {
//    //hm
//  }
//
//  public void saveProjectsToXML(ProjectsList ongoingProjects, ProjectsList completedProjects, String fileName)
//  {
//    try
//    {
//      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//
//      Document doc = docBuilder.newDocument();
//      Element rootElement = doc.createElement("projects");
//
//      doc.appendChild(rootElement);
//
//      appendProjectsToXML(doc, rootElement, "ongoingProjects", ongoingProjects);
//      appendProjectsToXML(doc, rootElement, "completedProjects", completedProjects);
//
//      writeXMLToFile(doc, fileName);
//    }
//    catch (Exception e)
//    {
//      e.printStackTrace();
//    }
//  }
//
//  public void loadProjectsFromXML(ProjectsList ongoingProjects, ProjectsList completedProjects, String fileName)
//  {
//    try
//    {
//      File file = new File(fileName);
//      if (file.exists())
//      {
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(file);
//
//        doc.getDocumentElement().normalize();
//
//        loadProjectsFromXML(doc, "ongoingProjects", ongoingProjects);
//        loadProjectsFromXML(doc, "completedProjects", completedProjects);
//      }
//    }
//    catch (Exception e)
//    {
//      e.printStackTrace();
//    }
//
//  }
//
//  private void appendProjectsToXML(Document doc, Element parentElement, String containerTagName, ProjectsList projects)
//  {
//    Element containerElement = doc.createElement(containerTagName);
//
//    for (int i=0; i<projects.getSize(); i++)
//    {
//      Project project=projects.getProject(i);
//      Element projectElement = createProjectElement(doc, project, projects);
//      containerElement.appendChild(projectElement);
//    }
//
//    parentElement.appendChild(containerElement);
//  }
//
//  private void loadProjectsFromXML(Document doc, String containerTagName, ProjectsList projects)
//  {
//    NodeList nodeList = doc.getElementsByTagName(containerTagName);
//    if (nodeList.getLength() > 0)
//    {
//      Element containerElement = (Element) nodeList.item(0);
//
//      NodeList projectList = containerElement.getElementsByTagName("project");
//      for (int i = 0; i < projectList.getLength(); i++)
//      {
//        Node node = projectList.item(i);
//        if (node.getNodeType() == Node.ELEMENT_NODE)
//        {
//          Element element = (Element) node;
//          Project existingProject = projects.getProjectById(getElementText(element, "id"));
//          if (existingProject != null)
//          {
//            updateProjectFromElement(existingProject, element);
//          }
//          else
//          {
//            Project newProject = createProjectFromElement(element);
//            projects.addProject(newProject);
//          }
//        }
//      }
//    }
//  }
//  private void updateProjectFromElement(Project existingProject, Element element)
//  {
//    existingProject.setIsAheadOfSchedule(Boolean.parseBoolean(getElementText(element, "isAheadOfSchedule")));
//    existingProject.setBudget(Double.parseDouble(getElementText(element, "budget")));
//
//    Resources existingResources = existingProject.getResources();
//
//
//    String projectType = element.getAttribute("type");
//
//    existingProject.setIsAheadOfSchedule(Boolean.parseBoolean(getElementText(element, "isAheadOfSchedule")));
//    existingProject.setBudget(Double.parseDouble(getElementText(element, "budget")));
//    existingProject.setExpectedDuration(Integer.parseInt(getElementText(element, "expectedDuration")));
//    existingProject.setIsOngoing(Boolean.parseBoolean(getElementText(element, "isOngoing")));
//
//    Element resourcesElement = (Element) element.getElementsByTagName(
//        "resources").item(0);
//    updateResourcesFromElement(existingResources, resourcesElement);
//    switch (projectType)
//    {
//      case "model.Residential":
//          Residential residential=(Residential) existingProject;
//          residential.setSize(Double.parseDouble(getElementText(element, "size")));
//          residential.setNumberOfKitchens(Integer.parseInt(getElementText(element, "numberOfKitchens")));
//          residential.setNumberOfBathrooms(Integer.parseInt(getElementText(element, "numberOfBathrooms")));
//          residential.setNumberOfOtherRoomsWithPlumbing(Integer.parseInt(getElementText(element, "numberOfOtherRoomsWithPlumbing")));
//          residential.setAsNewBuildOrRenovation(Boolean.parseBoolean(getElementText(element, "isNewBuild")));
//          break;
//
//      case "model.Commercial":
//        Commercial commercial = (Commercial) existingProject;
//        commercial.setSize(Double.parseDouble(getElementText(element, "size")));
//        commercial.setUseOfBuilding(getElementText(element, "useOfBuilding"));
//        commercial.setNumberOfFloors(Integer.parseInt(getElementText(element, "numberOfFloors")));
//        break;
//
//      case "model.Industrial":
//        Industrial industrial=(Industrial) existingProject;
//        industrial.setSize(Double.parseDouble(getElementText(element, "size")));
//        industrial.setUseOfBuilding(getElementText(element, "useOfBuilding"));
//        break;
//
//      case "model.RoadConstruction":
//        RoadConstruction road=(RoadConstruction) existingProject;
//        road.setLength(Double.parseDouble(getElementText(element, "length")));
//        road.setWidth(Double.parseDouble(getElementText(element, "width")));
//        road.setNumberOfBridges(Integer.parseInt(getElementText(element, "numberOfBridges")));
//        road.setNumberOfTunnels(Integer.parseInt(getElementText(element, "numberOfTunnels")));
//        road.setEnvironmentalChallenges(getElementText(element, "environmentalChallenges"));
//        break;
//    }
//  }
//  private void updateResourcesFromElement(Resources existingResources, Element resourcesElement)
//  {
//    Update update=new Update(Double.parseDouble(getElementText(resourcesElement, "manHoursWorkedExpenses")), Double.parseDouble(getElementText(resourcesElement, "materialsExpenses")), Double.parseDouble(getElementText(resourcesElement, "manHoursUsed")), null, null, null);
//    existingResources.updateResources(update);
//    existingResources.setExpectedExpenses(Double.parseDouble(getElementText(resourcesElement, "expectedExpenses")));
//    existingResources.setEstimatedTotalHours(Integer.parseInt(getElementText(resourcesElement, "estimatedTotalHours")));
//  }
//
//  private Element createProjectElement(Document doc, Project project, ProjectsList projects) {
//    Element projectElement = doc.createElement("project");
//    projectElement.setAttribute("type", project.getClass().getSimpleName());
//
//    appendTextElement(doc, projectElement, "id", project.getId());
//    appendTextElement(doc, projectElement, "isAheadOfSchedule", Boolean.toString(project.getIsAheadOfSchedule()));
//    appendTextElement(doc, projectElement, "budget", Double.toString(project.getBudget()));
//    appendTextElement(doc, projectElement, "expectedDuration", Integer.toString(project.getExpectedDuration()));
//    appendTextElement(doc, projectElement, "isOngoing", Boolean.toString(project.getIsOngoing()));
//
//    Element startDateElement = createDateElement(doc, project.getStartDate());
//    projectElement.appendChild(startDateElement);
//
//    Element resourcesElement = createResourcesElement(doc, project.getResources());
//    projectElement.appendChild(resourcesElement);
//
//    if (project instanceof Residential)
//    {
//      appendTextElement(doc, projectElement, "size", Double.toString(((Residential) project).getSize()));
//      appendTextElement(doc, projectElement, "numberOfKitchens", Integer.toString(((Residential) project).getNumberOfKitchens()));
//      appendTextElement(doc, projectElement, "numberOfBathrooms", Integer.toString(((Residential) project).getNumberOfBathrooms()));
//      appendTextElement(doc, projectElement, "numberOfOtherRoomsWithPlumbing", Integer.toString(((Residential) project).getNumberOfOtherRoomsWithPlumbing()));
//      appendTextElement(doc, projectElement, "isNewBuild", Boolean.toString(((Residential) project).getIsNewBuild()));
//
//    }
//    else if (project instanceof Commercial)
//    {
//      appendTextElement(doc, projectElement, "size", Double.toString(((Commercial) project).getSize()));
//      appendTextElement(doc, projectElement, "useOfBuilding", ((Commercial) project).getUseOfBuilding());
//      appendTextElement(doc, projectElement, "numberOfFloors", Integer.toString(((Commercial) project).getNumberOfFloors()));
//
//    }
//    else if (project instanceof Industrial)
//    {
//      appendTextElement(doc, projectElement, "Size", Double.toString(((Industrial) project).getSize()));
//      appendTextElement(doc, projectElement, "useOfBuilding", ((Industrial) project).getUseOfBuilding());
//
//    }
//    else if (project instanceof RoadConstruction)
//    {
//      appendTextElement(doc, projectElement, "length", Double.toString(((RoadConstruction) project).getLength()));
//      appendTextElement(doc, projectElement, "width", Double.toString(((RoadConstruction) project).getWidth()));
//      appendTextElement(doc, projectElement, "numberOfBridges", Double.toString(((RoadConstruction) project).getNumberOfBridges()));
//      appendTextElement(doc, projectElement, "numberOfTunnels", Double.toString(((RoadConstruction) project).getNumberOfTunnels()));
//      appendTextElement(doc, projectElement, "environmentalChallenges", ((RoadConstruction) project).getEnvironmentalChallenges());
//
//    }
//
//    return projectElement;
//  }
//
//  private Element createResourcesElement(Document doc, Resources resources)
//  {
//    Element resourcesElement = doc.createElement("resources");
//
//    appendTextElement(doc, resourcesElement, "expectedExpenses", Double.toString(resources.getExpectedExpenses()));
//    appendTextElement(doc, resourcesElement, "totalExpenses", Double.toString(resources.getTotalExpenses()));
//    appendTextElement(doc, resourcesElement, "materialsExpenses", Double.toString(resources.getMaterialsExpenses()));
//    appendTextElement(doc, resourcesElement, "manHoursUsed", Double.toString(resources.getManHoursUsed()));
//    appendTextElement(doc, resourcesElement, "manHoursWorkedExpenses", Double.toString(resources.getManHoursWorkedExpenses()));
//    appendTextElement(doc, resourcesElement, "estimatedTotalHours", Integer.toString(resources.getEstimatedTotalHours()));
//
//    return resourcesElement;
//  }
//  private Element createDateElement(Document doc, SimpleDate startDate)
//  {
//    Element startDateElement=doc.createElement("startDate");
//    appendTextElement(doc, startDateElement, "day", Integer.toString(startDate.getDay()));
//    appendTextElement(doc, startDateElement, "month", Integer.toString(startDate.getMonth()));
//    appendTextElement(doc, startDateElement, "year", Integer.toString(startDate.getYear()));
//    return startDateElement;
//  }
//
//  private void appendTextElement(Document doc, Element parentElement, String tagName, String textContent)
//  {
//    Element element = doc.createElement(tagName);
//    element.appendChild(doc.createTextNode(textContent));
//    parentElement.appendChild(element);
//  }
//  private SimpleDate createDateFromElement(Element startDateElement)
//  {
//    int day=Integer.parseInt(getElementText(startDateElement, "day"));
//    int month=Integer.parseInt(getElementText(startDateElement, "month"));
//    int year=Integer.parseInt(getElementText(startDateElement, "year"));
//    return new SimpleDate(day, month, year);
//  }
//
//  private Project createProjectFromElement(Element element)
//  {
//    String projectType = element.getAttribute("type");
//
//    String id = getElementText(element, "id");
//    boolean isAheadOfSchedule = Boolean.parseBoolean(getElementText(element, "isAheadOfSchedule"));
//    double budget = Double.parseDouble(getElementText(element, "budget"));
//    int expectedDuration = Integer.parseInt(getElementText(element, "expectedDuration"));
//    Element startDateElement = (Element) element.getElementsByTagName("startDate").item(0);
//
//    SimpleDate startDate = createDateFromElement(startDateElement);
//    boolean isOngoing = Boolean.parseBoolean(getElementText(element, "isOngoing"));
//
//    Element resourcesElement = (Element) element.getElementsByTagName("resources").item(0);
//    Resources resources = createResourcesFromElement(resourcesElement);
//
//    switch (projectType)
//    {
//      case "model.Residential":
//        try
//        {
//          double size = Double.parseDouble(getElementText(element, "size"));
//          int numberOfKitchens = Integer.parseInt(getElementText(element, "numberOfKitchens"));
//          int numberOfBathrooms = Integer.parseInt(getElementText(element, "numberOfBathrooms"));
//          int numberOfOtherRoomsWithPlumbing = Integer.parseInt(getElementText(element, "numberOfOtherRoomsWithPlumbing"));
//          boolean isNewBuild = Boolean.parseBoolean(getElementText(element, "isNewBuild"));
//
//          return new Residential(id, budget, startDate, expectedDuration,
//              resources, size, numberOfKitchens,
//              numberOfBathrooms, numberOfOtherRoomsWithPlumbing, isNewBuild);
//        }
//        catch(Exception e)
//        {
//          e.printStackTrace();
//        }
//      case "model.Commercial":
//        try
//        {
//          double commercialSize = Double.parseDouble(getElementText(element, "size"));
//          String useOfBuilding = getElementText(element, "useOfBuilding");
//          int numberOfFloors = Integer.parseInt(getElementText(element, "numberOfFloors"));
//
//          return new Commercial(id, budget, startDate, expectedDuration,
//              resources, commercialSize, useOfBuilding, numberOfFloors);
//        }
//        catch (Exception e)
//        {
//          e.printStackTrace();
//        }
//        case "model.Industrial":
//          try
//          {
//            double industrialSize = Double.parseDouble(getElementText(element, "size"));
//            String industrialUseOfBuilding = getElementText(element,
//                "useOfBuilding");
//
//            return new Industrial(id, budget, startDate, expectedDuration,
//                resources, industrialSize, industrialUseOfBuilding);
//          }
//          catch (Exception e)
//          {
//            e.printStackTrace();
//          }
//      case "model.RoadConstruction":
//        try
//        {
//          double length = Double.parseDouble(getElementText(element, "length"));
//          double width = Double.parseDouble(getElementText(element, "width"));
//          int numberOfBridges = Integer.parseInt(getElementText(element, "numberOfBridges"));
//          int numberOfTunnels = Integer.parseInt(getElementText(element, "numberOfTunnels"));
//          String environmentalChallenges = getElementText(element,
//              "environmentalChallenges");
//
//          return new RoadConstruction(id, budget,startDate, expectedDuration, resources,
//              length, width, numberOfBridges, numberOfTunnels, environmentalChallenges);
//        }
//        catch(Exception e)
//        {
//          e.printStackTrace();
//        }
//
//        return null;
//    }
///*
//<projects>
//    <projectsList>
//        <project type="model.Residential">
//            <id></id>
//            <isAheadOfSchedule></isAheadOfSchedule>
//            <budget></budget>
//            <expectedDuration></expectedDuration>
//            <startDate>
//                <day></day>
//                <month></month>
//                <year></year>
//            </startDate>
//            <isOngoing></isOngoing>
//            <resources>
//                <expectedExpenses></expectedExpenses>
//                <totalExpenses></totalExpenses>
//                <materialsExpenses></materialsExpenses>
//                <manHoursUsed></manHoursUsed>
//                <manHoursWorkedExpenses></manHoursWorkedExpenses>
//                <estimatedTotalHours></estimatedTotalHours>
//            </resources>
//            <size></size>
//            <numberOfKitchens></numberOfKitchens>
//            <numberOfBathrooms></numberOfBathrooms>
//            <numberOfOtherRoomsWithPlumbing></numberOfOtherRoomsWithPlumbing>
//            <isNewBuild></isNewBuild>
//        </project>
//
//        <project type="model.Commercial">
//            <id></id>
//            <isAheadOfSchedule></isAheadOfSchedule>
//            <budget></budget>
//            <expectedDuration></expectedDuration>
//            <startDate>
//                <day></day>
//                <month></month>
//                <year></year>
//            </startDate>
//            <isOngoing></isOngoing>
//            <resources>
//                <expectedExpenses></expectedExpenses>
//                <totalExpenses></totalExpenses>
//                <materialsExpenses></materialsExpenses>
//                <manHoursUsed></manHoursUsed>
//                <manHoursWorkedExpenses></manHoursWorkedExpenses>
//                <estimatedTotalHours></estimatedTotalHours>
//            </resources>
//            <size></size>
//            <useOfBuilding></useOfBuilding>
//            <numberOfFloors></numberOfFloors>
//        </project>
//
//        <project type="model.Industrial">
//            <id></id>
//            <isAheadOfSchedule></isAheadOfSchedule>
//            <budget></budget>
//            <expectedDuration></expectedDuration>
//            <startDate>
//                <day></day>
//                <month></month>
//                <year></year>
//            </startDate>
//            <isOngoing></isOngoing>
//            <resources>
//                <expectedExpenses></expectedExpenses>
//                <totalExpenses></totalExpenses>
//                <materialsExpenses></materialsExpenses>
//                <manHoursUsed></manHoursUsed>
//                <manHoursWorkedExpenses></manHoursWorkedExpenses>
//                <estimatedTotalHours></estimatedTotalHours>
//            </resources>
//            <size></size>
//            <useOfBuilding></useOfBuilding>
//        </project>
//
//        <project type="model.RoadConstruction">
//            <id></id>
//            <isAheadOfSchedule></isAheadOfSchedule>
//            <budget></budget>
//            <expectedDuration></expectedDuration>
//            <startDate>
//                <day></day>
//                <month></month>
//                <year></year>
//            </startDate>
//            <isOngoing></isOngoing>
//            <resources>
//                <expectedExpenses></expectedExpenses>
//                <totalExpenses></totalExpenses>
//                <materialsExpenses></materialsExpenses>
//                <manHoursUsed></manHoursUsed>
//                <manHoursWorkedExpenses></manHoursWorkedExpenses>
//                <estimatedTotalHours></estimatedTotalHours>
//            </resources>
//            <length></length>
//            <width></width>
//            <numberOfBridges></numberOfBridges>
//            <numberOfTunnels></numberOfTunnels>
//            <environmentalChallenges></environmentalChallenges>
//
//        </project>
//
//
//    </projectsList>
//</projects>
// */
//
//    return null;
//  }
//  private Resources createResourcesFromElement(Element resourcesElement)
//  {
//    double expectedExpenses = Double.parseDouble(getElementText(resourcesElement, "expectedExpenses"));
//    double materialsExpenses = Double.parseDouble(getElementText(resourcesElement, "materialsExpenses"));
//    double manHoursUsed = Double.parseDouble(getElementText(resourcesElement, "manHoursUsed"));
//    double manHoursWorkedExpenses = Double.parseDouble(getElementText(resourcesElement, "manHoursWorkedExpenses"));
//    int estimatedTotalHours = Integer.parseInt(getElementText(resourcesElement, "estimatedTotalHours"));
//
//    Resources resources = new Resources(expectedExpenses, estimatedTotalHours);
//    Update update =new Update(manHoursWorkedExpenses, materialsExpenses, manHoursUsed, null, null, null);
//    resources.updateResources(update);
//    return resources;
//  }
//
//  private String getElementText(Element element, String tagName)
//  {
//    return element.getElementsByTagName(tagName).item(0).getTextContent();
//  }
//
//
//  private void writeXMLToFile(Document doc, String fileName)
//  {
//    try
//    {
//      TransformerFactory transformerFactory = TransformerFactory.newInstance();
//      Transformer transformer = transformerFactory.newTransformer();
//      DOMSource source = new DOMSource(doc);
//      StreamResult result = new StreamResult(new File(fileName));
//      transformer.transform(source, result);
//    }
//    catch (Exception e)
//    {
//      e.printStackTrace();
//    }
//  }
//}
