package org.project;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TimeSlot {
    SLOT_0(LocalTime.of(8, 0), LocalTime.of(9, 30), DaySection.MORNING),
    SLOT_1(LocalTime.of(9, 30), LocalTime.of(11, 0), DaySection.MORNING),
    SLOT_2(LocalTime.of(11, 0), LocalTime.of(12, 30), DaySection.MORNING),
    SLOT_3(LocalTime.of(13, 0), LocalTime.of(14, 30), DaySection.AFTERNOON),
    SLOT_4(LocalTime.of(14, 30), LocalTime.of(16, 0), DaySection.AFTERNOON),
    SLOT_5(LocalTime.of(16, 0), LocalTime.of(17, 30), DaySection.AFTERNOON),
    SLOT_6(LocalTime.of(18, 0), LocalTime.of(19, 30), DaySection.NIGHT),
    SLOT_7(LocalTime.of(19, 30), LocalTime.of(21, 0), DaySection.NIGHT),
    SLOT_8(LocalTime.of(21, 0), LocalTime.of(22, 30), DaySection.NIGHT);

    private enum DaySection {
        MORNING(),
        AFTERNOON(),
        NIGHT();
    }

    LocalTime start;
    LocalTime end;
    DaySection section;

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

    private TimeSlot(LocalTime start, LocalTime end, DaySection section) {
        this.start = start;
        this.end = end;
        this.section = section;
    }

    public boolean intrudes(LocalTime start, LocalTime end) {
        //TODO?
        return false;
    }

    public List<TimeSlot> slotsOccupiedBy(LocalTime start, LocalTime end) {
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
}
