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
     * @param arguments Specifies details about specific lecture (room, start time, end time, name...).
     */
    public Lecture(String[] arguments) {
        this.course = arguments[0];
        this.curricuralUnit = arguments[1];
        this.shift = arguments[2];
        this.classN = arguments[3];
        this.numberOfStudentsAssigned = Integer.parseInt(arguments[4]);
        this.dayOfTheWeek = arguments[5];
        this.startOfClass = arguments[6];
        this.endOfClass = arguments[7];

        if (arguments.length == 8) {
            this.dateOfClass = "";
            this.specificationOfRoom = "";
            this.roomCode = "";
        } else if (arguments.length == 10 || arguments.length == 11){
            this.dateOfClass = arguments[8];
            this.specificationOfRoom = arguments[9];
            this.roomCode = arguments.length == 11 ? arguments[10] : "";
        } else {
            for (String s: arguments)
                System.err.println(s);
            throw new IllegalArgumentException("Read line with illegal number of fields");
        }
    }

    /**
     * Getter method for attribute course
     * @return course
     */
    public String getCourse() {
        return course;
    }

    /**
     * Getter method for attribute curricularUnit
     * @return curricularUnit
     */
    public String getCurricuralUnit() {
        return curricuralUnit;
    }

    /**
     * Getter method for attribute shift
     * @return shift
     */
    public String getShift() {
        return shift;
    }

    /**
     * Getter method for attribute classN
     * @return classN
     */
    public String getClassN() {
        return classN;
    }

    /**
     * Getter method for attribute numberOfStudentsAssigned
     * @return numberOfStudentsAssigned
     */
    public int getNumberOfStudentsAssigned() {
        return numberOfStudentsAssigned;
    }

    /**
     * Getter method for attribute dayOfTheWeek
     * @return dayOfTheWeek
     */
    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    /**
     * Getter method for attribute startOfClass
     * @return startOfClass
     */
    public String getStartOfClass() {
        return startOfClass;
    }

    /**
     * Getter method for attribute end of class
     * @return endOfClass
     */
    public String getEndOfClass() {
        return endOfClass;
    }

    /**
     * Getter method for attribute dateOfClass
     * @return dateOfClass
     */
    public String getDateOfClass() {
        return dateOfClass;
    }

    /**
     * Getter method for attribute specificationOfRoom
     * @return specificationOfRoom
     */
    public String getSpecificationOfRoom() {
        return specificationOfRoom;
    }

    /**
     * Getter method for attribute roomCode
     * @return roomCode
     */
    public String getRoomCode() {
        return roomCode;
    }

    /**
     * Lecture is represented by a string containing all it's information seperated by ";"
     * @return It will return this information in a single String.
     */
    @Override
    public String toString() {
        return this.course +
                ";" +  this.curricuralUnit +
                ";" + this.shift +
                ";" + this.classN +
                ";" + this.numberOfStudentsAssigned +
                ";" + this.dayOfTheWeek +
                ";" + this.startOfClass +
                ";" + this.endOfClass +
                ";" + this.dateOfClass +
                ";" + this.specificationOfRoom +
                ";" + this.roomCode;
    }
}
