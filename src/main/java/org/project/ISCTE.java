package org.project;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class represents the school which contains some lectures
 */
public class ISCTE {
    private final LinkedList<Lecture> lectures;
    public ISCTE() {
        this.lectures = new LinkedList<>();
    }

    /**
     *This funcion tries to read data from file specified and consequently making objects
     * of the class Lecture from this data.
     * @param filePath Specifies the file location from where are data readet
     * @throws IOException Exception in case of reading file
     */
    public void readLeactures(String filePath) throws IOException {
        URL url = this.getClass().getClassLoader().getResource(filePath);
        File f = new File(url.getFile());
        Scanner sc = new Scanner(f);

        sc.nextLine();

        String lecture = "";

        while (sc.hasNextLine()) {
            lecture = sc.nextLine();
            String[] arguments = lecture.split(";");
            this.lectures.add(new Lecture(arguments));
        }
        sc.close();
    }

    /**
     * This funcion write down all af the lectures.
     */
    public void writeDownLectures() {
        for (Lecture lecture : this.lectures) {
            System.out.println(lecture);
        }
    }
}
