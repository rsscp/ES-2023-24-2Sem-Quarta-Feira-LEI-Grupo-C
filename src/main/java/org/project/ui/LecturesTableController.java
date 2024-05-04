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

    private List<TableColumn> lectureTableColumns;

    @FXML
    private void initialize() {
        setTable();
        setFilters();
    }

    @FXML
    private void applyFilters() {

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
            grid.addColumn(0, new Label(a.getLabel()));
            grid.addColumn(1, textField);
            grid.addColumn(2, opButton);
            grid.addColumn(3, hideButton);
        }
    }

    private void setTable() {
        lectureTableColumns = new ArrayList<>();
        for (LectureAttribute a : LectureAttribute.values()) {
            TableColumn column = new TableColumn(a.getLabel());
            column.setCellValueFactory(new PropertyValueFactory<Lecture,String>(a.name()));
            lectureTableColumns.add(column);
        }
        lectureTable.getColumns().addAll(lectureTableColumns);
        lectureTable.setItems(ISCTE.getInstance().getLectures());
    }
}
