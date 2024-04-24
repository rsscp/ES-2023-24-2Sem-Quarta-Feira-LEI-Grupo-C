package org.project.ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;

public class StartController {

    @FXML
    private Parent root;

    @FXML
    private void openUrlInput() throws IOException{
        Parent sceneRoot = FXMLLoader.load(ClassLoader.getSystemResource("UrlInput.fxml"));
        ((Stage) root.getScene().getWindow()).setTitle("Remote File");
        ((Stage) root.getScene().getWindow()).setScene(new Scene(sceneRoot));
    }

    @FXML
    private void chooseLocalFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(((Stage) root.getScene().getWindow()));
        if (selectedFile == null)
            ;//TODO
    }
}
