package org.project;

import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javafx.collections.*;

public class ISCTETest {

    private ISCTE iscte;

    @Before
    public void setUp() {
        iscte = ISCTE.getInstance();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(iscte);
        ISCTE anotherIscte = ISCTE.getInstance();
        assertSame(iscte, anotherIscte);
    }

    @Test(expected = IllegalStateException.class)
    public void testSetSemesterDates_EmptyLecturesList() {
        iscte.setSemesterDates();
    }

    @Test(expected = IllegalStateException.class)
    public void testSetSemesterDates_IncompleteCurricularYear() {
        String[] lectureData = {"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Sex", "13:00:00", "14:30:00", "02/12/2022", "Sala Aulas Mestrado", "AA2.25"};
        String[] lectureData2 = {"MIA","Arquitectura III","L2245PL02","ARQ-B4, ARQ-B3","16","Ter","16:00:00","17:30:00"};
        Lecture lecture1 = new Lecture(lectureData);
        lecture1.setDateOfClass(LocalDate.of(2023, 9, 15));
        Lecture lecture2 = new Lecture(lectureData2);
        lecture2.setDateOfClass(LocalDate.of(2023, 12, 15));
        iscte.getLectures().addAll(lecture1, lecture2);
        iscte.setSemesterDates();
    }

    @Test
    public void testSetSemesterDates() {
        String[] lectureData = {"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Sex", "13:00:00", "14:30:00", "02/12/2022", "Sala Aulas Mestrado", "AA2.25"};
        String[] lectureData2 = {"MIA","Arquitectura III","L2245PL02","ARQ-B4, ARQ-B3","16","Ter","16:00:00","17:30:00"};
        Lecture lecture1 = new Lecture(lectureData);
        lecture1.setDateOfClass(LocalDate.of(2022, 9, 15));
        Lecture lecture2 = new Lecture(lectureData2);
        lecture2.setDateOfClass(LocalDate.of(2023, 12, 15));
        iscte.getLectures().addAll(lecture1, lecture2);
        iscte.setSemesterDates();
        assertEquals(LocalDate.of(2022, 8, 29), iscte.getFirstSemesterStart());
        assertEquals(LocalDate.of(2023, 1, 02), iscte.getSecondSemesterStart());
    }

    @Test
    public void testCheckConflict() {
        String[] lectureData = {"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Sex", "13:00:00", "14:30:00", "02/12/2022", "Sala Aulas Mestrado", "AA2.25"};
        Lecture lecture1 = new Lecture(lectureData);
        lecture1.setDateOfClass(LocalDate.of(2022, 9, 15));
        lecture1.setStartOfClass(LocalTime.from(LocalDate.of(2022, 9, 15).atTime(9, 0)));
        lecture1.setEndOfClass(LocalTime.from(LocalDate.of(2022, 9, 15).atTime(11, 0)));
        lecture1.setRoomCode("A101");

        String[] lectureData2 = {"MIA","Arquitectura III","L2245PL02","ARQ-B4, ARQ-B3","16","Ter","16:00:00","17:30:00"};
        Lecture lecture2 = new Lecture(lectureData2);
        lecture2.setDateOfClass(LocalDate.of(2022, 9, 15));
        lecture2.setStartOfClass(LocalTime.from(LocalDate.of(2022, 9, 15).atTime(10, 0)));
        lecture2.setEndOfClass(LocalTime.from(LocalDate.of(2022, 9, 15).atTime(12, 0)));
        lecture2.setRoomCode("A102");

        assertEquals(0, ISCTE.checkConflict(lecture1, lecture2));
    }

    @Test
    public void testMeasureConflicts() {
        String[] lectureData = {"ME", "Teoria dos Jogos e dos Contratos", "01789TP01", "MEA1", "30", "Sex", "13:00:00", "14:30:00", "02/12/2022", "Sala Aulas Mestrado", "AA2.25"};
        String[] lectureDataset = {"MA", "Teoria dos Jogos e dos Comandos", "01789TP00", "MIA1", "29", "Seg", "14:00:00", "15:30:00", "03/12/2022", "Sala Aulas Licenciatura", "AA1.25"};
        String[] lectureData10 = {"MIA","Arquitectura III","L2245PL02","ARQ-B4, ARQ-B3","16","Qui","13:00:00","14:30:00","30/09/2022","Arq 2"};

        Lecture lecture1 = new Lecture(lectureData);
        lecture1.setDateOfClass(LocalDate.of(2022, 9, 15));
        lecture1.setStartOfClass(LocalTime.from(LocalDate.of(2022, 9, 15).atTime(9, 0)));
        lecture1.setEndOfClass(LocalTime.from(LocalDate.of(2022, 9, 15).atTime(11, 0)));
        lecture1.setRoomCode("A101");

        Lecture lecture2 = new Lecture(lectureDataset);
        lecture2.setDateOfClass(LocalDate.of(2022, 9, 15));
        lecture2.setStartOfClass(LocalTime.from(LocalDate.of(2022, 9, 15).atTime(10, 0)));
        lecture2.setEndOfClass(LocalTime.from(LocalDate.of(2022, 9, 15).atTime(12, 0)));
        lecture2.setRoomCode("A102");

        Lecture lecture3 = new Lecture(lectureData10);
        lecture3.setDateOfClass(LocalDate.of(2022, 9, 15));
        lecture3.setStartOfClass(LocalTime.from(LocalDate.of(2022, 9, 15).atTime(13, 0)));
        lecture3.setEndOfClass(LocalTime.from(LocalDate.of(2022, 9, 15).atTime(15, 0)));
        lecture3.setRoomCode("A103");

        List<Lecture> lectures = new ArrayList<>();
        lectures.add(lecture1);
        lectures.add(lecture2);
        lectures.add(lecture3);

        ArrayList<ArrayList<Integer>> conflicts = ISCTE.measureConflicts(lectures);
        assertEquals(0, conflicts.get(0).size());
        assertEquals(0, conflicts.get(1).size());
        assertEquals(0, conflicts.get(2).size());
    }

    @Test
    public void testGetUrlFile() throws IOException, MalformedURLException {
        // Mock URL
        URL url = new URL("https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv");

        // Mock FileOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("HorarioDeExemplo.html") {
            @Override
            public void write(byte[] b, int off, int len) {
                byteArrayOutputStream.write(b, off, len);
            }
        };

        // Call the method
        ISCTE iscte = new ISCTE();
        iscte.getUrlFile(url.toString());

        // Verify the result
        assertEquals("",byteArrayOutputStream.toString());
    }

    @Test
    public void testReadLectures() throws IOException {
        String lectureData = "HorarioDeExemplo.csv";

        ISCTE iscte = ISCTE.getInstance();
        iscte.readLeactures(lectureData);

        List<Lecture> lectures = iscte.getLectures();
        assertEquals(26021, lectures.size());

        Lecture lecture1 = lectures.get(0);
        assertEquals("ME", lecture1.getCourse());
        assertEquals("Teoria dos Jogos e dos Contratos", lecture1.getCurricuralUnit());
        assertEquals(30, lecture1.getNumberOfStudentsAssigned());

        Lecture lecture2 = lectures.get(35);
        assertEquals("LP", lecture2.getCourse());
        assertEquals("Competências Académicas I", lecture2.getCurricuralUnit());
        assertEquals(23, lecture2.getNumberOfStudentsAssigned());

        List<Filter> filters = new ArrayList<>();


        filters.add(new Filter(LectureAttribute.course,"ME","AND"));
        filters.add(new Filter(LectureAttribute.curricularUnit,"Teoria dos Jogos e dos Contratos","AND"));
        filters.add(new Filter(LectureAttribute.shift,"01789TP01","AND"));
        filters.add(new Filter(LectureAttribute.classN,"MEA1","AND"));
        filters.add(new Filter(LectureAttribute.numberOfStudentsAssigned,"30","AND"));
        filters.add(new Filter(LectureAttribute.dayOfTheWeek, DayOfWeek.WEDNESDAY.toString(),"AND"));
        filters.add(new Filter(LectureAttribute.startOfClass,LocalTime.of(13, 0).toString(),"AND"));
        filters.add(new Filter(LectureAttribute.endOfClass,LocalTime.of(14, 30).toString(),"AND"));
        filters.add(new Filter(LectureAttribute.dateOfClass, "2022-11-23","AND"));
        filters.add(new Filter(LectureAttribute.specificationOfRoom,"Sala Aulas Mestrado","AND"));
        filters.add(new Filter(LectureAttribute.roomCode,"AA2.25","AND"));

        // Call the method
        List<Lecture> result = iscte.getLectures(filters, true);

        List<Filter> filtersOR = new ArrayList<>();
        filtersOR.add(new Filter(LectureAttribute.course,"ME","OR"));
        filtersOR.add(new Filter(LectureAttribute.curricularUnit,"Teoria dos Jogos e dos Contratos","OR"));

        // Call the method
        List<Lecture> resultOR = iscte.getLectures(filtersOR);

        // Verify the result
        assertEquals(91, resultOR.size());

        // Verify the result
            assertEquals(1, result.size());

        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.course,"ME"));
        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.curricularUnit,"Teoria dos Jogos e dos Contratos"));
        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.shift,"01789TP01"));
        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.classN,"MEA1"));
        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.numberOfStudentsAssigned,"30"));
        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.dayOfTheWeek,DayOfWeek.SATURDAY.toString()));
        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.startOfClass,LocalTime.of(13, 0).toString()));
        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.endOfClass,LocalTime.of(14, 30).toString()));
        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.dateOfClass,"2022-11-23"));
        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.roomCode,"AA2.25"));
        assertTrue(iscte.findSpecificElmOfSpecificLecture(LectureAttribute.specificationOfRoom,"Sala Aulas Mestrado"));

        iscte.deleteLecture(lectures.get(33));
        iscte.deleteLecture(null);

        // Verify the result
        assertEquals(26020, iscte.getLectures().size());
        assertEquals(26020, iscte.getLectures().size());
    }

    @Test
    public void testReadRooms() throws IOException {
        String testData = "CaracterizaçãoDasSalas.csv";


        ISCTE iscte = ISCTE.getInstance();
        iscte.readRooms(testData);

        List<Room> rooms = iscte.getRooms();
        assertEquals(131, rooms.size());

        Room room1 = rooms.get(0);
        assertEquals("Ala Aut�noma (ISCTE-IUL)", room1.getBuilding());
        assertEquals("Audit�rio Afonso de Barros", room1.getDesignation());
        assertEquals(80, room1.getNormalCapacity());

        Room room2 = rooms.get(1);
        assertEquals("Ala Aut�noma (ISCTE-IUL)", room2.getBuilding());
        assertEquals("Audit�rio Silva Leal", room2.getDesignation());
        assertEquals(54, room2.getNormalCapacity());

        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter(RoomAttribute.building, "Ala Aut�noma (ISCTE-IUL)", "AND"));
        filters.add(new Filter(RoomAttribute.designation,"AA2.24","AND"));

        // Call the method
        List<Room> result = iscte.getRooms(filters);

        // Verify the result
        assertEquals(1, result.size()); // Only one room matches the filter

        LocalDate start = LocalDate.now().minusDays(1); // Yesterday
        LocalDate end = LocalDate.now().plusDays(1); // Tomorrow

        List<Room> availableRooms = iscte.getAvailableRooms(start, end);

        assertEquals(131, availableRooms.size());

        String foundRoomCode = iscte.findRoomCode("Sala Aulas Mestrado");
        String foundRoomCodenull = iscte.findRoomCode("Panquecas");

        // Verify the result
        assertEquals("AA2.25", foundRoomCode);
        assertNull(foundRoomCodenull);
    }


}