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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

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
        ArrayList<Pair<Integer, ArrayList<Integer>>> listaDePares = new ArrayList<Pair<Integer, ArrayList<Integer>>>();

        for (int i = 0; i < listaDeListas.size(); i++) {
            ArrayList<Integer> lista = listaDeListas.get(i);
            Pair<Integer, ArrayList<Integer>> par = new Pair<>(i, lista);
            listaDePares.add(par);
        }
        ObservableList<Pair<Integer, ArrayList<Integer>>> list = FXCollections.observableArrayList(listaDePares);
        list=list.filtered(lista->{
            return !lista.getValue().isEmpty();
        });

        Pane pane = new Pane();
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Integer> listaInterna = list.get(i).getValue();
            if (!listaInterna.isEmpty()) {
                double x = 100 ;
                double y = 100 + i * 150;
                Circle circle = new Circle(x, y, RADIUS);
                circle.setFill(Color.BLACK);
                circle.setStroke(Color.BLACK);
                pane.getChildren().add(circle);

                /*Text text = new Text(l.get(list.get(i).getKey()).toString());
                text.setFill(Color.RED);
                double textWidth = text.getLayoutBounds().getWidth();
                text.setX(x -textWidth/2);
                double textHeight = text.getLayoutBounds().getHeight();
                text.setY(y);
                pane.getChildren().add(text);*/

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

        ArrayList<Circle> circulos = new ArrayList<Circle>();
        /*String[] s1= {"LETI, LEI, LEI-PL, LIGE, LIGE-PL","Fundamentos de Arquitectura de Computadores","L0705TP23", "ET-A9, ET-A8, ET-A7, ET-A12, ET-A11, ET-A10","44","Sex","12:45:00","16:30:00","09/12/2022","Sala/anfiteatro aulas","C5.06"};
        String[] s2= {"ME","Teoria dos Jogos e dos Contratos","01789TP01","MEA1","30","Ter","10:00:00","14:00:00","04/11/2022","Sala Aulas Mestrado","AA2.25"};*/
        ObservableList<Lecture> l = ISCTE.getInstance().getLectures();
        /*l.add(new Lecture(s1));
        l.add(new Lecture(s2));*/
        ArrayList<ArrayList<Integer>> listaDeListas = ISCTE.getInstance().measureConflicts(l);
        ArrayList<Pair<Integer, ArrayList<Integer>>> listaDePares = new ArrayList<Pair<Integer, ArrayList<Integer>>>();

        for (int i = 0; i < listaDeListas.size(); i++) {
            ArrayList<Integer> lista = listaDeListas.get(i);
            Pair<Integer, ArrayList<Integer>> par = new Pair<>(i, lista);
            listaDePares.add(par);
        }
        ObservableList<Pair<Integer, ArrayList<Integer>>> list = FXCollections.observableArrayList(listaDePares);
        list=list.filtered(lista->{
            return !lista.getValue().isEmpty();
        });

        Pane pane = new Pane();
        double centerX = 300;
        double centerY = 300;
        double radius = 200;

        for (int i = 0; i < list.size(); i++) {
            double angle = 2 * Math.PI * i / list.size();
            double x = calculateX(angle, radius, centerX);
            double y = calculateY(angle, radius, centerY);

            Circle circle = new Circle(x, y, RADIUS);
            circle.setFill(Color.BLACK);
            circle.setStroke(Color.BLACK);
            circle.setId(l.get(list.get(i).getKey()).toString());
            pane.getChildren().add(circle);
            circulos.add(circle);
            ArrayList<Integer> inteiros=list.get(i).getValue();
            for(int j=0; j<inteiros.size();j++){
                for(Circle circulo: circulos){
                    if(circulo.getId().equals(l.get(inteiros.get(j)).toString())){
                        Line line = new Line(x, y + RADIUS, circulo.getCenterX(), circulo.getCenterY() - RADIUS);
                        pane.getChildren().add(line);
                    }
                }
            }
        }

        for (Circle circulo : circulos) {
            circulo.setOnMouseClicked(event -> {
                Circle clickedCircle = (Circle) event.getSource();
                String circleId = clickedCircle.getId();
                Text text = new Text(circleId);
                text.setX(clickedCircle.getCenterX() - 15);
                text.setY(clickedCircle.getCenterY() - 15);
                text.setFill(Color.RED);
                pane.getChildren().add(text);
                pane.setOnMouseClicked(e -> {
                    pane.getChildren().remove(text);
                });
            });
        }

        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Conflict Graph Visualizer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static double calculateX(double angle, double radius, double centerX) {
        return centerX + radius * Math.cos(angle);
    }
    public static double calculateY(double angle, double radius, double centerY) {
        return centerY + radius * Math.sin(angle);
    }

}