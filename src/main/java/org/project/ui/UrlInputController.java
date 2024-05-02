package org.project.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.project.ISCTE;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class UrlInputController {

    @FXML
    private Parent root;
    @FXML
    private TextField urlInput;

    @FXML
    private void loadTablePage() {
        try {
            ISCTE.getInstance().getUrlFile(urlInput.getText());
            ISCTE.getInstance().readLeactures("HorarioDeExemplo.csv");              //TODO fazer para ele ler do atributo fileName em vez de argumento
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
