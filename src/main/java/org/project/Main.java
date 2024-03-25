package org.project;

import java.io.IOException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException { //TODO MalformedURLexception ??????????????????
        System.out.println("Hello");
        ISCTE iscte = new ISCTE();
        createCSV();
        iscte.writeDownLectures();

        iscte.readLeactures("HorarioDeExemplo.csv");
    }

    public static void createCSV() throws IOException {
        String urlStr = "https://raw.githubusercontent.com/jaswb/csvFilesES/main/HorarioDeExemplo.csv";
        String file = "HorarioDeExemplo.csv";
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(System.getProperty("user.dir") + File.separator + file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}