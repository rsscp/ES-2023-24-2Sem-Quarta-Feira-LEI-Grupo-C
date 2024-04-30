package org.project.ui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.lang.module.Configuration;
import java.nio.file.Files;

public class PathInputController {

    @FXML
    private Parent root;
    @FXML
    private TextField pathInput;

    @FXML
    private void chooseLocalFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(((Stage) root.getScene().getWindow()));
        if (selectedFile != null)
            pathInput.setText(selectedFile.getAbsolutePath());
    }
}
