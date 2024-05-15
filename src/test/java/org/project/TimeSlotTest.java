package org.project;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeSlotTest {

    @Test
    void testTimeSlotValues() {
        assertEquals(LocalTime.of(8, 0), TimeSlot.SLOT_0.getStart());
        assertEquals(LocalTime.of(9, 30), TimeSlot.SLOT_0.getEnd());

        assertEquals(LocalTime.of(9, 30), TimeSlot.SLOT_1.getStart());
        assertEquals(LocalTime.of(11, 0), TimeSlot.SLOT_1.getEnd());

        assertEquals(LocalTime.of(11, 0), TimeSlot.SLOT_2.getStart());
        assertEquals(LocalTime.of(12, 30), TimeSlot.SLOT_2.getEnd());

        assertEquals(LocalTime.of(13, 0), TimeSlot.SLOT_3.getStart());
        assertEquals(LocalTime.of(14, 30), TimeSlot.SLOT_3.getEnd());

        assertEquals(LocalTime.of(14, 30), TimeSlot.SLOT_4.getStart());
        assertEquals(LocalTime.of(16, 0), TimeSlot.SLOT_4.getEnd());

        assertEquals(LocalTime.of(16, 0), TimeSlot.SLOT_5.getStart());
        assertEquals(LocalTime.of(17, 30), TimeSlot.SLOT_5.getEnd());

        assertEquals(LocalTime.of(18, 0), TimeSlot.SLOT_6.getStart());
        assertEquals(LocalTime.of(19, 30), TimeSlot.SLOT_6.getEnd());

        assertEquals(LocalTime.of(19, 30), TimeSlot.SLOT_7.getStart());
        assertEquals(LocalTime.of(21, 0), TimeSlot.SLOT_7.getEnd());

        assertEquals(LocalTime.of(21, 0), TimeSlot.SLOT_8.getStart());
        assertEquals(LocalTime.of(22, 30), TimeSlot.SLOT_8.getEnd());
    }
}