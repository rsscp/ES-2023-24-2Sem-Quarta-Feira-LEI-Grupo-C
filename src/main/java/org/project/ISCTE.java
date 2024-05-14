package org.project;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javafx.collections.*;

/**
 * This class represents the school which contains some lectures
 */
public class ISCTE {

    private static ISCTE instance;

    public ObservableList<Room> getRooms() {
        return rooms;
    }

    private final ObservableList<Room> rooms;

    private final ObservableList<Lecture> lectures;
    private LocalDate firstSemesterStart;
    private LocalDate secondSemesterStart;
    private String fileName;

    private ISCTE() {
        lectures = FXCollections.observableArrayList();
        rooms = FXCollections.observableArrayList();
        this.fileName = null;
    }

    public static ISCTE getInstance() {
        if (instance == null)
            instance = new ISCTE();
        return instance;
    }

    public LocalDate getFirstSemesterStart() {
        return firstSemesterStart;
    }

    public LocalDate getSecondSemesterStart() {
        return secondSemesterStart;
    }

    /**
     * Downloads a CSV file from the provided URL
     * @param urlStr Provided URL String
     * @throws IOException Thrown when failed to open input or output stream, failed to read from input stream or failed to write to output stream
     * @throws MalformedURLException Thrown when parameter urlStr is an invalid URL String
     */
    public void getUrlFile(String urlStr) throws IOException, MalformedURLException {
        String[] urlSplit = urlStr.split("/");
        this.fileName = urlSplit[urlSplit.length - 1];
        URL url = new URL(urlStr);

        try (
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(System.getProperty("user.dir") + File.separator + fileName);
        ) {
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Tries to read data from file specified and consequently making objects
     * of the class Lecture from this data.
     * @param fileName Specifies the file location from where are data readet
     * @throws IOException Exception in case of reading file
     */
    public void readLeactures(String fileName) throws IOException {
        try (
            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr)
        ) {
            br.readLine();
            String lecture;
            while ((lecture = br.readLine()) != null) {
                String[] arguments = lecture.split(";");
                this.lectures.add(new Lecture(arguments));
            }
            setSemesterDates();
            setCalculatedColumns();
        }
    }

    public void readRooms(String fileName) throws IOException {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr)
        ) {
            br.readLine();
            String room;
            while ((room = br.readLine()) != null) {
                String[] arguments = room.split(";", -1);
                this.rooms.add(new Room(arguments));
            }
        }
    }
    public ObservableList<Lecture> getLectures() {
        return lectures;
    }

    public ObservableList<Lecture> getLectures(List<Filter> filters, boolean includeEveryFilter) {
        ObservableList<Lecture> filteredLectures = FXCollections.observableArrayList();
        for (Lecture l: lectures) {
            if (l.testFilters(filters, includeEveryFilter))
                filteredLectures.add(l);
        }
        return filteredLectures;
    }

    public ObservableList<Lecture> getLectures(List<Filter> filters) {
        ObservableList<Lecture> filteredLectures = FXCollections.observableArrayList();
        for (Lecture l: lectures) {
            if (l.testFilters(filters))
                filteredLectures.add(l);
        }
        return filteredLectures;
    }
    public ObservableList<Room> getRooms(List<Filter> filters) {
        ObservableList<Room> filteredRooms = FXCollections.observableArrayList();
        for (Room r: rooms) {
            if (r.testFilters(filters))
                filteredRooms.add(r);
        }
        return filteredRooms;
    }

    public ObservableList<Room> getAvailableRooms(LocalDate start, LocalDate end) {
        ObservableList<Lecture> lecturesBetween = lectures.filtered(l -> {
            return l.getDateOfClass().isAfter(start) && l.getDateOfClass().isBefore(end);
        });
        ObservableList<Room> availableRooms = rooms.filtered(r -> {
            return lecturesBetween.filtered(l -> {
                return l.getRoomCode() == r.getDesignation();
            }).size() == 0;
        });
        return availableRooms;
    }

    /*
    public ObservableList<DaySlots> getAllSlots() {
        HashMap<LocalDate, DaySlots> slots = new HashMap<>();
        for (LocalDate d = firstSemesterStart; d.isBefore(firstSemesterStart.plusDays(17*7)); d=d.plusDays(1))
            slots.put(d, new DaySlots(d));
        for (LocalDate d = secondSemesterStart; d.isBefore(secondSemesterStart.plusDays(15*7)); d=d.plusDays(1))
            slots.put(d, new DaySlots(d));
        for (Lecture l: lectures) {
            List<TimeSlot> slotsOccupied = TimeSlot.slotsOccupiedBy(
                l.getStartOfClass(),
                l.getEndOfClass()
            );
            DaySlots daySlots = slots.get(l.getDateOfClass());
            if (daySlots != null)
                for (TimeSlot slot: slotsOccupied)
                    daySlots.removeSlot(slot);
        }
        return FXCollections.observableArrayList(slots.values());
    }

     */

    public ObservableList<DaySlot> getFirstSemesterSlots() {
        ObservableList<DaySlot> slots = FXCollections.observableArrayList();
        putFirstSemesterSlots(slots);
        return slots;
    }

    public ObservableList<DaySlot> getSecondSemesterSlots() {
        ObservableList<DaySlot> slots = FXCollections.observableArrayList();
        putSecondSemesterSlots(slots);
        return slots;
    }

    private void putFirstSemesterSlots(ObservableList<DaySlot> slots) {
        for(TimeSlot t:TimeSlot.values()){
            DaySlot d1 = new DaySlot(TimeUtils.determineLocalDate("01/01/2022"),t);
            DaySlot d2 = new DaySlot(TimeUtils.determineLocalDate("02/01/2022"),t);
            DaySlot d3 = new DaySlot(TimeUtils.determineLocalDate("03/01/2022"),t);
            DaySlot d4 = new DaySlot(TimeUtils.determineLocalDate("04/01/2022"),t);
            DaySlot d5 = new DaySlot(TimeUtils.determineLocalDate("05/01/2022"),t);

            slots.add(d1);
            slots.add(d2);
            slots.add(d3);
            slots.add(d4);
            slots.add(d5);
        }
    }

    private void putSecondSemesterSlots(ObservableList<DaySlot> slots) {
        for(TimeSlot t:TimeSlot.values()){
            DaySlot d1 = new DaySlot(TimeUtils.determineLocalDate("01/02/2022"),t);
            DaySlot d2 = new DaySlot(TimeUtils.determineLocalDate("02/02/2022"),t);
            DaySlot d3 = new DaySlot(TimeUtils.determineLocalDate("03/02/2022"),t);
            DaySlot d4 = new DaySlot(TimeUtils.determineLocalDate("04/02/2022"),t);
            DaySlot d5 = new DaySlot(TimeUtils.determineLocalDate("05/02/2022"),t);

            slots.add(d1);
            slots.add(d2);
            slots.add(d3);
            slots.add(d4);
            slots.add(d5);
        }
    }

    public void writeCsv() throws Exception {
        Writer writer = null;
        try {
            File file = new File(System.getProperty("user.dir") + File.separator + "NovoHorario.csv");
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("Curso;Unidade Curricular;Turno;Turma;Inscritos no turno;Dia da semana;Hora início da aula;Hora fim da aula;Data da aula;Características da sala pedida para a aula;Sala atribuída à aula\n");
            for (Lecture lecture : lectures) {
                String text = lecture.toString();
                writer.write(text + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * Writes down all the lectures.
     */
    public void writeDownLectures() {
        for (Lecture lecture : this.lectures) {
            System.out.println(lecture);
            break;
        }
    }
    public static int checkConflict(Lecture l1, Lecture l2) {
        if (l1.equals(l2)) {
            return -1;
        }
        if(l1.getDateOfClass()==null || l2.getDateOfClass()==null || l1.getRoomCode() == null || l2.getRoomCode() == null){
            return 0;
        }
        if (l1.getDateOfClass().equals(l2.getDateOfClass()) && l1.getRoomCode().equals(l2.getRoomCode()) && !(l1.getStartOfClass().plusSeconds(1).isAfter(l2.getEndOfClass()) || l2.getStartOfClass().plusSeconds(1).isAfter(l1.getEndOfClass()))) {
            return 1;
        }
        if (l1.getCourse().equals(l2.getCourse()) && l1.getDateOfClass().equals(l2.getDateOfClass()) && l1.getShift().equals(l2.getShift()) && !l1.getRoomCode().equals(l2.getRoomCode()) && !(l1.getStartOfClass().plusSeconds(1).isAfter(l2.getEndOfClass()) || l2.getStartOfClass().plusSeconds(1).isAfter(l1.getEndOfClass()))) {
            return 1;
        }
        return 0;
    }

    public static ArrayList<ArrayList<Integer>> measureConflicts(List<Lecture> lectures) {
        ArrayList<ArrayList<Integer>> conflitos = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < lectures.size(); i++) {
            ArrayList<Integer> conflitosDeI = new ArrayList<Integer>();
            for (int j = 0; j < lectures.size(); j++) {
                if (checkConflict(lectures.get(i), lectures.get(j)) == 1) {
                    conflitosDeI.add(j);
                }
            }
            conflitos.add(conflitosDeI);
        }
        return conflitos;
    }

    public boolean findSpecificElmOfSpecificLecture(LectureAttribute attribute, String elm) {
        boolean founded = false;

        for (Lecture lecture : lectures) {
            switch (attribute) {
                case course -> {
                    if (lecture.getCourse().equals(elm)) {
                        founded = true;
                    }
                }
                case curricuralUnit -> {
                    if (lecture.getCurricuralUnit().equals(elm)) {
                        founded = true;
                    }
                }
                case shift -> {
                    if (lecture.getShift().equals(elm)) {
                        founded = true;
                    }
                }
                case classN -> {
                    if (lecture.getClassN().equals(elm)) {
                        founded = true;
                    }
                }
                case numberOfStudentsAssigned -> {
                    try {
                        if (lecture.getNumberOfStudentsAssigned() == Integer.parseInt(elm)) {
                            founded = true;
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                case dayOfTheWeek -> {
                    if (lecture.getDayOfTheWeek().toString().equals(elm)) {
                        founded = true;
                    }
                }
                case startOfClass -> {
                    if (lecture.getStartOfClass().toString().equals(elm)) {
                        founded = true;
                    }
                }
                case endOfClass -> {
                    if (lecture.getEndOfClass().toString().equals(elm)) {
                        founded = true;
                    }
                }
                case dateOfClass -> {
                    if (lecture.getDateOfClass() != null && lecture.getDateOfClass().toString().equals(elm)) {
                        founded = true;
                    }
                }
                case specificationOfRoom -> {
                    if (lecture.getSpecificationOfRoom() != null && lecture.getSpecificationOfRoom().equals(elm)) {
                        founded = true;
                    }
                }
                case roomCode -> {
                    if (lecture.getRoomCode() != null && lecture.getRoomCode().equals(elm)) {
                        founded = true;
                    }
                }
            }
        }
        return founded;
    }

    public String findRoomCode(String specificationOfRoom) {
        for (Lecture lecture : this.lectures) {
            if (lecture.getSpecificationOfRoom() != null && lecture.getSpecificationOfRoom().equals(specificationOfRoom)) {
                return lecture.getRoomCode();
            }
        }
        return null;
    }

    public void deleteLecture(Lecture lecture) {
        if (lecture != null) {
            this.lectures.remove(lecture);
        }
    }

    private void setSemesterDates() {
        if (lectures.size() == 0)
            throw new IllegalStateException("No lectures loaded");
        ObservableList<Lecture> lecturesSorted = lectures.sorted((l1, l2) -> {
            if (l1.getDateOfClass() == null)
                return -1;
            else if (l2.getDateOfClass() == null)
                return 1;
            else if (l1.getDateOfClass().isBefore(l2.getDateOfClass()))
                return -1;
            else
                return 1;
        }).filtered(l -> {
            return l.getDateOfClass() != null;
        });
        LocalDate firstDate = lecturesSorted.get(0).getDateOfClass();
        LocalDate lastDate = lecturesSorted.get(lecturesSorted.size()-1).getDateOfClass();
        if(firstDate.getYear() == lastDate.getYear())
            throw new IllegalStateException("Loaded incomplete curricular year");
        else {
            ObservableList<Lecture> secondSemesterLectures = lecturesSorted.filtered(l -> {
                return l.getDateOfClass().getYear() == lastDate.getYear();
            });
            firstSemesterStart = TimeUtils.determineStartOfWeek(firstDate);
            secondSemesterStart = TimeUtils.determineStartOfWeek(secondSemesterLectures.get(0).getDateOfClass());
        }
    }

    private void setCalculatedColumns() {
        ObservableList<Lecture> filteredLectures = lectures.filtered(l -> {
            return l.getDateOfClass() != null;
        });
        for (Lecture l: filteredLectures) {
            l.setSemesterWeek(firstSemesterStart, secondSemesterStart);
            l.setYearWeek(firstSemesterStart);
        }
    }
}
