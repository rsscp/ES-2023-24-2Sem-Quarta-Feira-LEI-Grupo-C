package org.project;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Schedule s1 = new Schedule("Engenharia Informática", "SGR","T1","classN",30,"Monday", "9:40","11:00", "21/2/2024", "sala","C504");
        Schedule s2 = new Schedule("Engenharia Informática", "DIAM","T1","classN",30,"Monday", "11:10","12:30", "21/2/2024", "sala","C507");
        Schedule s3 = new Schedule("Engenharia Informática", "IPM","T1","classN",30,"Wednesday", "9:40","11:00", "23/2/2024", "sala","C304");
        Schedule s4 = new Schedule("Engenharia Informática", "ES","T1","classN",30,"Thursday", "13:00","14:20", "24/2/2024", "auditorio","B104");

        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        schedules.add(s1);
        schedules.add(s2);
        schedules.add(s3);
        schedules.add(s4);

        for(Schedule s: schedules){
            System.out.println(s);
        }
    }
}
