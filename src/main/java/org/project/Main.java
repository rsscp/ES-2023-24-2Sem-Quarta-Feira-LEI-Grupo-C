package org.project;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ISCTE iscte = new ISCTE();
        try {
            iscte.readLeactures("HorarioDeExemplo.csv");
        } catch (IOException e) {
            System.out.println(e);
        }

        iscte.writeDownLectures();
    }
}