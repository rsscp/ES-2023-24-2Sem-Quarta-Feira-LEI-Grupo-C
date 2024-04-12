package org.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class UI extends Application {

    private final int COL_SIZE = 100;

    private ISCTE iscte = new ISCTE();

    Stage stage;
    BorderPane root = new BorderPane();
    final VBox filtersVBox = new VBox();
    final VBox rootVBox = new VBox();
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
        Scene scene = new Scene(root);

        this.stage = stage;
        stage.setTitle("Table View Sample");
        stage.setMaximized(true);

        rootVBox.setSpacing(5);
        rootVBox.setPadding(new Insets(10, 0, 0, 10));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(rootVBox);
        root.setCenter(scrollPane);

        tableLabel.setFont(new Font("Arial", 18));

        setupData();
        setupFilters();
        setupTable();

        stage.setScene(scene);
        stage.show();
    }

    private void setupData() {
        try {
            ISCTE.createCSV("https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv");
            iscte.readLeactures("HorarioDeExemplo.csv");
            iscte.writeDownLectures();
        } catch(MalformedURLException e) {
            // TODO Code run after receiving an invalid URL
        } catch(IOException e) {
            // TODO Code run when file couldn't be downloaded
        }
    }

    private void setupFilters() {
        for (LectureAttributes a : LectureAttributes.values()) {
            Label currentLabel = new Label(a.name());
            TextField currentTextField = new TextField();
            filterLabels.add(currentLabel);
            filterTextFields.add(currentTextField);
            filtersVBox.getChildren().addAll(currentLabel, currentTextField);
        }
        rootVBox.getChildren().add(filtersVBox);
    }

    private void setupTable() {
        table.prefHeightProperty().bind(stage.heightProperty());
        table.prefWidthProperty().bind(stage.widthProperty());
        table.setEditable(false);

        for (LectureAttributes a : LectureAttributes.values()) {
            TableColumn currentCol = new TableColumn(a.name());
            currentCol.setMinWidth(COL_SIZE);
            currentCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>(a.name()));
            tableColumns.add(currentCol);
        }

        table.setItems(iscte.getLectures());
        table.getColumns().addAll(tableColumns);

        rootVBox.getChildren().addAll(tableLabel, table);
    }
}