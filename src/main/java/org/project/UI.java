package org.project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.scene.text.Font;
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
    ScrollPane root;
    final HBox hBox = new HBox();
    final GridPane gridPane = new GridPane();
    private TableView table = new TableView();

    private BorderPane borderPane;
    List<Label> filterLabels = new ArrayList<>();
    List<TextField> filterTextFields = new ArrayList<>();
    List<TableColumn> tableColumns = new ArrayList<>();
    final Label tableLabel = new Label("Lectures");
    private TextField userInputField;


    private Button btnSearchAND;
    private Button btnSearchOR;
    private Button btnClear;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setWidth(1200);
        stage.setHeight(800);
        stage.setMaximized(true);
        this.creatButtons();
        this.makeFilters();
        this.getData();
        this.createTable();
        Pagination pagination = new Pagination((iscte.getLectures().size() / rowsPerPage + 1), 0);
        pagination.setPageFactory(this::createPage);
        this.borderPane.setCenter(pagination);
        //BorderPane borderPane = new BorderPane(pagination, this.gridPane, null, null, null);

        Scene scene = new Scene(this.borderPane, 1024, 768);
        stage.setScene(scene);
        stage.setTitle("Table pager");
        stage.setMaximized(true);
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
    }
    
    private void makeFilters() {
        HBox hbox = new HBox();
        VBox vBox = new VBox();

        for (LectureAttributes a : LectureAttributes.values()) {
            TextField currentTextField = new TextField();
            currentTextField.setPrefWidth(100);
            currentTextField.setStyle("-fx-padding: 5px;");
            hbox.getChildren().add(currentTextField);
        }

        Label label = new Label("Make your filtering!");
        label.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial';");
        label.setMinWidth(1100);
        label.setMinHeight(30);
        label.setAlignment(Pos.CENTER);

        vBox.getChildren().add(label);
        vBox.getChildren().add(hbox);

        HBox buttons = new HBox();
        buttons.setSpacing(5);
        buttons.getChildren().addAll(this.btnSearchAND, this.btnSearchOR, this.btnClear);

        VBox btns = new VBox();
        btns.setPadding(new Insets(5));
        btns.getChildren().add(buttons);

        this.hBox.setSpacing(5);
        this.hBox.getChildren().addAll(vBox, btns);

        this.borderPane = new BorderPane();
        this.borderPane.setTop(this.hBox);
    }

    private void creatButtons() {
        this.btnClear = new Button("Clear!");
        this.btnClear.setPrefWidth(100);
        this.btnClear.setPrefHeight(50);
        this.btnClear.setOnAction(event -> {
            this.clearAllTextFields();
        });


        this.btnSearchAND = new Button("Search AND");
        this.btnSearchAND.setPrefWidth(100);
        this.btnSearchAND.setPrefHeight(50);
        this.btnSearchAND.setOnAction(event -> {
            this.setVisibleFilteredItems(this.getFilters(), true);
            this.clearAllTextFields();
        });

        this.btnSearchOR = new Button("Search OR");
        this.btnSearchOR.setPrefWidth(100);
        this.btnSearchOR.setPrefHeight(50);
        this.btnSearchOR.setOnAction(event -> {
            this.setVisibleFilteredItems(this.getFilters(), false);
            this.clearAllTextFields();
        });

    }

    private void clearAllTextFields() {
        VBox first = (VBox) this.hBox.getChildren().get(0);
        HBox wantedHbox = (HBox) first.getChildren().get(1);
        ObservableList<Node> components = wantedHbox.getChildren();

        for (Node node : components) {
            if (node instanceof TextField textField) {
                if (!(textField.getText().equals(""))) {
                    textField.setText("");
                }
            }
        }
    }

    private String[] getFilters() {
        VBox first = (VBox) this.hBox.getChildren().get(0);
        HBox wantedHbox = (HBox) first.getChildren().get(1);
        ObservableList<Node> components = wantedHbox.getChildren();
        String[] filters = new String[11];
        int index = 0;

        for (Node node : components) {
            if (node instanceof TextField textField) {
                filters[index] = textField.getText();
            }
            index++;
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

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, iscte.getLectures().size());
        table.setItems(this.iscte.getLectures());

        return new BorderPane(table);
    }

    private void setVisibleFilteredItems(String[] items, boolean includeEverything) {
        table.setItems(this.filtersLectures(items, includeEverything));
    }

    private ObservableList<Lecture> filtersLectures(String[] pFilters, boolean includeEveryFilter) {
        String[] filters = pFilters;
        List<Filter> f = new ArrayList<>();
        LectureAttributes[] lectureAttributes = LectureAttributes.values();
        System.out.println(filters);

        for (int i = 0; i < filters.length; i++) {
            if (!filters[i].equals("")) {
                f.add(new Filter(lectureAttributes[i], filters[i]));
            }
        }
        if (f.isEmpty()) {
            return iscte.getLectures();
        }
        return iscte.getLectures(f, includeEveryFilter);
    }
}