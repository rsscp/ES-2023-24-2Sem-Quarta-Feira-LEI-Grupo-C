package org.project;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;

public class GraphNetwork extends Application {


    private static final int RADIUS = 20;

    /**
     * Método principal que inicia a aplicação JavaFX.
     *
     * @param args Argumentos da linha de comando (não são usados neste aplicativo).
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {}


    /**
     * Inicializa a interface gráfica de usuário para visualizar conflitos entre aulas.
     *
     * @param primaryStage O "palco" principal da aplicação.
     */
    public static void start2(Stage primaryStage) {
        // Declaração e inicialização das variáveis e estruturas de dados necessárias
        ArrayList<Circle> circulos = new ArrayList<Circle>();
        /*String[] s1 = {"LETI, LEI, LEI-PL, LIGE, LIGE-PL", "Fundamentos de Arquitectura de Computadores", "L0705TP23", "ET-A9, ET-A8, ET-A7, ET-A12, ET-A11, ET-A10", "44", "Sex", "12:45:00", "14:31:00", "09/12/2022", "Sala/anfiteatro aulas", "C5.06"};
        String[] s2 = {"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Ter", "10:00:00", "14:00:00", "04/11/2022", "Sala Aulas Mestrado", "AA2.25"};*/
        ObservableList<Lecture> l = ISCTE.getInstance().getLectures();
        /*l.add(new Lecture(s1));
        l.add(new Lecture(s2));*/
        ArrayList<ArrayList<Integer>> listaDeListas = ISCTE.getInstance().measureConflicts(l);
        ArrayList<Pair<Integer, ArrayList<Integer>>> listaDePares = new ArrayList<Pair<Integer, ArrayList<Integer>>>();

        // Criação dos pares de índices e suas listas de conflitos
        for (int i = 0; i < listaDeListas.size(); i++) {
            ArrayList<Integer> lista = listaDeListas.get(i);
            Pair<Integer, ArrayList<Integer>> par = new Pair<>(i, lista);
            listaDePares.add(par);
        }
        ObservableList<Pair<Integer, ArrayList<Integer>>> list = FXCollections.observableArrayList(listaDePares);
        list = list.filtered(lista -> {
            return !lista.getValue().isEmpty();
        });

        // Configuração das variaveis para o formato da interface gráfica
        Pane pane = new Pane();
        double centerX = 500;
        double centerY = 300;
        double radius = 200;

        // Desenho da rede de grafos através de circulos e linhas
        for (int i = 0; i < list.size(); i++) {
            double angle = 2 * Math.PI * i / list.size();
            double x = calculateX(angle, radius, centerX);
            double y = calculateY(angle, radius, centerY);

            Circle circle = new Circle(x, y, RADIUS);
            circle.setFill(Color.BLACK);
            circle.setStroke(Color.BLACK);
            circle.setId(l.get(list.get(i).getKey()).toString2());
            pane.getChildren().add(circle);
            circulos.add(circle);
            ArrayList<Integer> inteiros = list.get(i).getValue();
            for (int j = 0; j < inteiros.size(); j++) {
                for (Circle circulo : circulos) {
                    if (circulo.getId().equals(l.get(inteiros.get(j)).toString2())) {
                        Line line = new Line(x, y + RADIUS, circulo.getCenterX(), circulo.getCenterY() - RADIUS);
                        pane.getChildren().add(line);
                    }
                }
            }
        }

        // Adiciona o texto aos círculos com ajuste de posição
        for (Circle circulo : circulos) {
            String circleId = circulo.getId();
            Text text = new Text(circleId);
            if (circulo.getCenterX() < centerX) {
                text.setX(circulo.getCenterX() - 300);
            } else {
                text.setX(circulo.getCenterX() + RADIUS);
            }
            text.setY(circulo.getCenterY() - 25);
            text.setFill(Color.RED);
            pane.getChildren().add(text);
        }

        // Configuração e exibição da cena principal
        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Conflict Graph Visualizer");
        primaryStage.show();
    }

    /**
     * Calcula a coordenada X dado um ângulo, raio e coordenada X do centro.
     *
     * @param angle   O ângulo em radianos.
     * @param radius  O raio da circunferência.
     * @param centerX A coordenada X do centro da circunferência.
     * @return A coordenada X do nó na circunferência.
     */
    public static double calculateX(double angle, double radius, double centerX) {
        return centerX + radius * Math.cos(angle);
    }

    /**
     * Calcula a coordenada Y  dado um ângulo, raio e coordenada Y do centro.
     *
     * @param angle   O ângulo em radianos.
     * @param radius  O raio da circunferência.
     * @param centerY A coordenada Y do centro da circunferência.
     * @return A coordenada Y do nó na circunferência.
     */
    public static double calculateY(double angle, double radius, double centerY) {
        return centerY + radius * Math.sin(angle);
    }
}