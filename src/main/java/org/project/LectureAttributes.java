package org.project;
public enum LectureAttributes {
    course(0),
    curricuralUnit(1),
    shift(2),
    classN(3),
    numberOfStudentsAssigned(4),
    dayOfTheWeek(5),
    startOfClass(6),
    endOfClass(7),
    dateOfClass(8),
    specificationOfRoom(9),
    roomCode(10);

    private int value;
    private LectureAttributes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
