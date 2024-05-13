package org.project;

import java.util.ArrayList;
import java.util.List;

public class ucClassRules {

    private final String name;

    private int numberOfClassesToSchedule;

    private List<String> excludedPeriods;

    private List<String> allowedPeriods;

    private List<Room> preferredRooms;

    private List<Room> excludedRooms;

    public ucClassRules(String name) {
        this.name = name;
        this.excludedPeriods = new ArrayList<>();
        this.allowedPeriods = new ArrayList<>();
        this.preferredRooms = new ArrayList<>();
        this.excludedRooms = new ArrayList<>();
    }

    public String getUCName(){ return name; }

    public int getNumberOfClassesToSchedule(){return numberOfClassesToSchedule; }

    public void setNumberOfClassesToSchedule(int numberOfClassesToSchedule) {this.numberOfClassesToSchedule = numberOfClassesToSchedule;}

    public List<String> getExcludedPeriods() {return excludedPeriods;}

    public void setExcludedPeriods(List<String> excludedPeriods) {this.excludedPeriods = excludedPeriods;}

    public List<String> getAllowedPeriods() {return allowedPeriods;}

    public void setAllowedPeriods(List<String> allowedPeriods) {this.allowedPeriods = allowedPeriods;}

    public List<Room> getPreferredRooms() {return preferredRooms;}

    public void setPreferredRooms(List<Room> preferredRooms) {this.preferredRooms = preferredRooms;}

    public List<Room> getExcludedRooms() {return excludedRooms;}

    public void setExcludedRooms(List<Room> excludedRooms) {this.excludedRooms = excludedRooms;}

    public void addPreferredRoom (Room newRoom){preferredRooms.add(newRoom);}

    public void addExcludedRoom (Room newRoom){excludedRooms.add(newRoom);}

}
