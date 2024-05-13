package org.project.ui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.project.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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

    private List<TableColumn> lectureTableColumns = new ArrayList<>();
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
        lectureTable.setItems(ISCTE.getInstance().getLectures(filters));
        System.out.println("Filters working?");
    }

    private void setAddUC(){
        TextField textCurso = new TextField();

        TextField textUC = new TextField();

        TextField textTurno = new TextField();

        TextField textTurma = new TextField();

        TextField textInscritos = new TextField();


        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            SlotTableController.setCurso(textCurso.getText());
            SlotTableController.setUC(textUC.getText());
            SlotTableController.setTurno(textTurno.getText());
            SlotTableController.setTurma(textTurma.getText());
            SlotTableController.setInscritos(parseInt(textInscritos.getText()));
        });
        addButton.setPrefWidth(50);
        addButton.setPrefHeight(50);

        grid2.addColumn(1, textCurso);
        grid2.addColumn(2, textUC);
        grid2.addColumn(3, textTurno);
        grid2.addColumn(4, textTurma);
        grid2.addColumn(5, textInscritos);
        grid2.addColumn(6, addButton);
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
        lectureTable.setItems(ISCTE.getInstance().getLectures());
    }
}
