package org.project;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySlots {
    private Set<TimeSlot> timeSlots;
    private LocalDate date;

    public DaySlots(LocalDate date, boolean full) {
        this.timeSlots = HashSet.newHashSet(TimeSlot.values().length);
        this.date = date;
        if (full)
            timeSlots = new HashSet(TimeSlot.TIME_SLOTS_LIST);
    }

    public LocalDate getDate() {
        return date;
    }
    public Set<TimeSlot> getSlots() {
        return timeSlots;
    }

    public void addSlot(TimeSlot newSlot) {
        timeSlots.add(newSlot);
    }

    public void removeSlot(TimeSlot removeSlot) {
        timeSlots.remove(removeSlot);
    }
}
