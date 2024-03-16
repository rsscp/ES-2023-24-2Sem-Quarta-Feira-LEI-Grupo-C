package org.project;

/**
 * Representation of specific lecure in the school.
 */
public class Lecture {
    private String course;
    private String curricuralUnit;
    private String shift;
    private String classN;
    private int numberOfStudentsAssigned;
    private String  dayOfTheWeek;
    private String startOfClass;
    private String endOfClass;
    private String dateOfClass;
    private String specificationOfRoom;
    private String roomCode;

    /**
     * Constructor of specific lectures
     * @param agruments Those arguments specifies details about specific lecture (room, start time, end time, name...).
     */
    public Lecture(String[] agruments) {
        this.course = agruments[0];
        this.curricuralUnit = agruments[1];
        this.shift = agruments[2];
        this.classN = agruments[3];
        this.numberOfStudentsAssigned = Integer.parseInt(agruments[4]);
        this.dayOfTheWeek = agruments[5];
        this.startOfClass = agruments[6];
        this.endOfClass = agruments[7];

        if (agruments.length == 8) {
            this.dateOfClass = " ";
            this.specificationOfRoom = " ";
            this.roomCode = " ";
        } else {
            this.dateOfClass = agruments[8];
            this.specificationOfRoom = agruments[9];
            this.roomCode = agruments.length == 11 ? agruments[10] : " ";
        }
    }

    /**
     * Method will summarize informations about the lecure.
     * @return It will return this information.
     */
    @Override
    public String toString() {
        return this.course + ", " +  this.curricuralUnit + ", "
                + this.shift + ", " + this.classN + ", " +
                this.numberOfStudentsAssigned + ", " + this.dayOfTheWeek + ", "
                + this.startOfClass + ", " + this.endOfClass + ", " + this.dateOfClass +
                ", " + this.specificationOfRoom + ", " + this.roomCode + "\n";
    }
}
