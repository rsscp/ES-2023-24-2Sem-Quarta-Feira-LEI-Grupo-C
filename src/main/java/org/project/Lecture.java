package org.project;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Representation of specific lecure in the school.
 */
public class Lecture {
    public Lecture(String course, String curricuralUnit, String shift, String classN, int numberOfStudentsAssigned, DayOfWeek dayOfTheWeek, LocalTime startOfClass, LocalTime endOfClass, LocalDate dateOfClass, String specificationOfRoom, String roomCode) {
        this.course = course;
        this.curricuralUnit = curricuralUnit;
        this.shift = shift;
        this.classN = classN;
        this.numberOfStudentsAssigned = numberOfStudentsAssigned;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startOfClass = startOfClass;
        this.endOfClass = endOfClass;
        this.dateOfClass = dateOfClass;
        this.specificationOfRoom = specificationOfRoom;
        this.roomCode = roomCode;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    private String course;

    public void setCurricuralUnit(String curricuralUnit) {
        this.curricuralUnit = curricuralUnit;
    }

    private String curricuralUnit;

    public void setShift(String shift) {
        this.shift = shift;
    }

    private String shift;

    public void setClassN(String classN) {
        this.classN = classN;
    }

    private String classN;

    public void setNumberOfStudentsAssigned(int numberOfStudentsAssigned) {
        this.numberOfStudentsAssigned = numberOfStudentsAssigned;
    }

    private int numberOfStudentsAssigned;

    public void setDayOfTheWeek(DayOfWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = TimeUtils.determineDayOfWeek(dayOfTheWeek);
    }

    private DayOfWeek dayOfTheWeek;

    public void setStartOfClass(LocalTime startOfClass) {
        this.startOfClass = startOfClass;
    }
    public void setStartOfClass(String startOfClass) {
        this.startOfClass = TimeUtils.determineLocalTime(startOfClass);
    }

    private LocalTime startOfClass;

    public void setEndOfClass(LocalTime endOfClass) {
        this.endOfClass = endOfClass;
    }
    public void setEndOfClass(String endOfClass) {
        this.endOfClass = TimeUtils.determineLocalTime(endOfClass);
    }

    private LocalTime endOfClass;

    public void setDateOfClass(LocalDate dateOfClass) {
        this.dateOfClass = dateOfClass;
    }
    public void setDateOfClass(String dateOfClass, String splitStr) {
        this.dateOfClass = TimeUtils.determineLocalDate(dateOfClass, splitStr);
    }

    private LocalDate dateOfClass;

    public void setSpecificationOfRoom(String specificationOfRoom) {
        this.specificationOfRoom = specificationOfRoom;
    }

    private String specificationOfRoom;

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    private String roomCode;

    public void setSemesterWeek(int semeterWeek) {
        this.semesterWeek = semeterWeek;
    }
    public void setSemesterWeek(LocalDate firstSemesterStart, LocalDate secondSemesterStart) {
        long firstDay = 0;
        if (dateOfClass == null)
            this.semesterWeek = null;
        else if (dateOfClass.getYear() == firstSemesterStart.getYear()) {
            firstDay = firstSemesterStart.toEpochDay();
        }
        else if (dateOfClass.getYear() == secondSemesterStart.getYear())
            firstDay = secondSemesterStart.toEpochDay();
        else
            throw new IllegalArgumentException("Lecture date is outside the given date arguments' scope");
        semesterWeek = ((int)(dateOfClass.toEpochDay() - firstDay))/7;
    }

    private Integer semesterWeek;

    public void setYearWeek(int yearWeek) {
        this.yearWeek = yearWeek;
    }
    public void setYearWeek(LocalDate firstSemesterStart) {
        long firstDay = firstSemesterStart.toEpochDay();
        yearWeek = ((int)(dateOfClass.toEpochDay() - firstDay))/7;
    }

    private Integer yearWeek;

    private boolean isSelected;

    /**
     * Constructor of specific lectures
     * @param arguments Specifies details about specific lecture (room, start time, end time, name...).
     */
    public Lecture(String[] arguments) {
        this.isSelected = false;
        this.course = arguments[0];
        this.curricuralUnit = arguments[1];
        this.shift = arguments[2];
        this.classN = arguments[3];
        this.numberOfStudentsAssigned = Integer.parseInt(arguments[4]);
        this.dayOfTheWeek = TimeUtils.determineDayOfWeek(arguments[5]);
        this.startOfClass = TimeUtils.determineLocalTime(arguments[6]);
        this.endOfClass = TimeUtils.determineLocalTime(arguments[7]);

        switch (arguments.length) {
            case 8 -> {
                this.dateOfClass = null;
                this.specificationOfRoom = null;
                this.roomCode = null;
            }
            case 10, 11 -> {
                this.dateOfClass = TimeUtils.determineLocalDate(arguments[8]);
                this.specificationOfRoom = arguments[9];
                this.roomCode = arguments.length == 11 ? arguments[10] : null;
            }
            default -> {
                for (String s : arguments)
                    System.err.println(s);
                throw new IllegalArgumentException("Read line with illegal number of fields");
            }
        }

        /*
        filterMethods.add(filter -> filterString(course, filter));
        filterMethods.add(filter -> filterString(curricuralUnit, filter));
        filterMethods.add(filter -> filterString(shift, filter));
        filterMethods.add(filter -> filterString(classN, filter));
        filterMethods.add(filter -> filterString(Integer.toString(numberOfStudentsAssigned), filter));
        filterMethods.add(filter -> filterString(dayOfTheWeek.toString, filter));
        filterMethods.add(filter -> filterString(course, filter));
        filterMethods.add(filter -> filterString(course, filter));
        filterMethods.add(filter -> filterString(course, filter));
        filterMethods.add(filter -> filterString(course, filter));
        filterMethods.add(filter -> filterString(course, filter));
        */
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
    public DayOfWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    /**
     * Getter method for attribute startOfClass
     * @return startOfClass
     */
    public LocalTime getStartOfClass() {
        return startOfClass;
    }

    /**
     * Getter method for attribute end of class
     * @return endOfClass
     */
    public LocalTime getEndOfClass() {
        return endOfClass;
    }

    /**
     * Getter method for attribute dateOfClass
     * @return dateOfClass
     */
    public LocalDate getDateOfClass() {
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
     * Getter method for attribute semesterWeek
     * @return semesterWeek
     */
    public Integer getSemesterWeek() {
        return semesterWeek;
    }

    /**
     * Getter method for attribute yearWeek
     * @return yearWeek
     */
    public Integer getYearWeek() {
        return yearWeek;
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
                ";" + this.roomCode +
                ";" + this.semesterWeek +
                ";" + this.yearWeek;
    }

    /**
     * Getter method for attribute course
     * @return course
     */
    private boolean filterCourse(String filterString) {
        return filterString(course, filterString);
    }

    /**
     * Getter method for attribute curricularUnit
     * @return curricularUnit
     */
    private boolean filterCurricuralUnit(String filterString) {
        return filterString(curricuralUnit, filterString);
    }

    /**
     * Getter method for attribute shift
     * @return shift
     */
    private boolean filterShift(String filterString) {
        return filterString(shift, filterString);
    }

    /**
     * Getter method for attribute classN
     * @return classN
     */
    private boolean filterClassN(String filterString) {
        return filterString(classN, filterString);
    }

    /**
     * Getter method for attribute numberOfStudentsAssigned
     * @return numberOfStudentsAssigned
     */
    private boolean filterNumberOfStudentsAssigned(String filterString) {
        return filterString(Integer.toString(numberOfStudentsAssigned), filterString);
    }

    /**
     * Getter method for attribute dayOfTheWeek
     * @return dayOfTheWeek
     */
    private boolean filterDayOfTheWeek(String filterString) {
        return filterString(dayOfTheWeek.toString(), filterString);
    }

    /**
     * Getter method for attribute startOfClass
     * @return startOfClass
     */
    private boolean filterStartOfClass(String filterString) {
        return filterString(startOfClass.toString(), filterString);
    }

    /**
     * Getter method for attribute end of class
     * @return endOfClass
     */
    private boolean filterEndOfClass(String filterString) {
        return filterString(endOfClass.toString(), filterString);
    }

    /**
     * Getter method for attribute dateOfClass
     * @return dateOfClass
     */
    private boolean filterDateOfClass(String filterString) {
        return filterString(dateOfClass.toString(), filterString);
    }

    /**
     * Getter method for attribute specificationOfRoom
     * @return specificationOfRoom
     */
    private boolean filterSpecificationOfRoom(String filterString) {
        return filterString(specificationOfRoom, filterString);
    }

    /**
     * Getter method for attribute roomCode
     * @return roomCode
     */
    private boolean filterRoomCode(String filterString) {
        return filterString(roomCode, filterString);
    }

    public boolean testFilters(List<Filter> filters) {
        boolean result = true;
        boolean firstFilterCleared = false;
        String[] attributeStrings = toString().split(";");
        if(filters.size() > attributeStrings.length)//TODO Move this verification to ISCTE class
            throw new IllegalArgumentException("Unexpected arguments, filter list length greater than number of attributes to be filtered");
        for (Filter f : filters) {
            if (!firstFilterCleared) {
                result = filterString(attributeStrings[f.getAttributeIndex()], f.getFilterString());
                firstFilterCleared = true;
            } else {
                result = f.op(result, filterString(attributeStrings[f.getAttributeIndex()], f.getFilterString()));
            }
        }
        return result;
    }

    public void setWeeks() {
        LocalDate firstSemesterStart = ISCTE.getInstance().getFirstSemesterStart();
        LocalDate secondSemesterStart = ISCTE.getInstance().getSecondSemesterStart();
        setSemesterWeek(firstSemesterStart, secondSemesterStart);
        setYearWeek(firstSemesterStart);
    }

    public boolean testFilters(List<Filter> filters, boolean includeEveryFilter) {
        boolean result = true;
        for (Filter filter: filters) {
            switch (filter.getAttributeIndex()) {
                case 0:
                    result = filterCourse(filter.getFilterString());
                    break;
                case 1:
                    result = filterCurricuralUnit(filter.getFilterString());
                    break;
                case 2:
                    result = filterShift(filter.getFilterString());
                    break;
                case 3:
                    result = filterClassN(filter.getFilterString());
                    break;
                case 4:
                    result = filterNumberOfStudentsAssigned(filter.getFilterString());
                    break;
                case 5:
                    result = filterDayOfTheWeek(filter.getFilterString());
                    break;
                case 6:
                    result = filterStartOfClass(filter.getFilterString());
                    break;
                case 7:
                    result = filterEndOfClass(filter.getFilterString());
                    break;
                case 8:
                    if (this.dateOfClass == null) {
                        if (includeEveryFilter) {
                            return false;
                        } else {
                            result = false;
                        }
                    }
                    if (this.dateOfClass != null) {
                        result = filterDateOfClass(filter.getFilterString());
                    }
                    break;
                case 9:
                    if (this.specificationOfRoom == null) {
                        if (includeEveryFilter) {
                            return false;
                        } else {
                            result = false;
                        }
                    }
                    if (this.specificationOfRoom != null) {
                        result = filterSpecificationOfRoom(filter.getFilterString());
                    }
                    break;
                case 10:
                    if (this.roomCode == null) {
                        if (includeEveryFilter) {
                            return false;
                        } else {
                            result = false;
                        }
                    }
                    if (this.roomCode != null) {
                        result = filterRoomCode(filter.getFilterString());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("No lecture attribute with index " + filter.getAttributeIndex());
            }
            if (!includeEveryFilter && result) {
                return true;
            }
            if (includeEveryFilter && !result) {
                return false;
            }
        }
        return includeEveryFilter;
    }

    private boolean filterString(String toBeFiltered, String filter) {
        return toBeFiltered.equals(filter);
        /*Pattern pattern = Pattern.compile(filter, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(toBeFiltered);
        return matcher.find();*/
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public boolean isSelected() {
        return this.isSelected;
    }
}
