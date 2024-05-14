package org.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomAttributeTest {
    @Test
    public void testRoomAttribute() {
        // Test each attribute individually
        assertEquals(0, RoomAttribute.building.getValue());
        assertEquals("Building", RoomAttribute.building.getLabel());

        assertEquals(1, RoomAttribute.designation.getValue());
        assertEquals("Designation", RoomAttribute.designation.getLabel());

        // Add more tests for other attributes
    }
}