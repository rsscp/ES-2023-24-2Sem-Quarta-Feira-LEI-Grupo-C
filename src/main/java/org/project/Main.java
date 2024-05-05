package org.project;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.project.ui.StartController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        //App.main(args);
        UI.main(args);
        ObservableList<Lecture> a =ISCTE.getInstance().getLectures();
        String[] s = {"MIA","Urbanismo II","03836TP01","ARQ-D6, ARQ-D5, ARQ-D4, ARQ-D3, ARQ-D2, ARQ-D1","60","Sex","10:00:00","12:00:00","25/11/2022","Sala Aulas Mestrado","2E07"};
        Lecture l1 = new Lecture(s);
        a.add(l1);
        List<Set<Integer>> l = ISCTE.getInstance().measureConflicts(a);
        System.out.println(l.toString());


    }
}
