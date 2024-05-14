package org.project.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.project.*;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class LecturesTableController {

    @FXML
    private Parent root;

    @FXML
    private GridPane grid;
    @FXML
    private GridPane grid2;
    @FXML
    private TableView lectureTable;

    private ISCTE iscte = ISCTE.getInstance();

    private List<TableColumn> lectureTableColumns = new ArrayList<>();
    private List<Button> filterOpButtons = new ArrayList<>();
    private List<TextField> filterTextFields = new ArrayList<>();

    @FXML
    private void initialize() {
        setTable();
        setFilters();
    }

    @FXML
    private void applyFilters() {
        List<Filter> filters = new ArrayList<>();
        for (LectureAttribute a : LectureAttribute.values()) {
            String filterText = filterTextFields.get(a.getValue()).getText();
            String filterOp = filterOpButtons.get(a.getValue()).getText();
            if (filterText != "")
                filters.add(new Filter(a, filterText, filterOp));
        }
        lectureTable.setItems(iscte.getLectures(filters));

        //TODO Delete
        ISCTE.getInstance().getAllSlots();
    }

    private void setAddUC(){
        TextField textCurso = new TextField();

        TextField textUC = new TextField();

        TextField textTurno = new TextField();

        TextField textTurma = new TextField();

        TextField textInscritos = new TextField();

        TextField textWeek = new TextField();

        TextField textSpecRoom = new TextField();

        TextField textRoomCode = new TextField();


        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            SlotsTableController.setCurso(textCurso.getText());
            SlotsTableController.setUC(textUC.getText());
            SlotsTableController.setTurno(textTurno.getText());
            SlotsTableController.setTurma(textTurma.getText());
            SlotsTableController.setInscritos(parseInt(textInscritos.getText()));
            SlotsTableController.setDayOfTheWeek(TimeUtils.determineDayOfWeek(textWeek.getText()));
            SlotsTableController.setRoomCode(textRoomCode.getText());
            SlotsTableController.setSpecificationOfRoom(textSpecRoom.getText());
        });
        addButton.setPrefWidth(50);
        addButton.setPrefHeight(50);

        grid2.addColumn(1, textCurso);
        grid2.addColumn(2, textUC);
        grid2.addColumn(3, textTurno);
        grid2.addColumn(4, textTurma);
        grid2.addColumn(5, textInscritos);
        grid2.addColumn(6, textWeek);
        grid2.addColumn(7, textSpecRoom);
        grid2.addColumn(8, textRoomCode);
        grid2.addColumn(9, addButton);

    }

    private void setFilters() {
        for (LectureAttribute a : LectureAttribute.values()) {
            lectureTableColumns.get(a.getValue()).setVisible(true);
            TextField textField = new TextField();
            textField.setId(a.name());
            Button opButton = new Button(FilterOperation.NOP.getLabel());
            opButton.setId(a.name() + "_op");
            opButton.setPrefWidth(50);
            opButton.setPrefHeight(50);
            opButton.setOnAction(event -> {
                opButton.setText(FilterOperation.getNextFilterLabel(opButton.getText()));
            });
            Button hideButton = new Button("Hide Column");
            hideButton.setId(a.name() + "_hide");
            hideButton.setPrefHeight(50);
            hideButton.setOnAction(event -> {
                switch (hideButton.getText()) {
                    case "Hide Column" -> {
                        lectureTableColumns.get(a.getValue()).setVisible(false);
                        hideButton.setText("Show Column");
                    }
                    default -> {
                        lectureTableColumns.get(a.getValue()).setVisible(true);
                        hideButton.setText("Hide Column");
                    }
                }
            });

            filterTextFields.add(textField);
            filterOpButtons.add(opButton);

            grid.addColumn(0, new Label(a.getLabel()));
            grid.addColumn(1, textField);
            grid.addColumn(2, opButton);
            grid.addColumn(3, hideButton);
        }
    }

    private void setTable() {
        lectureTable.setEditable(true);
        for (LectureAttribute a : LectureAttribute.values()) {
            TableColumn column = a.getTableColumn();
            lectureTableColumns.add(column);
        }
        lectureTable.getColumns().addAll(lectureTableColumns);
        lectureTable.setItems(iscte.getLectures());
        createContextMenuForLecture();
    }

    private void createContextMenuForLecture() {
        ContextMenu rowMenu = new ContextMenu();
        MenuItem makeChange = new MenuItem("Make Change");
        MenuItem deleteItem = new MenuItem("Delete");

        makeChange.setOnAction(event -> {
            Lecture lecture = (Lecture) lectureTable.getSelectionModel().getSelectedItem();
            if (lecture != null) {

                Stage editStage = new Stage();
                editStage.setTitle("Edit");
                VBox elements = new VBox(10);

                elements.getChildren().add(this.createElmForEditTable(lecture, "Start of class"));
                elements.getChildren().add(this.createElmForEditTable(lecture, "End of class"));
                elements.getChildren().add(this.createElmForEditTable(lecture, "Date"));


                Button submitButton = new Button("Submit");
                Label info = new Label("All atributes you enter must be in right format, otherwise they whould not be accepted!");
                info.setStyle("-fx-font-style: italic;");
                info.setAlignment(Pos.TOP_CENTER);

                elements.getChildren().add(submitButton);
                elements.getChildren().add(info);
                elements.setAlignment(Pos.TOP_CENTER);

                Scene scene = new Scene(elements, 550, 270);
                editStage.setScene(scene);
                editStage.show();
                editStage.setResizable(false);

                submitButton.setOnAction(saveEvent -> {
                    ObservableList<Node> nodes = elements.getChildren();
                    ArrayList<TextField> textFields = new ArrayList<>();
                    StringBuilder sb = new StringBuilder();

                    for (Node node : nodes) {
                        if (node instanceof HBox box) {
                            System.out.println(((TextField) box.getChildren().get(1)).getText());
                            textFields.add((TextField) box.getChildren().get(1));
                        } else {
                            break;
                        }
                    }

                    for (int i = 0; i < textFields.size(); i++) {
                        if (i == 0 || i == 1) {
                            if(!this.isTimeValid(textFields.get(i).getText())) {
                                sb.append("Invalid time, ");
                            }
                        }
                        if (i == 2) {
                            if(!this.isDateValid(textFields.get(i).getText())) {
                                sb.append("Invalid date, ");
                            }
                        }
                    }

                    ArrayList<Lecture> l = new ArrayList<>();
                    l.add(lecture);
                    ArrayList<ArrayList<Integer>> conflicts = ISCTE.measureConflicts(l);
                    boolean isThereConflict = !(conflicts.get(0).isEmpty());

                    if (!sb.isEmpty()) {
                        JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
                    } else if (isThereConflict) {
                        JOptionPane.showMessageDialog(null, "There is a conflict with at least with one class, try different parameters!", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        SlotsTableController.setCurso(lecture.getCourse());
                        SlotsTableController.setUC(lecture.getCurricuralUnit());
                        SlotsTableController.setTurno(lecture.getShift());
                        SlotsTableController.setTurma(lecture.getClassN());
                        SlotsTableController.setInscritos(lecture.getNumberOfStudentsAssigned());
                        SlotsTableController.setDayOfTheWeek(lecture.getDayOfTheWeek());
                        SlotsTableController.setRoomCode(lecture.getRoomCode());
                        SlotsTableController.setSpecificationOfRoom(lecture.getSpecificationOfRoom());
                        if (lecture != null) {
                            this.iscte.deleteLecture(lecture);
                        }
                        try {
                            iscte.writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        editStage.close();
                        System.out.println("1");
                        loadSlotsTablePage();
                        System.out.println("2");
                    }
                });

            }
        });

        deleteItem.setOnAction(event -> {
            Lecture lecture = (Lecture) lectureTable.getSelectionModel().getSelectedItem();
            if (lecture != null) {
                this.iscte.deleteLecture(lecture);
            }
        });

        rowMenu.getItems().addAll(makeChange, deleteItem);

        this.lectureTable.setContextMenu(rowMenu);

        this.lectureTable.setOnContextMenuRequested(event -> {
            Lecture selectedLecture =  (Lecture) lectureTable.getSelectionModel().getSelectedItem();
            if (selectedLecture != null) {
                rowMenu.show(this.lectureTable, event.getScreenX(), event.getScreenY());
            }
        });
    }

    private void loadSlotsTablePage() {
        try {
            Parent sceneRoot = FXMLLoader.load(ClassLoader.getSystemResource("SlotsTable.fxml"));
            ((Stage) root.getScene().getWindow()).setTitle("Slots Table");
            ((Stage) root.getScene().getWindow()).setResizable(true);
            ((Stage) root.getScene().getWindow()).setScene(new Scene(sceneRoot));
            ((Stage) sceneRoot.getScene().getWindow()).setMaximized(true);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
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

    private HBox createElmForEditTable(Lecture lecture, String data) {
        HBox hbox = new HBox();
        Label label = new Label(data);
        label.setPrefWidth(100 * 2);
        TextField textField = new TextField();
        switch (data) {
            case "Day of the week" -> textField.setText(lecture.getDayOfTheWeek().toString());
            case "Start of class" -> textField.setText(lecture.getStartOfClass().toString());
            case "End of class" -> textField.setText(lecture.getEndOfClass().toString());
            case "Date" -> textField.setText(lecture.getDateOfClass().toString());
            case "Specification of room" -> textField.setText(lecture.getSpecificationOfRoom());
        }
        label.setPrefWidth(100 * 2);
        hbox.getChildren().addAll(label, textField);
        hbox.setAlignment(Pos.TOP_CENTER);
        return hbox;
    }

    private boolean isDayValid(String day) {
        return day.equals("MONDAY") || day.equals("TUESDAY") || day.equals("WEDNESDAY") ||
                day.equals("THURSDAY") || day.equals("FRIDAY") || day.equals("SATURDAY") ||
                day.equals("SUNDAY");
    }

    private boolean isTimeValid(String time) {
        String timeRegex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

        Pattern pattern = Pattern.compile(timeRegex);
        Matcher matcher = pattern.matcher(time);

        return matcher.matches();
    }

    private boolean isDateValid(String date) {
        String dateRegex = "\\d{4}-\\d{2}-\\d{2}";

        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches();
    }
}
