package org.project.ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.*;
import org.project.ISCTE;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class StartController {

    @FXML
    private Parent root;

    @FXML
    private Text fileselecterror;

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
        try {
            if (selectedFile != null) {
                ISCTE.getInstance().readLeactures(selectedFile.getAbsolutePath());
                loadTablePage();
            }
        } catch (IOException e) {
            fileselecterror.setManaged(true);
            fileselecterror.setVisible(true);
        }
    }

    private void loadTablePage() {
        try {
            Parent sceneRoot = FXMLLoader.load(ClassLoader.getSystemResource("LecturesTable.fxml"));
            ((Stage) root.getScene().getWindow()).setTitle("Lectures Table");
            ((Stage) root.getScene().getWindow()).setResizable(true);
            ((Stage) root.getScene().getWindow()).setScene(new Scene(sceneRoot));
            ((Stage) sceneRoot.getScene().getWindow()).setMaximized(true);
        } catch (MalformedURLException e1) {
            //TODO
        } catch (IOException e2) {
            //TODO
        }
    }
}
