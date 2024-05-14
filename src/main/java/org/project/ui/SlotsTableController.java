package org.project.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.project.*;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class SlotsTableController {

    @FXML
    private Parent root;

    public static void setCurso(String curso) {
        Curso = curso;
    }

    public static void setUC(String UC) {
        SlotsTableController.UC = UC;
    }

    public static void setTurno(String turno) {
        Turno = turno;
    }

    public static void setTurma(String turma) {
        Turma = turma;
    }

    public static void setInscritos(int inscritos) {
        Inscritos = inscritos;
    }

    @FXML
    private GridPane grid;
    @FXML
    private TableView slotTable;

    private ISCTE iscte = ISCTE.getInstance();

    private List<TableColumn> slotTableColumns = new ArrayList<>();
    static String Curso = null;
    static String UC = null;
    static String Turno = null;
    static String Turma = null;
    static int Inscritos = 0;

    public static void setDayOfTheWeek(DayOfWeek dayOfTheWeek) {
        DayOfTheWeek = dayOfTheWeek;
    }

    public static void setSpecificationOfRoom(String specificationOfRoom) {
        SpecificationOfRoom = specificationOfRoom;
    }

    public static void setRoomCode(String roomCode) {
        RoomCode = roomCode;
    }

    static DayOfWeek DayOfTheWeek = null;
    static String SpecificationOfRoom = null;
    static String RoomCode = null;

    @FXML
    private void initialize() {
        setTable();
        saveLecture();
    }

    private void setTable() {
        slotTable.setEditable(true);

        TableColumn timeCol = new TableColumn("TimeSlot");
        timeCol.setCellValueFactory(new PropertyValueFactory<DaySlot, TimeSlot>("TimeSlot"));
        slotTableColumns.add(timeCol);

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<DaySlot, LocalDate>("Date"));
        slotTableColumns.add(dateCol);

        slotTable.getColumns().addAll(slotTableColumns);
        ObservableList<DaySlot> slots = iscte.getFirstSemesterSlots();
        slots.addAll(iscte.getSecondSemesterSlots());
        slotTable.setItems(slots);
    }

    private void saveLecture() {
        ContextMenu rowMenu = new ContextMenu();
        MenuItem Save = new MenuItem("Save");

        Save.setOnAction(event -> {
            DaySlot slot = (DaySlot) slotTable.getSelectionModel().getSelectedItem();
            Lecture lecture = new Lecture(Curso, UC, Turno, Turma, Inscritos, DayOfTheWeek, slot.getTimeSlot().getStart(), slot.getTimeSlot().getEnd(), slot.getDate(), SpecificationOfRoom, RoomCode);
            ISCTE.getInstance().getLectures().add(lecture);
            try {
                ISCTE.getInstance().writeCsv();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("saved");
        });

        rowMenu.getItems().add(Save);

        this.slotTable.setContextMenu(rowMenu);

        this.slotTable.setOnContextMenuRequested(event -> {
            DaySlot selectedSlot =  (DaySlot) slotTable.getSelectionModel().getSelectedItem();
            if (selectedSlot != null) {
                rowMenu.show(this.slotTable, event.getScreenX(), event.getScreenY());
            }
        });
    }
}