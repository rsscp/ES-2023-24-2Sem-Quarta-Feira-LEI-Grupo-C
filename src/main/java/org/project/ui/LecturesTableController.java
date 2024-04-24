package org.project.ui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.project.ISCTE;
import org.project.Lecture;
import org.project.LectureAttributes;

import java.util.ArrayList;
import java.util.List;

public class LecturesTableController {

    @FXML
    private Parent root;

    @FXML
    private TextField courseFilter;
    @FXML
    private TextField curricularUnitFilter;
    @FXML
    private TextField shiftFilter;
    @FXML
    private TextField classNFilter;
    @FXML
    private TextField numberOfStudentsAssignedFilter;
    @FXML
    private TextField dayOfTheWeekFilter;
    @FXML
    private TextField startOfClassFilter;
    @FXML
    private TextField endOfClassFilter;
    @FXML
    private TextField dateOfClassFilter;
    @FXML
    private TextField specificationOfRoomFilter;
    @FXML
    private TextField roomCodeFilter;
    @FXML
    private TableView lectureTable;

    @FXML
    public void initialize() {
        //lectureTable.prefHeightProperty().bind(((Stage) root.getScene().getWindow()).heightProperty());
        List<TableColumn> tableColumns = new ArrayList<>();
        for (LectureAttributes a : LectureAttributes.values()) {
            TableColumn currentCol = new TableColumn(a.name());
            //currentCol.prefWidthProperty().bind(((Stage) root.getScene().getWindow()).widthProperty().divide(11));  //TODO not hardcoded
            currentCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>(a.name()));
            tableColumns.add(currentCol);
        }
        lectureTable.getColumns().addAll(tableColumns);
        lectureTable.setItems(ISCTE.getInstance().getLectures());
    }
}
