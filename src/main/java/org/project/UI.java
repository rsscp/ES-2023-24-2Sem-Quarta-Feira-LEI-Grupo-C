package org.project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class UI extends Application {

    private final int COL_SIZE = 100;

    private ISCTE iscte = new ISCTE();
    int rowsPerPage = 10;

    Stage stage;
    StackPane root = new StackPane();
    final VBox mainVBox = new VBox();
    final HBox filtersHBox = new HBox();
    private TableView table = new TableView();
    List<Label> filterLabels = new ArrayList<>();
    List<TextField> filterTextFields = new ArrayList<>();
    List<TableColumn> tableColumns = new ArrayList<>();
    final Label tableLabel = new Label("Lectures");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setWidth(1200);
        stage.setHeight(800);
        stage.setMaximized(true);
        //mainVBox.setSpacing(5);
        //mainVBox.setPadding(new Insets(10, 0, 0, 10));
        mainVBox.getChildren().addAll(filtersHBox);
        getData();
        createFilters();
        createTable();
        root.getChildren().addAll(mainVBox);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Table pager");
        stage.show();
    }

    private void getData() {
        try {
            iscte.createCSV("https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv");
            iscte.readLeactures("HorarioDeExemplo.csv");
            iscte.writeDownLectures();
        } catch(MalformedURLException e) {
            // TODO Code run after receiving an invalid URL
        } catch(IOException e) {
            // TODO Code run when file couldn't be downloaded
        }
    }

    private void createFilters() {
        for (LectureAttributes a : LectureAttributes.values()) {
            //Label currentLabel = new Label(a.name());
            TextField currentTextField = new TextField();
            currentTextField.prefWidthProperty().bind(stage.widthProperty().divide(11));  //TODO not hardcoded
            //filterLabels.add(currentLabel);
            filterTextFields.add(currentTextField);
            //filtersVBox.getChildren().addAll(currentLabel, currentTextField);
            filtersHBox.getChildren().addAll(currentTextField);
        }
    }

    private void createTable() {
        table.prefHeightProperty().bind(stage.heightProperty());
        //table.prefWidthProperty().bind(stage.widthProperty());
        table.setEditable(false);
        for (LectureAttributes a : LectureAttributes.values()) {
            TableColumn currentCol = new TableColumn(a.name());
            currentCol.prefWidthProperty().bind(stage.widthProperty().divide(11));  //TODO not hardcoded
            currentCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>(a.name()));
            tableColumns.add(currentCol);
        }
        table.getColumns().addAll(tableColumns);
        table.setItems(iscte.getLectures());
        mainVBox.getChildren().addAll(table);
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, iscte.getLectures().size());
        table.setItems(FXCollections.observableArrayList(iscte.getLectures().subList(fromIndex, toIndex)));

        return new BorderPane(table);
    }
}