package org.project;

import javafx.event.EventHandler;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TableColumn;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public enum LectureAttribute implements Attribute{
    course(0, "Course"),
    curricuralUnit(1, "Curricular Unit"),
    shift(2, "Shift"),
    classN(3, "Class N"),
    numberOfStudentsAssigned(4, "Students Assigned"),
    dayOfTheWeek(5, "Day of the Week"),
    startOfClass(6, "Start"),
    endOfClass(7, "End"),
    dateOfClass(8, "Date"),
    specificationOfRoom(9, "Room"),
    roomCode(10, "Room Code"),
    semesterWeek(11, "Semester Week"),
    yearWeek(12, "Year Week");

    private int value;
    private String label;
    private LectureAttribute(int value, String label) {
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
            case 0 -> getTableColumnCourse();
            case 1 -> getTableColumnCurricuralUnit();
            case 2 -> getTableColumnShift();
            case 3 -> getTableColumnClassN();
            case 4 -> getTableColumnNumberOfStudentsAssigned();
            case 5 -> getTableColumnDayOfTheWeek();
            case 6 -> getTableColumnStartOfClass();
            case 7 -> getTableColumnEndOfClass();
            case 8 -> getTableColumnDateOfClass();
            case 9 -> getTableColumnSpecificationOfRoom();
            case 10 -> getTableColumnRoomCode();
            case 11 -> getTableColumnSemesterWeek();
            case 12 -> getTableColumnYearWeek();
            default -> throw new IllegalArgumentException();
        };
    }

    public static TableColumn getTableColumnCourse() {
        TableColumn courseCol = new TableColumn("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("Course"));
        courseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setCourse(t.getNewValue());
                    try {
                        ISCTE.getInstance().writeCsv();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );
        return courseCol;
    }
    public static TableColumn getTableColumnCurricuralUnit() {
        TableColumn cUnitCol = new TableColumn("curricuralUnit");
        cUnitCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("curricuralUnit"));
        cUnitCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cUnitCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setCurricuralUnit(t.getNewValue());
                    try {
                        ISCTE.getInstance().writeCsv();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );
        return cUnitCol;
    }
    public static TableColumn getTableColumnShift() {
        TableColumn shiftCol = new TableColumn("Shift");
        shiftCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("Shift"));
        shiftCol.setCellFactory(TextFieldTableCell.forTableColumn());
        shiftCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setShift(t.getNewValue());
                    try {
                        ISCTE.getInstance().writeCsv();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );
        return shiftCol;
    }
    public static TableColumn getTableColumnClassN() {
        TableColumn classNCol = new TableColumn("ClassN");
        classNCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("ClassN"));
        classNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        classNCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setClassN(t.getNewValue());
                    try {
                        ISCTE.getInstance().writeCsv();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );
        return classNCol;
    }
    public static TableColumn getTableColumnNumberOfStudentsAssigned() {
        TableColumn nStudentsCol = new TableColumn("numberOfStudentsAssigned");
        nStudentsCol.setCellValueFactory(new PropertyValueFactory<Lecture,Integer>("numberOfStudentsAssigned"));
        nStudentsCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nStudentsCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Lecture, Integer>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lecture, Integer> t) {
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNumberOfStudentsAssigned(t.getNewValue());
                    try {
                        ISCTE.getInstance().writeCsv();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );
        return nStudentsCol;
    }
    public static TableColumn getTableColumnDayOfTheWeek() {
        TableColumn dayWeekCol = new TableColumn("dayOfTheWeek");
        dayWeekCol.setCellValueFactory(new PropertyValueFactory<Lecture, DayOfWeek>("dayOfTheWeek"));
        dayWeekCol.setCellFactory(
            TextFieldTableCell.<Lecture, DayOfWeek> forTableColumn(new StringConverter<DayOfWeek>() {
                @Override
                public String toString(DayOfWeek object) {
                    return object != null ? object.toString() : "";
                }
                @Override
                public DayOfWeek fromString(String string) {
                    return Lecture.determineDayOfWeek(string);
                }
            }));
        dayWeekCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lecture, DayOfWeek>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lecture, DayOfWeek> t) {
                        ((Lecture) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDayOfTheWeek(t.getNewValue());
                        try {
                            ISCTE.getInstance().writeCsv();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        return dayWeekCol;
    }
    public static TableColumn getTableColumnStartOfClass() {
        TableColumn startClassCol = new TableColumn("startOfClass");
        startClassCol.setCellValueFactory(new PropertyValueFactory<Lecture, LocalTime>("startOfClass"));
        startClassCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
        startClassCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Lecture, LocalTime>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lecture, LocalTime> t) {
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setStartOfClass(t.getNewValue());
                    try {
                        ISCTE.getInstance().writeCsv();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );
        return startClassCol;
    }
    public static TableColumn getTableColumnEndOfClass() {
        TableColumn endClassCol = new TableColumn("endOfClass");
        endClassCol.setCellValueFactory(new PropertyValueFactory<Lecture, LocalTime>("endOfClass"));
        endClassCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalTimeStringConverter()));
        endClassCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Lecture, LocalTime>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lecture, LocalTime> t) {
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setEndOfClass(t.getNewValue());
                    try {
                        ISCTE.getInstance().writeCsv();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );
        return endClassCol;
    }
    public static TableColumn getTableColumnDateOfClass() {
        TableColumn dateClassCol = new TableColumn("dateOfClass");
        dateClassCol.setCellValueFactory(new PropertyValueFactory<Lecture, LocalDate>("dateOfClass"));
        dateClassCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        dateClassCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Lecture, LocalDate>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lecture, LocalDate> t) {
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setDateOfClass(t.getNewValue());
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setWeeks();
                    try {
                        ISCTE.getInstance().writeCsv();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );
        return dateClassCol;
    }
    public static TableColumn getTableColumnSpecificationOfRoom() {
        TableColumn specRoomCol = new TableColumn("specificationOfRoom");
        specRoomCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("specificationOfRoom"));
        specRoomCol.setCellFactory(TextFieldTableCell.forTableColumn());
        specRoomCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setSpecificationOfRoom(t.getNewValue());
                    try {
                        ISCTE.getInstance().writeCsv();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );
        return specRoomCol;
    }
    public static TableColumn getTableColumnRoomCode() {
        TableColumn roomCodeCol = new TableColumn("roomCode");
        roomCodeCol.setCellValueFactory(new PropertyValueFactory<Lecture,String>("roomCode"));
        roomCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        roomCodeCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Lecture, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Lecture, String> t) {
                    ((Lecture) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setRoomCode(t.getNewValue());
                    try {
                        ISCTE.getInstance().writeCsv();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        );
        return roomCodeCol;
    }
    public static TableColumn getTableColumnSemesterWeek() {
        TableColumn semestreWeekCol = new TableColumn("Semestre Week");
        semestreWeekCol.setCellValueFactory(new PropertyValueFactory<Lecture,Integer>("semesterWeek"));
        semestreWeekCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        return semestreWeekCol;
    }
    public static TableColumn getTableColumnYearWeek() {
        TableColumn yearWeekCol = new TableColumn("Year Week");
        yearWeekCol.setCellValueFactory(new PropertyValueFactory<Lecture,Integer>("yearWeek"));
        yearWeekCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        return yearWeekCol;
    }
}
