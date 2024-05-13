package org.project;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
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
}
