package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Button cancelButton;
    private AnchorPane scenePane;
    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSelectProjectType(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/SelectProjectType.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToEditCommercial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EditCommercial1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToEditIndustrial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EditIndustrial1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToEditResidential(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EditResidential1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToEditRoadConstruction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EditroadConstruction1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToViewEdit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ViewEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCreateCommercial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/createCommercial.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCreateIndustrial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CreateIndustrial.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCreateResidential(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CreateResidential.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCreateRoadConstruction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CreateRoadConstruction.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void logout(ActionEvent event)
    {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
}
