package org.project;

import java.time.LocalTime;

public enum TimeSlot {
    slot0(1, LocalTime.of(8, 0), LocalTime.of(9, 30), DaySection.MORNING),
    slot1(1, LocalTime.of(9, 30), LocalTime.of(11, 0), DaySection.MORNING),
    slot2(1, LocalTime.of(11, 0), LocalTime.of(12, 30), DaySection.MORNING),
    slot3(1, LocalTime.of(13, 0), LocalTime.of(14, 30), DaySection.AFTERNOON),
    slot4(1, LocalTime.of(14, 30), LocalTime.of(16, 0), DaySection.AFTERNOON),
    slot5(1, LocalTime.of(16, 0), LocalTime.of(17, 30), DaySection.AFTERNOON),
    slot6(1, LocalTime.of(18, 0), LocalTime.of(19, 30), DaySection.NIGHT),
    slot7(1, LocalTime.of(8, 0), LocalTime.of(9, 30), DaySection.NIGHT),
    slot8(1, LocalTime.of(8, 0), LocalTime.of(9, 30), DaySection.NIGHT);


    private final LocalTime[][] slots = {
        {
            LocalTime.of(8, 0), LocalTime.of(9, 30)
        },{
            LocalTime.of(9, 30), LocalTime.of(11, 0)
        },{
            LocalTime.of(11, 0), LocalTime.of(12, 30)
        },{
            LocalTime.of(13, 0), LocalTime.of(14, 30)
        },{
            LocalTime.of(14, 30), LocalTime.of(16, 0)
        },{
            LocalTime.of(16, 0), LocalTime.of(17, 30)
        },{
            LocalTime.of(18, 0), LocalTime.of(19, 30)
        },{
            LocalTime.of(19, 30), LocalTime.of(21, 0)
        },{
            LocalTime.of(21, 0), LocalTime.of(22, 30)
        }
    };

    private enum DaySection {
        MORNING(),
        AFTERNOON(),
        NIGHT();
    }

    private TimeSlot(int value, LocalTime start, LocalTime end, DaySection section) {

    }
}
