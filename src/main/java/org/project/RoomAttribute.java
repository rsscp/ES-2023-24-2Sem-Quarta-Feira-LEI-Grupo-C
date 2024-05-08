package org.project;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public enum RoomAttribute implements Attribute{
    building(0,"Building"),
    designation(1,"Designation"),
    normalCapacity(2,"Normal Capacity"),
    examCapacity(3,"Exam Capacity"),
    numOfCharacteristics(4,"Number of Characteristics"),
    otherProperties(5,"Other Properties");

    private int value;
    private String label;
    private RoomAttribute(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
    public TableColumn getTableColumn() {
        return switch (value) {
            case 0 -> getTableColumnBuilding();
            case 1 -> getTableColumnDesignation();
            case 2 -> getTableColumnNormalCapacity();
            case 3 -> getTableColumnExamCapacity();
            case 4 -> getTableColumnNumOfCaracteristics();
            case 5 -> getTableColumnOtherProperties();
            default -> throw new IllegalArgumentException();
        };
    }

    public static TableColumn getTableColumnBuilding() {
        TableColumn buildingCol = new TableColumn("Building");
        buildingCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("Building"));
        buildingCol.setCellFactory(TextFieldTableCell.forTableColumn());
        return buildingCol;
    }

    public static TableColumn getTableColumnDesignation() {
        TableColumn designationCol = new TableColumn("Designtation");
        designationCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("Designation"));
        designationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        return designationCol;
    }
    public static TableColumn getTableColumnNormalCapacity() {
        TableColumn normalCol = new TableColumn("Normal Capacity");
        normalCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("Normal Capacity"));
        normalCol.setCellFactory(TextFieldTableCell.forTableColumn());
        return normalCol;
    }
    public static TableColumn getTableColumnExamCapacity() {
        TableColumn examCol = new TableColumn("Exam Capacity");
        examCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("Exam Capacity"));
        examCol.setCellFactory(TextFieldTableCell.forTableColumn());
        return examCol;
    }
    public static TableColumn getTableColumnNumOfCaracteristics() {
        TableColumn caraCol = new TableColumn("Number of Characteristics");
        caraCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("Number of Characteristics"));
        caraCol.setCellFactory(TextFieldTableCell.forTableColumn());
        return caraCol;
    }
    public static TableColumn getTableColumnOtherProperties() {
        TableColumn propCol = new TableColumn("Other Properties");
        propCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("Other Properties"));
        propCol.setCellFactory(TextFieldTableCell.forTableColumn());
        TableColumn col = new TableColumn("7");
        return propCol;
    }
}
