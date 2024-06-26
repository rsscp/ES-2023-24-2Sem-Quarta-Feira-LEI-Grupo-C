package org.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource("Start.fxml"));
        stage.setResizable(false);
        stage.setTitle("Start");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
