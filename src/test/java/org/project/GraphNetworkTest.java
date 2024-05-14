package org.project;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphNetworkTest {



    @Test
    void start2() {
        // Create and start JavaFX application on the JavaFX Application Thread
        Platform.runLater(() -> {
            // Create a new stage
            Stage stage = new Stage();
            // Call the start2 method of GraphNetwork
            GraphNetwork.start2(stage);
            // Get the scene from the stage
            Scene scene = stage.getScene();
            // Get the root from the scene
            Pane root = (Pane) scene.getRoot();
            // Get the number of circles added to the pane
            long circleCount = root.getChildren().stream().filter(node -> node instanceof Circle).count();
            // Assuming how many circles you expect to be added to the pane
            assertEquals(2, circleCount); // Update this number according to your expected result
        });
    }

     @Test
    void calculateX() {
        // Test cases for calculateX method
        assertEquals(500.0 + 200.0 * Math.cos(0.0), GraphNetwork.calculateX(0.0, 200.0, 500.0));
        assertEquals(500.0 + 200.0 * Math.cos(Math.PI / 2), GraphNetwork.calculateX(Math.PI / 2, 200.0, 500.0));
        assertEquals(500.0 + 200.0 * Math.cos(Math.PI), GraphNetwork.calculateX(Math.PI, 200.0, 500.0));
        assertEquals(500.0 + 200.0 * Math.cos(3 * Math.PI / 2), GraphNetwork.calculateX(3 * Math.PI / 2, 200.0, 500.0));
    }

    @Test
    void calculateY() {
        // Test cases for calculateY method
        assertEquals(300.0 + 200.0 * Math.sin(0.0), GraphNetwork.calculateY(0.0, 200.0, 300.0));
        assertEquals(300.0 + 200.0 * Math.sin(Math.PI / 2), GraphNetwork.calculateY(Math.PI / 2, 200.0, 300.0));
        assertEquals(300.0 + 200.0 * Math.sin(Math.PI), GraphNetwork.calculateY(Math.PI, 200.0, 300.0));
        assertEquals(300.0 + 200.0 * Math.sin(3 * Math.PI / 2), GraphNetwork.calculateY(3 * Math.PI / 2, 200.0, 300.0));
    }
}