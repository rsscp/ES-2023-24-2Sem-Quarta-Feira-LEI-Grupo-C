package org.project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TimeSlot {
    SLOT_0(LocalTime.of(8, 0), LocalTime.of(9, 30), DaySection.MORNING, "08:00-09:30"),
    SLOT_1(LocalTime.of(9, 30), LocalTime.of(11, 0), DaySection.MORNING,  "09:30-11:00"),
    SLOT_2(LocalTime.of(11, 0), LocalTime.of(12, 30), DaySection.MORNING,  "11:00-12:30"),
    SLOT_3(LocalTime.of(13, 0), LocalTime.of(14, 30), DaySection.AFTERNOON,  "13:00-14:30"),
    SLOT_4(LocalTime.of(14, 30), LocalTime.of(16, 0), DaySection.AFTERNOON,  "14:30-16:00"),
    SLOT_5(LocalTime.of(16, 0), LocalTime.of(17, 30), DaySection.AFTERNOON,  "16:00-17:30"),
    SLOT_6(LocalTime.of(18, 0), LocalTime.of(19, 30), DaySection.NIGHT,  "18:00-19:30"),
    SLOT_7(LocalTime.of(19, 30), LocalTime.of(21, 0), DaySection.NIGHT,  "19:30-21:00"),
    SLOT_8(LocalTime.of(21, 0), LocalTime.of(22, 30), DaySection.NIGHT,  "21:00-22:30");

    private enum DaySection {
        MORNING(),
        AFTERNOON(),
        NIGHT();
    }

    private LocalTime start;
    private LocalTime end;
    private DaySection section;

    public String getLabel() {
        return label;
    }

    String label;

    private static final TimeSlot[] TIME_SLOTS_ARRAY = {
            SLOT_0,
            SLOT_1,
            SLOT_2,
            SLOT_3,
            SLOT_4,
            SLOT_5,
            SLOT_6,
            SLOT_7,
            SLOT_8
    };
    public static final List<TimeSlot> TIME_SLOTS_LIST = Arrays.asList(TIME_SLOTS_ARRAY);

    private TimeSlot(LocalTime start, LocalTime end, DaySection section, String label) {
        this.start = start;
        this.end = end;
        this.section = section;
        this.label = label;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public boolean intrudes(LocalTime start, LocalTime end) {
        //TODO?
        return false;
    }

    public static List<TimeSlot> slotsOccupiedBy(LocalTime start, LocalTime end) {
        if (start.isAfter(end))
            throw new IllegalArgumentException("Argumento de início é posterior a argumento de fim");
        List<TimeSlot> occupiedSlots = new ArrayList<>();
        for (TimeSlot s: TIME_SLOTS_LIST) {
            boolean isBefore = end.equals(s.start) || end.isBefore(s.start);
            boolean isAfter = start.equals(s.end) || start.isAfter(s.end);
            if (!(isBefore || isAfter))
                occupiedSlots.add(s);
        }
        return occupiedSlots;
    }

    @Override
    public String toString(){
        return this.getLabel();
    }
}
