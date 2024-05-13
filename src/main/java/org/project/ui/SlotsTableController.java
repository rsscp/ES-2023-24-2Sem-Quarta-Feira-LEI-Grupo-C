package org.project.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.DayOfWeek;
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
    private List<Button> filterOpButtons = new ArrayList<>();
    private List<TextField> filterTextFields = new ArrayList<>();
    static String Curso = null;
    static String UC = null;
    static String Turno = null;
    static String Turma = null;
    static int Inscritos = 0;

    @FXML
    private void initialize() {
        setTable();
    }

    private void setTable() {
        slotTable.setEditable(true);
        for (LectureAttribute a : LectureAttribute.values()) {
            TableColumn column = a.getTableColumn();
            slotTableColumns.add(column);
        }
        slotTable.getColumns().addAll(slotTableColumns);
        slotTable.setItems(iscte.getLectures());
    }

}