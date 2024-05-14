package org.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilterTest {

    @Test
    public void testGetAttributeIndex() {
        Attribute attribute = new Attribute() {
            @Override
            public int getValue() {
                return 10; // Sample attribute value
            }
        };
        Filter filter = new Filter(attribute, "testFilter", "AND");
        assertEquals(10, filter.getAttributeIndex());
    }

    @Test
    public void testGetFilterString() {
        Attribute attribute = new Attribute() {
            @Override
            public int getValue() {
                return 10; // Sample attribute value
            }
        };
        Filter filter = new Filter(attribute, "testFilter", "AND");
        assertEquals("testFilter", filter.getFilterString());
    }

    @Test
    public void testOp() {
        Attribute attribute = new Attribute() {
            @Override
            public int getValue() {
                return 10; // Sample attribute value
            }
        };
        Filter filter = new Filter(attribute, "testFilter", "AND");
        assertTrue(filter.op(true, true));
        assertFalse(filter.op(true, false));
        assertFalse(filter.op(false, true));
        assertFalse(filter.op(false, false));
    }
}