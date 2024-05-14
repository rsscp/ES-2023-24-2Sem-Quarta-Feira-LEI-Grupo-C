package org.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LectureTest {

    @Test
    void testSettersAndGetters() {
        String[] lectureData = {"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Sex", "13:00:00", "14:30:00", "02/12/2022", "Sala Aulas Mestrado", "AA2.25"};
        String[] lectureDataset = {"MA", "Teoria dos Jogos e dos Comandos", "01789TP00", "MIA1", "29", "Seg", "14:00:00", "15:30:00", "03/12/2022", "Sala Aulas Licenciatura", "AA1.25"};
        String[] lectureData8 = {"MIA","Arquitectura III","L2245PL02","ARQ-B4, ARQ-B3","16","Ter","16:00:00","17:30:00"};
        String[] lectureData10 = {"MIA","Arquitectura III","L2245PL02","ARQ-B4, ARQ-B3","16","Qui","13:00:00","14:30:00","30/09/2022","Arq 2"};
        assertThrows(IllegalArgumentException.class, () -> new Lecture(new String[]{"MIA","Arquitectura III","L2245PL02","ARQ-B4, ARQ-B3","16","Ter","16:00:00","17:30:00","06/12/2022"}));
        Lecture lecture = new Lecture(lectureData);
        Lecture lecture8 = new Lecture(lectureData8);
        Lecture lecture10 = new Lecture(lectureData10);
        Lecture lectureset = new Lecture(lectureDataset);
        lecture.setSemesterWeek(1);
        lecture.setYearWeek(1);
        assertEquals("ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;FRIDAY;13:00;14:30;2022-12-02;Sala Aulas Mestrado;AA2.25;1;1",lecture.toString());
        lecture8.setSemesterWeek(1);
        lecture8.setYearWeek(2);
        lecture10.setSemesterWeek(LocalDate.of(2022,9,22),LocalDate.of(2023,1,06));
        lecture10.setYearWeek(LocalDate.of(2022,9,22));
        lectureset.setCourse("ME");
        lectureset.setSemesterWeek(1);
        lectureset.setYearWeek(1);
        lectureset.setCurricuralUnit("Teoria dos Jogos e dos Contratos");
        lectureset.setShift("01789TP01");
        lectureset.setClassN("MEA1");
        lectureset.setNumberOfStudentsAssigned(30);
        lectureset.setDayOfTheWeek("Sex");
        lectureset.setStartOfClass("14:00:00");
        lectureset.setEndOfClass("15:30:00");
        lectureset.setDateOfClass("02/12/2022","/");
        lectureset.setSpecificationOfRoom("Sala Aulas Mestrado");
        lectureset.setRoomCode("AA2.25");

        assertEquals("ME", lecture.getCourse());
        assertEquals("Teoria dos Jogos e dos Contratos", lecture.getCurricuralUnit());
        assertEquals("01789TP01", lecture.getShift());
        assertEquals("MEA1", lecture.getClassN());
        assertEquals(30, lecture.getNumberOfStudentsAssigned());
        assertEquals(DayOfWeek.FRIDAY, lecture.getDayOfTheWeek());
        assertEquals(LocalTime.of(13, 0), lecture.getStartOfClass());
        assertEquals(LocalTime.of(14, 30), lecture.getEndOfClass());
        assertEquals(LocalDate.of(2022, 12, 02), lecture.getDateOfClass());
        assertEquals("Sala Aulas Mestrado", lecture.getSpecificationOfRoom());
        assertEquals("AA2.25", lecture.getRoomCode());
        assertEquals(1, lecture.getSemesterWeek());
        assertEquals(1, lecture.getYearWeek());

        assertEquals("MIA", lecture8.getCourse());
        assertEquals("Arquitectura III", lecture8.getCurricuralUnit());
        assertEquals("L2245PL02", lecture8.getShift());
        assertEquals("ARQ-B4, ARQ-B3", lecture8.getClassN());
        assertEquals(16, lecture8.getNumberOfStudentsAssigned());
        assertEquals(DayOfWeek.TUESDAY, lecture8.getDayOfTheWeek());
        assertEquals(LocalTime.of(16, 0), lecture8.getStartOfClass());
        assertEquals(LocalTime.of(17, 30), lecture8.getEndOfClass());
        assertNull(lecture8.getDateOfClass());
        assertNull(lecture8.getSpecificationOfRoom());
        assertNull(lecture8.getRoomCode());
        assertEquals(1, lecture8.getSemesterWeek());
        assertEquals(2, lecture8.getYearWeek());

        assertEquals("MIA", lecture10.getCourse());
        assertEquals("Arquitectura III", lecture10.getCurricuralUnit());
        assertEquals("L2245PL02", lecture10.getShift());
        assertEquals("ARQ-B4, ARQ-B3", lecture10.getClassN());
        assertEquals(16, lecture10.getNumberOfStudentsAssigned());
        assertEquals(DayOfWeek.THURSDAY, lecture10.getDayOfTheWeek());
        assertEquals(LocalTime.of(13, 0), lecture10.getStartOfClass());
        assertEquals(LocalTime.of(14, 30), lecture10.getEndOfClass());
        assertEquals(LocalDate.of(2022, 9, 30), lecture10.getDateOfClass());
        assertEquals("Arq 2", lecture10.getSpecificationOfRoom());
        assertNull(lecture10.getRoomCode());
        assertEquals(1, lecture10.getSemesterWeek());
        assertEquals(1, lecture10.getYearWeek());

        assertEquals("ME", lectureset.getCourse());
        assertEquals("Teoria dos Jogos e dos Contratos", lecture.getCurricuralUnit());
        assertEquals("01789TP01", lecture.getShift());
        assertEquals("MEA1", lecture.getClassN());
        assertEquals(30, lecture.getNumberOfStudentsAssigned());
        assertEquals(DayOfWeek.FRIDAY, lecture.getDayOfTheWeek());
        assertEquals(LocalTime.of(13, 0), lecture.getStartOfClass());
        assertEquals(LocalTime.of(14, 30), lecture.getEndOfClass());
        assertEquals(LocalDate.of(2022, 12, 02), lecture.getDateOfClass());
        assertEquals("Sala Aulas Mestrado", lecture.getSpecificationOfRoom());
        assertEquals("AA2.25", lecture.getRoomCode());
        assertEquals(1, lecture.getSemesterWeek());
        assertEquals(1, lecture.getYearWeek());
    }

    @Test
    void testInvalidDayOfWeek() {
        assertThrows(IllegalArgumentException.class, () -> new Lecture(new String[]{"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Sexta", "13:00:00", "14:30:00"}));
    }

    @Test
    void testInvalidTimeFormat() {
        assertThrows(IndexOutOfBoundsException.class, () -> new Lecture(new String[]{"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Sex", "25:", "14:30:00"}));
        assertThrows(IndexOutOfBoundsException.class, () -> new Lecture(new String[]{"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Sex", "13:00:00", "25:"}));
    }

    @Test
    void testInvalidDateFormat() {
        Lecture lecture = new Lecture(new String[]{"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Sex", "13:00:00", "14:30:00", "02/12/2024", "Sala Aulas Mestrado", "AA2.25"});
        assertThrows(IllegalArgumentException.class, () -> lecture.setSemesterWeek(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 2, 1)));
    }

}