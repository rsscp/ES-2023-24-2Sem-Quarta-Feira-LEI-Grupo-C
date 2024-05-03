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
    private final LinkedList<Room> rooms;
    private final ObservableList<Lecture> lectures;
    private String fileName;

    public ISCTE() {
        lectures = FXCollections.observableArrayList();
        rooms = new LinkedList<>();
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
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            br.readLine();

            String lecture;
            while ((lecture = br.readLine()) != null) {
                String[] arguments = lecture.split(";");
                this.lectures.add(new Lecture(arguments));
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

    /**
     * Writes down all the lectures.
     */
    public void writeDownLectures() {
        for (Lecture lecture : this.lectures) {
            System.out.println(lecture);
            break;
        }
    }
    public int checkConflict(Lecture l1, Lecture l2){
        if( l1.equals(l2)){
            return -1;
        }
        if(l1.getDateOfClass().equals(l2.getDateOfClass()) && l1.getRoomCode().equals(l2.getRoomCode()) && !(l1.getStartOfClass().isAfter(l2.getEndOfClass()) || l2.getStartOfClass().isAfter(l1.getEndOfClass()))){
            return 1;
        }
        if(l1.getCourse().equals(l2.getCourse()) && l1.getDateOfClass().equals(l2.getDateOfClass()) && l1.getShift().equals(l2.getShift()) && !l1.getRoomCode().equals(l2.getRoomCode()) && !(l1.getStartOfClass().isAfter(l2.getEndOfClass()) || l2.getStartOfClass().isAfter(l1.getEndOfClass()))){
            return 1;
        }
        return 0;
    }
    public ArrayList<ArrayList<Lecture>> mesureConflicts(){
        ArrayList<ArrayList<Lecture>> l = new ArrayList<>();
        for(int i=0; i<lectures.size();i++){
            ArrayList<Lecture> li = new ArrayList<>();
            for(int j=0; j<lectures.size();j++){
               if((this.checkConflict(lectures.get(i),lectures.get(j)))!=0){
                   li.add(lectures.get(j));
               }
            }
            l.add(li);
        }
        return l;
    }
}
