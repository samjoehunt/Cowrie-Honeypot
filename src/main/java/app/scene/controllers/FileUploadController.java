package app.scene.controllers;

import app.data.JsonStore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class FileUploadController {
    @FXML
    private Label fileNameLabel;

    @FXML
    private Button dashboardButton;

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

    @FXML
    public void dashboardScene() {
        SceneController.switchScene("/app.scene/dashboard.fxml");
    }
}
