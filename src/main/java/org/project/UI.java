package org.project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
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
    int rowsPerPage = 10;

    Stage stage;
    ScrollPane root;
    final VBox filtersVBox = new VBox();
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
        getData();
        createTable();
        Pagination pagination = new Pagination((iscte.getLectures().size() / rowsPerPage + 1), 0);
        pagination.setPageFactory(this::createPage);
        Scene scene = new Scene(new BorderPane(pagination), 1024, 768);
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

    private void makeFilters() {
        for (LectureAttributes a : LectureAttributes.values()) {
            Label currentLabel = new Label(a.name());
            TextField currentTextField = new TextField();
            filterLabels.add(currentLabel);
            filterTextFields.add(currentTextField);
            filtersVBox.getChildren().addAll(currentLabel, currentTextField);
        }
    }

    private void createTable() {
        table.setEditable(false);
        for (LectureAttributes a : LectureAttributes.values()) {
            TableColumn currentCol = new TableColumn(a.name());
            currentCol.setMinWidth(COL_SIZE);
            currentCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>(a.name()));
            tableColumns.add(currentCol);
        }
        table.getColumns().addAll(tableColumns);
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, iscte.getLectures().size());
        table.setItems(FXCollections.observableArrayList(iscte.getLectures().subList(fromIndex, toIndex)));

        return new BorderPane(table);
    }
}