package org.project;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

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
    public static int checkConflict(Lecture l1, Lecture l2){
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
    public static List<Set<Integer>> measureConflicts(List<Lecture> lectures) {
        List<Set<Integer>> conflictGroups = new ArrayList<>();
        int n = lectures.size();
        boolean[] visited = new boolean[n];

        Graph graph = new Graph(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (checkConflict(lectures.get(i), lectures.get(j)) == 1) {
                    graph.addEdge(i, j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Set<Integer> conflictGroup = new HashSet<>();
                dfs(graph, i, visited, conflictGroup);
                conflictGroups.add(conflictGroup);
            }
        }

        return conflictGroups;
    }

    static class Graph {
        int V;
        LinkedList<Integer>[] list;

        Graph(int V) {
            this.V = V;
            list = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                list[i] = new LinkedList<>();
            }
        }

        void addEdge(int src, int dest) {
            list[src].add(dest);
            list[dest].add(src);
        }
    }
    private static void dfs(Graph graph, int v, boolean[] visited, Set<Integer> conflictGroup) {
        visited[v] = true;
        conflictGroup.add(v);

        for (int neighbor : graph.list[v]) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, conflictGroup);
            }
        }
    }
}
