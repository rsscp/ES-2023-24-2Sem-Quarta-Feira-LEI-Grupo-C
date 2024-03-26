package org.project;

import java.io.IOException;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) {
        ISCTE iscte = new ISCTE();
        try {
            ISCTE.createCSV("https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv");
            iscte.readLeactures("HorarioDeExemplo.csv");
            iscte.writeDownLectures();
        } catch(MalformedURLException e) {
            // TODO Code run after receiving an invalid URL
        } catch(IOException e) {
            // TODO Code run when file couldn't be downloaded
        }
    }
}