package app.scene.controllers;

import app.data.JsonStore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

/**
 * Controller class for fileUpload.fxml
 */
public class FileUploadController {
    /**
     * Label containing the file name uploaded by the user
     */
    @FXML
    private Label fileNameLabel;

    /**
     * Button instance directing the user to the dashboard scene
     */
    @FXML
    private Button dashboardButton;

    /**
     * Function to be called when the "Upload" button is clicked.
     * Brings up a file chooser, restricted to only allow the user to choose .json files.
     * If a valid file is selected, the fileNameLabel is updated to display the chosen file's name,
     * and dashboardButton is made visible and managed.
     */
    @FXML
    public void uploadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Log File");
        FileChooser.ExtensionFilter jsonFilter = new FileChooser.ExtensionFilter("JSON Files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(jsonFilter);

        File selectedFile = fileChooser.showOpenDialog(fileNameLabel.getScene().getWindow());

        if (selectedFile != null) {
            try {
                JsonStore.getInstance().loadJson(selectedFile);
                fileNameLabel.setText("Uploaded " + selectedFile.getName());
                dashboardButton.setVisible(true);
                dashboardButton.setManaged(true);
            } catch (IOException e) {
                System.err.println("Failed to load JSON: " + e.getMessage());
            }
        }
    }

    /**
     * Function to change the scene to the dashboard scene
     */
    @FXML
    public void dashboardScene() {
        SceneController.switchScene("/app.scene/dashboard.fxml");
    }
}
