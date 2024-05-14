package org.project;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeSlotTest {

    @Test
    void testTimeSlotValues() {
        assertEquals(LocalTime.of(8, 0), TimeSlot.slot0.start);
        assertEquals(LocalTime.of(9, 30), TimeSlot.slot0.end);

        assertEquals(LocalTime.of(9, 30), TimeSlot.slot1.start);
        assertEquals(LocalTime.of(11, 0), TimeSlot.slot1.end);

        assertEquals(LocalTime.of(11, 0), TimeSlot.slot2.start);
        assertEquals(LocalTime.of(12, 30), TimeSlot.slot2.end);

        assertEquals(LocalTime.of(13, 0), TimeSlot.slot3.start);
        assertEquals(LocalTime.of(14, 30), TimeSlot.slot3.end);

        assertEquals(LocalTime.of(14, 30), TimeSlot.slot4.start);
        assertEquals(LocalTime.of(16, 0), TimeSlot.slot4.end);

        assertEquals(LocalTime.of(16, 0), TimeSlot.slot5.start);
        assertEquals(LocalTime.of(17, 30), TimeSlot.slot5.end);

        assertEquals(LocalTime.of(18, 0), TimeSlot.slot6.start);
        assertEquals(LocalTime.of(19, 30), TimeSlot.slot6.end);

        assertEquals(LocalTime.of(19, 30), TimeSlot.slot7.start);
        assertEquals(LocalTime.of(21, 0), TimeSlot.slot7.end);

        assertEquals(LocalTime.of(21, 0), TimeSlot.slot8.start);
        assertEquals(LocalTime.of(22, 30), TimeSlot.slot8.end);
    }
}