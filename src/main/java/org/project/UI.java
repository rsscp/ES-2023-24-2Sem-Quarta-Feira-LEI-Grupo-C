package org.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UI extends Application {

    private final int COL_SIZE = 100;

    private ISCTE iscte = new ISCTE();
    int rowsPerPage = 10;
    Stage stage;
    VBox startPageRoot;
    VBox tablePageRoot;
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

        startStartPage();
        startTablePage();

        stage.setScene(new Scene(startPageRoot));
        stage.setTitle("Title");
        stage.show();
    }

    private void startStartPage() {
        Label urlLabel = new Label("URL do ficheiro CSV");
        TextField urlField = new TextField();
        HBox urlLayout = new HBox(urlLabel, urlField);
        urlLayout.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE );
        urlLayout.setSpacing(10);

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            try {
                getData(urlField.getText(), "");
            } catch (IOException ex) {
                startPageRoot.getChildren().addAll(new Label("Error"));
                return;
            }
            stage.setScene(new Scene(tablePageRoot));
        });

        Button chooseFileButton = new Button("Select CSV file");
        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File selectedFile = fileChooser.showOpenDialog(stage);
        });

        startPageRoot = new VBox(urlLayout, chooseFileButton, continueButton);
        startPageRoot.setAlignment(Pos.CENTER);
        startPageRoot.setSpacing(10);
    }

    private void startTablePage() {
        tablePageRoot = new VBox(createFilters(), createTable());
    }

    private void getData(String url, String path) throws IOException, IllegalArgumentException {
        //iscte.createCSV("https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv");  //TODO Delete
        if (path == "") {
            iscte.getUrlFile(url);
            iscte.readLeactures("HorarioDeExemplo.csv");
        } else {
            iscte.readLeactures(path);
        }
    }

    private Node createFilters() {
        GridPane filters = new GridPane();
        filters.getColumnConstraints().addAll(new ColumnConstraints(20), new ColumnConstraints(80));
        int rowIndex = 0;
        for (LectureAttributes a : LectureAttributes.values()) {
            Label currentLabel = new Label(a.name());
            TextField currentTextField = new TextField();
            currentTextField.setPrefWidth(500);
            filters.addRow(rowIndex, currentLabel, currentTextField);
            rowIndex++;
        }
        return filters;
    }

    private Node createTable() {
        TableView table = new TableView();
        table.prefHeightProperty().bind(stage.heightProperty());
        table.setEditable(false);
        for (LectureAttributes a : LectureAttributes.values()) {
            TableColumn currentCol = new TableColumn(a.name());
            currentCol.prefWidthProperty().bind(stage.widthProperty().divide(11));  //TODO not hardcoded
            currentCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>(a.name()));
            tableColumns.add(currentCol);
        }
        table.getColumns().addAll(tableColumns);
        table.setItems(iscte.getLectures());
        return table;
    }
}