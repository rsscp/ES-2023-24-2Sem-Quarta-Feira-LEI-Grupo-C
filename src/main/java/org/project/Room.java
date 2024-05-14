package org.project;

import java.util.List;

public class Room {
    private final String building;
    private String designation;
    private int normalCapacity;
    private int examCapacity;
    private int numOfCharacteristics;
    private boolean amphitheatre;
    private boolean techHelp;
    private boolean arq1;
    private boolean arq2;
    private boolean arq3;
    private boolean arq4;
    private boolean arq5;
    private boolean arq6;
    private boolean arq9;
    private boolean BYOD;
    private boolean focusGroup;
    private boolean schedule;
    private boolean labACI;
    private boolean labACII;
    private boolean labBE;
    private boolean labEle;
    private boolean labInf;
    private boolean labJ;
    private boolean labRCI;
    private boolean labRCII;
    private boolean labTel;
    private boolean masterClass;
    private boolean masterClassPlus;
    private boolean NEERoom;
    private boolean testRoom;
    private boolean reunionRoom;
    private boolean architectureRoom;
    private boolean normalClassRoom;
    private boolean videoCall;

    public String getBuilding() {
        return building;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getNormalCapacity() {
        return normalCapacity;
    }

    public void setNormalCapacity(int normalCapacity) {
        this.normalCapacity = normalCapacity;
    }

    public int getExamCapacity() {
        return examCapacity;
    }

    public void setExamCapacity(int examCapacity) {
        this.examCapacity = examCapacity;
    }

    public int getNumOfCharacteristics() {
        return numOfCharacteristics;
    }

    public void setNumOfCharacteristics(int numOfCharacteristics) {
        this.numOfCharacteristics = numOfCharacteristics;
    }

    public boolean isAmphitheatre() {
        return amphitheatre;
    }

    public void setAmphitheatre(boolean amphitheatre) {
        this.amphitheatre = amphitheatre;
    }

    public boolean isTechHelp() {
        return techHelp;
    }

    public void setTechHelp(boolean techHelp) {
        this.techHelp = techHelp;
    }

    public boolean isArq1() {
        return arq1;
    }

    public void setArq1(boolean arq1) {
        this.arq1 = arq1;
    }

    public boolean isArq2() {
        return arq2;
    }

    public void setArq2(boolean arq2) {
        this.arq2 = arq2;
    }

    public boolean isArq3() {
        return arq3;
    }

    public void setArq3(boolean arq3) {
        this.arq3 = arq3;
    }

    public boolean isArq4() {
        return arq4;
    }

    public void setArq4(boolean arq4) {
        this.arq4 = arq4;
    }

    public boolean isArq5() {
        return arq5;
    }

    public void setArq5(boolean arq5) {
        this.arq5 = arq5;
    }

    public boolean isArq6() {
        return arq6;
    }

    public void setArq6(boolean arq6) {
        this.arq6 = arq6;
    }

    public boolean isArq9() {
        return arq9;
    }

    public void setArq9(boolean arq9) {
        this.arq9 = arq9;
    }

    public boolean isBYOD() {
        return BYOD;
    }

    public void setBYOD(boolean BYOD) {
        this.BYOD = BYOD;
    }

    public boolean isFocusGroup() {
        return focusGroup;
    }

    public void setFocusGroup(boolean focusGroup) {
        this.focusGroup = focusGroup;
    }

    public boolean isSchedule() {
        return schedule;
    }

    public void setSchedule(boolean schedule) {
        this.schedule = schedule;
    }

    public boolean isLabACI() {
        return labACI;
    }

    public void setLabACI(boolean labACI) {
        this.labACI = labACI;
    }

    public boolean isLabACII() {
        return labACII;
    }

    public void setLabACII(boolean labACII) {
        this.labACII = labACII;
    }

    public boolean isLabBE() {
        return labBE;
    }

    public void setLabBE(boolean labBE) {
        this.labBE = labBE;
    }

    public boolean isLabEle() {
        return labEle;
    }

    public void setLabEle(boolean labEle) {
        this.labEle = labEle;
    }

    public boolean isLabInf() {
        return labInf;
    }

    public void setLabInf(boolean labInf) {
        this.labInf = labInf;
    }

    public boolean isLabJ() {
        return labJ;
    }

    public void setLabJ(boolean labJ) {
        this.labJ = labJ;
    }

    public boolean isLabRCI() {
        return labRCI;
    }

    public void setLabRCI(boolean labRCI) {
        this.labRCI = labRCI;
    }

    public boolean isLabRCII() {
        return labRCII;
    }

    public void setLabRCII(boolean labRCII) {
        this.labRCII = labRCII;
    }

    public boolean isLabTel() {
        return labTel;
    }

    public void setLabTel(boolean labTel) {
        this.labTel = labTel;
    }

    public boolean isMasterClass() {
        return masterClass;
    }

    public void setMasterClass(boolean masterClass) {
        this.masterClass = masterClass;
    }

    public boolean isMasterClassPlus() {
        return masterClassPlus;
    }

    public void setMasterClassPlus(boolean masterClassPlus) {
        this.masterClassPlus = masterClassPlus;
    }

    public boolean isNEERoom() {
        return NEERoom;
    }

    public void setNEERoom(boolean NEERoom) {
        this.NEERoom = NEERoom;
    }

    public boolean isTestRoom() {
        return testRoom;
    }

    public void setTestRoom(boolean testRoom) {
        this.testRoom = testRoom;
    }

    public boolean isReunionRoom() {
        return reunionRoom;
    }

    public void setReunionRoom(boolean reunionRoom) {
        this.reunionRoom = reunionRoom;
    }

    public boolean isArchitectureRoom() {
        return architectureRoom;
    }

    public void setArchitectureRoom(boolean architectureRoom) {
        this.architectureRoom = architectureRoom;
    }

    public boolean isNormalClassRoom() {
        return normalClassRoom;
    }

    public void setNormalClassRoom(boolean normalClassRoom) {
        this.normalClassRoom = normalClassRoom;
    }

    public boolean isVideoCall() {
        return videoCall;
    }

    public void setVideoCall(boolean videoCall) {
        this.videoCall = videoCall;
    }

    public Room(String[] arguments) {
        this.building = arguments[0];
        this.designation = arguments[1];
        this.normalCapacity = Integer.parseInt(arguments[2]);
        this.examCapacity = Integer.parseInt(arguments[3]);
        this.numOfCharacteristics = Integer.parseInt(arguments[4]);
        this.amphitheatre = check(arguments[5]);
        this.techHelp = check(arguments[6]);
        this.arq1 = check(arguments[7]);
        this.arq2 = check(arguments[8]);
        this.arq3 = check(arguments[9]);
        this.arq4 = check(arguments[10]);
        this.arq5 = check(arguments[11]);
        this.arq6 = check(arguments[12]);
        this.arq9 = check(arguments[13]);
        this.BYOD = check(arguments[14]);
        this.focusGroup = check(arguments[15]);
        this.schedule = check(arguments[16]);
        this.labACI = check(arguments[17]);
        this.labACII = check(arguments[18]);
        this.labBE = check(arguments[19]);
        this.labEle = check(arguments[20]);
        this.labInf = check(arguments[21]);
        this.labJ = check(arguments[22]);
        this.labRCI = check(arguments[23]);
        this.labRCII = check(arguments[24]);
        this.labTel = check(arguments[25]);
        this.masterClass = check(arguments[26]);
        this.masterClassPlus = check(arguments[27]);
        this.NEERoom = check(arguments[28]);
        this.testRoom = check(arguments[29]);
        this.reunionRoom = check(arguments[30]);
        this.architectureRoom = check(arguments[31]);
        this.normalClassRoom = check(arguments[32]);
        this.videoCall = check(arguments[33]);
    }

    public static boolean check(String c){
        if(c.equals("X")){
            return true;
        } else {
            return false;
        }
    }

    public boolean testFilters(List<Filter> filters) {
        String[] attributeStrings = toString().split(";");
        if(filters.size() > attributeStrings.length)
            throw new IllegalArgumentException("Unexpected arguments, filter list length greater than number of attributes to be filtered");
        boolean result = true;
        for (Filter f : filters)
            result = f.op(result, filterString(attributeStrings[f.getAttributeIndex()], f.getFilterString()));
        return result;
    }

    private boolean filterString(String toBeFiltered, String filter) {
        return toBeFiltered.equals(filter);
        /*Pattern pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(toBeFiltered);
        return matcher.find();*/
    }

    @Override
    public String toString() {
        return this.building +
                ";" +  this.designation +
                ";" +  this.normalCapacity +
                ";" +  this.examCapacity +
                ";" +  this.numOfCharacteristics +
                ";" +  this.amphitheatre +
                ";" +  this.techHelp +
                ";" +  this.arq1 +
                ";" +  this.arq2 +
                ";" +  this.arq3 +
                ";" +  this.arq4 +
                ";" +  this.arq5 +
                ";" +  this.arq6 +
                ";" +  this.arq9 +
                ";" +  this.BYOD +
                ";" +  this.focusGroup +
                ";" +  this.schedule +
                ";" +  this.labACI +
                ";" +  this.labACII +
                ";" +  this.labBE +
                ";" +  this.labEle +
                ";" +  this.labInf +
                ";" +  this.labJ +
                ";" +  this.labRCI +
                ";" +  this.labRCII +
                ";" +  this.labTel +
                ";" +  this.masterClass +
                ";" +  this.masterClassPlus +
                ";" +  this.NEERoom +
                ";" +  this.testRoom +
                ";" +  this.reunionRoom +
                ";" +  this.architectureRoom +
                ";" +  this.normalClassRoom +
                ";" +  this.videoCall;
    }
}
