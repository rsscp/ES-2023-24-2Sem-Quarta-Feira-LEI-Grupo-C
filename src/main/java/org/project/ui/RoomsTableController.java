package org.project.ui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import org.project.*;

import java.util.ArrayList;
import java.util.List;

public class RoomsTableController {

    @FXML
    private Parent root;

    @FXML
    private GridPane grid;
    @FXML
    private TableView roomTable;

    private List<TableColumn> roomTableColumns = new ArrayList<>();
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
        for (RoomAttribute a : RoomAttribute.values()) {
            String filterText = filterTextFields.get(a.getValue()).getText();
            String filterOp = filterOpButtons.get(a.getValue()).getText();
            if (!filterText.equals("") && !filterOp.equals("-"))
                filters.add(new Filter(a, filterText, filterOp));
        }
        roomTable.setItems(ISCTE.getInstance().getRooms(filters));
    }

    private void setFilters() {
        for (RoomAttribute a : RoomAttribute.values()) {
            roomTableColumns.get(a.getValue()).setVisible(true);
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
                        roomTableColumns.get(a.getValue()).setVisible(false);
                        hideButton.setText("Show Column");
                    }
                    default -> {
                        roomTableColumns.get(a.getValue()).setVisible(true);
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
        roomTable.setEditable(false);
        for(RoomAttribute a :RoomAttribute.values()){
            TableColumn tableCol = new TableColumn(a.name());
            tableCol.setCellValueFactory(new PropertyValueFactory<Room,String>(a.name()));
            roomTableColumns.add(tableCol);
        }
        roomTable.getColumns().addAll(roomTableColumns);
        roomTable.setItems(ISCTE.getInstance().getRooms());
    }
}
