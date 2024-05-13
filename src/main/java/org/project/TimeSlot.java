package org.project;

import java.time.LocalDateTime;
import java.time.LocalTime;

public enum TimeSlot {
    slot0(LocalTime.of(8, 0), LocalTime.of(9, 30), DaySection.MORNING),
    slot1(LocalTime.of(9, 30), LocalTime.of(11, 0), DaySection.MORNING),
    slot2(LocalTime.of(11, 0), LocalTime.of(12, 30), DaySection.MORNING),
    slot3(LocalTime.of(13, 0), LocalTime.of(14, 30), DaySection.AFTERNOON),
    slot4(LocalTime.of(14, 30), LocalTime.of(16, 0), DaySection.AFTERNOON),
    slot5(LocalTime.of(16, 0), LocalTime.of(17, 30), DaySection.AFTERNOON),
    slot6(LocalTime.of(18, 0), LocalTime.of(19, 30), DaySection.NIGHT),
    slot7(LocalTime.of(19, 30), LocalTime.of(21, 0), DaySection.NIGHT),
    slot8(LocalTime.of(21, 0), LocalTime.of(22, 30), DaySection.NIGHT);

    private enum DaySection {
        MORNING(),
        AFTERNOON(),
        NIGHT();
    }

    LocalTime start;
    LocalTime end;
    DaySection section;

    private TimeSlot(LocalTime start, LocalTime end, DaySection section) {
        this.start = start;
        this.end = end;
        this.section = section;
    }
}
