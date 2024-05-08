package org.project;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class idk extends Application {


    private static final int RADIUS = 20;

    @Override
    public void start(Stage primaryStage) {

        ArrayList<ArrayList<Integer>> listaDeListas = ISCTE.getInstance().measureConflicts(ISCTE.getInstance().getLectures());
        Pane pane = new Pane();
        for (int i = 0; i < listaDeListas.size(); i++) {
            ArrayList<Integer> listaInterna = listaDeListas.get(i);
            if (!listaInterna.isEmpty()) {
                double x = 100 + i * 150;
                double y = 100;
                Circle circle = new Circle(x, y, RADIUS);
                circle.setFill(Color.BLACK);
                circle.setStroke(Color.BLACK);
                pane.getChildren().add(circle);

                for (int j = 0; j < listaInterna.size(); j++) {
                    double childX = x + (j - listaInterna.size() / 2.0) * 50;
                    double childY = y + 100;
                    Circle childCircle = new Circle(childX, childY, RADIUS);
                    childCircle.setFill(Color.WHITE);
                    childCircle.setStroke(Color.BLACK);
                    pane.getChildren().add(childCircle);

                    Line line = new Line(x, y + RADIUS, childX, childY - RADIUS);
                    pane.getChildren().add(line);
                }
            }
        }


        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Conflict Graph Visualizer");
        primaryStage.show();
    }


    public static void start2(Stage primaryStage) {

        ArrayList<ArrayList<Integer>> listaDeListas = ISCTE.getInstance().measureConflicts(ISCTE.getInstance().getLectures());
        Pane pane = new Pane();
        for (int i = 0; i < listaDeListas.size(); i++) {
            ArrayList<Integer> listaInterna = listaDeListas.get(i);
            if (!listaInterna.isEmpty()) {
                double x = 100 + i * 150;
                double y = 100;
                Circle circle = new Circle(x, y, RADIUS);
                circle.setFill(Color.BLACK);
                circle.setStroke(Color.BLACK);
                pane.getChildren().add(circle);

                for (int j = 0; j < listaInterna.size(); j++) {
                    double childX = x + (j - listaInterna.size() / 2.0) * 50;
                    double childY = y + 100;
                    Circle childCircle = new Circle(childX, childY, RADIUS);
                    childCircle.setFill(Color.WHITE);
                    childCircle.setStroke(Color.BLACK);
                    pane.getChildren().add(childCircle);

                    Line line = new Line(x, y + RADIUS, childX, childY - RADIUS);
                    pane.getChildren().add(line);
                }
            }
        }


        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Conflict Graph Visualizer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}