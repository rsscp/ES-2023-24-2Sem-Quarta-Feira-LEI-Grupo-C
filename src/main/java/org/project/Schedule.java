package org.project;

public class Schedule {
    private String course;
    private String curricuralUnit;
    private String shift;
    private String classN;
    private int numberOfStudents;
    private String dayOfTheWeek;
    private String startOfClass;
    private String endOfClass;
    private String dateOfClass;
    private String specificationOfRoom;
    private String roomCode;

    public Schedule(String c, String uc, String s, String cl, int n, String d, String st, String e, String dt, String sr,String rc ){
        this.course=c;
        this.curricuralUnit=uc;
        this.shift=s;
        this.classN=cl;
        this.numberOfStudents=n;
        this.dayOfTheWeek=d;
        this.startOfClass=st;
        this.endOfClass=e;
        this.dateOfClass=dt;
        this.specificationOfRoom=sr;
        this.roomCode=rc;
    }

    @Override
    public String toString() {
        return "[" + course + ',' + curricuralUnit + ',' + shift + ',' + classN + ',' + numberOfStudents + ',' + dayOfTheWeek + ',' + startOfClass + ',' + endOfClass + ',' + dateOfClass + ',' + specificationOfRoom + ',' + roomCode + ']';
    }

    public String getCourse() {
        return course;
    }

    public String getCurricuralUnit() {
        return curricuralUnit;
    }

    public String getShift() {
        return shift;
    }

    public String getClassN() {
        return classN;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public String getStartOfClass() {
        return startOfClass;
    }

    public String getEndOfClass() {
        return endOfClass;
    }

    public String getDateOfClass() {
        return dateOfClass;
    }

    public String getSpecificationOfRoom() {
        return specificationOfRoom;
    }

    public String getRoomCode() {
        return roomCode;
    }
}
