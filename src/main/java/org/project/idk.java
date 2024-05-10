package org.project;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class idk extends Application {


    private static final int RADIUS = 20;

    @Override
    public void start(Stage primaryStage) {

        String[] s1= {"LETI, LEI, LEI-PL, LIGE, LIGE-PL","Fundamentos de Arquitectura de Computadores","L0705TP23", "ET-A9, ET-A8, ET-A7, ET-A12, ET-A11, ET-A10","44","Sex","12:45:00","16:30:00","09/12/2022","Sala/anfiteatro aulas","C5.06"};
        ObservableList<Lecture> l = ISCTE.getInstance().getLectures();
        l.add(new Lecture(s1));
        ArrayList<ArrayList<Integer>> listaDeListas = ISCTE.getInstance().measureConflicts(l);
        ObservableList<ArrayList<Integer>> list = FXCollections.observableArrayList(listaDeListas);
        list=list.filtered(lista->{
            return !lista.isEmpty();
        });

        Pane pane = new Pane();
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Integer> listaInterna = list.get(i);
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

        String[] s1= {"LETI, LEI, LEI-PL, LIGE, LIGE-PL","Fundamentos de Arquitectura de Computadores","L0705TP23", "ET-A9, ET-A8, ET-A7, ET-A12, ET-A11, ET-A10","44","Sex","12:45:00","16:30:00","09/12/2022","Sala/anfiteatro aulas","C5.06"};
        ObservableList<Lecture> l = ISCTE.getInstance().getLectures();
        l.add(new Lecture(s1));
        ArrayList<ArrayList<Integer>> listaDeListas = ISCTE.getInstance().measureConflicts(l);
        ObservableList<ArrayList<Integer>> list = FXCollections.observableArrayList(listaDeListas);
        list=list.filtered(lista->{
            return !lista.isEmpty();
        });


        Pane pane = new Pane();
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Integer> listaInterna = list.get(i);
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