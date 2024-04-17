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
    private final ObservableList<Lecture> lectures;

    public ISCTE() {
        lectures = FXCollections.observableArrayList();
    }

    /**
     * Downloads a CSV file from the provided URL
     * @param urlStr Provided URL String
     * @throws IOException Thrown when failed to open input or output stream, failed to read from input stream or failed to write to output stream
     * @throws MalformedURLException Thrown when parameter urlStr is an invalid URL String
     */
    public void createCSV(String urlStr) throws IOException, MalformedURLException {
        String[] urlSplit = urlStr.split("/");
        this.file = urlSplit[urlSplit.length - 1];
        URL url = new URL(urlStr);

        try (
                BufferedInputStream bis = new BufferedInputStream(url.openStream());
                FileOutputStream fis = new FileOutputStream(System.getProperty("user.dir") + File.separator + file);
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
   /* public void readLeactures(String fileName) throws IOException {
        File f = new File(fileName);
        Scanner sc = new Scanner(f);

        sc.nextLine();

        String lecture = "";

        while (sc.hasNextLine()) {
            lecture = sc.nextLine();
            String[] arguments = lecture.split(";");
            this.lectures.add(new Lecture(arguments));
        }
        sc.close();
    }*/

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

    public ObservableList<Lecture> getPage(int pageNumber, int pageSize) throws  IndexOutOfBoundsException {
        int firstIndex = pageNumber * pageSize;
        if (firstIndex > lectures.size())
            throw new IndexOutOfBoundsException("Page out of bounds");
        int truePageSize = Math.min(lectures.size() - firstIndex + 1, pageSize);
        return FXCollections.observableArrayList(lectures.subList(firstIndex, truePageSize));
    }

    public ObservableList<Lecture> getLectures() {
        return lectures;
    }

    public ObservableList<Lecture> getLectures(List<Filter> filters) {
        ObservableList<Lecture> filteredLectures = FXCollections.observableArrayList();
        for (Lecture l: lectures) {
            if (l.testFilters(filters))
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
        }
    }
}
