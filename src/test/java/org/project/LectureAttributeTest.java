package org.project;

import javafx.application.Platform;
import javafx.scene.control.TableColumn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;




import static org.junit.jupiter.api.Assertions.*;


public class LectureAttributeTest {


    @Test
    public void testGetTableColumnCourse() {
        LectureAttribute attribute = LectureAttribute.course;
        assertEquals("Course", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnCurricularUnit() {
        LectureAttribute attribute = LectureAttribute.curricularUnit;
        assertEquals("Curricular Unit", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnshift() {
        LectureAttribute attribute = LectureAttribute.shift;
        assertEquals("Shift", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnClassN() {
        LectureAttribute attribute = LectureAttribute.classN;
        assertEquals("Class N", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnNumberOfStudentsAssigned() {
        LectureAttribute attribute = LectureAttribute.numberOfStudentsAssigned;
        assertEquals("Students Assigned", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnDayOfTheWeek() {
        LectureAttribute attribute = LectureAttribute.dayOfTheWeek;
        assertEquals("Day of the Week", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnStartOfClass() {
        LectureAttribute attribute = LectureAttribute.startOfClass;
        assertEquals("Start", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnEndOfClass() {
        LectureAttribute attribute = LectureAttribute.endOfClass;
        assertEquals("End", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnDateOfClass() {
        LectureAttribute attribute = LectureAttribute.dateOfClass;
        assertEquals("Date", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnSpecificationOfRoom() {
        LectureAttribute attribute = LectureAttribute.specificationOfRoom;
        assertEquals("Room", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnRoomCode() {
        LectureAttribute attribute = LectureAttribute.roomCode;
        assertEquals("Room Code", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnSemesterWeek() {
        LectureAttribute attribute = LectureAttribute.semesterWeek;
        assertEquals("Semester Week", attribute.getLabel());
    }

    @Test
    public void testGetTableColumnYearWeek() {
        LectureAttribute attribute = LectureAttribute.yearWeek;
        assertEquals("Year Week", attribute.getLabel());
    }

    @Test
    public void testGetValue() {
        assertEquals(1, LectureAttribute.curricularUnit.getValue());
    }

    @Test
    public void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            LectureAttribute attribute = LectureAttribute.valueOf("invalid");
        });
    }

    @Test
    public void testGetTableColumn() {
        for (LectureAttribute attribute : LectureAttribute.values()) {
            TableColumn column = attribute.getTableColumn();
            assertNotNull(column);
            // Optionally, you can assert specific properties of the TableColumn, such as its text
            switch (attribute) {
                case course:
                    assertEquals("Course", column.getText());
                    break;
                case curricularUnit:
                    assertEquals("curricularUnit", column.getText());
                    break;
                case shift:
                    assertEquals("Shift", column.getText());
                    break;
                case classN:
                    assertEquals("ClassN", column.getText());
                    break;
                case numberOfStudentsAssigned:
                    assertEquals("numberOfStudentsAssigned", column.getText());
                    break;
                case dayOfTheWeek:
                    assertEquals("dayOfTheWeek", column.getText());
                    break;
                case startOfClass:
                    assertEquals("startOfClass", column.getText());
                    break;
                case endOfClass:
                    assertEquals("endOfClass", column.getText());
                    break;
                case dateOfClass:
                    assertEquals("dateOfClass", column.getText());
                    break;
                case specificationOfRoom:
                    assertEquals("specificationOfRoom", column.getText());
                    break;
                case roomCode:
                    assertEquals("roomCode", column.getText());
                    break;
                case semesterWeek:
                    assertEquals("Semestre Week", column.getText());
                    break;
                case yearWeek:
                    assertEquals("Year Week", column.getText());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown attribute: " + attribute);
            }
        }
    }

}