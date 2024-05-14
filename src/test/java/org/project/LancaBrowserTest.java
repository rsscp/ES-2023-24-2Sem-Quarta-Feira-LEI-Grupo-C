package org.project;


import static org.junit.Assert.*;

import org.junit.*;
import java.io.*;

public class LancaBrowserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testMain() throws IOException {
        LancaBrowser.main(null);
        String expectedOutputStart = "URL para o ficheiro html remoto a ser descarregado para o sistema de ficheiros local = ";
        String expectedOutputEnd = "Nome do ficheiro html local = SalasDeAulaPorTiposDeSala.html";
        String[] lines = outContent.toString().split(System.getProperty("line.separator"));
        assertTrue(lines[0].startsWith(expectedOutputStart));
        assertTrue(lines[2].endsWith(expectedOutputEnd));
        File file = new File("SalasDeAulaPorTiposDeSala.html");
        assertTrue(file.exists());
        file.delete();
    }
}