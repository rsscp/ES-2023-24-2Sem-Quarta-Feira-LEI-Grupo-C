package org.project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

import javax.swing.*;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.ApplicationTest;


import java.time.DayOfWeek;

public class UITest{

    @Test
    public void testIsDayValid() {
        UI ui = new UI();
        assertTrue(ui.isDayValid("MONDAY"));
        assertTrue(ui.isDayValid("TUESDAY"));
        assertTrue(ui.isDayValid("WEDNESDAY"));
        assertTrue(ui.isDayValid("THURSDAY"));
        assertTrue(ui.isDayValid("FRIDAY"));
        assertTrue(ui.isDayValid("SATURDAY"));
        assertTrue(ui.isDayValid("SUNDAY"));

        assertFalse(ui.isDayValid("INVALID_DAY"));
    }

    @Test
    public void testIsTimeValid() {
        UI ui = new UI();
        assertTrue(ui.isTimeValid("12:00"));
        assertTrue(ui.isTimeValid("23:59"));
        assertTrue(ui.isTimeValid("00:00"));

        assertFalse(ui.isTimeValid("24:00"));
        assertFalse(ui.isTimeValid("12:60"));
        assertFalse(ui.isTimeValid("INVALID_TIME"));
    }

    @Test
    public void testIsDateValid() {
        UI ui = new UI();
        assertTrue(ui.isDateValid("2024-05-14"));

        assertFalse(ui.isDateValid("2024/05/14"));
        assertFalse(ui.isDateValid("INVALID_DATE"));
    }

    @Test
    public void testDetermineDayOfWeek() {
        UI ui = new UI();
        assertEquals(DayOfWeek.MONDAY, ui.determineDayOfWeek("MONDAY"));
        assertEquals(DayOfWeek.TUESDAY, ui.determineDayOfWeek("TUESDAY"));
        assertEquals(DayOfWeek.WEDNESDAY, ui.determineDayOfWeek("WEDNESDAY"));
        assertEquals(DayOfWeek.THURSDAY, ui.determineDayOfWeek("THURSDAY"));
        assertEquals(DayOfWeek.FRIDAY, ui.determineDayOfWeek("FRIDAY"));
        assertEquals(DayOfWeek.SATURDAY, ui.determineDayOfWeek("SATURDAY"));
        assertEquals(DayOfWeek.SUNDAY, ui.determineDayOfWeek("SUNDAY"));

        assertNull(ui.determineDayOfWeek("INVALID_DAY"));
    }


        @Test
    public void testStart() {
            // Arrange
            UI ui = new UI();

            ui.creatButtons();
            ui.makeFilters();

            // Assert
            assertNotNull(ui.btnClear);
            assertNotNull(ui.btnSearchAND);
            assertNotNull(ui.btnSearchOR);
            assertNotNull(ui.hBox);
            assertNotNull(ui.borderPane);
            assertNotNull(ui.borderPane.getTop());

            String url = "https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv";
            String path = "";
            String notpath = "HorarioDeExemplo.csv";

            try {
                ui.getData(url, path);
                assertNotNull(ui.iscte.getLectures());
            } catch (Exception e) {
                fail("Exception occurred: " + e.getMessage());
            }
            try {
                ui.getData(url, notpath);
            } catch (Exception e) {
                fail("Exception occurred: " + e.getMessage());
            }
            ui.createTable();

            // Assert
            assertNotNull(ui.iscte.getLectures());
            assertFalse(ui.iscte.getLectures().isEmpty());
            assertNotNull(ui.table);
            assertEquals(11, ui.table.getColumns().size()); // Assuming 11 columns are created
            assertNotNull(ui.table.getItems());
            assertFalse(ui.table.getItems().isEmpty());

            // Assert
            assertNotNull(ui.btnClear);
            assertNotNull(ui.btnSearchAND);
            assertNotNull(ui.btnSearchOR);
            assertNotNull(ui.borderPane);
            assertNotNull(ui.table);

            ContextMenu contextMenu = ui.table.getContextMenu();
            assertNotNull(contextMenu);

            // Assert context menu items
            ObservableList<MenuItem> items = contextMenu.getItems();
            assertEquals(2, items.size()); // Assuming there are 2 items

            MenuItem makeChange = items.get(0);
            MenuItem deleteItem = items.get(1);
            assertNotNull(makeChange);
            assertNotNull(deleteItem);

    }

    @Test
    public void testCreateElmForEditTable() {
        // Arrange
        String[] lectureData = {"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Sex", "13:00:00", "14:30:00", "02/12/2022", "Sala Aulas Mestrado", "AA2.25"};
        Lecture lecture = new Lecture(lectureData);
        UI ui = new UI(); // Assuming UI class is instantiated properly
        String[] testData = {"Day of the week", "Start of class", "End of class", "Date", "Specification of room"};

        // Act
        for (String data : testData) {
            HBox result = ui.createElmForEditTable(lecture, data);

            // Assert
            assertNotNull(result);
            assertEquals(2, result.getChildren().size());

            Label label = (Label) result.getChildren().get(0);
            TextField textField = (TextField) result.getChildren().get(1);

            assertNotNull(label);
            assertNotNull(textField);

            switch (data) {
                case "Day of the week":
                    assertEquals("Day of the week", label.getText());
                    assertEquals("FRIDAY", textField.getText());
                    break;
                case "Start of class":
                    assertEquals("Start of class", label.getText());
                    assertEquals("13:00", textField.getText());
                    break;
                case "End of class":
                    assertEquals("End of class", label.getText());
                    assertEquals("14:30", textField.getText());
                    break;
                case "Date":
                    assertEquals("Date", label.getText());
                    assertEquals("2022-12-02", textField.getText());
                    break;
                case "Specification of room":
                    assertEquals("Specification of room", label.getText());
                    assertEquals("Sala Aulas Mestrado", textField.getText());
                    break;
                default:
                    fail("Invalid data type");
            }
        }
    }
}