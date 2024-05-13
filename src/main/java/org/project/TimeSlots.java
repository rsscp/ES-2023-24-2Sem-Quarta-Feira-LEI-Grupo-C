package org.project;

import java.time.LocalTime;

public class TimeSlots {
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
}
