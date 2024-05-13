package org.project;

import java.time.LocalDate;
import java.util.List;

public class DaySlot {
    List<TimeSlot> timeSlots;
    LocalDate date;

    public DaySlot(List<TimeSlot> timeSlots, LocalDate date) {
        this.timeSlots = timeSlots;
        this.date = date;
    }
}
