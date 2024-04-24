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
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

import java.io.*;
import java.net.MalformedURLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
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

    private void getData(String url, String path) throws IOException, IllegalArgumentException {
        //iscte.createCSV("https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv");  //TODO Delete
        if (path == "") {
            iscte.getUrlFile(url);
            iscte.readLeactures("HorarioDeExemplo.csv");
        } else {
            iscte.readLeactures(path);
        }
    }
     private void getData() {
         try {
             getData("https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv", "");
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    private void createFilters() {
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

    private void createTable() {
        table.setEditable(true);

        TableColumn courseCol = new TableColumn("Course");
        courseCol.setMinWidth(COL_SIZE);
        courseCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("Course"));
        courseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCourse(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(courseCol);


        TableColumn CUnitCol = new TableColumn("curricuralUnit");
        CUnitCol.setMinWidth(COL_SIZE);
        CUnitCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("curricuralUnit"));
        CUnitCol.setCellFactory(TextFieldTableCell.forTableColumn());
        CUnitCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCurricuralUnit(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(CUnitCol);

        TableColumn shiftCol = new TableColumn("Shift");
        shiftCol.setMinWidth(COL_SIZE);
        shiftCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("Shift"));
        shiftCol.setCellFactory(TextFieldTableCell.forTableColumn());
        shiftCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setShift(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(shiftCol);

        TableColumn ClassNCol = new TableColumn("ClassN");
        ClassNCol.setMinWidth(COL_SIZE);
        ClassNCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("ClassN"));
        ClassNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ClassNCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setClassN(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(ClassNCol);

        TableColumn NStudentsCol = new TableColumn("numberOfStudentsAssigned");
        NStudentsCol.setMinWidth(COL_SIZE);
        NStudentsCol.setCellValueFactory(new PropertyValueFactory<Lecture,Integer>("numberOfStudentsAssigned"));
        NStudentsCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        NStudentsCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, Integer> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNumberOfStudentsAssigned(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(NStudentsCol);

        TableColumn dayWeekCol = new TableColumn("dayOfTheWeek");
        dayWeekCol.setMinWidth(COL_SIZE);
        dayWeekCol.setCellValueFactory(new PropertyValueFactory<Lecture,DayOfWeek>("dayOfTheWeek"));

        dayWeekCol.setCellFactory(
            TextFieldTableCell.<Lecture, DayOfWeek> forTableColumn(new StringConverter<DayOfWeek>() {
                @Override
                public String toString(DayOfWeek object) {
                    return object != null ? object.toString() : "";
                }

                @Override
                public DayOfWeek fromString(String string) {
                    return determineDayOfWeek(string);
                }
            }
         ));

        dayWeekCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, DayOfWeek>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, DayOfWeek> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDayOfTheWeek(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(dayWeekCol);

        TableColumn StartClassCol = new TableColumn("startOfClass");
        StartClassCol.setMinWidth(COL_SIZE);
        StartClassCol.setCellValueFactory(new PropertyValueFactory<Lecture, LocalTime>("startOfClass"));
        StartClassCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));

        StartClassCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, LocalTime>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, LocalTime> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setStartOfClass(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(StartClassCol);


        TableColumn EndClassCol = new TableColumn("endOfClass");
        EndClassCol.setMinWidth(COL_SIZE);
        EndClassCol.setCellValueFactory(new PropertyValueFactory<Lecture, LocalTime>("endOfClass"));
        EndClassCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));

        EndClassCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, LocalTime>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, LocalTime> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setEndOfClass(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(EndClassCol);

        TableColumn DateClassCol = new TableColumn("dateOfClass");
        DateClassCol.setMinWidth(COL_SIZE);
        DateClassCol.setCellValueFactory(new PropertyValueFactory<Lecture, LocalDate>("dateOfClass"));
        DateClassCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

        DateClassCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, LocalDate>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, LocalDate> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDateOfClass(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(DateClassCol);

        TableColumn SpecRoomCol = new TableColumn("specificationOfRoom");
        SpecRoomCol.setMinWidth(COL_SIZE);
        SpecRoomCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("specificationOfRoom"));
        SpecRoomCol.setCellFactory(TextFieldTableCell.forTableColumn());
        SpecRoomCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setSpecificationOfRoom(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(SpecRoomCol);

        TableColumn RoomCodeCol = new TableColumn("roomCode");
        RoomCodeCol.setMinWidth(COL_SIZE);
        RoomCodeCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("roomCode"));
        RoomCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        RoomCodeCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setRoomCode(t.getNewValue());
                        try {
                            writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        tableColumns.add(RoomCodeCol);
        table.getColumns().addAll(tableColumns);
        table.setItems(iscte.getLectures());
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

    private DayOfWeek determineDayOfWeek(String dayString) throws IllegalArgumentException {
        switch(dayString) {
            case "MONDAY":
                return DayOfWeek.MONDAY;
            case "TUESDAY":
                return DayOfWeek.TUESDAY;
            case "THURSDAY":
                return DayOfWeek.THURSDAY;
            case "WEDNESDAY":
                return DayOfWeek.WEDNESDAY;
            case "FRIDAY":
                return DayOfWeek.FRIDAY;
            case "SATURDAY":
                return DayOfWeek.SATURDAY;
            case "SUNDAY":
                return DayOfWeek.SUNDAY;
            default:
                return null;
        }
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, iscte.getLectures().size());
        table.setItems(this.iscte.getLectures());

        return new BorderPane(table);
    }

    public void writeCsv() throws Exception {
        Writer writer = null;
        try {
            File file = new File(System.getProperty("user.dir") + File.separator + "NovoHorario.csv");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Características da sala pedida para a aula;Sala atribuída à aula\n");
            for (Lecture lecture : iscte.getLectures()) {

                String text =lecture.toString();

                writer.write(text + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }
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