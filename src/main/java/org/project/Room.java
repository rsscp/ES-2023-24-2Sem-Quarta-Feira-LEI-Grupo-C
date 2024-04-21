package org.project;

public class Room {
    private final String building;
    private String designation;
    private int normalCapacity;
    private int examCapacity;
    private int numOfCharacteristics;
    private boolean[] otherProperties;

    public Room(String b, String d, int nc, int ec, int ncha, boolean[] op) {
        this.building=b;
        this.designation=d;
        this.normalCapacity=nc;
        this.examCapacity=ec;
        this.numOfCharacteristics=ncha;
        this.otherProperties=op;
    }

    public String getBuilding() {
        return building;
    }

    public String getDesignation() {
        return designation;
    }

    public int getNormalCapacity() {
        return normalCapacity;
    }

    public int getExamCapacity() {
        return examCapacity;
    }

    public int getNumOfCharacteristics() {
        return numOfCharacteristics;
    }

    public boolean getPropertie(int i) {
        return otherProperties[i];
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setNormalCapacity(int normalCapacity) {
        this.normalCapacity = normalCapacity;
    }

    public void setExamCapacity(int examCapacity) {
        this.examCapacity = examCapacity;
    }

    public void setNumOfCharacteristics(int numOfCharacteristics) {
        this.numOfCharacteristics = numOfCharacteristics;
    }

    public void setOtherProperties(boolean[] otherProperties) {
        this.otherProperties = otherProperties;
    }
}
