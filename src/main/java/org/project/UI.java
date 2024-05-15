package org.project;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

import javax.swing.*;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UI extends Application {
    private final int COL_SIZE = 100;
    ISCTE iscte = ISCTE.getInstance();
    int rowsPerPage = 10;
    Stage stage;
    ScrollPane root;
    final HBox hBox = new HBox();
    public TableView table = new TableView();
    public BorderPane borderPane;
    List<TableColumn> tableColumns = new ArrayList<>();
   // final GridPane gridPane = new GridPane();


    //List<Label> filterLabels = new ArrayList<>();
   // List<TextField> filterTextFields = new ArrayList<>();

    //final Label tableLabel = new Label("Lectures");
   // private TextField userInputField;


    public Button btnSearchAND;
    public Button btnSearchOR;
    public Button btnClear;


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
        //Pagination pagination = new Pagination((iscte.getLectures().size() / rowsPerPage + 1), 0);
        Pagination pagination = new Pagination();
        pagination.setPageCount(1);
        pagination.setPageFactory(this::createPage);
       // pagination.setPageFactory(null);
        this.borderPane.setLeft(pagination);
        //BorderPane borderPane = new BorderPane(pagination, this.gridPane, null, null, null);

        Scene scene = new Scene(this.borderPane, 1024, 768);
        stage.setScene(scene);
        stage.setTitle("Table pager");
        stage.setMaximized(true);
        stage.show();
    }

    public void getData(String url, String path) throws IOException, IllegalArgumentException {
        //iscte.createCSV("https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv");  //TODO Delete
        if (path == "") {
            iscte.getUrlFile(url);
            iscte.readLeactures("HorarioDeExemplo.csv");
        } else {
            iscte.readLeactures(path);
        }
    }
     public void getData() {
         try {
             getData("https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv", "");
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    public void createFilters() {
        GridPane filters = new GridPane();
        filters.getColumnConstraints().addAll(new ColumnConstraints(20), new ColumnConstraints(80));
        int rowIndex = 0;
    }

    /**
     * Making filtering bookmark
     */
    public void makeFilters() {
        HBox hbox = new HBox();
        VBox vBox = new VBox();

        for (LectureAttribute a : LectureAttribute.values()) {
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

    /**
     * Creating functionalities buttons
     */
    public void creatButtons() {
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

    /**
     * Function will clear all of the textFields which contains some text
     */
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

    /**
     * Function will create specific text field with name to the table
     * @param lecture
     * @param type of the text
     * @return
     */
    public HBox createElmForEditTable(Lecture lecture, String data) {
        HBox hbox = new HBox();
        Label label = new Label(data);
        label.setPrefWidth(COL_SIZE * 2);
        TextField textField = new TextField();
        switch (data) {
            case "Day of the week" -> textField.setText(lecture.getDayOfTheWeek().toString());
            case "Start of class" -> textField.setText(lecture.getStartOfClass().toString());
            case "End of class" -> textField.setText(lecture.getEndOfClass().toString());
            case "Date" -> textField.setText(lecture.getDateOfClass().toString());
            case "Specification of room" -> textField.setText(lecture.getSpecificationOfRoom());
        }
        label.setPrefWidth(COL_SIZE * 2);
        hbox.getChildren().addAll(label, textField);
        hbox.setAlignment(Pos.TOP_CENTER);
        return hbox;
    }

    /**
     * Fuction will get all of the filters entered
     * @return filters.
     */
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

    public DayOfWeek determineDayOfWeek(String dayString) throws IllegalArgumentException {
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

    /**
     * Checks if the day is valid
     * @param day
     * @return result
     */
    public boolean isDayValid(String day) {
        return day.equals("MONDAY") || day.equals("TUESDAY") || day.equals("WEDNESDAY") ||
                day.equals("THURSDAY") || day.equals("FRIDAY") || day.equals("SATURDAY") ||
                day.equals("SUNDAY");
    }

    /**
     * Checks if the time is valid
     * @param time
     * @return result
     */
    public boolean isTimeValid(String time) {
        String timeRegex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

        Pattern pattern = Pattern.compile(timeRegex);
        Matcher matcher = pattern.matcher(time);

        return matcher.matches();
    }

    /**
     * Checks if the date is valid
     * @param date
     * @return result
     */
    public boolean isDateValid(String date) {
        String dateRegex = "\\d{4}-\\d{2}-\\d{2}";

        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }

    public Node createPage(int pageIndex) {
       // int fromIndex = pageIndex * rowsPerPage;
        //int toIndex = Math.min(fromIndex + rowsPerPage, iscte.getLectures().size());
        this.table.setItems(this.iscte.getLectures());

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

    /**
     * Function apply filters to the table
     * @param items promps
     * @param includeEverything or AND
     */
    private void setVisibleFilteredItems(String[] items, boolean includeEverything) {
        this.table.setItems(this.filtersLectures(items, includeEverything));
    }

    /**
     * Function will find appropriate lectures according to filters
     * @param pFilters
     * @param includeEveryFilter or OR
     * @return
     */
    private ObservableList<Lecture> filtersLectures(String[] pFilters, boolean includeEveryFilter) {
        String[] filters = pFilters;
        List<Filter> f = new ArrayList<>();
        LectureAttribute[] lectureAttributes = LectureAttribute.values();
        System.out.println(filters);

        for (int i = 0; i < filters.length; i++) {
            if (!filters[i].equals("")) {
                f.add(new Filter(lectureAttributes[i], filters[i], ""));
            }
        }
        if (f.isEmpty()) {
            return iscte.getLectures();
        }
        return iscte.getLectures(f, includeEveryFilter);
    }
}