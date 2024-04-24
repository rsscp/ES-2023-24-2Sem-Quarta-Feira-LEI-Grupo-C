package org.project;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
class LectureTest {

    private Lecture lecture;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        String[] test = {"EI","Engenharia de Software","01789TP01","EI-C5", "60" ,"Seg","13:00:00","14:30:00"};
        lecture=new Lecture(test);
    }

    @org.junit.jupiter.api.Test
    void getCourse() {
        assertEquals("EI",lecture.getCourse());
    }

    @org.junit.jupiter.api.Test
    void getCurricuralUnit() {
        assertEquals("Engenharia de Software",lecture.getCurricuralUnit());
    }

    @org.junit.jupiter.api.Test
    void getShift() {
        assertEquals("01789TP01",lecture.getShift());
    }

    @org.junit.jupiter.api.Test
    void getClassN() {
        assertEquals("EI-C5",lecture.getClassN());
    }

    @org.junit.jupiter.api.Test
    void getNumberOfStudentsAssigned() {
        assertEquals(60,lecture.getNumberOfStudentsAssigned());
    }

    @org.junit.jupiter.api.Test
    void getDayOfTheWeek() {
        assertEquals(DayOfWeek.MONDAY,lecture.getDayOfTheWeek());
    }

    @org.junit.jupiter.api.Test
    void getStartOfClass() {
        String timeString = "13:00";
        String[] timeParts = timeString.split(":");;
        LocalTime.of(Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1]));
        assertEquals(LocalTime.of(Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1])),lecture.getStartOfClass());
    }

    @org.junit.jupiter.api.Test
    void getEndOfClass() {
        String timeString = "14:30";
        String[] timeParts = timeString.split(":");;
        LocalTime.of(Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1]));

        assertEquals(LocalTime.of(Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1])),lecture.getEndOfClass());
    }

    @org.junit.jupiter.api.Test
    void getDateOfClass() {
    }

    @org.junit.jupiter.api.Test
    void getSpecificationOfRoom() {
    }

    @org.junit.jupiter.api.Test
    void getRoomCode() {
    }
}