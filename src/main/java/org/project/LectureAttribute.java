package org.project;
public enum LectureAttribute {
    course(0, "Course"),
    curricuralUnit(1, "Curricular Unit"),
    shift(2, "Shift"),
    classN(3, "Class N"),
    numberOfStudentsAssigned(4, "Students Assigned"),
    dayOfTheWeek(5, "Day of the Week"),
    startOfClass(6, "Start"),
    endOfClass(7, "End"),
    dateOfClass(8, "Date"),
    specificationOfRoom(9, "Room"),
    roomCode(10, "Room Code");

    private int value;
    private String label;
    private LectureAttribute(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
