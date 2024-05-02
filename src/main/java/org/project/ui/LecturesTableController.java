package org.project.ui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.project.FilterOperation;
import org.project.ISCTE;
import org.project.Lecture;
import org.project.LectureAttribute;

import java.util.ArrayList;
import java.util.List;

public class LecturesTableController {

    @FXML
    private Parent root;

    @FXML
    private GridPane grid;
    @FXML
    private TableView lectureTable;

    @FXML
    private void initialize() {
        setFilters();
        setTable();
    }

    @FXML
    private void applyFilters() {

    }

    private void setFilters() {
        for (LectureAttribute a : LectureAttribute.values()) {
            TextField textField = new TextField();
            textField.setId(a.name());
            Button button = new Button(FilterOperation.NOP.getLabel());
            button.setId(a.name() + "_op");
            button.setPrefWidth(50);
            button.setPrefHeight(50);
            button.setOnAction(event -> {
                button.setText(FilterOperation.getNextFilterLabel(button.getText()));
            });
            grid.addColumn(0, new Label(a.getLabel()));
            grid.addColumn(1, textField);
            grid.addColumn(2, button);
        }
    }

    private void setTable() {
        List<TableColumn> tableColumns = new ArrayList<>();
        for (LectureAttribute a : LectureAttribute.values()) {
            TableColumn currentCol = new TableColumn(a.getLabel());
            currentCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>(a.name()));
            tableColumns.add(currentCol);
        }
        lectureTable.getColumns().addAll(tableColumns);
        lectureTable.setItems(ISCTE.getInstance().getLectures());
    }
}
