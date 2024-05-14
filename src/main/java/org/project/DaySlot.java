package org.project;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySlot {

    private TimeSlot timeSlot;
    private LocalDate date;

    public DaySlot(LocalDate date, TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}